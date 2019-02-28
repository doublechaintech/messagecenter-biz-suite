
package com.doublechaintech.messagecenter.profile;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.math.BigDecimal;
import com.doublechaintech.messagecenter.MessagecenterNamingServiceDAO;
import com.doublechaintech.messagecenter.BaseEntity;
import com.doublechaintech.messagecenter.SmartList;
import com.doublechaintech.messagecenter.AccessKey;
import com.doublechaintech.messagecenter.DateKey;
import com.doublechaintech.messagecenter.StatsInfo;
import com.doublechaintech.messagecenter.StatsItem;

import com.doublechaintech.messagecenter.MultipleAccessKey;
import com.doublechaintech.messagecenter.MessagecenterUserContext;


import com.doublechaintech.messagecenter.platform.Platform;
import com.doublechaintech.messagecenter.privatemessage.PrivateMessage;

import com.doublechaintech.messagecenter.platform.PlatformDAO;
import com.doublechaintech.messagecenter.privatemessage.PrivateMessageDAO;



import org.springframework.dao.EmptyResultDataAccessException;

public class ProfileJDBCTemplateDAO extends MessagecenterNamingServiceDAO implements ProfileDAO{
 
 	
 	private  PlatformDAO  platformDAO;
 	public void setPlatformDAO(PlatformDAO platformDAO){
	 	this.platformDAO = platformDAO;
 	}
 	public PlatformDAO getPlatformDAO(){
	 	return this.platformDAO;
 	}


			
		
	
  	private  PrivateMessageDAO  privateMessageDAO;
 	public void setPrivateMessageDAO(PrivateMessageDAO pPrivateMessageDAO){
 	
 		if(pPrivateMessageDAO == null){
 			throw new IllegalStateException("Do not try to set privateMessageDAO to null.");
 		}
	 	this.privateMessageDAO = pPrivateMessageDAO;
 	}
 	public PrivateMessageDAO getPrivateMessageDAO(){
 		if(this.privateMessageDAO == null){
 			throw new IllegalStateException("The privateMessageDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.privateMessageDAO;
 	}	
 	
			
		

	
	/*
	protected Profile load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalProfile(accessKey, options);
	}
	*/
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public Profile load(String id,Map<String,Object> options) throws Exception{
		return loadInternalProfile(ProfileTable.withId(id), options);
	}
	
	
	
	public Profile save(Profile profile,Map<String,Object> options){
		
		String methodName="save(Profile profile,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(profile, methodName, "profile");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalProfile(profile,options);
	}
	public Profile clone(String profileId, Map<String,Object> options) throws Exception{
	
		return clone(ProfileTable.withId(profileId),options);
	}
	
	protected Profile clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String profileId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		Profile newProfile = loadInternalProfile(accessKey, options);
		newProfile.setVersion(0);
		
		
 		
 		if(isSavePrivateMessageListAsSenderEnabled(options)){
 			for(PrivateMessage item: newProfile.getPrivateMessageListAsSender()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSavePrivateMessageListAsReceiverEnabled(options)){
 			for(PrivateMessage item: newProfile.getPrivateMessageListAsReceiver()){
 				item.setVersion(0);
 			}
 		}
		

		
		saveInternalProfile(newProfile,options);
		
		return newProfile;
	}
	
	
	
	

	protected void throwIfHasException(String profileId,int version,int count) throws Exception{
		if (count == 1) {
			throw new ProfileVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new ProfileNotFoundException(
					"The " + this.getTableName() + "(" + profileId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String profileId, int version) throws Exception{
	
		String methodName="delete(String profileId, int version)";
		assertMethodArgumentNotNull(profileId, methodName, "profileId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{profileId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(profileId,version);
		}
		
	
	}
	
	
	
	
	

	public Profile disconnectFromAll(String profileId, int version) throws Exception{
	
		
		Profile profile = loadInternalProfile(ProfileTable.withId(profileId), emptyOptions());
		profile.clearFromAll();
		this.saveProfile(profile);
		return profile;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return ProfileTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "profile";
	}
	@Override
	protected String getBeanName() {
		
		return "profile";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return ProfileTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractPlatformEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, ProfileTokens.PLATFORM);
 	}

 	protected boolean isSavePlatformEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, ProfileTokens.PLATFORM);
 	}
 	

 	
 
		
	
	protected boolean isExtractPrivateMessageListAsSenderEnabled(Map<String,Object> options){		
 		return checkOptions(options,ProfileTokens.PRIVATE_MESSAGE_LIST_AS_SENDER);
 	}
 	protected boolean isAnalyzePrivateMessageListAsSenderEnabled(Map<String,Object> options){		 		
 		return ProfileTokens.of(options).analyzePrivateMessageListAsSenderEnabled();
 	}
	
	protected boolean isSavePrivateMessageListAsSenderEnabled(Map<String,Object> options){
		return checkOptions(options, ProfileTokens.PRIVATE_MESSAGE_LIST_AS_SENDER);
		
 	}
 	
		
	
	protected boolean isExtractPrivateMessageListAsReceiverEnabled(Map<String,Object> options){		
 		return checkOptions(options,ProfileTokens.PRIVATE_MESSAGE_LIST_AS_RECEIVER);
 	}
 	protected boolean isAnalyzePrivateMessageListAsReceiverEnabled(Map<String,Object> options){		 		
 		return ProfileTokens.of(options).analyzePrivateMessageListAsReceiverEnabled();
 	}
	
	protected boolean isSavePrivateMessageListAsReceiverEnabled(Map<String,Object> options){
		return checkOptions(options, ProfileTokens.PRIVATE_MESSAGE_LIST_AS_RECEIVER);
		
 	}
 	
		

	

	protected ProfileMapper getProfileMapper(){
		return new ProfileMapper();
	}

	
	
	protected Profile extractProfile(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			Profile profile = loadSingleObject(accessKey, getProfileMapper());
			return profile;
		}catch(EmptyResultDataAccessException e){
			throw new ProfileNotFoundException("Profile("+accessKey+") is not found!");
		}

	}

	
	

	protected Profile loadInternalProfile(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		Profile profile = extractProfile(accessKey, loadOptions);
 	
 		if(isExtractPlatformEnabled(loadOptions)){
	 		extractPlatform(profile, loadOptions);
 		}
 
		
		if(isExtractPrivateMessageListAsSenderEnabled(loadOptions)){
	 		extractPrivateMessageListAsSender(profile, loadOptions);
 		}	
 		if(isAnalyzePrivateMessageListAsSenderEnabled(loadOptions)){
	 		analyzePrivateMessageListAsSender(profile, loadOptions);
 		}
 		
		
		if(isExtractPrivateMessageListAsReceiverEnabled(loadOptions)){
	 		extractPrivateMessageListAsReceiver(profile, loadOptions);
 		}	
 		if(isAnalyzePrivateMessageListAsReceiverEnabled(loadOptions)){
	 		analyzePrivateMessageListAsReceiver(profile, loadOptions);
 		}
 		
		
		return profile;
		
	}

	 

 	protected Profile extractPlatform(Profile profile, Map<String,Object> options) throws Exception{

		if(profile.getPlatform() == null){
			return profile;
		}
		String platformId = profile.getPlatform().getId();
		if( platformId == null){
			return profile;
		}
		Platform platform = getPlatformDAO().load(platformId,options);
		if(platform != null){
			profile.setPlatform(platform);
		}
		
 		
 		return profile;
 	}
 		
 
		
	protected void enhancePrivateMessageListAsSender(SmartList<PrivateMessage> privateMessageListAsSender,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Profile extractPrivateMessageListAsSender(Profile profile, Map<String,Object> options){
		
		
		if(profile == null){
			return null;
		}
		if(profile.getId() == null){
			return profile;
		}
		convertListOptions(options,"privateMessageListAsSender","privateMessageList");

		
		
		SmartList<PrivateMessage> privateMessageListAsSender = getPrivateMessageDAO().findPrivateMessageBySender(profile.getId(),options);
		if(privateMessageListAsSender != null){
			enhancePrivateMessageListAsSender(privateMessageListAsSender,options);
			profile.setPrivateMessageListAsSender(privateMessageListAsSender);
		}
		
		return profile;
	
	}	
	
	protected Profile analyzePrivateMessageListAsSender(Profile profile, Map<String,Object> options){
		
		
		if(profile == null){
			return null;
		}
		if(profile.getId() == null){
			return profile;
		}
		convertListOptions(options,"privateMessageListAsSender","privateMessageList");

		
		
		SmartList<PrivateMessage> privateMessageListAsSender = profile.getPrivateMessageListAsSender();
		if(privateMessageListAsSender != null){
			getPrivateMessageDAO().analyzePrivateMessageBySender(privateMessageListAsSender, profile.getId(), options);
			
		}
		
		return profile;
	
	}	
	
		
	protected void enhancePrivateMessageListAsReceiver(SmartList<PrivateMessage> privateMessageListAsReceiver,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Profile extractPrivateMessageListAsReceiver(Profile profile, Map<String,Object> options){
		
		
		if(profile == null){
			return null;
		}
		if(profile.getId() == null){
			return profile;
		}
		convertListOptions(options,"privateMessageListAsReceiver","privateMessageList");

		
		
		SmartList<PrivateMessage> privateMessageListAsReceiver = getPrivateMessageDAO().findPrivateMessageByReceiver(profile.getId(),options);
		if(privateMessageListAsReceiver != null){
			enhancePrivateMessageListAsReceiver(privateMessageListAsReceiver,options);
			profile.setPrivateMessageListAsReceiver(privateMessageListAsReceiver);
		}
		
		return profile;
	
	}	
	
	protected Profile analyzePrivateMessageListAsReceiver(Profile profile, Map<String,Object> options){
		
		
		if(profile == null){
			return null;
		}
		if(profile.getId() == null){
			return profile;
		}
		convertListOptions(options,"privateMessageListAsReceiver","privateMessageList");

		
		
		SmartList<PrivateMessage> privateMessageListAsReceiver = profile.getPrivateMessageListAsReceiver();
		if(privateMessageListAsReceiver != null){
			getPrivateMessageDAO().analyzePrivateMessageBySender(privateMessageListAsReceiver, profile.getId(), options);
			
		}
		
		return profile;
	
	}	
	
		
		
  	
 	public SmartList<Profile> findProfileByPlatform(String platformId,Map<String,Object> options){
 	
  		SmartList<Profile> resultList = queryWith(ProfileTable.COLUMN_PLATFORM, platformId, options, getProfileMapper());
		// analyzeProfileByPlatform(resultList, platformId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<Profile> findProfileByPlatform(String platformId, int start, int count,Map<String,Object> options){
 		
 		SmartList<Profile> resultList =  queryWithRange(ProfileTable.COLUMN_PLATFORM, platformId, options, getProfileMapper(), start, count);
 		//analyzeProfileByPlatform(resultList, platformId, options);
 		return resultList;
 		
 	}
 	public void analyzeProfileByPlatform(SmartList<Profile> resultList, String platformId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}

 	
 		
 	}
 	@Override
 	public int countProfileByPlatform(String platformId,Map<String,Object> options){

 		return countWith(ProfileTable.COLUMN_PLATFORM, platformId, options);
 	}
 	@Override
	public Map<String, Integer> countProfileByPlatformIds(String[] ids, Map<String, Object> options) {
		return countWithIds(ProfileTable.COLUMN_PLATFORM, ids, options);
	}
 	
 	
		
		
		

	

	protected Profile saveProfile(Profile  profile){
		
		if(!profile.isChanged()){
			return profile;
		}
		
		
		String SQL=this.getSaveProfileSQL(profile);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSaveProfileParameters(profile);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		profile.incVersion();
		return profile;
	
	}
	public SmartList<Profile> saveProfileList(SmartList<Profile> profileList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitProfileList(profileList);
		
		batchProfileCreate((List<Profile>)lists[CREATE_LIST_INDEX]);
		
		batchProfileUpdate((List<Profile>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(Profile profile:profileList){
			if(profile.isChanged()){
				profile.incVersion();
			}
			
		
		}
		
		
		return profileList;
	}

	public SmartList<Profile> removeProfileList(SmartList<Profile> profileList,Map<String,Object> options){
		
		
		super.removeList(profileList, options);
		
		return profileList;
		
		
	}
	
	protected List<Object[]> prepareProfileBatchCreateArgs(List<Profile> profileList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(Profile profile:profileList ){
			Object [] parameters = prepareProfileCreateParameters(profile);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> prepareProfileBatchUpdateArgs(List<Profile> profileList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(Profile profile:profileList ){
			if(!profile.isChanged()){
				continue;
			}
			Object [] parameters = prepareProfileUpdateParameters(profile);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchProfileCreate(List<Profile> profileList){
		String SQL=getCreateSQL();
		List<Object[]> args=prepareProfileBatchCreateArgs(profileList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchProfileUpdate(List<Profile> profileList){
		String SQL=getUpdateSQL();
		List<Object[]> args=prepareProfileBatchUpdateArgs(profileList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitProfileList(List<Profile> profileList){
		
		List<Profile> profileCreateList=new ArrayList<Profile>();
		List<Profile> profileUpdateList=new ArrayList<Profile>();
		
		for(Profile profile: profileList){
			if(isUpdateRequest(profile)){
				profileUpdateList.add( profile);
				continue;
			}
			profileCreateList.add(profile);
		}
		
		return new Object[]{profileCreateList,profileUpdateList};
	}
	
	protected boolean isUpdateRequest(Profile profile){
 		return profile.getVersion() > 0;
 	}
 	protected String getSaveProfileSQL(Profile profile){
 		if(isUpdateRequest(profile)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSaveProfileParameters(Profile profile){
 		if(isUpdateRequest(profile) ){
 			return prepareProfileUpdateParameters(profile);
 		}
 		return prepareProfileCreateParameters(profile);
 	}
 	protected Object[] prepareProfileUpdateParameters(Profile profile){
 		Object[] parameters = new Object[5];
 
 		parameters[0] = profile.getName(); 	
 		if(profile.getPlatform() != null){
 			parameters[1] = profile.getPlatform().getId();
 		}
 		
 		parameters[2] = profile.nextVersion();
 		parameters[3] = profile.getId();
 		parameters[4] = profile.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] prepareProfileCreateParameters(Profile profile){
		Object[] parameters = new Object[3];
		String newProfileId=getNextId();
		profile.setId(newProfileId);
		parameters[0] =  profile.getId();
 
 		parameters[1] = profile.getName(); 	
 		if(profile.getPlatform() != null){
 			parameters[2] = profile.getPlatform().getId();
 		
 		}
 				
 				
 		return parameters;
 	}
 	
	protected Profile saveInternalProfile(Profile profile, Map<String,Object> options){
		
		saveProfile(profile);
 	
 		if(isSavePlatformEnabled(options)){
	 		savePlatform(profile, options);
 		}
 
		
		if(isSavePrivateMessageListAsSenderEnabled(options)){
	 		savePrivateMessageListAsSender(profile, options);
	 		//removePrivateMessageListAsSender(profile, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSavePrivateMessageListAsReceiverEnabled(options)){
	 		savePrivateMessageListAsReceiver(profile, options);
	 		//removePrivateMessageListAsReceiver(profile, options);
	 		//Not delete the record
	 		
 		}		
		
		return profile;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected Profile savePlatform(Profile profile, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(profile.getPlatform() == null){
 			return profile;//do nothing when it is null
 		}
 		
 		getPlatformDAO().save(profile.getPlatform(),options);
 		return profile;
 		
 	}
 	
 	
 	
 	 
	
 

	
	public Profile planToRemovePrivateMessageListAsSender(Profile profile, String privateMessageIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(PrivateMessage.SENDER_PROPERTY, profile.getId());
		key.put(PrivateMessage.ID_PROPERTY, privateMessageIds);
		
		SmartList<PrivateMessage> externalPrivateMessageList = getPrivateMessageDAO().
				findPrivateMessageWithKey(key, options);
		if(externalPrivateMessageList == null){
			return profile;
		}
		if(externalPrivateMessageList.isEmpty()){
			return profile;
		}
		
		for(PrivateMessage privateMessage: externalPrivateMessageList){

			privateMessage.clearFromAll();
		}
		
		
		SmartList<PrivateMessage> privateMessageListAsSender = profile.getPrivateMessageListAsSender();		
		privateMessageListAsSender.addAllToRemoveList(externalPrivateMessageList);
		return profile;	
	
	}


	//disconnect Profile with platform in PrivateMessage
	public Profile planToRemovePrivateMessageListAsSenderWithPlatform(Profile profile, String platformId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(PrivateMessage.SENDER_PROPERTY, profile.getId());
		key.put(PrivateMessage.PLATFORM_PROPERTY, platformId);
		
		SmartList<PrivateMessage> externalPrivateMessageList = getPrivateMessageDAO().
				findPrivateMessageWithKey(key, options);
		if(externalPrivateMessageList == null){
			return profile;
		}
		if(externalPrivateMessageList.isEmpty()){
			return profile;
		}
		
		for(PrivateMessage privateMessage: externalPrivateMessageList){
			privateMessage.clearPlatform();
			privateMessage.clearSender();
			
		}
		
		
		SmartList<PrivateMessage> privateMessageList = profile.getPrivateMessageListAsSender();		
		privateMessageList.addAllToRemoveList(externalPrivateMessageList);
		return profile;
	}
	
	public int countPrivateMessageListAsSenderWithPlatform(String profileId, String platformId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(PrivateMessage.SENDER_PROPERTY, profileId);
		key.put(PrivateMessage.PLATFORM_PROPERTY, platformId);
		
		int count = getPrivateMessageDAO().countPrivateMessageWithKey(key, options);
		return count;
	}
	
	public Profile planToRemovePrivateMessageListAsReceiver(Profile profile, String privateMessageIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(PrivateMessage.SENDER_PROPERTY, profile.getId());
		key.put(PrivateMessage.ID_PROPERTY, privateMessageIds);
		
		SmartList<PrivateMessage> externalPrivateMessageList = getPrivateMessageDAO().
				findPrivateMessageWithKey(key, options);
		if(externalPrivateMessageList == null){
			return profile;
		}
		if(externalPrivateMessageList.isEmpty()){
			return profile;
		}
		
		for(PrivateMessage privateMessage: externalPrivateMessageList){

			privateMessage.clearFromAll();
		}
		
		
		SmartList<PrivateMessage> privateMessageListAsReceiver = profile.getPrivateMessageListAsReceiver();		
		privateMessageListAsReceiver.addAllToRemoveList(externalPrivateMessageList);
		return profile;	
	
	}


	//disconnect Profile with platform in PrivateMessage
	public Profile planToRemovePrivateMessageListAsReceiverWithPlatform(Profile profile, String platformId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(PrivateMessage.SENDER_PROPERTY, profile.getId());
		key.put(PrivateMessage.PLATFORM_PROPERTY, platformId);
		
		SmartList<PrivateMessage> externalPrivateMessageList = getPrivateMessageDAO().
				findPrivateMessageWithKey(key, options);
		if(externalPrivateMessageList == null){
			return profile;
		}
		if(externalPrivateMessageList.isEmpty()){
			return profile;
		}
		
		for(PrivateMessage privateMessage: externalPrivateMessageList){
			privateMessage.clearPlatform();
			privateMessage.clearSender();
			
		}
		
		
		SmartList<PrivateMessage> privateMessageList = profile.getPrivateMessageListAsSender();		
		privateMessageList.addAllToRemoveList(externalPrivateMessageList);
		return profile;
	}
	
	public int countPrivateMessageListAsReceiverWithPlatform(String profileId, String platformId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(PrivateMessage.SENDER_PROPERTY, profileId);
		key.put(PrivateMessage.PLATFORM_PROPERTY, platformId);
		
		int count = getPrivateMessageDAO().countPrivateMessageWithKey(key, options);
		return count;
	}
	

		
	protected Profile savePrivateMessageListAsSender(Profile profile, Map<String,Object> options){
		
		
		
		
		SmartList<PrivateMessage> privateMessageListAsSender = profile.getPrivateMessageListAsSender();
		if(privateMessageListAsSender == null){
			//null list means nothing
			return profile;
		}
		SmartList<PrivateMessage> mergedUpdatePrivateMessageListAsSender = new SmartList<PrivateMessage>();
		
		
		mergedUpdatePrivateMessageListAsSender.addAll(privateMessageListAsSender); 
		if(privateMessageListAsSender.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdatePrivateMessageListAsSender.addAll(privateMessageListAsSender.getToRemoveList());
			privateMessageListAsSender.removeAll(privateMessageListAsSender.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getPrivateMessageDAO().savePrivateMessageList(mergedUpdatePrivateMessageListAsSender,options);
		
		if(privateMessageListAsSender.getToRemoveList() != null){
			privateMessageListAsSender.removeAll(privateMessageListAsSender.getToRemoveList());
		}
		
		
		return profile;
	
	}
	
	protected Profile removePrivateMessageListAsSender(Profile profile, Map<String,Object> options){
	
	
		SmartList<PrivateMessage> privateMessageListAsSender = profile.getPrivateMessageListAsSender();
		if(privateMessageListAsSender == null){
			return profile;
		}	
	
		SmartList<PrivateMessage> toRemovePrivateMessageListAsSender = privateMessageListAsSender.getToRemoveList();
		
		if(toRemovePrivateMessageListAsSender == null){
			return profile;
		}
		if(toRemovePrivateMessageListAsSender.isEmpty()){
			return profile;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getPrivateMessageDAO().removePrivateMessageList(toRemovePrivateMessageListAsSender,options);
		
		return profile;
	
	}
	
	

 	
 	
	
	
	
		
	protected Profile savePrivateMessageListAsReceiver(Profile profile, Map<String,Object> options){
		
		
		
		
		SmartList<PrivateMessage> privateMessageListAsReceiver = profile.getPrivateMessageListAsReceiver();
		if(privateMessageListAsReceiver == null){
			//null list means nothing
			return profile;
		}
		SmartList<PrivateMessage> mergedUpdatePrivateMessageListAsReceiver = new SmartList<PrivateMessage>();
		
		
		mergedUpdatePrivateMessageListAsReceiver.addAll(privateMessageListAsReceiver); 
		if(privateMessageListAsReceiver.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdatePrivateMessageListAsReceiver.addAll(privateMessageListAsReceiver.getToRemoveList());
			privateMessageListAsReceiver.removeAll(privateMessageListAsReceiver.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getPrivateMessageDAO().savePrivateMessageList(mergedUpdatePrivateMessageListAsReceiver,options);
		
		if(privateMessageListAsReceiver.getToRemoveList() != null){
			privateMessageListAsReceiver.removeAll(privateMessageListAsReceiver.getToRemoveList());
		}
		
		
		return profile;
	
	}
	
	protected Profile removePrivateMessageListAsReceiver(Profile profile, Map<String,Object> options){
	
	
		SmartList<PrivateMessage> privateMessageListAsReceiver = profile.getPrivateMessageListAsReceiver();
		if(privateMessageListAsReceiver == null){
			return profile;
		}	
	
		SmartList<PrivateMessage> toRemovePrivateMessageListAsReceiver = privateMessageListAsReceiver.getToRemoveList();
		
		if(toRemovePrivateMessageListAsReceiver == null){
			return profile;
		}
		if(toRemovePrivateMessageListAsReceiver.isEmpty()){
			return profile;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getPrivateMessageDAO().removePrivateMessageList(toRemovePrivateMessageListAsReceiver,options);
		
		return profile;
	
	}
	
	

 	
 	
	
	
	
		

	public Profile present(Profile profile,Map<String, Object> options){
	
		presentPrivateMessageListAsSender(profile,options);
		presentPrivateMessageListAsReceiver(profile,options);

		return profile;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected Profile presentPrivateMessageListAsSender(
			Profile profile,
			Map<String, Object> options) {

		SmartList<PrivateMessage> privateMessageListAsSender = profile.getPrivateMessageListAsSender();		
				SmartList<PrivateMessage> newList= presentSubList(profile.getId(),
				privateMessageListAsSender,
				options,
				getPrivateMessageDAO()::countPrivateMessageBySender,
				getPrivateMessageDAO()::findPrivateMessageBySender
				);

		
		profile.setPrivateMessageListAsSender(newList);
		

		return profile;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected Profile presentPrivateMessageListAsReceiver(
			Profile profile,
			Map<String, Object> options) {

		SmartList<PrivateMessage> privateMessageListAsReceiver = profile.getPrivateMessageListAsReceiver();		
				SmartList<PrivateMessage> newList= presentSubList(profile.getId(),
				privateMessageListAsReceiver,
				options,
				getPrivateMessageDAO()::countPrivateMessageByReceiver,
				getPrivateMessageDAO()::findPrivateMessageByReceiver
				);

		
		profile.setPrivateMessageListAsReceiver(newList);
		

		return profile;
	}			
		

	
    public SmartList<Profile> requestCandidateProfileForPrivateMessageAsSender(MessagecenterUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(ProfileTable.COLUMN_NAME, filterKey, pageNo, pageSize, getProfileMapper());
    }
		
    public SmartList<Profile> requestCandidateProfileForPrivateMessageAsReceiver(MessagecenterUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(ProfileTable.COLUMN_NAME, filterKey, pageNo, pageSize, getProfileMapper());
    }
		

	protected String getTableName(){
		return ProfileTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<Profile> profileList) {		
		this.enhanceListInternal(profileList, this.getProfileMapper());
	}
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<Profile> profileList = ownerEntity.collectRefsWithType(Profile.INTERNAL_TYPE);
		this.enhanceList(profileList);
		
	}
	
	@Override
	public SmartList<Profile> findProfileWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getProfileMapper());

	}
	@Override
	public int countProfileWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countProfileWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<Profile> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getProfileMapper());
	}
}


