
package com.doublechaintech.messagecenter.privatemessage;

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


import com.doublechaintech.messagecenter.profile.Profile;
import com.doublechaintech.messagecenter.platform.Platform;

import com.doublechaintech.messagecenter.platform.PlatformDAO;
import com.doublechaintech.messagecenter.profile.ProfileDAO;



import org.springframework.dao.EmptyResultDataAccessException;

public class PrivateMessageJDBCTemplateDAO extends MessagecenterNamingServiceDAO implements PrivateMessageDAO{
 
 	
 	private  ProfileDAO  profileDAO;
 	public void setProfileDAO(ProfileDAO profileDAO){
	 	this.profileDAO = profileDAO;
 	}
 	public ProfileDAO getProfileDAO(){
	 	return this.profileDAO;
 	}
 
 	
 	private  PlatformDAO  platformDAO;
 	public void setPlatformDAO(PlatformDAO platformDAO){
	 	this.platformDAO = platformDAO;
 	}
 	public PlatformDAO getPlatformDAO(){
	 	return this.platformDAO;
 	}


			
		

	
	/*
	protected PrivateMessage load(AccessKey accessKey,Map<String,Object> options) throws Exception{
		return loadInternalPrivateMessage(accessKey, options);
	}
	*/
	
	protected String getIdFormat()
	{
		return getShortName(this.getName())+"%06d";
	}
	
	public PrivateMessage load(String id,Map<String,Object> options) throws Exception{
		return loadInternalPrivateMessage(PrivateMessageTable.withId(id), options);
	}
	
	
	
	public PrivateMessage save(PrivateMessage privateMessage,Map<String,Object> options){
		
		String methodName="save(PrivateMessage privateMessage,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(privateMessage, methodName, "privateMessage");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		return saveInternalPrivateMessage(privateMessage,options);
	}
	public PrivateMessage clone(String privateMessageId, Map<String,Object> options) throws Exception{
	
		return clone(PrivateMessageTable.withId(privateMessageId),options);
	}
	
	protected PrivateMessage clone(AccessKey accessKey, Map<String,Object> options) throws Exception{
	
		String methodName="clone(String privateMessageId,Map<String,Object> options)";
		
		assertMethodArgumentNotNull(accessKey, methodName, "accessKey");
		assertMethodArgumentNotNull(options, methodName, "options");
		
		PrivateMessage newPrivateMessage = loadInternalPrivateMessage(accessKey, options);
		newPrivateMessage.setVersion(0);
		
		

		
		saveInternalPrivateMessage(newPrivateMessage,options);
		
		return newPrivateMessage;
	}
	
	
	
	

	protected void throwIfHasException(String privateMessageId,int version,int count) throws Exception{
		if (count == 1) {
			throw new PrivateMessageVersionChangedException(
					"The object version has been changed, please reload to delete");
		}
		if (count < 1) {
			throw new PrivateMessageNotFoundException(
					"The " + this.getTableName() + "(" + privateMessageId + ") has already been deleted.");
		}
		if (count > 1) {
			throw new IllegalStateException(
					"The table '" + this.getTableName() + "' PRIMARY KEY constraint has been damaged, please fix it.");
		}
	}
	
	
	public void delete(String privateMessageId, int version) throws Exception{
	
		String methodName="delete(String privateMessageId, int version)";
		assertMethodArgumentNotNull(privateMessageId, methodName, "privateMessageId");
		assertMethodIntArgumentGreaterThan(version,0, methodName, "options");
		
	
		String SQL=this.getDeleteSQL();
		Object [] parameters=new Object[]{privateMessageId,version};
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber == 1){
			return ; //Delete successfully
		}
		if(affectedNumber == 0){
			handleDeleteOneError(privateMessageId,version);
		}
		
	
	}
	
	
	
	
	

	public PrivateMessage disconnectFromAll(String privateMessageId, int version) throws Exception{
	
		
		PrivateMessage privateMessage = loadInternalPrivateMessage(PrivateMessageTable.withId(privateMessageId), emptyOptions());
		privateMessage.clearFromAll();
		this.savePrivateMessage(privateMessage);
		return privateMessage;
		
	
	}
	
	@Override
	protected String[] getNormalColumnNames() {

		return PrivateMessageTable.NORMAL_CLOUMNS;
	}
	@Override
	protected String getName() {
		
		return "private_message";
	}
	@Override
	protected String getBeanName() {
		
		return "privateMessage";
	}
	
	
	
	
	
	protected boolean checkOptions(Map<String,Object> options, String optionToCheck){
	
 		return PrivateMessageTokens.checkOptions(options, optionToCheck);
	
	}

 

 	protected boolean isExtractSenderEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, PrivateMessageTokens.SENDER);
 	}

 	protected boolean isSaveSenderEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, PrivateMessageTokens.SENDER);
 	}
 	

 	
  

 	protected boolean isExtractReceiverEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, PrivateMessageTokens.RECEIVER);
 	}

 	protected boolean isSaveReceiverEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, PrivateMessageTokens.RECEIVER);
 	}
 	

 	
  

 	protected boolean isExtractPlatformEnabled(Map<String,Object> options){
 		
	 	return checkOptions(options, PrivateMessageTokens.PLATFORM);
 	}

 	protected boolean isSavePlatformEnabled(Map<String,Object> options){
	 	
 		return checkOptions(options, PrivateMessageTokens.PLATFORM);
 	}
 	

 	
 
		

	

	protected PrivateMessageMapper getPrivateMessageMapper(){
		return new PrivateMessageMapper();
	}

	
	
	protected PrivateMessage extractPrivateMessage(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		try{
			PrivateMessage privateMessage = loadSingleObject(accessKey, getPrivateMessageMapper());
			return privateMessage;
		}catch(EmptyResultDataAccessException e){
			throw new PrivateMessageNotFoundException("PrivateMessage("+accessKey+") is not found!");
		}

	}

	
	

	protected PrivateMessage loadInternalPrivateMessage(AccessKey accessKey, Map<String,Object> loadOptions) throws Exception{
		
		PrivateMessage privateMessage = extractPrivateMessage(accessKey, loadOptions);
 	
 		if(isExtractSenderEnabled(loadOptions)){
	 		extractSender(privateMessage, loadOptions);
 		}
  	
 		if(isExtractReceiverEnabled(loadOptions)){
	 		extractReceiver(privateMessage, loadOptions);
 		}
  	
 		if(isExtractPlatformEnabled(loadOptions)){
	 		extractPlatform(privateMessage, loadOptions);
 		}
 
		
		return privateMessage;
		
	}

	 

 	protected PrivateMessage extractSender(PrivateMessage privateMessage, Map<String,Object> options) throws Exception{

		if(privateMessage.getSender() == null){
			return privateMessage;
		}
		String senderId = privateMessage.getSender().getId();
		if( senderId == null){
			return privateMessage;
		}
		Profile sender = getProfileDAO().load(senderId,options);
		if(sender != null){
			privateMessage.setSender(sender);
		}
		
 		
 		return privateMessage;
 	}
 		
  

 	protected PrivateMessage extractReceiver(PrivateMessage privateMessage, Map<String,Object> options) throws Exception{

		if(privateMessage.getReceiver() == null){
			return privateMessage;
		}
		String receiverId = privateMessage.getReceiver().getId();
		if( receiverId == null){
			return privateMessage;
		}
		Profile receiver = getProfileDAO().load(receiverId,options);
		if(receiver != null){
			privateMessage.setReceiver(receiver);
		}
		
 		
 		return privateMessage;
 	}
 		
  

 	protected PrivateMessage extractPlatform(PrivateMessage privateMessage, Map<String,Object> options) throws Exception{

		if(privateMessage.getPlatform() == null){
			return privateMessage;
		}
		String platformId = privateMessage.getPlatform().getId();
		if( platformId == null){
			return privateMessage;
		}
		Platform platform = getPlatformDAO().load(platformId,options);
		if(platform != null){
			privateMessage.setPlatform(platform);
		}
		
 		
 		return privateMessage;
 	}
 		
 
		
		
  	
 	public SmartList<PrivateMessage> findPrivateMessageBySender(String profileId,Map<String,Object> options){
 	
  		SmartList<PrivateMessage> resultList = queryWith(PrivateMessageTable.COLUMN_SENDER, profileId, options, getPrivateMessageMapper());
		// analyzePrivateMessageBySender(resultList, profileId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<PrivateMessage> findPrivateMessageBySender(String profileId, int start, int count,Map<String,Object> options){
 		
 		SmartList<PrivateMessage> resultList =  queryWithRange(PrivateMessageTable.COLUMN_SENDER, profileId, options, getPrivateMessageMapper(), start, count);
 		//analyzePrivateMessageBySender(resultList, profileId, options);
 		return resultList;
 		
 	}
 	public void analyzePrivateMessageBySender(SmartList<PrivateMessage> resultList, String profileId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(PrivateMessage.SENDER_PROPERTY, profileId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem sendTimeStatsItem = new StatsItem();
		//PrivateMessage.SEND_TIME_PROPERTY
		sendTimeStatsItem.setDisplayName("Private Message");
		sendTimeStatsItem.setInternalName(formatKeyForDateLine(PrivateMessage.SEND_TIME_PROPERTY));
		sendTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(PrivateMessage.SEND_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(sendTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countPrivateMessageBySender(String profileId,Map<String,Object> options){

 		return countWith(PrivateMessageTable.COLUMN_SENDER, profileId, options);
 	}
 	@Override
	public Map<String, Integer> countPrivateMessageBySenderIds(String[] ids, Map<String, Object> options) {
		return countWithIds(PrivateMessageTable.COLUMN_SENDER, ids, options);
	}
 	
  	
 	public SmartList<PrivateMessage> findPrivateMessageByReceiver(String profileId,Map<String,Object> options){
 	
  		SmartList<PrivateMessage> resultList = queryWith(PrivateMessageTable.COLUMN_RECEIVER, profileId, options, getPrivateMessageMapper());
		// analyzePrivateMessageByReceiver(resultList, profileId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<PrivateMessage> findPrivateMessageByReceiver(String profileId, int start, int count,Map<String,Object> options){
 		
 		SmartList<PrivateMessage> resultList =  queryWithRange(PrivateMessageTable.COLUMN_RECEIVER, profileId, options, getPrivateMessageMapper(), start, count);
 		//analyzePrivateMessageByReceiver(resultList, profileId, options);
 		return resultList;
 		
 	}
 	public void analyzePrivateMessageByReceiver(SmartList<PrivateMessage> resultList, String profileId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(PrivateMessage.RECEIVER_PROPERTY, profileId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem sendTimeStatsItem = new StatsItem();
		//PrivateMessage.SEND_TIME_PROPERTY
		sendTimeStatsItem.setDisplayName("Private Message");
		sendTimeStatsItem.setInternalName(formatKeyForDateLine(PrivateMessage.SEND_TIME_PROPERTY));
		sendTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(PrivateMessage.SEND_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(sendTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countPrivateMessageByReceiver(String profileId,Map<String,Object> options){

 		return countWith(PrivateMessageTable.COLUMN_RECEIVER, profileId, options);
 	}
 	@Override
	public Map<String, Integer> countPrivateMessageByReceiverIds(String[] ids, Map<String, Object> options) {
		return countWithIds(PrivateMessageTable.COLUMN_RECEIVER, ids, options);
	}
 	
  	
 	public SmartList<PrivateMessage> findPrivateMessageByPlatform(String platformId,Map<String,Object> options){
 	
  		SmartList<PrivateMessage> resultList = queryWith(PrivateMessageTable.COLUMN_PLATFORM, platformId, options, getPrivateMessageMapper());
		// analyzePrivateMessageByPlatform(resultList, platformId, options);
		return resultList;
 	}
 	 
 
 	public SmartList<PrivateMessage> findPrivateMessageByPlatform(String platformId, int start, int count,Map<String,Object> options){
 		
 		SmartList<PrivateMessage> resultList =  queryWithRange(PrivateMessageTable.COLUMN_PLATFORM, platformId, options, getPrivateMessageMapper(), start, count);
 		//analyzePrivateMessageByPlatform(resultList, platformId, options);
 		return resultList;
 		
 	}
 	public void analyzePrivateMessageByPlatform(SmartList<PrivateMessage> resultList, String platformId, Map<String,Object> options){
		if(resultList==null){
			return;//do nothing when the list is null.
		}
		
 		MultipleAccessKey filterKey = new MultipleAccessKey();
 		filterKey.put(PrivateMessage.PLATFORM_PROPERTY, platformId);
 		Map<String,Object> emptyOptions = new HashMap<String,Object>();
 		
 		StatsInfo info = new StatsInfo();
 		
 
		StatsItem sendTimeStatsItem = new StatsItem();
		//PrivateMessage.SEND_TIME_PROPERTY
		sendTimeStatsItem.setDisplayName("Private Message");
		sendTimeStatsItem.setInternalName(formatKeyForDateLine(PrivateMessage.SEND_TIME_PROPERTY));
		sendTimeStatsItem.setResult(statsWithGroup(DateKey.class,wrapWithDate(PrivateMessage.SEND_TIME_PROPERTY),filterKey,emptyOptions));
		info.addItem(sendTimeStatsItem);
 				
 		resultList.setStatsInfo(info);

 	
 		
 	}
 	@Override
 	public int countPrivateMessageByPlatform(String platformId,Map<String,Object> options){

 		return countWith(PrivateMessageTable.COLUMN_PLATFORM, platformId, options);
 	}
 	@Override
	public Map<String, Integer> countPrivateMessageByPlatformIds(String[] ids, Map<String, Object> options) {
		return countWithIds(PrivateMessageTable.COLUMN_PLATFORM, ids, options);
	}
 	
 	
		
		
		

	

	protected PrivateMessage savePrivateMessage(PrivateMessage  privateMessage){
		
		if(!privateMessage.isChanged()){
			return privateMessage;
		}
		
		
		String SQL=this.getSavePrivateMessageSQL(privateMessage);
		//FIXME: how about when an item has been updated more than MAX_INT?
		Object [] parameters = getSavePrivateMessageParameters(privateMessage);
		int affectedNumber = singleUpdate(SQL,parameters);
		if(affectedNumber != 1){
			throw new IllegalStateException("The save operation should return value = 1, while the value = "
				+ affectedNumber +"If the value = 0, that mean the target record has been updated by someone else!");
		}
		
		privateMessage.incVersion();
		return privateMessage;
	
	}
	public SmartList<PrivateMessage> savePrivateMessageList(SmartList<PrivateMessage> privateMessageList,Map<String,Object> options){
		//assuming here are big amount objects to be updated.
		//First step is split into two groups, one group for update and another group for create
		Object [] lists=splitPrivateMessageList(privateMessageList);
		
		batchPrivateMessageCreate((List<PrivateMessage>)lists[CREATE_LIST_INDEX]);
		
		batchPrivateMessageUpdate((List<PrivateMessage>)lists[UPDATE_LIST_INDEX]);
		
		
		//update version after the list successfully saved to database;
		for(PrivateMessage privateMessage:privateMessageList){
			if(privateMessage.isChanged()){
				privateMessage.incVersion();
			}
			
		
		}
		
		
		return privateMessageList;
	}

	public SmartList<PrivateMessage> removePrivateMessageList(SmartList<PrivateMessage> privateMessageList,Map<String,Object> options){
		
		
		super.removeList(privateMessageList, options);
		
		return privateMessageList;
		
		
	}
	
	protected List<Object[]> preparePrivateMessageBatchCreateArgs(List<PrivateMessage> privateMessageList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(PrivateMessage privateMessage:privateMessageList ){
			Object [] parameters = preparePrivateMessageCreateParameters(privateMessage);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected List<Object[]> preparePrivateMessageBatchUpdateArgs(List<PrivateMessage> privateMessageList){
		
		List<Object[]> parametersList=new ArrayList<Object[]>();
		for(PrivateMessage privateMessage:privateMessageList ){
			if(!privateMessage.isChanged()){
				continue;
			}
			Object [] parameters = preparePrivateMessageUpdateParameters(privateMessage);
			parametersList.add(parameters);
		
		}
		return parametersList;
		
	}
	protected void batchPrivateMessageCreate(List<PrivateMessage> privateMessageList){
		String SQL=getCreateSQL();
		List<Object[]> args=preparePrivateMessageBatchCreateArgs(privateMessageList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
	}
	
	
	protected void batchPrivateMessageUpdate(List<PrivateMessage> privateMessageList){
		String SQL=getUpdateSQL();
		List<Object[]> args=preparePrivateMessageBatchUpdateArgs(privateMessageList);
		
		int affectedNumbers[] = batchUpdate(SQL, args);
		
		
		
	}
	
	
	
	static final int CREATE_LIST_INDEX=0;
	static final int UPDATE_LIST_INDEX=1;
	
	protected Object[] splitPrivateMessageList(List<PrivateMessage> privateMessageList){
		
		List<PrivateMessage> privateMessageCreateList=new ArrayList<PrivateMessage>();
		List<PrivateMessage> privateMessageUpdateList=new ArrayList<PrivateMessage>();
		
		for(PrivateMessage privateMessage: privateMessageList){
			if(isUpdateRequest(privateMessage)){
				privateMessageUpdateList.add( privateMessage);
				continue;
			}
			privateMessageCreateList.add(privateMessage);
		}
		
		return new Object[]{privateMessageCreateList,privateMessageUpdateList};
	}
	
	protected boolean isUpdateRequest(PrivateMessage privateMessage){
 		return privateMessage.getVersion() > 0;
 	}
 	protected String getSavePrivateMessageSQL(PrivateMessage privateMessage){
 		if(isUpdateRequest(privateMessage)){
 			return getUpdateSQL();
 		}
 		return getCreateSQL();
 	}
 	
 	protected Object[] getSavePrivateMessageParameters(PrivateMessage privateMessage){
 		if(isUpdateRequest(privateMessage) ){
 			return preparePrivateMessageUpdateParameters(privateMessage);
 		}
 		return preparePrivateMessageCreateParameters(privateMessage);
 	}
 	protected Object[] preparePrivateMessageUpdateParameters(PrivateMessage privateMessage){
 		Object[] parameters = new Object[11];
 
 		parameters[0] = privateMessage.getTitle();
 		parameters[1] = privateMessage.getContent();
 		parameters[2] = privateMessage.getSendTime();
 		parameters[3] = privateMessage.getReadTime(); 	
 		if(privateMessage.getSender() != null){
 			parameters[4] = privateMessage.getSender().getId();
 		}
  	
 		if(privateMessage.getReceiver() != null){
 			parameters[5] = privateMessage.getReceiver().getId();
 		}
 
 		parameters[6] = privateMessage.getStatus(); 	
 		if(privateMessage.getPlatform() != null){
 			parameters[7] = privateMessage.getPlatform().getId();
 		}
 		
 		parameters[8] = privateMessage.nextVersion();
 		parameters[9] = privateMessage.getId();
 		parameters[10] = privateMessage.getVersion();
 				
 		return parameters;
 	}
 	protected Object[] preparePrivateMessageCreateParameters(PrivateMessage privateMessage){
		Object[] parameters = new Object[9];
		String newPrivateMessageId=getNextId();
		privateMessage.setId(newPrivateMessageId);
		parameters[0] =  privateMessage.getId();
 
 		parameters[1] = privateMessage.getTitle();
 		parameters[2] = privateMessage.getContent();
 		parameters[3] = privateMessage.getSendTime();
 		parameters[4] = privateMessage.getReadTime(); 	
 		if(privateMessage.getSender() != null){
 			parameters[5] = privateMessage.getSender().getId();
 		
 		}
 		 	
 		if(privateMessage.getReceiver() != null){
 			parameters[6] = privateMessage.getReceiver().getId();
 		
 		}
 		
 		parameters[7] = privateMessage.getStatus(); 	
 		if(privateMessage.getPlatform() != null){
 			parameters[8] = privateMessage.getPlatform().getId();
 		
 		}
 				
 				
 		return parameters;
 	}
 	
	protected PrivateMessage saveInternalPrivateMessage(PrivateMessage privateMessage, Map<String,Object> options){
		
		savePrivateMessage(privateMessage);
 	
 		if(isSaveSenderEnabled(options)){
	 		saveSender(privateMessage, options);
 		}
  	
 		if(isSaveReceiverEnabled(options)){
	 		saveReceiver(privateMessage, options);
 		}
  	
 		if(isSavePlatformEnabled(options)){
	 		savePlatform(privateMessage, options);
 		}
 
		
		return privateMessage;
		
	}
	
	
	
	//======================================================================================
	 
 
 	protected PrivateMessage saveSender(PrivateMessage privateMessage, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(privateMessage.getSender() == null){
 			return privateMessage;//do nothing when it is null
 		}
 		
 		getProfileDAO().save(privateMessage.getSender(),options);
 		return privateMessage;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected PrivateMessage saveReceiver(PrivateMessage privateMessage, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(privateMessage.getReceiver() == null){
 			return privateMessage;//do nothing when it is null
 		}
 		
 		getProfileDAO().save(privateMessage.getReceiver(),options);
 		return privateMessage;
 		
 	}
 	
 	
 	
 	 
	
  
 
 	protected PrivateMessage savePlatform(PrivateMessage privateMessage, Map<String,Object> options){
 		//Call inject DAO to execute this method
 		if(privateMessage.getPlatform() == null){
 			return privateMessage;//do nothing when it is null
 		}
 		
 		getPlatformDAO().save(privateMessage.getPlatform(),options);
 		return privateMessage;
 		
 	}
 	
 	
 	
 	 
	
 

	

		

	public PrivateMessage present(PrivateMessage privateMessage,Map<String, Object> options){
	

		return privateMessage;
	
	}
		

	

	protected String getTableName(){
		return PrivateMessageTable.TABLE_NAME;
	}
	
	
	
	public void enhanceList(List<PrivateMessage> privateMessageList) {		
		this.enhanceListInternal(privateMessageList, this.getPrivateMessageMapper());
	}
	@Override
	public void collectAndEnhance(BaseEntity ownerEntity) {
		List<PrivateMessage> privateMessageList = ownerEntity.collectRefsWithType(PrivateMessage.INTERNAL_TYPE);
		this.enhanceList(privateMessageList);
		
	}
	
	@Override
	public SmartList<PrivateMessage> findPrivateMessageWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return queryWith(key, options, getPrivateMessageMapper());

	}
	@Override
	public int countPrivateMessageWithKey(MultipleAccessKey key,
			Map<String, Object> options) {
		
  		return countWith(key, options);

	}
	public Map<String, Integer> countPrivateMessageWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options) {
			
  		return countWithGroup(groupKey, filterKey, options);

	}
	
	@Override
	public SmartList<PrivateMessage> queryList(String sql, Object... parameters) {
	    return this.queryForList(sql, parameters, this.getPrivateMessageMapper());
	}
}


