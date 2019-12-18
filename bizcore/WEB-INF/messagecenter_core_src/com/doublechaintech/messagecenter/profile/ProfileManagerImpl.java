
package com.doublechaintech.messagecenter.profile;

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

import com.doublechaintech.messagecenter.platform.Platform;
import com.doublechaintech.messagecenter.privatemessage.PrivateMessage;

import com.doublechaintech.messagecenter.platform.CandidatePlatform;

import com.doublechaintech.messagecenter.profile.Profile;
import com.doublechaintech.messagecenter.platform.Platform;






public class ProfileManagerImpl extends CustomMessagecenterCheckerManager implements ProfileManager {
	
	private static final String SERVICE_TYPE = "Profile";
	
	@Override
	public String serviceFor(){
		return SERVICE_TYPE;
	}
	
	
	protected void throwExceptionWithMessage(String value) throws ProfileManagerException{
	
		Message message = new Message();
		message.setBody(value);
		throw new ProfileManagerException(message);

	}
	
	

 	protected Profile saveProfile(MessagecenterUserContext userContext, Profile profile, String [] tokensExpr) throws Exception{	
 		//return getProfileDAO().save(profile, tokens);
 		
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		return saveProfile(userContext, profile, tokens);
 	}
 	
 	protected Profile saveProfileDetail(MessagecenterUserContext userContext, Profile profile) throws Exception{	

 		
 		return saveProfile(userContext, profile, allTokens());
 	}
 	
 	public Profile loadProfile(MessagecenterUserContext userContext, String profileId, String [] tokensExpr) throws Exception{				
 
 		userContext.getChecker().checkIdOfProfile(profileId);
		userContext.getChecker().throwExceptionIfHasErrors( ProfileManagerException.class);

 			
 		Map<String,Object>tokens = parseTokens(tokensExpr);
 		
 		Profile profile = loadProfile( userContext, profileId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,profile, tokens);
 	}
 	
 	
 	 public Profile searchProfile(MessagecenterUserContext userContext, String profileId, String textToSearch,String [] tokensExpr) throws Exception{				
 
 		userContext.getChecker().checkIdOfProfile(profileId);
		userContext.getChecker().throwExceptionIfHasErrors( ProfileManagerException.class);

 		
 		Map<String,Object>tokens = tokens().allTokens().searchEntireObjectText("startsWith", textToSearch).initWithArray(tokensExpr);
 		
 		Profile profile = loadProfile( userContext, profileId, tokens);
 		//do some calc before sent to customer?
 		return present(userContext,profile, tokens);
 	}
 	
 	

 	protected Profile present(MessagecenterUserContext userContext, Profile profile, Map<String, Object> tokens) throws Exception {
		
		
		addActions(userContext,profile,tokens);
		
		
		Profile  profileToPresent = userContext.getDAOGroup().getProfileDAO().present(profile, tokens);
		
		List<BaseEntity> entityListToNaming = profileToPresent.collectRefercencesFromLists();
		userContext.getDAOGroup().getProfileDAO().alias(entityListToNaming);
		
		return  profileToPresent;
		
		
	}
 
 	
 	
 	public Profile loadProfileDetail(MessagecenterUserContext userContext, String profileId) throws Exception{	
 		Profile profile = loadProfile( userContext, profileId, allTokens());
 		return present(userContext,profile, allTokens());
		
 	}
 	
 	public Object view(MessagecenterUserContext userContext, String profileId) throws Exception{	
 		Profile profile = loadProfile( userContext, profileId, viewTokens());
 		return present(userContext,profile, allTokens());
		
 	}
 	protected Profile saveProfile(MessagecenterUserContext userContext, Profile profile, Map<String,Object>tokens) throws Exception{	
 		return userContext.getDAOGroup().getProfileDAO().save(profile, tokens);
 	}
 	protected Profile loadProfile(MessagecenterUserContext userContext, String profileId, Map<String,Object>tokens) throws Exception{	
		userContext.getChecker().checkIdOfProfile(profileId);
		userContext.getChecker().throwExceptionIfHasErrors( ProfileManagerException.class);

 
 		return userContext.getDAOGroup().getProfileDAO().load(profileId, tokens);
 	}

	


 	


 	
 	
 	protected<T extends BaseEntity> void addActions(MessagecenterUserContext userContext, Profile profile, Map<String, Object> tokens){
		super.addActions(userContext, profile, tokens);
		
		addAction(userContext, profile, tokens,"@create","createProfile","createProfile/","main","primary");
		addAction(userContext, profile, tokens,"@update","updateProfile","updateProfile/"+profile.getId()+"/","main","primary");
		addAction(userContext, profile, tokens,"@copy","cloneProfile","cloneProfile/"+profile.getId()+"/","main","primary");
		
		addAction(userContext, profile, tokens,"profile.transfer_to_platform","transferToAnotherPlatform","transferToAnotherPlatform/"+profile.getId()+"/","main","primary");
		addAction(userContext, profile, tokens,"profile.addPrivateMessage","addPrivateMessage","addPrivateMessage/"+profile.getId()+"/","privateMessageListAsSender","primary");
		addAction(userContext, profile, tokens,"profile.removePrivateMessage","removePrivateMessage","removePrivateMessage/"+profile.getId()+"/","privateMessageListAsSender","primary");
		addAction(userContext, profile, tokens,"profile.updatePrivateMessage","updatePrivateMessage","updatePrivateMessage/"+profile.getId()+"/","privateMessageListAsSender","primary");
		addAction(userContext, profile, tokens,"profile.copyPrivateMessageFrom","copyPrivateMessageFrom","copyPrivateMessageFrom/"+profile.getId()+"/","privateMessageListAsSender","primary");
		addAction(userContext, profile, tokens,"profile.addPrivateMessage","addPrivateMessage","addPrivateMessage/"+profile.getId()+"/","privateMessageListAsReceiver","primary");
		addAction(userContext, profile, tokens,"profile.removePrivateMessage","removePrivateMessage","removePrivateMessage/"+profile.getId()+"/","privateMessageListAsReceiver","primary");
		addAction(userContext, profile, tokens,"profile.updatePrivateMessage","updatePrivateMessage","updatePrivateMessage/"+profile.getId()+"/","privateMessageListAsReceiver","primary");
		addAction(userContext, profile, tokens,"profile.copyPrivateMessageFrom","copyPrivateMessageFrom","copyPrivateMessageFrom/"+profile.getId()+"/","privateMessageListAsReceiver","primary");
	
		
		
	}// end method of protected<T extends BaseEntity> void addActions(MessagecenterUserContext userContext, Profile profile, Map<String, Object> tokens){
	
 	
 	
 
 	
 	


	public Profile createProfile(MessagecenterUserContext userContext,String name, String platformId) throws Exception
	{
		
		

		

		userContext.getChecker().checkNameOfProfile(name);
	
		userContext.getChecker().throwExceptionIfHasErrors(ProfileManagerException.class);


		Profile profile=createNewProfile();	

		profile.setName(name);
			
		Platform platform = loadPlatform(userContext, platformId,emptyOptions());
		profile.setPlatform(platform);
		
		

		profile = saveProfile(userContext, profile, emptyOptions());
		
		onNewInstanceCreated(userContext, profile);
		return profile;

		
	}
	protected Profile createNewProfile() 
	{
		
		return new Profile();		
	}
	
	protected void checkParamsForUpdatingProfile(MessagecenterUserContext userContext,String profileId, int profileVersion, String property, String newValueExpr,String [] tokensExpr)throws Exception
	{
		

		
		
		userContext.getChecker().checkIdOfProfile(profileId);
		userContext.getChecker().checkVersionOfProfile( profileVersion);
		

		if(Profile.NAME_PROPERTY.equals(property)){
			userContext.getChecker().checkNameOfProfile(parseString(newValueExpr));
		}		

		
	
		userContext.getChecker().throwExceptionIfHasErrors(ProfileManagerException.class);
	
		
	}
	
	
	
	public Profile clone(MessagecenterUserContext userContext, String fromProfileId) throws Exception{
		
		return userContext.getDAOGroup().getProfileDAO().clone(fromProfileId, this.allTokens());
	}
	
	public Profile internalSaveProfile(MessagecenterUserContext userContext, Profile profile) throws Exception 
	{
		return internalSaveProfile(userContext, profile, allTokens());

	}
	public Profile internalSaveProfile(MessagecenterUserContext userContext, Profile profile, Map<String,Object> options) throws Exception 
	{
		//checkParamsForUpdatingProfile(userContext, profileId, profileVersion, property, newValueExpr, tokensExpr);
		
		
		synchronized(profile){ 
			//will be good when the profile loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Profile.
			if (profile.isChanged()){
			
			}
			profile = saveProfile(userContext, profile, options);
			return profile;
			
		}

	}
	
	public Profile updateProfile(MessagecenterUserContext userContext,String profileId, int profileVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingProfile(userContext, profileId, profileVersion, property, newValueExpr, tokensExpr);
		
		
		
		Profile profile = loadProfile(userContext, profileId, allTokens());
		if(profile.getVersion() != profileVersion){
			String message = "The target version("+profile.getVersion()+") is not equals to version("+profileVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(profile){ 
			//will be good when the profile loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Profile.
			
			profile.changeProperty(property, newValueExpr);
			profile = saveProfile(userContext, profile, tokens().done());
			return present(userContext,profile, mergedAllTokens(tokensExpr));
			//return saveProfile(userContext, profile, tokens().done());
		}

	}
	
	public Profile updateProfileProperty(MessagecenterUserContext userContext,String profileId, int profileVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception 
	{
		checkParamsForUpdatingProfile(userContext, profileId, profileVersion, property, newValueExpr, tokensExpr);
		
		Profile profile = loadProfile(userContext, profileId, allTokens());
		if(profile.getVersion() != profileVersion){
			String message = "The target version("+profile.getVersion()+") is not equals to version("+profileVersion+") provided";
			throwExceptionWithMessage(message);
		}
		synchronized(profile){ 
			//will be good when the profile loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			//make changes to Profile.
			
			profile.changeProperty(property, newValueExpr);
			
			profile = saveProfile(userContext, profile, tokens().done());
			return present(userContext,profile, mergedAllTokens(tokensExpr));
			//return saveProfile(userContext, profile, tokens().done());
		}

	}
	protected Map<String,Object> emptyOptions(){
		return tokens().done();
	}
	
	protected ProfileTokens tokens(){
		return ProfileTokens.start();
	}
	protected Map<String,Object> parseTokens(String [] tokensExpr){
		return tokens().initWithArray(tokensExpr);
	}
	protected Map<String,Object> allTokens(){
		return ProfileTokens.all();
	}
	protected Map<String,Object> viewTokens(){
		return tokens().allTokens()
		.sortPrivateMessageListAsSenderWith("id","desc")
		.sortPrivateMessageListAsReceiverWith("id","desc")
		.analyzeAllLists().done();

	}
	protected Map<String,Object> mergedAllTokens(String []tokens){
		return ProfileTokens.mergeAll(tokens).done();
	}
	
	protected void checkParamsForTransferingAnotherPlatform(MessagecenterUserContext userContext, String profileId, String anotherPlatformId) throws Exception
 	{
 		
 		userContext.getChecker().checkIdOfProfile(profileId);
 		userContext.getChecker().checkIdOfPlatform(anotherPlatformId);//check for optional reference
 		userContext.getChecker().throwExceptionIfHasErrors(ProfileManagerException.class);
 		
 	}
 	public Profile transferToAnotherPlatform(MessagecenterUserContext userContext, String profileId, String anotherPlatformId) throws Exception
 	{
 		checkParamsForTransferingAnotherPlatform(userContext, profileId,anotherPlatformId);
 
		Profile profile = loadProfile(userContext, profileId, allTokens());	
		synchronized(profile){
			//will be good when the profile loaded from this JVM process cache.
			//also good when there is a ram based DAO implementation
			Platform platform = loadPlatform(userContext, anotherPlatformId, emptyOptions());		
			profile.updatePlatform(platform);		
			profile = saveProfile(userContext, profile, emptyOptions());
			
			return present(userContext,profile, allTokens());
			
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
		SmartList<Platform> candidateList = userContext.getDAOGroup().getPlatformDAO().requestCandidatePlatformForProfile(userContext,ownerClass, id, filterKey, pageNo, pageSize);
		result.setCandidates(candidateList);
		int totalCount = candidateList.getTotalCount();
		result.setTotalPage(Math.max(1, (totalCount + pageSize -1)/pageSize ));
		return result;
	}
 	
 //--------------------------------------------------------------
	
	 	
 	protected Platform loadPlatform(MessagecenterUserContext userContext, String newPlatformId, Map<String,Object> options) throws Exception
 	{
		
 		return userContext.getDAOGroup().getPlatformDAO().load(newPlatformId, options);
 	}
 	
 	
 	
	
	//--------------------------------------------------------------

	public void delete(MessagecenterUserContext userContext, String profileId, int profileVersion) throws Exception {
		//deleteInternal(userContext, profileId, profileVersion);		
	}
	protected void deleteInternal(MessagecenterUserContext userContext,
			String profileId, int profileVersion) throws Exception{
			
		userContext.getDAOGroup().getProfileDAO().delete(profileId, profileVersion);
	}
	
	public Profile forgetByAll(MessagecenterUserContext userContext, String profileId, int profileVersion) throws Exception {
		return forgetByAllInternal(userContext, profileId, profileVersion);		
	}
	protected Profile forgetByAllInternal(MessagecenterUserContext userContext,
			String profileId, int profileVersion) throws Exception{
			
		return userContext.getDAOGroup().getProfileDAO().disconnectFromAll(profileId, profileVersion);
	}
	

	
	public int deleteAll(MessagecenterUserContext userContext, String secureCode) throws Exception
	{
		/*
		if(!("dElEtEaLl".equals(secureCode))){
			throw new ProfileManagerException("Your secure code is not right, please guess again");
		}
		return deleteAllInternal(userContext);
		*/
		return 0;
	}
	
	
	protected int deleteAllInternal(MessagecenterUserContext userContext) throws Exception{
		return userContext.getDAOGroup().getProfileDAO().deleteAll();
	}


	//disconnect Profile with platform in PrivateMessage
	protected Profile breakWithPrivateMessageAsSenderByPlatform(MessagecenterUserContext userContext, String profileId, String platformId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			Profile profile = loadProfile(userContext, profileId, allTokens());

			synchronized(profile){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				userContext.getDAOGroup().getProfileDAO().planToRemovePrivateMessageListAsSenderWithPlatform(profile, platformId, this.emptyOptions());

				profile = saveProfile(userContext, profile, tokens().withPrivateMessageListAsSender().done());
				return profile;
			}
	}
	//disconnect Profile with platform in PrivateMessage
	protected Profile breakWithPrivateMessageAsReceiverByPlatform(MessagecenterUserContext userContext, String profileId, String platformId,  String [] tokensExpr)
		 throws Exception{
			
			//TODO add check code here
			
			Profile profile = loadProfile(userContext, profileId, allTokens());

			synchronized(profile){ 
				//Will be good when the thread loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				
				userContext.getDAOGroup().getProfileDAO().planToRemovePrivateMessageListAsReceiverWithPlatform(profile, platformId, this.emptyOptions());

				profile = saveProfile(userContext, profile, tokens().withPrivateMessageListAsReceiver().done());
				return profile;
			}
	}
	
	
	
	
	

	protected void checkParamsForAddingPrivateMessageAsSender(MessagecenterUserContext userContext, String profileId, String title, String content, String status, String platformId,String [] tokensExpr) throws Exception{
		
		

		
		
		userContext.getChecker().checkIdOfProfile(profileId);

		
		userContext.getChecker().checkTitleOfPrivateMessage(title);
		
		userContext.getChecker().checkContentOfPrivateMessage(content);
		
		userContext.getChecker().checkStatusOfPrivateMessage(status);
		
		userContext.getChecker().checkPlatformIdOfPrivateMessage(platformId);
	
		userContext.getChecker().throwExceptionIfHasErrors(ProfileManagerException.class);

	
	}
	public  Profile addPrivateMessageAsSender(MessagecenterUserContext userContext, String profileId, String title, String content, String status, String platformId, String [] tokensExpr) throws Exception
	{	
		
		checkParamsForAddingPrivateMessageAsSender(userContext,profileId,title, content, status, platformId,tokensExpr);
		
		PrivateMessage privateMessage = createPrivateMessageAsSender(userContext,title, content, status, platformId);
		
		Profile profile = loadProfile(userContext, profileId, allTokens());
		synchronized(profile){ 
			//Will be good when the profile loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			profile.addPrivateMessageAsSender( privateMessage );		
			profile = saveProfile(userContext, profile, tokens().withPrivateMessageListAsSender().done());
			
			userContext.getManagerGroup().getPrivateMessageManager().onNewInstanceCreated(userContext, privateMessage);
			return present(userContext,profile, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingPrivateMessageAsSenderProperties(MessagecenterUserContext userContext, String profileId,String id,String title,String content,String status,String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfProfile(profileId);
		userContext.getChecker().checkIdOfPrivateMessage(id);
		
		userContext.getChecker().checkTitleOfPrivateMessage( title);
		userContext.getChecker().checkContentOfPrivateMessage( content);
		userContext.getChecker().checkStatusOfPrivateMessage( status);

		userContext.getChecker().throwExceptionIfHasErrors(ProfileManagerException.class);
		
	}
	public  Profile updatePrivateMessageAsSenderProperties(MessagecenterUserContext userContext, String profileId, String id,String title,String content,String status, String [] tokensExpr) throws Exception
	{	
		checkParamsForUpdatingPrivateMessageAsSenderProperties(userContext,profileId,id,title,content,status,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withPrivateMessageListAsSenderList()
				.searchPrivateMessageListAsSenderWith(PrivateMessage.ID_PROPERTY, "is", id).done();
		
		Profile profileToUpdate = loadProfile(userContext, profileId, options);
		
		if(profileToUpdate.getPrivateMessageListAsSender().isEmpty()){
			throw new ProfileManagerException("PrivateMessage is NOT FOUND with id: '"+id+"'");
		}
		
		PrivateMessage item = profileToUpdate.getPrivateMessageListAsSender().first();
		
		item.updateTitle( title );
		item.updateContent( content );
		item.updateStatus( status );

		
		//checkParamsForAddingPrivateMessageAsSender(userContext,profileId,name, code, used,tokensExpr);
		Profile profile = saveProfile(userContext, profileToUpdate, tokens().withPrivateMessageListAsSender().done());
		synchronized(profile){ 
			return present(userContext,profile, mergedAllTokens(tokensExpr));
		}
	}
	
	
	protected PrivateMessage createPrivateMessageAsSender(MessagecenterUserContext userContext, String title, String content, String status, String platformId) throws Exception{

		PrivateMessage privateMessage = new PrivateMessage();
		
		
		privateMessage.setTitle(title);		
		privateMessage.setContent(content);		
		privateMessage.setSendTime(userContext.now());		
		privateMessage.setReadTime(userContext.now());		
		privateMessage.setStatus(status);		
		Platform  platform = new Platform();
		platform.setId(platformId);		
		privateMessage.setPlatform(platform);
	
		
		return privateMessage;
	
		
	}
	
	protected PrivateMessage createIndexedPrivateMessageAsSender(String id, int version){

		PrivateMessage privateMessage = new PrivateMessage();
		privateMessage.setId(id);
		privateMessage.setVersion(version);
		return privateMessage;			
		
	}
	
	protected void checkParamsForRemovingPrivateMessageListAsSender(MessagecenterUserContext userContext, String profileId, 
			String privateMessageIds[],String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfProfile(profileId);
		for(String privateMessageIdItem: privateMessageIds){
			userContext.getChecker().checkIdOfPrivateMessage(privateMessageIdItem);
		}
		
		userContext.getChecker().throwExceptionIfHasErrors(ProfileManagerException.class);
		
	}
	public  Profile removePrivateMessageListAsSender(MessagecenterUserContext userContext, String profileId, 
			String privateMessageIds[],String [] tokensExpr) throws Exception{
			
			checkParamsForRemovingPrivateMessageListAsSender(userContext, profileId,  privateMessageIds, tokensExpr);
			
			
			Profile profile = loadProfile(userContext, profileId, allTokens());
			synchronized(profile){ 
				//Will be good when the profile loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				userContext.getDAOGroup().getProfileDAO().planToRemovePrivateMessageListAsSender(profile, privateMessageIds, allTokens());
				profile = saveProfile(userContext, profile, tokens().withPrivateMessageListAsSender().done());
				deleteRelationListInGraph(userContext, profile.getPrivateMessageListAsSender());
				return present(userContext,profile, mergedAllTokens(tokensExpr));
			}
	}
	
	protected void checkParamsForRemovingPrivateMessageAsSender(MessagecenterUserContext userContext, String profileId, 
		String privateMessageId, int privateMessageVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfProfile( profileId);
		userContext.getChecker().checkIdOfPrivateMessage(privateMessageId);
		userContext.getChecker().checkVersionOfPrivateMessage(privateMessageVersion);
		userContext.getChecker().throwExceptionIfHasErrors(ProfileManagerException.class);
	
	}
	public  Profile removePrivateMessageAsSender(MessagecenterUserContext userContext, String profileId, 
		String privateMessageId, int privateMessageVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForRemovingPrivateMessageAsSender(userContext,profileId, privateMessageId, privateMessageVersion,tokensExpr);
		
		PrivateMessage privateMessage = createIndexedPrivateMessageAsSender(privateMessageId, privateMessageVersion);
		Profile profile = loadProfile(userContext, profileId, allTokens());
		synchronized(profile){ 
			//Will be good when the profile loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			profile.removePrivateMessageAsSender( privateMessage );		
			profile = saveProfile(userContext, profile, tokens().withPrivateMessageListAsSender().done());
			deleteRelationInGraph(userContext, privateMessage);
			return present(userContext,profile, mergedAllTokens(tokensExpr));
		}
		
		
	}
	protected void checkParamsForCopyingPrivateMessageAsSender(MessagecenterUserContext userContext, String profileId, 
		String privateMessageId, int privateMessageVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfProfile( profileId);
		userContext.getChecker().checkIdOfPrivateMessage(privateMessageId);
		userContext.getChecker().checkVersionOfPrivateMessage(privateMessageVersion);
		userContext.getChecker().throwExceptionIfHasErrors(ProfileManagerException.class);
	
	}
	public  Profile copyPrivateMessageAsSenderFrom(MessagecenterUserContext userContext, String profileId, 
		String privateMessageId, int privateMessageVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForCopyingPrivateMessageAsSender(userContext,profileId, privateMessageId, privateMessageVersion,tokensExpr);
		
		PrivateMessage privateMessageAsSender = createIndexedPrivateMessageAsSender(privateMessageId, privateMessageVersion);
		Profile profile = loadProfile(userContext, profileId, allTokens());
		synchronized(profile){ 
			//Will be good when the profile loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			
			privateMessageAsSender.updateReadTime(userContext.now());
			
			profile.copyPrivateMessageAsSenderFrom( privateMessageAsSender );		
			profile = saveProfile(userContext, profile, tokens().withPrivateMessageListAsSender().done());
			
			userContext.getManagerGroup().getPrivateMessageManager().onNewInstanceCreated(userContext, (PrivateMessage)profile.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,profile, mergedAllTokens(tokensExpr));
		}
		
	}
	
	protected void checkParamsForUpdatingPrivateMessageAsSender(MessagecenterUserContext userContext, String profileId, String privateMessageId, int privateMessageVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		userContext.getChecker().checkIdOfProfile(profileId);
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
		
	
		userContext.getChecker().throwExceptionIfHasErrors(ProfileManagerException.class);
	
	}
	
	public  Profile updatePrivateMessageAsSender(MessagecenterUserContext userContext, String profileId, String privateMessageId, int privateMessageVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{
		
		checkParamsForUpdatingPrivateMessageAsSender(userContext, profileId, privateMessageId, privateMessageVersion, property, newValueExpr,  tokensExpr);
		
		Map<String,Object> loadTokens = this.tokens().withPrivateMessageListAsSender().searchPrivateMessageListAsSenderWith(PrivateMessage.ID_PROPERTY, "eq", privateMessageId).done();
		
		
		
		Profile profile = loadProfile(userContext, profileId, loadTokens);
		
		synchronized(profile){ 
			//Will be good when the profile loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//profile.removePrivateMessageAsSender( privateMessage );	
			//make changes to AcceleraterAccount.
			PrivateMessage privateMessageIndex = createIndexedPrivateMessageAsSender(privateMessageId, privateMessageVersion);
		
			PrivateMessage privateMessageAsSender = profile.findThePrivateMessageAsSender(privateMessageIndex);
			if(privateMessageAsSender == null){
				throw new ProfileManagerException(privateMessageAsSender+" is NOT FOUND" );
			}
			
			privateMessageAsSender.changeProperty(property, newValueExpr);
			privateMessageAsSender.updateReadTime(userContext.now());
			profile = saveProfile(userContext, profile, tokens().withPrivateMessageListAsSender().done());
			return present(userContext,profile, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/
	



	protected void checkParamsForAddingPrivateMessageAsReceiver(MessagecenterUserContext userContext, String profileId, String title, String content, String status, String platformId,String [] tokensExpr) throws Exception{
		
		

		
		
		userContext.getChecker().checkIdOfProfile(profileId);

		
		userContext.getChecker().checkTitleOfPrivateMessage(title);
		
		userContext.getChecker().checkContentOfPrivateMessage(content);
		
		userContext.getChecker().checkStatusOfPrivateMessage(status);
		
		userContext.getChecker().checkPlatformIdOfPrivateMessage(platformId);
	
		userContext.getChecker().throwExceptionIfHasErrors(ProfileManagerException.class);

	
	}
	public  Profile addPrivateMessageAsReceiver(MessagecenterUserContext userContext, String profileId, String title, String content, String status, String platformId, String [] tokensExpr) throws Exception
	{	
		
		checkParamsForAddingPrivateMessageAsReceiver(userContext,profileId,title, content, status, platformId,tokensExpr);
		
		PrivateMessage privateMessage = createPrivateMessageAsReceiver(userContext,title, content, status, platformId);
		
		Profile profile = loadProfile(userContext, profileId, allTokens());
		synchronized(profile){ 
			//Will be good when the profile loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			profile.addPrivateMessageAsReceiver( privateMessage );		
			profile = saveProfile(userContext, profile, tokens().withPrivateMessageListAsReceiver().done());
			
			userContext.getManagerGroup().getPrivateMessageManager().onNewInstanceCreated(userContext, privateMessage);
			return present(userContext,profile, mergedAllTokens(tokensExpr));
		}
	}
	protected void checkParamsForUpdatingPrivateMessageAsReceiverProperties(MessagecenterUserContext userContext, String profileId,String id,String title,String content,String status,String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfProfile(profileId);
		userContext.getChecker().checkIdOfPrivateMessage(id);
		
		userContext.getChecker().checkTitleOfPrivateMessage( title);
		userContext.getChecker().checkContentOfPrivateMessage( content);
		userContext.getChecker().checkStatusOfPrivateMessage( status);

		userContext.getChecker().throwExceptionIfHasErrors(ProfileManagerException.class);
		
	}
	public  Profile updatePrivateMessageAsReceiverProperties(MessagecenterUserContext userContext, String profileId, String id,String title,String content,String status, String [] tokensExpr) throws Exception
	{	
		checkParamsForUpdatingPrivateMessageAsReceiverProperties(userContext,profileId,id,title,content,status,tokensExpr);

		Map<String, Object> options = tokens()
				.allTokens()
				//.withPrivateMessageListAsReceiverList()
				.searchPrivateMessageListAsReceiverWith(PrivateMessage.ID_PROPERTY, "is", id).done();
		
		Profile profileToUpdate = loadProfile(userContext, profileId, options);
		
		if(profileToUpdate.getPrivateMessageListAsReceiver().isEmpty()){
			throw new ProfileManagerException("PrivateMessage is NOT FOUND with id: '"+id+"'");
		}
		
		PrivateMessage item = profileToUpdate.getPrivateMessageListAsReceiver().first();
		
		item.updateTitle( title );
		item.updateContent( content );
		item.updateStatus( status );

		
		//checkParamsForAddingPrivateMessageAsReceiver(userContext,profileId,name, code, used,tokensExpr);
		Profile profile = saveProfile(userContext, profileToUpdate, tokens().withPrivateMessageListAsReceiver().done());
		synchronized(profile){ 
			return present(userContext,profile, mergedAllTokens(tokensExpr));
		}
	}
	
	
	protected PrivateMessage createPrivateMessageAsReceiver(MessagecenterUserContext userContext, String title, String content, String status, String platformId) throws Exception{

		PrivateMessage privateMessage = new PrivateMessage();
		
		
		privateMessage.setTitle(title);		
		privateMessage.setContent(content);		
		privateMessage.setSendTime(userContext.now());		
		privateMessage.setReadTime(userContext.now());		
		privateMessage.setStatus(status);		
		Platform  platform = new Platform();
		platform.setId(platformId);		
		privateMessage.setPlatform(platform);
	
		
		return privateMessage;
	
		
	}
	
	protected PrivateMessage createIndexedPrivateMessageAsReceiver(String id, int version){

		PrivateMessage privateMessage = new PrivateMessage();
		privateMessage.setId(id);
		privateMessage.setVersion(version);
		return privateMessage;			
		
	}
	
	protected void checkParamsForRemovingPrivateMessageListAsReceiver(MessagecenterUserContext userContext, String profileId, 
			String privateMessageIds[],String [] tokensExpr) throws Exception {
		
		userContext.getChecker().checkIdOfProfile(profileId);
		for(String privateMessageIdItem: privateMessageIds){
			userContext.getChecker().checkIdOfPrivateMessage(privateMessageIdItem);
		}
		
		userContext.getChecker().throwExceptionIfHasErrors(ProfileManagerException.class);
		
	}
	public  Profile removePrivateMessageListAsReceiver(MessagecenterUserContext userContext, String profileId, 
			String privateMessageIds[],String [] tokensExpr) throws Exception{
			
			checkParamsForRemovingPrivateMessageListAsReceiver(userContext, profileId,  privateMessageIds, tokensExpr);
			
			
			Profile profile = loadProfile(userContext, profileId, allTokens());
			synchronized(profile){ 
				//Will be good when the profile loaded from this JVM process cache.
				//Also good when there is a RAM based DAO implementation
				userContext.getDAOGroup().getProfileDAO().planToRemovePrivateMessageListAsReceiver(profile, privateMessageIds, allTokens());
				profile = saveProfile(userContext, profile, tokens().withPrivateMessageListAsReceiver().done());
				deleteRelationListInGraph(userContext, profile.getPrivateMessageListAsReceiver());
				return present(userContext,profile, mergedAllTokens(tokensExpr));
			}
	}
	
	protected void checkParamsForRemovingPrivateMessageAsReceiver(MessagecenterUserContext userContext, String profileId, 
		String privateMessageId, int privateMessageVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfProfile( profileId);
		userContext.getChecker().checkIdOfPrivateMessage(privateMessageId);
		userContext.getChecker().checkVersionOfPrivateMessage(privateMessageVersion);
		userContext.getChecker().throwExceptionIfHasErrors(ProfileManagerException.class);
	
	}
	public  Profile removePrivateMessageAsReceiver(MessagecenterUserContext userContext, String profileId, 
		String privateMessageId, int privateMessageVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForRemovingPrivateMessageAsReceiver(userContext,profileId, privateMessageId, privateMessageVersion,tokensExpr);
		
		PrivateMessage privateMessage = createIndexedPrivateMessageAsReceiver(privateMessageId, privateMessageVersion);
		Profile profile = loadProfile(userContext, profileId, allTokens());
		synchronized(profile){ 
			//Will be good when the profile loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			profile.removePrivateMessageAsReceiver( privateMessage );		
			profile = saveProfile(userContext, profile, tokens().withPrivateMessageListAsReceiver().done());
			deleteRelationInGraph(userContext, privateMessage);
			return present(userContext,profile, mergedAllTokens(tokensExpr));
		}
		
		
	}
	protected void checkParamsForCopyingPrivateMessageAsReceiver(MessagecenterUserContext userContext, String profileId, 
		String privateMessageId, int privateMessageVersion,String [] tokensExpr) throws Exception{
		
		userContext.getChecker().checkIdOfProfile( profileId);
		userContext.getChecker().checkIdOfPrivateMessage(privateMessageId);
		userContext.getChecker().checkVersionOfPrivateMessage(privateMessageVersion);
		userContext.getChecker().throwExceptionIfHasErrors(ProfileManagerException.class);
	
	}
	public  Profile copyPrivateMessageAsReceiverFrom(MessagecenterUserContext userContext, String profileId, 
		String privateMessageId, int privateMessageVersion,String [] tokensExpr) throws Exception{
		
		checkParamsForCopyingPrivateMessageAsReceiver(userContext,profileId, privateMessageId, privateMessageVersion,tokensExpr);
		
		PrivateMessage privateMessageAsReceiver = createIndexedPrivateMessageAsReceiver(privateMessageId, privateMessageVersion);
		Profile profile = loadProfile(userContext, profileId, allTokens());
		synchronized(profile){ 
			//Will be good when the profile loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			
			privateMessageAsReceiver.updateReadTime(userContext.now());
			
			profile.copyPrivateMessageAsReceiverFrom( privateMessageAsReceiver );		
			profile = saveProfile(userContext, profile, tokens().withPrivateMessageListAsReceiver().done());
			
			userContext.getManagerGroup().getPrivateMessageManager().onNewInstanceCreated(userContext, (PrivateMessage)profile.getFlexiableObjects().get(BaseEntity.COPIED_CHILD));
			return present(userContext,profile, mergedAllTokens(tokensExpr));
		}
		
	}
	
	protected void checkParamsForUpdatingPrivateMessageAsReceiver(MessagecenterUserContext userContext, String profileId, String privateMessageId, int privateMessageVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception{
		

		
		userContext.getChecker().checkIdOfProfile(profileId);
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
		
	
		userContext.getChecker().throwExceptionIfHasErrors(ProfileManagerException.class);
	
	}
	
	public  Profile updatePrivateMessageAsReceiver(MessagecenterUserContext userContext, String profileId, String privateMessageId, int privateMessageVersion, String property, String newValueExpr,String [] tokensExpr)
			throws Exception{
		
		checkParamsForUpdatingPrivateMessageAsReceiver(userContext, profileId, privateMessageId, privateMessageVersion, property, newValueExpr,  tokensExpr);
		
		Map<String,Object> loadTokens = this.tokens().withPrivateMessageListAsReceiver().searchPrivateMessageListAsReceiverWith(PrivateMessage.ID_PROPERTY, "eq", privateMessageId).done();
		
		
		
		Profile profile = loadProfile(userContext, profileId, loadTokens);
		
		synchronized(profile){ 
			//Will be good when the profile loaded from this JVM process cache.
			//Also good when there is a RAM based DAO implementation
			//profile.removePrivateMessageAsReceiver( privateMessage );	
			//make changes to AcceleraterAccount.
			PrivateMessage privateMessageIndex = createIndexedPrivateMessageAsReceiver(privateMessageId, privateMessageVersion);
		
			PrivateMessage privateMessageAsReceiver = profile.findThePrivateMessageAsReceiver(privateMessageIndex);
			if(privateMessageAsReceiver == null){
				throw new ProfileManagerException(privateMessageAsReceiver+" is NOT FOUND" );
			}
			
			privateMessageAsReceiver.changeProperty(property, newValueExpr);
			privateMessageAsReceiver.updateReadTime(userContext.now());
			profile = saveProfile(userContext, profile, tokens().withPrivateMessageListAsReceiver().done());
			return present(userContext,profile, mergedAllTokens(tokensExpr));
		}

	}
	/*

	*/
	



	public void onNewInstanceCreated(MessagecenterUserContext userContext, Profile newCreated) throws Exception{
		ensureRelationInGraph(userContext, newCreated);
		sendCreationEvent(userContext, newCreated);
	}

}


