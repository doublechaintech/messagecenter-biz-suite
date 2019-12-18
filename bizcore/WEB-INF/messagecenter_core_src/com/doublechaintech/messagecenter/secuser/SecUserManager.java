
package com.doublechaintech.messagecenter.secuser;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.messagecenter.MessagecenterUserContext;
import com.doublechaintech.messagecenter.BaseEntity;
import com.doublechaintech.messagecenter.SmartList;

public interface SecUserManager{

		
	

	public SecUser loadSecUserWithLogin(MessagecenterUserContext userContext, String login, Map<String,Object>tokens) throws Exception;

	 
	

	public SecUser loadSecUserWithEmail(MessagecenterUserContext userContext, String email, Map<String,Object>tokens) throws Exception;

	 
	

	public SecUser loadSecUserWithMobile(MessagecenterUserContext userContext, String mobile, Map<String,Object>tokens) throws Exception;

	 

	public SecUser createSecUser(MessagecenterUserContext userContext, String login, String mobile, String email, String pwd, String weixinOpenid, String weixinAppid, String accessToken, int verificationCode, DateTime verificationCodeExpire, DateTime lastLoginTime, String domainId) throws Exception;	
	public SecUser updateSecUser(MessagecenterUserContext userContext,String secUserId, int secUserVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public SecUser loadSecUser(MessagecenterUserContext userContext, String secUserId, String [] tokensExpr) throws Exception;
	public SecUser internalSaveSecUser(MessagecenterUserContext userContext, SecUser secUser) throws Exception;
	public SecUser internalSaveSecUser(MessagecenterUserContext userContext, SecUser secUser,Map<String,Object>option) throws Exception;
	
	public SecUser transferToAnotherDomain(MessagecenterUserContext userContext, String secUserId, String anotherDomainId)  throws Exception;
 	public SecUser block(MessagecenterUserContext userContext, String secUserId, String who, String comments
)  throws Exception;


	public void delete(MessagecenterUserContext userContext, String secUserId, int version) throws Exception;
	public int deleteAll(MessagecenterUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(MessagecenterUserContext userContext, SecUser newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	

	//public  UserAppManager getUserAppManager(MessagecenterUserContext userContext, String secUserId, String title, String appIcon, boolean fullAccess, String permission, String objectType, String objectId, String location ,String [] tokensExpr)  throws Exception;
	
	public  SecUser addUserApp(MessagecenterUserContext userContext, String secUserId, String title, String appIcon, boolean fullAccess, String permission, String objectType, String objectId, String location , String [] tokensExpr)  throws Exception;
	public  SecUser removeUserApp(MessagecenterUserContext userContext, String secUserId, String userAppId, int userAppVersion,String [] tokensExpr)  throws Exception;
	public  SecUser updateUserApp(MessagecenterUserContext userContext, String secUserId, String userAppId, int userAppVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  LoginHistoryManager getLoginHistoryManager(MessagecenterUserContext userContext, String secUserId, String fromIp, String description ,String [] tokensExpr)  throws Exception;
	
	public  SecUser addLoginHistory(MessagecenterUserContext userContext, String secUserId, String fromIp, String description , String [] tokensExpr)  throws Exception;
	public  SecUser removeLoginHistory(MessagecenterUserContext userContext, String secUserId, String loginHistoryId, int loginHistoryVersion,String [] tokensExpr)  throws Exception;
	public  SecUser updateLoginHistory(MessagecenterUserContext userContext, String secUserId, String loginHistoryId, int loginHistoryVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/



}


