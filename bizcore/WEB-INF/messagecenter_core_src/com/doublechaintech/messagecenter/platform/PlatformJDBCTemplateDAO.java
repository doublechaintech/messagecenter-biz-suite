
package com.doublechaintech.messagecenter.platform;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;
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


import com.doublechaintech.messagecenter.profile.Profile;
import com.doublechaintech.messagecenter.privatemessage.PrivateMessage;

import com.doublechaintech.messagecenter.privatemessage.PrivateMessageDAO;
import com.doublechaintech.messagecenter.profile.ProfileDAO;



import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowCallbackHandler;


public class PlatformJDBCTemplateDAO extends MessagecenterNamingServiceDAO implements PlatformDAO{


			
		
	
  	private  ProfileDAO  profileDAO;
 	public void setProfileDAO(ProfileDAO pProfileDAO){
 	
 		if(pProfileDAO == null){
 			throw new IllegalStateException("Do not try to set profileDAO to null.");
 		}
	 	this.profileDAO = pProfileDAO;
 	}
 	public ProfileDAO getProfileDAO(){
 		if(this.profileDAO == null){
 			throw new IllegalStateException("The profileDAO is not configured yet, please config it some where.");
 		}
 		
	 	return this.profileDAO;
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
	protected Platform load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalPlatform(accessKey, options);
	}
	*/
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public Platform load(String id,Map<String,Object> options) throws Exception{
		return loadInternalPlatform(PlatformTable.withId(id), options);
	}
	
	
	
	public Platform save(Platform platform,Map<String,Object> options){
		
		String methodName="save(Platform platform,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(platform, methodName, "platform");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalPlatform(platform,options);
	}
	public Platform clone(String platformId, Map<String,Object> options) throws Exception{
	
		return clone(PlatformTable.withId(platformId),options);
	}
	
	protected Platform clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String platformId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		Platform newPlatform = loadInternalPlatform(accessKey, options);
		newPlatform.setVersion(0);
		
		
 		
 		if(isSaveProfileListEnabled(options)){
 			for(Profile item: newPlatform.getProfileList()){
 				item.setVersion(0);
 			}
 		}
		
 		
 		if(isSavePrivateMessageListEnabled(options)){
 			for(PrivateMessage item: newPlatform.getPrivateMessageList()){
 				item.setVersion(0);
 			}
 		}
		

		
		saveInternalPlatform(newPlatform,options);
		
		return newPlatform;
	}
	
	
	
	

	protected void throwIfHasException(String platformId,int version,int count) throws Exception{
		if (count == 1) {
			throw new PlatformVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new PlatformNotFoundException(
					"The " + this.getTableName() + "(" + platformId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String platformId, int version) throws Exception{
	
		String methodName="delete(String platformId, int version)";
		assertMethodArgumentNotNull(platformId, methodName, "platformId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{platformId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(platformId,version);
		}
		
	
	}
	
	
	
	
	

	public Platform disconnectFromAll(String platformId, int version) throws Exception{
	
		
		Platform platform = loadInternalPlatform(PlatformTable.withId(platformId), emptyOptions());
		platform.clearFromAll();
		this.savePlatform(platform);
		return platform;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return PlatformTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "platform";
	}
	@Override
	protected String getBeanName() {
		
		return "platform";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return PlatformTokens.checkOptions(options, optionToCheck);
	
	}


		
	
	protected boolean isExtractProfileListEnabled(Map<String,Object> options){		
 		return checkOptions(options,PlatformTokens.PROFILE_LIST);
 	}
 	protected boolean isAnalyzeProfileListEnabled(Map<String,Object> options){		 		
 		return PlatformTokens.of(options).analyzeProfileListEnabled();
 	}
	
	protected boolean isSaveProfileListEnabled(Map<String,Object> options){
		return checkOptions(options, PlatformTokens.PROFILE_LIST);
		
 	}
 	
		
	
	protected boolean isExtractPrivateMessageListEnabled(Map<String,Object> options){		
 		return checkOptions(options,PlatformTokens.PRIVATE_MESSAGE_LIST);
 	}
 	protected boolean isAnalyzePrivateMessageListEnabled(Map<String,Object> options){		 		
 		return PlatformTokens.of(options).analyzePrivateMessageListEnabled();
 	}
	
	protected boolean isSavePrivateMessageListEnabled(Map<String,Object> options){
		return checkOptions(options, PlatformTokens.PRIVATE_MESSAGE_LIST);
		
 	}
 	
		

	

	protected PlatformMapper getPlatformMapper(){
		return new PlatformMapper();
	}

	
	
	protected Platform extractPlatform(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			Platform platform = loadSingleObject(accessKey, getPlatformMapper());
			return platform;
		}catch(EmptyResultDataAccessException e){
			throw new PlatformNotFoundException("Platform("+accessKey+") is not found!");
		}

	}

	
	

	protected Platform loadInternalPlatform(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		Platform platform = extractPlatform(accessKey, loadOptions);

		
		if(isExtractProfileListEnabled(loadOptions)){
	 		extractProfileList(platform, loadOptions);
 		}	
 		if(isAnalyzeProfileListEnabled(loadOptions)){
	 		analyzeProfileList(platform, loadOptions);
 		}
 		
		
		if(isExtractPrivateMessageListEnabled(loadOptions)){
	 		extractPrivateMessageList(platform, loadOptions);
 		}	
 		if(isAnalyzePrivateMessageListEnabled(loadOptions)){
	 		analyzePrivateMessageList(platform, loadOptions);
 		}
 		
		
		return platform;
		
	}

	
		
	protected void enhanceProfileList(SmartList<Profile> profileList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Platform extractProfileList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<Profile> profileList = getProfileDAO().findProfileByPlatform(platform.getId(),options);
		if(profileList != null){
			enhanceProfileList(profileList,options);
			platform.setProfileList(profileList);
		}
		
		return platform;
	
	}	
	
	protected Platform analyzeProfileList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<Profile> profileList = platform.getProfileList();
		if(profileList != null){
			getProfileDAO().analyzeProfileByPlatform(profileList, platform.getId(), options);
			
		}
		
		return platform;
	
	}	
	
		
	protected void enhancePrivateMessageList(SmartList<PrivateMessage> privateMessageList,Map<String,Object> options){
		//extract multiple list from difference sources
		//Trying to use a single SQL to extract all data from database and do the work in java side, java is easier to scale to N ndoes;
	}
	
	protected Platform extractPrivateMessageList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<PrivateMessage> privateMessageList = getPrivateMessageDAO().findPrivateMessageByPlatform(platform.getId(),options);
		if(privateMessageList != null){
			enhancePrivateMessageList(privateMessageList,options);
			platform.setPrivateMessageList(privateMessageList);
		}
		
		return platform;
	
	}	
	
	protected Platform analyzePrivateMessageList(Platform platform, Map<String,Object> options){
		
		
		if(platform == null){
			return null;
		}
		if(platform.getId() == null){
			return platform;
		}

		
		
		SmartList<PrivateMessage> privateMessageList = platform.getPrivateMessageList();
		if(privateMessageList != null){
			getPrivateMessageDAO().analyzePrivateMessageByPlatform(privateMessageList, platform.getId(), options);
			
		}
		
		return platform;
	
	}	
	
		
		
 	
		
		
		

	

	protected Platform savePlatform(Platform  platform){
		
		if(!platform.isChanged()){
			return platform;
		}
		
		
		String SQL=this.getSavePlatformSQL(platform);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSavePlatformParameters(platform);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		platform.incVersion();
		return platform;
	
	}
	public SmartList<Platform> savePlatformList(SmartList<Platform> platformList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitPlatformList(platformList);
		
		batchPlatformCreate((List<Platform>)lists[CREATE_LIST_INDEX]);
		
		batchPlatformUpdate((List<Platform>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(Platform platform:platformList){
			if(platform.isChanged()){
				platform.incVersion();
			}
			
		
		}
		
		
		return platformList;
	}

	public SmartList<Platform> removePlatformList(SmartList<Platform> platformList,Map<String,Object> options){
		
		
		super.removeList(platformList, options);
		
		return platformList;
		
		
	}
	
	protected List<Object[]> preparePlatformBatchCreateArgs(List<Platform> platformList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(Platform platform:platformList ){
			Object [] parameters = preparePlatformCreateParameters(platform);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> preparePlatformBatchUpdateArgs(List<Platform> platformList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(Platform platform:platformList ){
			if(!platform.isChanged()){
				continue;
			}
			Object [] parameters = preparePlatformUpdateParameters(platform);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchPlatformCreate(List<Platform> platformList){
		String SQL=getCreateSQL();
		List<Object[]> args=preparePlatformBatchCreateArgs(platformList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchPlatformUpdate(List<Platform> platformList){
		String SQL=getUpdateSQL();
		List<Object[]> args=preparePlatformBatchUpdateArgs(platformList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitPlatformList(List<Platform> platformList){
		
		List<Platform> platformCreateList=new ArrayList<Platform>();
		List<Platform> platformUpdateList=new ArrayList<Platform>();
		
		for(Platform platform: platformList){
			if(isUpdateRequest(platform)){
				platformUpdateList.add( platform);
				continue;
			}
			platformCreateList.add(platform);
		}
		
		return new Object[]{platformCreateList,platformUpdateList};
	}
	
	protected boolean isUpdateRequest(Platform platform){
 		return platform.getVersion() > 0;
 	}
 	protected String getSavePlatformSQL(Platform platform){
 		if(isUpdateRequest(platform)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSavePlatformParameters(Platform platform){
 		if(isUpdateRequest(platform) ){
 			return preparePlatformUpdateParameters(platform);
 		}
 		return preparePlatformCreateParameters(platform);
 	}
 	protected Object[] preparePlatformUpdateParameters(Platform platform){
 		Object[] parameters = new Object[6];
 
 		parameters[0] = platform.getName();
 		parameters[1] = platform.getIntroduction();
 		parameters[2] = platform.getCurrentVersion();		
 		parameters[3] = platform.nextVersion();
 		parameters[4] = platform.getId();
 		parameters[5] = platform.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] preparePlatformCreateParameters(Platform platform){
		Object[] parameters = new Object[4];
		String newPlatformId=getNextId();
		platform.setId(newPlatformId);
		parameters[0] =  platform.getId();
 
 		parameters[1] = platform.getName();
 		parameters[2] = platform.getIntroduction();
 		parameters[3] = platform.getCurrentVersion();		
 				
 		return parameters;
 	}
 	
	protected Platform saveInternalPlatform(Platform platform, Map<String,Object> options){
		
		savePlatform(platform);

		
		if(isSaveProfileListEnabled(options)){
	 		saveProfileList(platform, options);
	 		//removeProfileList(platform, options);
	 		//Not delete the record
	 		
 		}		
		
		if(isSavePrivateMessageListEnabled(options)){
	 		savePrivateMessageList(platform, options);
	 		//removePrivateMessageList(platform, options);
	 		//Not delete the record
	 		
 		}		
		
		return platform;
		
	}
	
	
	
	//======================================================================================
	

	
	public Platform planToRemoveProfileList(Platform platform, String profileIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Profile.PLATFORM_PROPERTY, platform.getId());
		key.put(Profile.ID_PROPERTY, profileIds);
		
		SmartList<Profile> externalProfileList = getProfileDAO().
				findProfileWithKey(key, options);
		if(externalProfileList == null){
			return platform;
		}
		if(externalProfileList.isEmpty()){
			return platform;
		}
		
		for(Profile profileItem: externalProfileList){

			profileItem.clearFromAll();
		}
		
		
		SmartList<Profile> profileList = platform.getProfileList();		
		profileList.addAllToRemoveList(externalProfileList);
		return platform;	
	
	}


	public Platform planToRemovePrivateMessageList(Platform platform, String privateMessageIds[], Map<String,Object> options)throws Exception{
	
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(PrivateMessage.PLATFORM_PROPERTY, platform.getId());
		key.put(PrivateMessage.ID_PROPERTY, privateMessageIds);
		
		SmartList<PrivateMessage> externalPrivateMessageList = getPrivateMessageDAO().
				findPrivateMessageWithKey(key, options);
		if(externalPrivateMessageList == null){
			return platform;
		}
		if(externalPrivateMessageList.isEmpty()){
			return platform;
		}
		
		for(PrivateMessage privateMessageItem: externalPrivateMessageList){

			privateMessageItem.clearFromAll();
		}
		
		
		SmartList<PrivateMessage> privateMessageList = platform.getPrivateMessageList();		
		privateMessageList.addAllToRemoveList(externalPrivateMessageList);
		return platform;	
	
	}


	//disconnect Platform with sender in PrivateMessage
	public Platform planToRemovePrivateMessageListWithSender(Platform platform, String senderId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(PrivateMessage.PLATFORM_PROPERTY, platform.getId());
		key.put(PrivateMessage.SENDER_PROPERTY, senderId);
		
		SmartList<PrivateMessage> externalPrivateMessageList = getPrivateMessageDAO().
				findPrivateMessageWithKey(key, options);
		if(externalPrivateMessageList == null){
			return platform;
		}
		if(externalPrivateMessageList.isEmpty()){
			return platform;
		}
		
		for(PrivateMessage privateMessageItem: externalPrivateMessageList){
			privateMessageItem.clearSender();
			privateMessageItem.clearPlatform();
			
		}
		
		
		SmartList<PrivateMessage> privateMessageList = platform.getPrivateMessageList();		
		privateMessageList.addAllToRemoveList(externalPrivateMessageList);
		return platform;
	}
	
	public int countPrivateMessageListWithSender(String platformId, String senderId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(PrivateMessage.PLATFORM_PROPERTY, platformId);
		key.put(PrivateMessage.SENDER_PROPERTY, senderId);
		
		int count = getPrivateMessageDAO().countPrivateMessageWithKey(key, options);
		return count;
	}
	
	//disconnect Platform with receiver in PrivateMessage
	public Platform planToRemovePrivateMessageListWithReceiver(Platform platform, String receiverId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);
		
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(PrivateMessage.PLATFORM_PROPERTY, platform.getId());
		key.put(PrivateMessage.RECEIVER_PROPERTY, receiverId);
		
		SmartList<PrivateMessage> externalPrivateMessageList = getPrivateMessageDAO().
				findPrivateMessageWithKey(key, options);
		if(externalPrivateMessageList == null){
			return platform;
		}
		if(externalPrivateMessageList.isEmpty()){
			return platform;
		}
		
		for(PrivateMessage privateMessageItem: externalPrivateMessageList){
			privateMessageItem.clearReceiver();
			privateMessageItem.clearPlatform();
			
		}
		
		
		SmartList<PrivateMessage> privateMessageList = platform.getPrivateMessageList();		
		privateMessageList.addAllToRemoveList(externalPrivateMessageList);
		return platform;
	}
	
	public int countPrivateMessageListWithReceiver(String platformId, String receiverId, Map<String,Object> options)throws Exception{
				//SmartList<ThreadLike> toRemoveThreadLikeList = threadLikeList.getToRemoveList();
		//the list will not be null here, empty, maybe
		//getThreadLikeDAO().removeThreadLikeList(toRemoveThreadLikeList,options);

		MultipleAccessKey key = new MultipleAccessKey();
		key.put(PrivateMessage.PLATFORM_PROPERTY, platformId);
		key.put(PrivateMessage.RECEIVER_PROPERTY, receiverId);
		
		int count = getPrivateMessageDAO().countPrivateMessageWithKey(key, options);
		return count;
	}
	

		
	protected Platform saveProfileList(Platform platform, Map<String,Object> options){
		
		
		
		
		SmartList<Profile> profileList = platform.getProfileList();
		if(profileList == null){
			//null list means nothing
			return platform;
		}
		SmartList<Profile> mergedUpdateProfileList = new SmartList<Profile>();
		
		
		mergedUpdateProfileList.addAll(profileList); 
		if(profileList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdateProfileList.addAll(profileList.getToRemoveList());
			profileList.removeAll(profileList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getProfileDAO().saveProfileList(mergedUpdateProfileList,options);
		
		if(profileList.getToRemoveList() != null){
			profileList.removeAll(profileList.getToRemoveList());
		}
		
		
		return platform;
	
	}
	
	protected Platform removeProfileList(Platform platform, Map<String,Object> options){
	
	
		SmartList<Profile> profileList = platform.getProfileList();
		if(profileList == null){
			return platform;
		}	
	
		SmartList<Profile> toRemoveProfileList = profileList.getToRemoveList();
		
		if(toRemoveProfileList == null){
			return platform;
		}
		if(toRemoveProfileList.isEmpty()){
			return platform;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getProfileDAO().removeProfileList(toRemoveProfileList,options);
		
		return platform;
	
	}
	
	

 	
 	
	
	
	
		
	protected Platform savePrivateMessageList(Platform platform, Map<String,Object> options){
		
		
		
		
		SmartList<PrivateMessage> privateMessageList = platform.getPrivateMessageList();
		if(privateMessageList == null){
			//null list means nothing
			return platform;
		}
		SmartList<PrivateMessage> mergedUpdatePrivateMessageList = new SmartList<PrivateMessage>();
		
		
		mergedUpdatePrivateMessageList.addAll(privateMessageList); 
		if(privateMessageList.getToRemoveList() != null){
			//ensures the toRemoveList is not null
			mergedUpdatePrivateMessageList.addAll(privateMessageList.getToRemoveList());
			privateMessageList.removeAll(privateMessageList.getToRemoveList());
			//OK for now, need fix later
		}

		//adding new size can improve performance
	
		getPrivateMessageDAO().savePrivateMessageList(mergedUpdatePrivateMessageList,options);
		
		if(privateMessageList.getToRemoveList() != null){
			privateMessageList.removeAll(privateMessageList.getToRemoveList());
		}
		
		
		return platform;
	
	}
	
	protected Platform removePrivateMessageList(Platform platform, Map<String,Object> options){
	
	
		SmartList<PrivateMessage> privateMessageList = platform.getPrivateMessageList();
		if(privateMessageList == null){
			return platform;
		}	
	
		SmartList<PrivateMessage> toRemovePrivateMessageList = privateMessageList.getToRemoveList();
		
		if(toRemovePrivateMessageList == null){
			return platform;
		}
		if(toRemovePrivateMessageList.isEmpty()){
			return platform;// Does this mean delete all from the parent object?
		}
		//Call DAO to remove the list
		
		getPrivateMessageDAO().removePrivateMessageList(toRemovePrivateMessageList,options);
		
		return platform;
	
	}
	
	

 	
 	
	
	
	
		

	public Platform present(Platform platform,Map<String, Object> options){
	
		presentProfileList(platform,options);
		presentPrivateMessageList(platform,options);

		return platform;
	
	}
		
	//Using java8 feature to reduce the code significantly
 	protected Platform presentProfileList(
			Platform platform,
			Map<String, Object> options) {

		SmartList<Profile> profileList = platform.getProfileList();		
				SmartList<Profile> newList= presentSubList(platform.getId(),
				profileList,
				options,
				getProfileDAO()::countProfileByPlatform,
				getProfileDAO()::findProfileByPlatform
				);

		
		platform.setProfileList(newList);
		

		return platform;
	}			
		
	//Using java8 feature to reduce the code significantly
 	protected Platform presentPrivateMessageList(
			Platform platform,
			Map<String, Object> options) {

		SmartList<PrivateMessage> privateMessageList = platform.getPrivateMessageList();		
				SmartList<PrivateMessage> newList= presentSubList(platform.getId(),
				privateMessageList,
				options,
				getPrivateMessageDAO()::countPrivateMessageByPlatform,
				getPrivateMessageDAO()::findPrivateMessageByPlatform
				);

		
		platform.setPrivateMessageList(newList);
		

		return platform;
	}			
		

	
    public SmartList<Platform> requestCandidatePlatformForProfile(MessagecenterUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(PlatformTable.COLUMN_NAME, filterKey, pageNo, pageSize, getPlatformMapper());
    }
		
    public SmartList<Platform> requestCandidatePlatformForPrivateMessage(MessagecenterUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception {
        // NOTE: by default, ignore owner info, just return all by filter key.
		// You need override this method if you have different candidate-logic
		return findAllCandidateByFilter(PlatformTable.COLUMN_NAME, filterKey, pageNo, pageSize, getPlatformMapper());
    }
		

	protected String getTableName(){
		return PlatformTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<Platform> platformList) {		
		this.enhanceListInternal(platformList, this.getPlatformMapper());
	}
	
	
	// 需要一个加载引用我的对象的enhance方法:Profile的platform的ProfileList
	public SmartList<Profile> loadOurProfileList(MessagecenterUserContext userContext, List<Platform> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(Profile.PLATFORM_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<Profile> loadedObjs = userContext.getDAOGroup().getProfileDAO().findProfileWithKey(key, options);
		Map<String, List<Profile>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getPlatform().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<Profile> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<Profile> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setProfileList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	// 需要一个加载引用我的对象的enhance方法:PrivateMessage的platform的PrivateMessageList
	public SmartList<PrivateMessage> loadOurPrivateMessageList(MessagecenterUserContext userContext, List<Platform> us, Map<String,Object> options) throws Exception{
		if (us == null || us.isEmpty()){
			return new SmartList<>();
		}
		Set<String> ids = us.stream().map(it->it.getId()).collect(Collectors.toSet());
		MultipleAccessKey key = new MultipleAccessKey();
		key.put(PrivateMessage.PLATFORM_PROPERTY, ids.toArray(new String[ids.size()]));
		SmartList<PrivateMessage> loadedObjs = userContext.getDAOGroup().getPrivateMessageDAO().findPrivateMessageWithKey(key, options);
		Map<String, List<PrivateMessage>> loadedMap = loadedObjs.stream().collect(Collectors.groupingBy(it->it.getPlatform().getId()));
		us.forEach(it->{
			String id = it.getId();
			List<PrivateMessage> loadedList = loadedMap.get(id);
			if (loadedList == null || loadedList.isEmpty()) {
				return;
			}
			SmartList<PrivateMessage> loadedSmartList = new SmartList<>();
			loadedSmartList.addAll(loadedList);
			it.setPrivateMessageList(loadedSmartList);
		});
		return loadedObjs;
	}
	
	
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<Platform> platformList = ownerEntity.collectRefsWithType(Platform.INTERNAL_TYPE);
		this.enhanceList(platformList);
		
	}
	
	@Override
	public SmartList<Platform> findPlatformWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getPlatformMapper());

	}
	@Override
	public int countPlatformWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countPlatformWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<Platform> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getPlatformMapper());
	}
	
	
    
	public Map<String, Integer> countBySql(String sql, Object[] params) {
		if (params == null || params.length == 0) {
			return new HashMap<>();
		}
		List<Map<String, Object>> result = this.getJdbcTemplateObject().queryForList(sql, params);
		if (result == null || result.isEmpty()) {
			return new HashMap<>();
		}
		Map<String, Integer> cntMap = new HashMap<>();
		for (Map<String, Object> data : result) {
			String key = (String) data.get("id");
			Number value = (Number) data.get("count");
			cntMap.put(key, value.intValue());
		}
		this.logSQLAndParameters("countBySql", sql, params, cntMap.size() + " Counts");
		return cntMap;
	}

	public Integer singleCountBySql(String sql, Object[] params) {
		Integer cnt = this.getJdbcTemplateObject().queryForObject(sql, params, Integer.class);
		logSQLAndParameters("singleCountBySql", sql, params, cnt + "");
		return cnt;
	}

	public BigDecimal summaryBySql(String sql, Object[] params) {
		BigDecimal cnt = this.getJdbcTemplateObject().queryForObject(sql, params, BigDecimal.class);
		logSQLAndParameters("summaryBySql", sql, params, cnt + "");
		return cnt == null ? BigDecimal.ZERO : cnt;
	}

	public <T> List<T> queryForList(String sql, Object[] params, Class<T> claxx) {
		List<T> result = this.getJdbcTemplateObject().queryForList(sql, params, claxx);
		logSQLAndParameters("queryForList", sql, params, result.size() + " items");
		return result;
	}

	public Map<String, Object> queryForMap(String sql, Object[] params) throws DataAccessException {
		Map<String, Object> result = null;
		try {
			result = this.getJdbcTemplateObject().queryForMap(sql, params);
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			// 空结果，返回null
		}
		logSQLAndParameters("queryForObject", sql, params, result == null ? "not found" : String.valueOf(result));
		return result;
	}

	public <T> T queryForObject(String sql, Object[] params, Class<T> claxx) throws DataAccessException {
		T result = null;
		try {
			result = this.getJdbcTemplateObject().queryForObject(sql, params, claxx);
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			// 空结果，返回null
		}
		logSQLAndParameters("queryForObject", sql, params, result == null ? "not found" : String.valueOf(result));
		return result;
	}

	public List<Map<String, Object>> queryAsMapList(String sql, Object[] params) {
		List<Map<String, Object>> result = getJdbcTemplateObject().queryForList(sql, params);
		logSQLAndParameters("queryAsMapList", sql, params, result.size() + " items");
		return result;
	}

	public synchronized int updateBySql(String sql, Object[] params) {
		int result = getJdbcTemplateObject().update(sql, params);
		logSQLAndParameters("updateBySql", sql, params, result + " items");
		return result;
	}

	public void execSqlWithRowCallback(String sql, Object[] args, RowCallbackHandler callback) {
		getJdbcTemplateObject().query(sql, args, callback);
	}

	public void executeSql(String sql) {
		logSQLAndParameters("executeSql", sql, new Object[] {}, "");
		getJdbcTemplateObject().execute(sql);
	}


}


