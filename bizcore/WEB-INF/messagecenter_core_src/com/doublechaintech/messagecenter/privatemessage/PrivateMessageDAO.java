
package com.doublechaintech.messagecenter.privatemessage;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.doublechaintech.messagecenter.BaseEntity;
import com.doublechaintech.messagecenter.SmartList;
import com.doublechaintech.messagecenter.MultipleAccessKey;
import com.doublechaintech.messagecenter.MessagecenterUserContext;

import com.doublechaintech.messagecenter.profile.Profile;
import com.doublechaintech.messagecenter.platform.Platform;

import com.doublechaintech.messagecenter.platform.PlatformDAO;
import com.doublechaintech.messagecenter.profile.ProfileDAO;


public interface PrivateMessageDAO{

	
	public PrivateMessage load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<PrivateMessage> privateMessageList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public PrivateMessage present(PrivateMessage privateMessage,Map<String,Object> options) throws Exception;
	public PrivateMessage clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public PrivateMessage save(PrivateMessage privateMessage,Map<String,Object> options);
	public SmartList<PrivateMessage> savePrivateMessageList(SmartList<PrivateMessage> privateMessageList,Map<String,Object> options);
	public SmartList<PrivateMessage> removePrivateMessageList(SmartList<PrivateMessage> privateMessageList,Map<String,Object> options);
	public SmartList<PrivateMessage> findPrivateMessageWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countPrivateMessageWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countPrivateMessageWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String privateMessageId, int version) throws Exception;
	public PrivateMessage disconnectFromAll(String privateMessageId, int version) throws Exception;
	public int deleteAll() throws Exception;

	
	
	
	public SmartList<PrivateMessage> queryList(String sql, Object ... parmeters);
 
 	public SmartList<PrivateMessage> findPrivateMessageBySender(String profileId, Map<String,Object> options);
 	public int countPrivateMessageBySender(String profileId, Map<String,Object> options);
 	public Map<String, Integer> countPrivateMessageBySenderIds(String[] ids, Map<String,Object> options);
 	public SmartList<PrivateMessage> findPrivateMessageBySender(String profileId, int start, int count, Map<String,Object> options);
 	public void analyzePrivateMessageBySender(SmartList<PrivateMessage> resultList, String profileId, Map<String,Object> options);

 
  
 	public SmartList<PrivateMessage> findPrivateMessageByReceiver(String profileId, Map<String,Object> options);
 	public int countPrivateMessageByReceiver(String profileId, Map<String,Object> options);
 	public Map<String, Integer> countPrivateMessageByReceiverIds(String[] ids, Map<String,Object> options);
 	public SmartList<PrivateMessage> findPrivateMessageByReceiver(String profileId, int start, int count, Map<String,Object> options);
 	public void analyzePrivateMessageByReceiver(SmartList<PrivateMessage> resultList, String profileId, Map<String,Object> options);

 
  
 	public SmartList<PrivateMessage> findPrivateMessageByPlatform(String platformId, Map<String,Object> options);
 	public int countPrivateMessageByPlatform(String platformId, Map<String,Object> options);
 	public Map<String, Integer> countPrivateMessageByPlatformIds(String[] ids, Map<String,Object> options);
 	public SmartList<PrivateMessage> findPrivateMessageByPlatform(String platformId, int start, int count, Map<String,Object> options);
 	public void analyzePrivateMessageByPlatform(SmartList<PrivateMessage> resultList, String platformId, Map<String,Object> options);

 
 
}


