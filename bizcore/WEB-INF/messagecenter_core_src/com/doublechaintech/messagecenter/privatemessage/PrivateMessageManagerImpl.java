
package com.doublechaintech.messagecenter.privatemessage;

import java.util.Date;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.math.BigDecimal;
import com.terapico.caf.DateTime;
import com.doublechaintech.messagecenter.BaseEntity;


import com.doublechaintech.messagecenter.Message;
import com.doublechaintech.messagecenter.SmartList;
import com.doublechaintech.messagecenter.MultipleAccessKey;

import com.doublechaintech.messagecenter.MessagecenterUserContext;
//import com.doublechaintech.messagecenter.BaseManagerImpl;
import com.doublechaintech.messagecenter.MessagecenterCheckerManager;
import com.doublechaintech.messagecenter.CustomMessagecenterCheckerManager;

import com.doublechaintech.messagecenter.profile.Profile;
import com.doublechaintech.messagecenter.platform.Platform;

import com.doublechaintech.messagecenter.profile.CandidateProfile;
import com.doublechaintech.messagecenter.platform.CandidatePlatform;







public class PrivateMessageManagerImpl extends CustomMessagecenterCheckerManager implements PrivateMessageManager {
	
	private static final String SERVICE_TYPE = "PrivateMessage";
	
	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}
	
	
	protected void throwExceptionWithMessage(String value) throws PrivateMessageManagerException{
	
		Message message = new Message();
		message.setBody(value);
		throw new PrivateMessageManagerException(message);

	}
	
	

 	protected PrivateMessage savePrivateMessage(MessagecenterUserContext userContext, PrivateMessage privateMessage, String [] tokensExpr) throws Exception{	
 		//return getPrivateMessageDAO().save(privateMessage, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return savePrivateMessage(userContext, privateMessage, tokens);
 	}
 	
 	protected PrivateMessage savePrivateMessageDetail(MessagecenterUserContext userContext, PrivateMessage privateMessage) throws Exception{	

 		
 		return savePrivateMessage(userContext, privateMessage, allTokens());
 	}
 	
 	public PrivateMessage loadPrivateMessage(MessagecenterUserContext userContext, String privateMessageId, String [] tokensExpr) throws Exception{				
 
 		userContext.getChecker().checkIdOfPrivateMessage(privateMessageId);
		userContext.getChecker().throwExceptionIfHasErrors( PrivateMessageManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		PrivateMessage privateMessage = loadPrivateMessage( userContext, privateMessageId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,privateMessage, tokens);
 	}
 	
 	
 	 public PrivateMessage searchPrivateMessage(MessagecenterUserContext userContext, String privateMessageId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		userContext.getChecker().checkIdOfPrivateMessage(privateMessageId);
		userContext.getChecker().throwExceptionIfHasErrors( PrivateMessageManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		PrivateMessage privateMessage = loadPrivateMessage( userContext, privateMessageId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,privateMessage, tokens);
 	}
 	
 	

 	protected PrivateMessage present(MessagecenterUserContext userContext, PrivateMessage privateMessage, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,privateMessage,tokens);
		
		
		PrivateMessage  privateMessageToPresent = userContext.getDAOGroup().getPrivateMessageDAO().present(privateMessage, tokens);
		
		List<BaseEntity> entityListToNaming = privateMessageToPresent.collectRefercencesFromLists();
		userContext.getDAOGroup().getPrivateMessageDAO().alias(entityListToNaming);
		
		return  privateMessageToPresent;
		
		
	}
 
 	
 	
 	public PrivateMessage loadPrivateMessageDetail(MessagecenterUserContext userContext, String privateMessageId) throws Exception{	
 		PrivateMessage privateMessage = loadPrivateMessage( userContext, privateMessageId, allTokens());
 		return present(userContext,privateMessage, allTokens());
		
 	}
 	
 	public Object view(MessagecenterUserContext userContext, String privateMessageId) throws Exception{	
 		PrivateMessage privateMessage = loadPrivateMessage( userContext, privateMessageId, viewTokens());
 		return present(userContext,privateMessage, allTokens());
		
 	}
 	protected PrivateMessage savePrivateMessage(MessagecenterUserContext userContext, PrivateMessage privateMessage, Map<String,Object>tokens) throws Exception{	
 		return userContext.getDAOGroup().getPrivateMessageDAO().save(privateMessage, tokens);
 	}
 	protected PrivateMessage loadPrivateMessage(MessagecenterUserContext userContext, String privateMessageId, Map<String,Object>tokens) throws Exception{	
		userContext.getChecker().checkIdOfPrivateMessage(privateMessageId);
		userContext.getChecker().throwExceptionIfHasErrors( PrivateMessageManagerException.class);

 
 		return userContext.getDAOGroup().getPrivateMessageDAO().load(privateMessageId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(MessagecenterUserContext userContext, PrivateMessage privateMessage, Map<String, Object> tokens){
		super.addActions(userContext, privateMessage, tokens);
		
		addAction(userContext, privateMessage, tokens,"@create","createPrivateMessage","createPrivateMessage/","main","primary");
		addAction(userContext, privateMessage, tokens,"@update","updatePrivateMessage","updatePrivateMessage/"+privateMessage.getId()+"/","main","primary");
		addAction(userContext, privateMessage, tokens,"@copy","clonePrivateMessage","clonePrivateMessage/"+privateMessage.getId()+"/","main","primary");
		
		addAction(userContext, privateMessage, tokens,"private_message.transfer_to_sender","transferToAnotherSender","transferToAnotherSender/"+privateMessage.getId()+"/","main","primary");
		addAction(userContext, privateMessage, tokens,"private_message.transfer_to_receiver","transferToAnotherReceiver","transferToAnotherReceiver/"+privateMessage.getId()+"/","main","primary");
		addAction(userContext, privateMessage, tokens,"private_message.transfer_to_platform","transferToAnotherPlatform","transferToAnotherPlatform/"+privateMessage.getId()+"/","main","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(MessagecenterUserContext userContext, PrivateMessage privateMessage, Map<String, Object> tokens){
	
 	
 	
 
 	
 	


	public PrivateMessage createPrivateMessage(MessagecenterUserContext userContext,String title, String content, String senderId, String receiverId, String status, String platformId) throws Exception
	{
		
		

		

		userContext.getChecker().checkTitleOfPrivateMessage(title);
		userContext.getChecker().checkContentOfPrivateMessage(content);
		userContext.getChecker().checkStatusOfPrivateMessage(status);
	
		userContext.getChecker().throwExceptionIfHasErrors(PrivateMessageManagerException.class);


		PrivateMessage privateMessage=createNewPrivateMessage();	

		privateMessage.setTitle(title);
		privateMessage.setContent(content);
		privateMessage.setSendTime(userContext.now());
		privateMessage.setReadTime(userContext.now());
			
		Profile sender = loadProfile(userContext, senderId,emptyOptions());
		privateMessage.setSender(sender);
		
		
			
		Profile receiver = loadProfile(userContext, receiverId,emptyOptions());
		privateMessage.setReceiver(receiver);
		
		
		privateMessage.setStatus(status);
			
		Platform platform = loadPlatform(userContext, platformId,emptyOptions());
		privateMessage.setPlatform(platform);
		
		

		privateMessage = savePrivateMessage(userContext, privateMessage, emptyOptions());
		
		onNewInstanceCreated(userContext, privateMessage);
		return privateMessage;

		
	}
	protected PrivateMessage createNewPrivateMessage() 
	{
		
		return new PrivateMessage();		
	}
	
	protected void checkParamsForUpdatingPrivateMessage(MessagecenterUserContext userContext,String privateMessageId, int privateMessageVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		userContext.getChecker().checkIdOfPrivateMessage(privateMessageId);
		userContext.getChecker().checkVersionOfPrivateMessage( privateMessageVersion);
		

		if(PrivateMessage.TITLE_PROPERTY.equals(property)){
			userContext.getChecker().checkTitleOfPrivateMessage(parseString(newValueExpr));
		}
		if(PrivateMessage.CONTENT_PROPERTY.equals(property)){
			userContext.getChecker().checkContentOfPrivateMessage(parseString(newValueExpr));
		}		

				

		
		if(PrivateMessage.STATUS_PROPERTY.equals(property)){
			userContext.getChecker().checkStatusOfPrivateMessage(parseString(newValueExpr));
		}		

		
	
		userContext.getChecker().throwExceptionIfHasErrors(PrivateMessageManagerException.class);
	
		
	}
	
	
	
	public PrivateMessage clone(MessagecenterUserContext userContext, String fromPrivateMessageId) throws Exception{
		
		return userContext.getDAOGroup().getPrivateMessageDAO().clone(fromPrivateMessageId, this.allTokens());
	}
	
	public PrivateMessage internalSavePrivateMessage(MessagecenterUserContext userContext, PrivateMessage privateMessage) throws Exception 
	{
		return internalSavePrivateMessage(userContext, privateMessage, allTokens());

	}
	public PrivateMessage internalSavePrivateMessage(MessagecenterUserContext userContext, PrivateMessage privateMessage, Map<String,Object> options) throws Exception 
	{
		//checkParamsForUpdatingPrivateMessage(userContext, privateMessageId, privateMessageVersion, property, newValueExpr, tokensExpr);
		
		
		synchronized(privateMessage){ 
			//will be good when the privateMessage loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to PrivateMessage.
			
			
			privateMessage = savePrivateMessage(userContext, privateMessage, options);
			return privateMessage;
			
		}

	}
	
	public PrivateMessage updatePrivateMessage(MessagecenterUserContext userContext,String privateMessageId, int privateMessageVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingPrivateMessage(userContext, privateMessageId, privateMessageVersion, property, newValueExpr, tokensExpr);
		
		
		
		PrivateMessage privateMessage = loadPrivateMessage(userContext, privateMessageId, allTokens());
		if(privateMessage.getVersion() != privateMessageVersion){
			String message = "The target version("+privateMessage.getVersion()+") is not equals to version("+privateMessageVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(privateMessage){ 
			//will be good when the privateMessage loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to PrivateMessage.
			privateMessage.updateReadTime(userContext.now());
			privateMessage.changeProperty(property, newValueExpr);
			privateMessage = savePrivateMessage(userContext, privateMessage, tokens().done());
			return present(userContext,privateMessage, mergedAllTokens(tokensExpr));
			//return savePrivateMessage(userContext, privateMessage, tokens().done());
		}

	}
	
	public PrivateMessage updatePrivateMessageProperty(MessagecenterUserContext userContext,String privateMessageId, int privateMessageVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingPrivateMessage(userContext, privateMessageId, privateMessageVersion, property, newValueExpr, tokensExpr);
		
		PrivateMessage privateMessage = loadPrivateMessage(userContext, privateMessageId, allTokens());
		if(privateMessage.getVersion() != privateMessageVersion){
			String message = "The target version("+privateMessage.getVersion()+") is not equals to version("+privateMessageVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(privateMessage){ 
			//will be good when the privateMessage loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to PrivateMessage.
			
			privateMessage.changeProperty(property, newValueExpr);
			privateMessage.updateReadTime(userContext.now());
			privateMessage = savePrivateMessage(userContext, privateMessage, tokens().done());
			return present(userContext,privateMessage, mergedAllTokens(tokensExpr));
			//return savePrivateMessage(userContext, privateMessage, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}
	
	protected PrivateMessageTokens tokens(){
		return PrivateMessageTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return PrivateMessageTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return PrivateMessageTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherSender(MessagecenterUserContext userContext, String privateMessageId, String anotherSenderId) throws Exception
 	{
 		
 		userContext.getChecker().checkIdOfPrivateMessage(privateMessageId);
 		userContext.getChecker().checkIdOfProfile(anotherSenderId);//check for optional reference
 		userContext.getChecker().throwExceptionIfHasErrors(PrivateMessageManagerException.class);
 		
 	}
 	public PrivateMessage transferToAnotherSender(MessagecenterUserContext userContext, String privateMessageId, String anotherSenderId) throws Exception
 	{
 		checkParamsForTransferingAnotherSender(userContext, privateMessageId,anotherSenderId);
 
		PrivateMessage privateMessage = loadPrivateMessage(userContext, privateMessageId, allTokens());	
		synchronized(privateMessage){
			//will be good when the privateMessage loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Profile sender = loadProfile(userContext, anotherSenderId, emptyOptions());		
			privateMessage.updateSender(sender);		
			privateMessage = savePrivateMessage(userContext, privateMessage, emptyOptions());
			
			return present(userContext,privateMessage, allTokens());
			
		}

 	}
 	
	 	
 	
 	
	public CandidateProfile requestCandidateSender(MessagecenterUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateProfile result = new CandidateProfile();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");
		
		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<Profile> candidateList = userContext.getDAOGroup().getProfileDAO().requestCandidateProfileForPrivateMessageAsSender(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}
 	
 	protected void checkParamsForTransferingAnotherReceiver(MessagecenterUserContext userContext, String privateMessageId, String anotherReceiverId) throws Exception
 	{
 		
 		userContext.getChecker().checkIdOfPrivateMessage(privateMessageId);
 		userContext.getChecker().checkIdOfProfile(anotherReceiverId);//check for optional reference
 		userContext.getChecker().throwExceptionIfHasErrors(PrivateMessageManagerException.class);
 		
 	}
 	public PrivateMessage transferToAnotherReceiver(MessagecenterUserContext userContext, String privateMessageId, String anotherReceiverId) throws Exception
 	{
 		checkParamsForTransferingAnotherReceiver(userContext, privateMessageId,anotherReceiverId);
 
		PrivateMessage privateMessage = loadPrivateMessage(userContext, privateMessageId, allTokens());	
		synchronized(privateMessage){
			//will be good when the privateMessage loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Profile receiver = loadProfile(userContext, anotherReceiverId, emptyOptions());		
			privateMessage.updateReceiver(receiver);		
			privateMessage = savePrivateMessage(userContext, privateMessage, emptyOptions());
			
			return present(userContext,privateMessage, allTokens());
			
		}

 	}
 	
	 	
 	
 	
	public CandidateProfile requestCandidateReceiver(MessagecenterUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidateProfile result = new CandidateProfile();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");
		
		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<Profile> candidateList = userContext.getDAOGroup().getProfileDAO().requestCandidateProfileForPrivateMessageAsReceiver(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}
 	
 	protected void checkParamsForTransferingAnotherPlatform(MessagecenterUserContext userContext, String privateMessageId, String anotherPlatformId) throws Exception
 	{
 		
 		userContext.getChecker().checkIdOfPrivateMessage(privateMessageId);
 		userContext.getChecker().checkIdOfPlatform(anotherPlatformId);//check for optional reference
 		userContext.getChecker().throwExceptionIfHasErrors(PrivateMessageManagerException.class);
 		
 	}
 	public PrivateMessage transferToAnotherPlatform(MessagecenterUserContext userContext, String privateMessageId, String anotherPlatformId) throws Exception
 	{
 		checkParamsForTransferingAnotherPlatform(userContext, privateMessageId,anotherPlatformId);
 
		PrivateMessage privateMessage = loadPrivateMessage(userContext, privateMessageId, allTokens());	
		synchronized(privateMessage){
			//will be good when the privateMessage loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Platform platform = loadPlatform(userContext, anotherPlatformId, emptyOptions());		
			privateMessage.updatePlatform(platform);		
			privateMessage = savePrivateMessage(userContext, privateMessage, emptyOptions());
			
			return present(userContext,privateMessage, allTokens());
			
		}

 	}
 	
	 	
 	
 	
	public CandidatePlatform requestCandidatePlatform(MessagecenterUserContext userContext, String ownerClass, String id, String filterKey, int pageNo) throws Exception {

		CandidatePlatform result = new CandidatePlatform();
		result.setOwnerClass(ownerClass);
		result.setOwnerId(id);
		result.setFilterKey(filterKey==null?"":filterKey.trim());
		result.setPageNo(pageNo);
		result.setValueFieldName("id");
		result.setDisplayFieldName("name");
		
		pageNo = Math.max(1, pageNo);
		int pageSize = 20;
		//requestCandidateProductForSkuAsOwner
		SmartList<Platform> candidateList = userContext.getDAOGroup().getPlatformDAO().requestCandidatePlatformForPrivateMessage(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}
 	
 //--------------------------------------------------------------
	
	 	
 	protected Profile loadProfile(MessagecenterUserContext userContext, String newSenderId, Map<String,Object> options) throws Exception
 	{
		
 		return userContext.getDAOGroup().getProfileDAO().load(newSenderId, options);
 	}
 	
 	
 	
	
	 	
 	protected Platform loadPlatform(MessagecenterUserContext userContext, String newPlatformId, Map<String,Object> options) throws Exception
 	{
		
 		return userContext.getDAOGroup().getPlatformDAO().load(newPlatformId, options);
 	}
 	
 	
 	
	
	//--------------------------------------------------------------

	public void delete(MessagecenterUserContext userContext, String privateMessageId, int privateMessageVersion) throws Exception {
		//deleteInternal(userContext, privateMessageId, privateMessageVersion);		
	}
	protected void deleteInternal(MessagecenterUserContext userContext,
			String privateMessageId, int privateMessageVersion) throws Exception{
			
		userContext.getDAOGroup().getPrivateMessageDAO().delete(privateMessageId, privateMessageVersion);
	}
	
	public PrivateMessage forgetByAll(MessagecenterUserContext userContext, String privateMessageId, int privateMessageVersion) throws Exception {
		return forgetByAllInternal(userContext, privateMessageId, privateMessageVersion);		
	}
	protected PrivateMessage forgetByAllInternal(MessagecenterUserContext userContext,
			String privateMessageId, int privateMessageVersion) throws Exception{
			
		return userContext.getDAOGroup().getPrivateMessageDAO().disconnectFromAll(privateMessageId, privateMessageVersion);
	}
	

	
	public int deleteAll(MessagecenterUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new PrivateMessageManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}
	
	
	protected int deleteAllInternal(MessagecenterUserContext userContext) throws Exception{
		return userContext.getDAOGroup().getPrivateMessageDAO().deleteAll();
	}


	
	
	
	
	

	public void onNewInstanceCreated(MessagecenterUserContext userContext, PrivateMessage newCreated) throws Exception{
		ensureRelationInGraph(userContext, newCreated);
		sendCreationEvent(userContext, newCreated);
	}

}


