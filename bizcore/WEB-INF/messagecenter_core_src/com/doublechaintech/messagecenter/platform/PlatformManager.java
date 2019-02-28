
package com.doublechaintech.messagecenter.platform;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.messagecenter.MessagecenterUserContext;
import com.doublechaintech.messagecenter.BaseEntity;
import com.doublechaintech.messagecenter.SmartList;

public interface PlatformManager{

		

	public Platform createPlatform(MessagecenterUserContext userContext, String name, String introduction, String currentVersion) throws Exception;	
	public Platform updatePlatform(MessagecenterUserContext userContext,String platformId, int platformVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public Platform loadPlatform(MessagecenterUserContext userContext, String platformId, String [] tokensExpr) throws Exception;
	public Platform internalSavePlatform(MessagecenterUserContext userContext, Platform platform) throws Exception;
	public Platform internalSavePlatform(MessagecenterUserContext userContext, Platform platform,Map<String,Object>option) throws Exception;
	


	public void delete(MessagecenterUserContext userContext, String platformId, int version) throws Exception;
	public int deleteAll(MessagecenterUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(MessagecenterUserContext userContext, Platform newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	

	//public  ProfileManager getProfileManager(MessagecenterUserContext userContext, String platformId, String name ,String [] tokensExpr)  throws Exception;
	
	public  Platform addProfile(MessagecenterUserContext userContext, String platformId, String name , String [] tokensExpr)  throws Exception;
	public  Platform removeProfile(MessagecenterUserContext userContext, String platformId, String profileId, int profileVersion,String [] tokensExpr)  throws Exception;
	public  Platform updateProfile(MessagecenterUserContext userContext, String platformId, String profileId, int profileVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  PrivateMessageManager getPrivateMessageManager(MessagecenterUserContext userContext, String platformId, String title, String content, String senderId, String receiverId, String status ,String [] tokensExpr)  throws Exception;
	
	public  Platform addPrivateMessage(MessagecenterUserContext userContext, String platformId, String title, String content, String senderId, String receiverId, String status , String [] tokensExpr)  throws Exception;
	public  Platform removePrivateMessage(MessagecenterUserContext userContext, String platformId, String privateMessageId, int privateMessageVersion,String [] tokensExpr)  throws Exception;
	public  Platform updatePrivateMessage(MessagecenterUserContext userContext, String platformId, String privateMessageId, int privateMessageVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


