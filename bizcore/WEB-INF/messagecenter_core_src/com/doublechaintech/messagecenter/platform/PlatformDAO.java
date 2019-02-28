
package com.doublechaintech.messagecenter.platform;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.doublechaintech.messagecenter.BaseEntity;
import com.doublechaintech.messagecenter.SmartList;
import com.doublechaintech.messagecenter.MultipleAccessKey;
import com.doublechaintech.messagecenter.MessagecenterUserContext;
import com.doublechaintech.messagecenter.privatemessage.PrivateMessageDAO;
import com.doublechaintech.messagecenter.profile.ProfileDAO;


public interface PlatformDAO{

	
	public Platform load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<Platform> platformList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public Platform present(Platform platform,Map<String,Object> options) throws Exception;
	public Platform clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public Platform save(Platform platform,Map<String,Object> options);
	public SmartList<Platform> savePlatformList(SmartList<Platform> platformList,Map<String,Object> options);
	public SmartList<Platform> removePlatformList(SmartList<Platform> platformList,Map<String,Object> options);
	public SmartList<Platform> findPlatformWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countPlatformWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countPlatformWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String platformId, int version) throws Exception;
	public Platform disconnectFromAll(String platformId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public ProfileDAO getProfileDAO();
		
	public PrivateMessageDAO getPrivateMessageDAO();
		
	
 	public SmartList<Platform> requestCandidatePlatformForProfile(MessagecenterUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<Platform> requestCandidatePlatformForPrivateMessage(MessagecenterUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public Platform planToRemoveProfileList(Platform platform, String profileIds[], Map<String,Object> options)throws Exception;


	public Platform planToRemovePrivateMessageList(Platform platform, String privateMessageIds[], Map<String,Object> options)throws Exception;


	//disconnect Platform with sender in PrivateMessage
	public Platform planToRemovePrivateMessageListWithSender(Platform platform, String senderId, Map<String,Object> options)throws Exception;
	public int countPrivateMessageListWithSender(String platformId, String senderId, Map<String,Object> options)throws Exception;
	
	//disconnect Platform with receiver in PrivateMessage
	public Platform planToRemovePrivateMessageListWithReceiver(Platform platform, String receiverId, Map<String,Object> options)throws Exception;
	public int countPrivateMessageListWithReceiver(String platformId, String receiverId, Map<String,Object> options)throws Exception;
	
	
	public SmartList<Platform> queryList(String sql, Object ... parmeters);
}


