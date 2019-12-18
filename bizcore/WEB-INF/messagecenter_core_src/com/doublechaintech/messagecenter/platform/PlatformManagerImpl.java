
package com.doublechaintech.messagecenter.platform;

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
import com.doublechaintech.messagecenter.privatemessage.PrivateMessage;


import com.doublechaintech.messagecenter.profile.Profile;
import com.doublechaintech.messagecenter.platform.Platform;






public class PlatformManagerImpl extends CustomMessagecenterCheckerManager implements PlatformManager {
	
	private static final String SERVICE_TYPE = "Platform";
	
	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}
	
	
	protected void throwExceptionWithMessage(String value) throws PlatformManagerException{
	
		Message message = new Message();
		message.setBody(value);
		throw new PlatformManagerException(message);

	}
	
	

 	protected Platform savePlatform(MessagecenterUserContext userContext, Platform platform, String [] tokensExpr) throws Exception{	
 		//return getPlatformDAO().save(platform, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return savePlatform(userContext, platform, tokens);
 	}
 	
 	protected Platform savePlatformDetail(MessagecenterUserContext userContext, Platform platform) throws Exception{	

 		
 		return savePlatform(userContext, platform, allTokens());
 	}
 	
 	public Platform loadPlatform(MessagecenterUserContext userContext, String platformId, String [] tokensExpr) throws Exception{				
 
 		userContext.getChecker().checkIdOfPlatform(platformId);
		userContext.getChecker().throwExceptionIfHasErrors( PlatformManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		Platform platform = loadPlatform( userContext, platformId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,platform, tokens);
 	}
 	
 	
 	 public Platform searchPlatform(MessagecenterUserContext userContext, String platformId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		userContext.getChecker().checkIdOfPlatform(platformId);
		userContext.getChecker().throwExceptionIfHasErrors( PlatformManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		Platform platform = loadPlatform( userContext, platformId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,platform, tokens);
 	}
 	
 	

 	protected Platform present(MessagecenterUserContext userContext, Platform platform, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,platform,tokens);
		
		
		Platform  platformToPresent = userContext.getDAOGroup().getPlatformDAO().present(platform, tokens);
		
		List<BaseEntity> entityListToNaming = platformToPresent.collectRefercencesFromLists();
		userContext.getDAOGroup().getPlatformDAO().alias(entityListToNaming);
		
		return  platformToPresent;
		
		
	}
 
 	
 	
 	public Platform loadPlatformDetail(MessagecenterUserContext userContext, String platformId) throws Exception{	
 		Platform platform = loadPlatform( userContext, platformId, allTokens());
 		return present(userContext,platform, allTokens());
		
 	}
 	
 	public Object view(MessagecenterUserContext userContext, String platformId) throws Exception{	
 		Platform platform = loadPlatform( userContext, platformId, viewTokens());
 		return present(userContext,platform, allTokens());
		
 	}
 	protected Platform savePlatform(MessagecenterUserContext userContext, Platform platform, Map<String,Object>tokens) throws Exception{	
 		return userContext.getDAOGroup().getPlatformDAO().save(platform, tokens);
 	}
 	protected Platform loadPlatform(MessagecenterUserContext userContext, String platformId, Map<String,Object>tokens) throws Exception{	
		userContext.getChecker().checkIdOfPlatform(platformId);
		userContext.getChecker().throwExceptionIfHasErrors( PlatformManagerException.class);

 
 		return userContext.getDAOGroup().getPlatformDAO().load(platformId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(MessagecenterUserContext userContext, Platform platform, Map<String, Object> tokens){
		super.addActions(userContext, platform, tokens);
		
		addAction(userContext, platform, tokens,"@create","createPlatform","createPlatform/","main","primary");
		addAction(userContext, platform, tokens,"@update","updatePlatform","updatePlatform/"+platform.getId()+"/","main","primary");
		addAction(userContext, platform, tokens,"@copy","clonePlatform","clonePlatform/"+platform.getId()+"/","main","primary");
		
		addAction(userContext, platform, tokens,"platform.addProfile","addProfile","addProfile/"+platform.getId()+"/","profileList","primary");
		addAction(userContext, platform, tokens,"platform.removeProfile","removeProfile","removeProfile/"+platform.getId()+"/","profileList","primary");
		addAction(userContext, platform, tokens,"platform.updateProfile","updateProfile","updateProfile/"+platform.getId()+"/","profileList","primary");
		addAction(userContext, platform, tokens,"platform.copyProfileFrom","copyProfileFrom","copyProfileFrom/"+platform.getId()+"/","profileList","primary");
		addAction(userContext, platform, tokens,"platform.addPrivateMessage","addPrivateMessage","addPrivateMessage/"+platform.getId()+"/","privateMessageList","primary");
		addAction(userContext, platform, tokens,"platform.removePrivateMessage","removePrivateMessage","removePrivateMessage/"+platform.getId()+"/","privateMessageList","primary");
		addAction(userContext, platform, tokens,"platform.updatePrivateMessage","updatePrivateMessage","updatePrivateMessage/"+platform.getId()+"/","privateMessageList","primary");
		addAction(userContext, platform, tokens,"platform.copyPrivateMessageFrom","copyPrivateMessageFrom","copyPrivateMessageFrom/"+platform.getId()+"/","privateMessageList","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(MessagecenterUserContext userContext, Platform platform, Map<String, Object> tokens){
	
 	
 	
 
 	
 	


	public Platform createPlatform(MessagecenterUserContext userContext,String name, String introduction, String currentVersion) throws Exception
	{
		
		

		

		userContext.getChecker().checkNameOfPlatform(name);
		userContext.getChecker().checkIntroductionOfPlatform(introduction);
		userContext.getChecker().checkCurrentVersionOfPlatform(currentVersion);
	
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);


		Platform platform=createNewPlatform();	

		platform.setName(name);
		platform.setIntroduction(introduction);
		platform.setCurrentVersion(currentVersion);

		platform = savePlatform(userContext, platform, emptyOptions());
		
		onNewInstanceCreated(userContext, platform);
		return platform;

		
	}
	protected Platform createNewPlatform() 
	{
		
		return new Platform();		
	}
	
	protected void checkParamsForUpdatingPlatform(MessagecenterUserContext userContext,String platformId, int platformVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		userContext.getChecker().checkIdOfPlatform(platformId);
		userContext.getChecker().checkVersionOfPlatform( platformVersion);
		

		if(Platform.NAME_PROPERTY.equals(property)){
			userContext.getChecker().checkNameOfPlatform(parseString(newValueExpr));
		}
		if(Platform.INTRODUCTION_PROPERTY.equals(property)){
			userContext.getChecker().checkIntroductionOfPlatform(parseString(newValueExpr));
		}
		if(Platform.CURRENT_VERSION_PROPERTY.equals(property)){
			userContext.getChecker().checkCurrentVersionOfPlatform(parseString(newValueExpr));
		}
	
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
	
		
	}
	
	
	
	public Platform clone(MessagecenterUserContext userContext, String fromPlatformId) throws Exception{
		
		return userContext.getDAOGroup().getPlatformDAO().clone(fromPlatformId, this.allTokens());
	}
	
	public Platform internalSavePlatform(MessagecenterUserContext userContext, Platform platform) throws Exception 
	{
		return internalSavePlatform(userContext, platform, allTokens());

	}
	public Platform internalSavePlatform(MessagecenterUserContext userContext, Platform platform, Map<String,Object> options) throws Exception 
	{
		//checkParamsForUpdatingPlatform(userContext, platformId, platformVersion, property, newValueExpr, tokensExpr);
		
		
		synchronized(platform){ 
			//will be good when the platform loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Platform.
			if (platform.isChanged()){
			
			}
			platform = savePlatform(userContext, platform, options);
			return platform;
			
		}

	}
	
	public Platform updatePlatform(MessagecenterUserContext userContext,String platformId, int platformVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingPlatform(userContext, platformId, platformVersion, property, newValueExpr, tokensExpr);
		
		
		
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		if(platform.getVersion() != platformVersion){
			String message = "The target version("+platform.getVersion()+") is not equals to version("+platformVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(platform){ 
			//will be good when the platform loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Platform.
			
			platform.changeProperty(property, newValueExpr);
			platform = savePlatform(userContext, platform, tokens().done());
			return present(userContext,platform, mergedAllTokens(tokensExpr));
			//return savePlatform(userContext, platform, tokens().done());
		}

	}
	
	public Platform updatePlatformProperty(MessagecenterUserContext userContext,String platformId, int platformVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingPlatform(userContext, platformId, platformVersion, property, newValueExpr, tokensExpr);
		
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		if(platform.getVersion() != platformVersion){
			String message = "The target version("+platform.getVersion()+") is not equals to version("+platformVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(platform){ 
			//will be good when the platform loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Platform.
			
			platform.changeProperty(property, newValueExpr);
			
			platform = savePlatform(userContext, platform, tokens().done());
			return present(userContext,platform, mergedAllTokens(tokensExpr));
			//return savePlatform(userContext, platform, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}
	
	protected PlatformTokens tokens(){
		return PlatformTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return PlatformTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortProfileListWith("id","desc")
		.sortPrivateMessageListWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return PlatformTokens.mergeAll(tokens).done();
	}
	
//--------------------------------------------------------------
	
	//--------------------------------------------------------------

	public void delete(MessagecenterUserContext userContext, String platformId, int platformVersion) throws Exception {
		//deleteInternal(userContext, platformId, platformVersion);		
	}
	protected void deleteInternal(MessagecenterUserContext userContext,
			String platformId, int platformVersion) throws Exception{
			
		userContext.getDAOGroup().getPlatformDAO().delete(platformId, platformVersion);
	}
	
	public Platform forgetByAll(MessagecenterUserContext userContext, String platformId, int platformVersion) throws Exception {
		return forgetByAllInternal(userContext, platformId, platformVersion);		
	}
	protected Platform forgetByAllInternal(MessagecenterUserContext userContext,
			String platformId, int platformVersion) throws Exception{
			
		return userContext.getDAOGroup().getPlatformDAO().disconnectFromAll(platformId, platformVersion);
	}
	

	
	public int deleteAll(MessagecenterUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new PlatformManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}
	
	
	protected int deleteAllInternal(MessagecenterUserContext userContext) throws Exception{
		return userContext.getDAOGroup().getPlatformDAO().deleteAll();
	}


	//disconnect Platform with sender in PrivateMessage
	protected Platform breakWithPrivateMessageBySender(MessagecenterUserContext userContext, String platformId, String senderId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			Platform platform = loadPlatform(userContext, platformId, allTokens());

			synchronized(platform){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				userContext.getDAOGroup().getPlatformDAO().planToRemovePrivateMessageListWithSender(platform, senderId, this.emptyOptions());

				platform = savePlatform(userContext, platform, tokens().withPrivateMessageList().done());
				return platform;
			}
	}
	//disconnect Platform with receiver in PrivateMessage
	protected Platform breakWithPrivateMessageByReceiver(MessagecenterUserContext userContext, String platformId, String receiverId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			Platform platform = loadPlatform(userContext, platformId, allTokens());

			synchronized(platform){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				userContext.getDAOGroup().getPlatformDAO().planToRemovePrivateMessageListWithReceiver(platform, receiverId, this.emptyOptions());

				platform = savePlatform(userContext, platform, tokens().withPrivateMessageList().done());
				return platform;
			}
	}
	
	
	
	
	

	protected void checkParamsForAddingProfile(MessagecenterUserContext userContext, String platformId, String name,String [] tokensExpr) throws Exception{
		
		

		
		
		userContext.getChecker().checkIdOfPlatform(platformId);

		
		userContext.getChecker().checkNameOfProfile(name);
	
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);

	
	}
	public  Platform addProfile(MessagecenterUserContext userContext, String platformId, String name, String [] tokensExpr) throws Exception
	{	
		
		checkParamsForAddingProfile(userContext,platformId,name,tokensExpr);
		
		Profile profile = createProfile(userContext,name);
		
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){ 
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.addProfile( profile );		
			platform = savePlatform(userContext, platform, tokens().withProfileList().done());
			
			userContext.getManagerGroup().getProfileManager().onNewInstanceCreated(userContext, profile);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingProfileProperties(MessagecenterUserContext userContext, String platformId,String id,String name,String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfPlatform(platformId);
		userContext.getChecker().checkIdOfProfile(id);
		
		userContext.getChecker().checkNameOfProfile( name);

		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
		
	}
	public  Platform updateProfileProperties(MessagecenterUserContext userContext, String platformId, String id,String name, String [] tokensExpr) throws Exception
	{	
		checkParamsForUpdatingProfileProperties(userContext,platformId,id,name,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withProfileListList()
				.searchProfileListWith(Profile.ID_PROPERTY, "is", id).done();
		
		Platform platformToUpdate = loadPlatform(userContext, platformId, options);
		
		if(platformToUpdate.getProfileList().isEmpty()){
			throw new PlatformManagerException("Profile is NOT FOUND with id: '"+id+"'");
		}
		
		Profile item = platformToUpdate.getProfileList().first();
		
		item.updateName( name );

		
		//checkParamsForAddingProfile(userContext,platformId,name, code, used,tokensExpr);
		Platform platform = savePlatform(userContext, platformToUpdate, tokens().withProfileList().done());
		synchronized(platform){ 
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}
	
	
	protected Profile createProfile(MessagecenterUserContext userContext, String name) throws Exception{

		Profile profile = new Profile();
		
		
		profile.setName(name);
	
		
		return profile;
	
		
	}
	
	protected Profile createIndexedProfile(String id, int version){

		Profile profile = new Profile();
		profile.setId(id);
		profile.setVersion(version);
		return profile;			
		
	}
	
	protected void checkParamsForRemovingProfileList(MessagecenterUserContext userContext, String platformId, 
			String profileIds[],String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfPlatform(platformId);
		for(String profileIdItem: profileIds){
			userContext.getChecker().checkIdOfProfile(profileIdItem);
		}
		
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
		
	}
	public  Platform removeProfileList(MessagecenterUserContext userContext, String platformId, 
			String profileIds[],String [] tokensExpr) throws Exception{
			
			checkParamsForRemovingProfileList(userContext, platformId,  profileIds, tokensExpr);
			
			
			Platform platform = loadPlatform(userContext, platformId, allTokens());
			synchronized(platform){ 
				//Will be good when the platform loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				userContext.getDAOGroup().getPlatformDAO().planToRemoveProfileList(platform, profileIds, allTokens());
				platform = savePlatform(userContext, platform, tokens().withProfileList().done());
				deleteRelationListInGraph(userContext, platform.getProfileList());
				return present(userContext,platform, mergedAllTokens(tokensExpr));
			}
	}
	
	protected void checkParamsForRemovingProfile(MessagecenterUserContext userContext, String platformId, 
		String profileId, int profileVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfPlatform( platformId);
		userContext.getChecker().checkIdOfProfile(profileId);
		userContext.getChecker().checkVersionOfProfile(profileVersion);
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
	
	}
	public  Platform removeProfile(MessagecenterUserContext userContext, String platformId, 
		String profileId, int profileVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForRemovingProfile(userContext,platformId, profileId, profileVersion,tokensExpr);
		
		Profile profile = createIndexedProfile(profileId, profileVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){ 
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.removeProfile( profile );		
			platform = savePlatform(userContext, platform, tokens().withProfileList().done());
			deleteRelationInGraph(userContext, profile);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
		
		
	}
	protected void checkParamsForCopyingProfile(MessagecenterUserContext userContext, String platformId, 
		String profileId, int profileVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfPlatform( platformId);
		userContext.getChecker().checkIdOfProfile(profileId);
		userContext.getChecker().checkVersionOfProfile(profileVersion);
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
	
	}
	public  Platform copyProfileFrom(MessagecenterUserContext userContext, String platformId, 
		String profileId, int profileVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForCopyingProfile(userContext,platformId, profileId, profileVersion,tokensExpr);
		
		Profile profile = createIndexedProfile(profileId, profileVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){ 
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			
			
			
			platform.copyProfileFrom( profile );		
			platform = savePlatform(userContext, platform, tokens().withProfileList().done());
			
			userContext.getManagerGroup().getProfileManager().onNewInstanceCreated(userContext, (Profile)platform.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
		
	}
	
	protected void checkParamsForUpdatingProfile(MessagecenterUserContext userContext, String platformId, String profileId, int profileVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		userContext.getChecker().checkIdOfPlatform(platformId);
		userContext.getChecker().checkIdOfProfile(profileId);
		userContext.getChecker().checkVersionOfProfile(profileVersion);
		

		if(Profile.NAME_PROPERTY.equals(property)){
			userContext.getChecker().checkNameOfProfile(parseString(newValueExpr));
		}
		
	
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
	
	}
	
	public  Platform updateProfile(MessagecenterUserContext userContext, String platformId, String profileId, int profileVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{
		
		checkParamsForUpdatingProfile(userContext, platformId, profileId, profileVersion, property, newValueExpr,  tokensExpr);
		
		Map<String,Object> loadTokens = this.tokens().withProfileList().searchProfileListWith(Profile.ID_PROPERTY, "eq", profileId).done();
		
		
		
		Platform platform = loadPlatform(userContext, platformId, loadTokens);
		
		synchronized(platform){ 
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//platform.removeProfile( profile );	
			//make changes to AcceleraterAccount.
			Profile profileIndex = createIndexedProfile(profileId, profileVersion);
		
			Profile profile = platform.findTheProfile(profileIndex);
			if(profile == null){
				throw new PlatformManagerException(profile+" is NOT FOUND" );
			}
			
			profile.changeProperty(property, newValueExpr);
			
			platform = savePlatform(userContext, platform, tokens().withProfileList().done());
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/
	



	protected void checkParamsForAddingPrivateMessage(MessagecenterUserContext userContext, String platformId, String title, String content, String senderId, String receiverId, String status,String [] tokensExpr) throws Exception{
		
		

		
		
		userContext.getChecker().checkIdOfPlatform(platformId);

		
		userContext.getChecker().checkTitleOfPrivateMessage(title);
		
		userContext.getChecker().checkContentOfPrivateMessage(content);
		
		userContext.getChecker().checkSenderIdOfPrivateMessage(senderId);
		
		userContext.getChecker().checkReceiverIdOfPrivateMessage(receiverId);
		
		userContext.getChecker().checkStatusOfPrivateMessage(status);
	
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);

	
	}
	public  Platform addPrivateMessage(MessagecenterUserContext userContext, String platformId, String title, String content, String senderId, String receiverId, String status, String [] tokensExpr) throws Exception
	{	
		
		checkParamsForAddingPrivateMessage(userContext,platformId,title, content, senderId, receiverId, status,tokensExpr);
		
		PrivateMessage privateMessage = createPrivateMessage(userContext,title, content, senderId, receiverId, status);
		
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){ 
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.addPrivateMessage( privateMessage );		
			platform = savePlatform(userContext, platform, tokens().withPrivateMessageList().done());
			
			userContext.getManagerGroup().getPrivateMessageManager().onNewInstanceCreated(userContext, privateMessage);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingPrivateMessageProperties(MessagecenterUserContext userContext, String platformId,String id,String title,String content,String status,String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfPlatform(platformId);
		userContext.getChecker().checkIdOfPrivateMessage(id);
		
		userContext.getChecker().checkTitleOfPrivateMessage( title);
		userContext.getChecker().checkContentOfPrivateMessage( content);
		userContext.getChecker().checkStatusOfPrivateMessage( status);

		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
		
	}
	public  Platform updatePrivateMessageProperties(MessagecenterUserContext userContext, String platformId, String id,String title,String content,String status, String [] tokensExpr) throws Exception
	{	
		checkParamsForUpdatingPrivateMessageProperties(userContext,platformId,id,title,content,status,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withPrivateMessageListList()
				.searchPrivateMessageListWith(PrivateMessage.ID_PROPERTY, "is", id).done();
		
		Platform platformToUpdate = loadPlatform(userContext, platformId, options);
		
		if(platformToUpdate.getPrivateMessageList().isEmpty()){
			throw new PlatformManagerException("PrivateMessage is NOT FOUND with id: '"+id+"'");
		}
		
		PrivateMessage item = platformToUpdate.getPrivateMessageList().first();
		
		item.updateTitle( title );
		item.updateContent( content );
		item.updateStatus( status );

		
		//checkParamsForAddingPrivateMessage(userContext,platformId,name, code, used,tokensExpr);
		Platform platform = savePlatform(userContext, platformToUpdate, tokens().withPrivateMessageList().done());
		synchronized(platform){ 
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
	}
	
	
	protected PrivateMessage createPrivateMessage(MessagecenterUserContext userContext, String title, String content, String senderId, String receiverId, String status) throws Exception{

		PrivateMessage privateMessage = new PrivateMessage();
		
		
		privateMessage.setTitle(title);		
		privateMessage.setContent(content);		
		privateMessage.setSendTime(userContext.now());		
		privateMessage.setReadTime(userContext.now());		
		Profile  sender = new Profile();
		sender.setId(senderId);		
		privateMessage.setSender(sender);		
		Profile  receiver = new Profile();
		receiver.setId(receiverId);		
		privateMessage.setReceiver(receiver);		
		privateMessage.setStatus(status);
	
		
		return privateMessage;
	
		
	}
	
	protected PrivateMessage createIndexedPrivateMessage(String id, int version){

		PrivateMessage privateMessage = new PrivateMessage();
		privateMessage.setId(id);
		privateMessage.setVersion(version);
		return privateMessage;			
		
	}
	
	protected void checkParamsForRemovingPrivateMessageList(MessagecenterUserContext userContext, String platformId, 
			String privateMessageIds[],String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfPlatform(platformId);
		for(String privateMessageIdItem: privateMessageIds){
			userContext.getChecker().checkIdOfPrivateMessage(privateMessageIdItem);
		}
		
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
		
	}
	public  Platform removePrivateMessageList(MessagecenterUserContext userContext, String platformId, 
			String privateMessageIds[],String [] tokensExpr) throws Exception{
			
			checkParamsForRemovingPrivateMessageList(userContext, platformId,  privateMessageIds, tokensExpr);
			
			
			Platform platform = loadPlatform(userContext, platformId, allTokens());
			synchronized(platform){ 
				//Will be good when the platform loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				userContext.getDAOGroup().getPlatformDAO().planToRemovePrivateMessageList(platform, privateMessageIds, allTokens());
				platform = savePlatform(userContext, platform, tokens().withPrivateMessageList().done());
				deleteRelationListInGraph(userContext, platform.getPrivateMessageList());
				return present(userContext,platform, mergedAllTokens(tokensExpr));
			}
	}
	
	protected void checkParamsForRemovingPrivateMessage(MessagecenterUserContext userContext, String platformId, 
		String privateMessageId, int privateMessageVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfPlatform( platformId);
		userContext.getChecker().checkIdOfPrivateMessage(privateMessageId);
		userContext.getChecker().checkVersionOfPrivateMessage(privateMessageVersion);
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
	
	}
	public  Platform removePrivateMessage(MessagecenterUserContext userContext, String platformId, 
		String privateMessageId, int privateMessageVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForRemovingPrivateMessage(userContext,platformId, privateMessageId, privateMessageVersion,tokensExpr);
		
		PrivateMessage privateMessage = createIndexedPrivateMessage(privateMessageId, privateMessageVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){ 
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			platform.removePrivateMessage( privateMessage );		
			platform = savePlatform(userContext, platform, tokens().withPrivateMessageList().done());
			deleteRelationInGraph(userContext, privateMessage);
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
		
		
	}
	protected void checkParamsForCopyingPrivateMessage(MessagecenterUserContext userContext, String platformId, 
		String privateMessageId, int privateMessageVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfPlatform( platformId);
		userContext.getChecker().checkIdOfPrivateMessage(privateMessageId);
		userContext.getChecker().checkVersionOfPrivateMessage(privateMessageVersion);
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
	
	}
	public  Platform copyPrivateMessageFrom(MessagecenterUserContext userContext, String platformId, 
		String privateMessageId, int privateMessageVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForCopyingPrivateMessage(userContext,platformId, privateMessageId, privateMessageVersion,tokensExpr);
		
		PrivateMessage privateMessage = createIndexedPrivateMessage(privateMessageId, privateMessageVersion);
		Platform platform = loadPlatform(userContext, platformId, allTokens());
		synchronized(platform){ 
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			
			privateMessage.updateReadTime(userContext.now());
			
			platform.copyPrivateMessageFrom( privateMessage );		
			platform = savePlatform(userContext, platform, tokens().withPrivateMessageList().done());
			
			userContext.getManagerGroup().getPrivateMessageManager().onNewInstanceCreated(userContext, (PrivateMessage)platform.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}
		
	}
	
	protected void checkParamsForUpdatingPrivateMessage(MessagecenterUserContext userContext, String platformId, String privateMessageId, int privateMessageVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		userContext.getChecker().checkIdOfPlatform(platformId);
		userContext.getChecker().checkIdOfPrivateMessage(privateMessageId);
		userContext.getChecker().checkVersionOfPrivateMessage(privateMessageVersion);
		

		if(PrivateMessage.TITLE_PROPERTY.equals(property)){
			userContext.getChecker().checkTitleOfPrivateMessage(parseString(newValueExpr));
		}
		
		if(PrivateMessage.CONTENT_PROPERTY.equals(property)){
			userContext.getChecker().checkContentOfPrivateMessage(parseString(newValueExpr));
		}
		
		if(PrivateMessage.STATUS_PROPERTY.equals(property)){
			userContext.getChecker().checkStatusOfPrivateMessage(parseString(newValueExpr));
		}
		
	
		userContext.getChecker().throwExceptionIfHasErrors(PlatformManagerException.class);
	
	}
	
	public  Platform updatePrivateMessage(MessagecenterUserContext userContext, String platformId, String privateMessageId, int privateMessageVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{
		
		checkParamsForUpdatingPrivateMessage(userContext, platformId, privateMessageId, privateMessageVersion, property, newValueExpr,  tokensExpr);
		
		Map<String,Object> loadTokens = this.tokens().withPrivateMessageList().searchPrivateMessageListWith(PrivateMessage.ID_PROPERTY, "eq", privateMessageId).done();
		
		
		
		Platform platform = loadPlatform(userContext, platformId, loadTokens);
		
		synchronized(platform){ 
			//Will be good when the platform loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//platform.removePrivateMessage( privateMessage );	
			//make changes to AcceleraterAccount.
			PrivateMessage privateMessageIndex = createIndexedPrivateMessage(privateMessageId, privateMessageVersion);
		
			PrivateMessage privateMessage = platform.findThePrivateMessage(privateMessageIndex);
			if(privateMessage == null){
				throw new PlatformManagerException(privateMessage+" is NOT FOUND" );
			}
			
			privateMessage.changeProperty(property, newValueExpr);
			privateMessage.updateReadTime(userContext.now());
			platform = savePlatform(userContext, platform, tokens().withPrivateMessageList().done());
			return present(userContext,platform, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/
	



	public void onNewInstanceCreated(MessagecenterUserContext userContext, Platform newCreated) throws Exception{
		ensureRelationInGraph(userContext, newCreated);
		sendCreationEvent(userContext, newCreated);
	}

}


