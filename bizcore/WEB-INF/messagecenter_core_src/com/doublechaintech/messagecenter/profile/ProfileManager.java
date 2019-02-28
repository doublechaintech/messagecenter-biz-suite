
package com.doublechaintech.messagecenter.profile;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.messagecenter.MessagecenterUserContext;
import com.doublechaintech.messagecenter.BaseEntity;
import com.doublechaintech.messagecenter.SmartList;

public interface ProfileManager{

		

	public Profile createProfile(MessagecenterUserContext userContext, String name, String platformId) throws Exception;	
	public Profile updateProfile(MessagecenterUserContext userContext,String profileId, int profileVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public Profile loadProfile(MessagecenterUserContext userContext, String profileId, String [] tokensExpr) throws Exception;
	public Profile internalSaveProfile(MessagecenterUserContext userContext, Profile profile) throws Exception;
	public Profile internalSaveProfile(MessagecenterUserContext userContext, Profile profile,Map<String,Object>option) throws Exception;
	
	public Profile transferToAnotherPlatform(MessagecenterUserContext userContext, String profileId, String anotherPlatformId)  throws Exception;
 

	public void delete(MessagecenterUserContext userContext, String profileId, int version) throws Exception;
	public int deleteAll(MessagecenterUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(MessagecenterUserContext userContext, Profile newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	

	//public  PrivateMessageManager getPrivateMessageManager(MessagecenterUserContext userContext, String profileId, String title, String content, String status, String platformId ,String [] tokensExpr)  throws Exception;
	
	public  Profile addPrivateMessageAsSender(MessagecenterUserContext userContext, String profileId, String title, String content, String status, String platformId , String [] tokensExpr)  throws Exception;
	public  Profile removePrivateMessageAsSender(MessagecenterUserContext userContext, String profileId, String privateMessageId, int privateMessageVersion,String [] tokensExpr)  throws Exception;
	public  Profile updatePrivateMessageAsSender(MessagecenterUserContext userContext, String profileId, String privateMessageId, int privateMessageVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  PrivateMessageManager getPrivateMessageManager(MessagecenterUserContext userContext, String profileId, String title, String content, String status, String platformId ,String [] tokensExpr)  throws Exception;
	
	public  Profile addPrivateMessageAsReceiver(MessagecenterUserContext userContext, String profileId, String title, String content, String status, String platformId , String [] tokensExpr)  throws Exception;
	public  Profile removePrivateMessageAsReceiver(MessagecenterUserContext userContext, String profileId, String privateMessageId, int privateMessageVersion,String [] tokensExpr)  throws Exception;
	public  Profile updatePrivateMessageAsReceiver(MessagecenterUserContext userContext, String profileId, String privateMessageId, int privateMessageVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


