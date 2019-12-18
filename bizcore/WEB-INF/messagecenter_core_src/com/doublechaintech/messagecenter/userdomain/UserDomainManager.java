
package com.doublechaintech.messagecenter.userdomain;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.messagecenter.MessagecenterUserContext;
import com.doublechaintech.messagecenter.BaseEntity;
import com.doublechaintech.messagecenter.SmartList;

public interface UserDomainManager{

		

	public UserDomain createUserDomain(MessagecenterUserContext userContext, String name) throws Exception;	
	public UserDomain updateUserDomain(MessagecenterUserContext userContext,String userDomainId, int userDomainVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public UserDomain loadUserDomain(MessagecenterUserContext userContext, String userDomainId, String [] tokensExpr) throws Exception;
	public UserDomain internalSaveUserDomain(MessagecenterUserContext userContext, UserDomain userDomain) throws Exception;
	public UserDomain internalSaveUserDomain(MessagecenterUserContext userContext, UserDomain userDomain,Map<String,Object>option) throws Exception;
	


	public void delete(MessagecenterUserContext userContext, String userDomainId, int version) throws Exception;
	public int deleteAll(MessagecenterUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(MessagecenterUserContext userContext, UserDomain newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	

	//public  UserWhiteListManager getUserWhiteListManager(MessagecenterUserContext userContext, String userDomainId, String userIdentity, String userSpecialFunctions ,String [] tokensExpr)  throws Exception;
	
	public  UserDomain addUserWhiteList(MessagecenterUserContext userContext, String userDomainId, String userIdentity, String userSpecialFunctions , String [] tokensExpr)  throws Exception;
	public  UserDomain removeUserWhiteList(MessagecenterUserContext userContext, String userDomainId, String userWhiteListId, int userWhiteListVersion,String [] tokensExpr)  throws Exception;
	public  UserDomain updateUserWhiteList(MessagecenterUserContext userContext, String userDomainId, String userWhiteListId, int userWhiteListVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*

	*/

	//public  SecUserManager getSecUserManager(MessagecenterUserContext userContext, String userDomainId, String login, String mobile, String email, String pwd, String weixinOpenid, String weixinAppid, String accessToken, int verificationCode, DateTime verificationCodeExpire, DateTime lastLoginTime ,String [] tokensExpr)  throws Exception;
	
	public  UserDomain addSecUser(MessagecenterUserContext userContext, String userDomainId, String login, String mobile, String email, String pwd, String weixinOpenid, String weixinAppid, String accessToken, int verificationCode, DateTime verificationCodeExpire, DateTime lastLoginTime , String [] tokensExpr)  throws Exception;
	public  UserDomain removeSecUser(MessagecenterUserContext userContext, String userDomainId, String secUserId, int secUserVersion,String [] tokensExpr)  throws Exception;
	public  UserDomain updateSecUser(MessagecenterUserContext userContext, String userDomainId, String secUserId, int secUserVersion, String property, String newValueExpr,String [] tokensExpr)  throws Exception;

	/*
	public  UserDomain associateSecUserListToNewBlocking(MessagecenterUserContext userContext, String userDomainId, String  secUserIds[], String who, String comments, String [] tokensExpr) throws Exception ;
	public  UserDomain associateSecUserListToBlocking(MessagecenterUserContext userContext, String userDomainId, String  secUserIds[],String blockingId, String [] tokensExpr) throws Exception ;

	*/



}


