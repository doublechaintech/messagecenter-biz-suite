
package com.doublechaintech.messagecenter.privatemessage;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.messagecenter.MessagecenterUserContext;
import com.doublechaintech.messagecenter.BaseEntity;
import com.doublechaintech.messagecenter.SmartList;

public interface PrivateMessageManager{

		

	public PrivateMessage createPrivateMessage(MessagecenterUserContext userContext, String title, String content, String senderId, String receiverId, String status, String platformId) throws Exception;	
	public PrivateMessage updatePrivateMessage(MessagecenterUserContext userContext,String privateMessageId, int privateMessageVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public PrivateMessage loadPrivateMessage(MessagecenterUserContext userContext, String privateMessageId, String [] tokensExpr) throws Exception;
	public PrivateMessage internalSavePrivateMessage(MessagecenterUserContext userContext, PrivateMessage privateMessage) throws Exception;
	public PrivateMessage internalSavePrivateMessage(MessagecenterUserContext userContext, PrivateMessage privateMessage,Map<String,Object>option) throws Exception;
	
	public PrivateMessage transferToAnotherSender(MessagecenterUserContext userContext, String privateMessageId, String anotherSenderId)  throws Exception;
 	public PrivateMessage transferToAnotherReceiver(MessagecenterUserContext userContext, String privateMessageId, String anotherReceiverId)  throws Exception;
 	public PrivateMessage transferToAnotherPlatform(MessagecenterUserContext userContext, String privateMessageId, String anotherPlatformId)  throws Exception;
 

	public void delete(MessagecenterUserContext userContext, String privateMessageId, int version) throws Exception;
	public int deleteAll(MessagecenterUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(MessagecenterUserContext userContext, PrivateMessage newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	



}


