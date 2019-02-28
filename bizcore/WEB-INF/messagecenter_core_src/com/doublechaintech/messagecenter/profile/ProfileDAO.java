
package com.doublechaintech.messagecenter.profile;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.doublechaintech.messagecenter.BaseEntity;
import com.doublechaintech.messagecenter.SmartList;
import com.doublechaintech.messagecenter.MultipleAccessKey;
import com.doublechaintech.messagecenter.MessagecenterUserContext;
import com.doublechaintech.messagecenter.platform.PlatformDAO;
import com.doublechaintech.messagecenter.privatemessage.PrivateMessageDAO;


public interface ProfileDAO{

	
	public Profile load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<Profile> profileList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public Profile present(Profile profile,Map<String,Object> options) throws Exception;
	public Profile clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public Profile save(Profile profile,Map<String,Object> options);
	public SmartList<Profile> saveProfileList(SmartList<Profile> profileList,Map<String,Object> options);
	public SmartList<Profile> removeProfileList(SmartList<Profile> profileList,Map<String,Object> options);
	public SmartList<Profile> findProfileWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countProfileWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countProfileWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String profileId, int version) throws Exception;
	public Profile disconnectFromAll(String profileId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public PrivateMessageDAO getPrivateMessageDAO();
		
	
 	public SmartList<Profile> requestCandidateProfileForPrivateMessageAsSender(MessagecenterUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<Profile> requestCandidateProfileForPrivateMessageAsReceiver(MessagecenterUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public Profile planToRemovePrivateMessageListAsSender(Profile profile, String privateMessageIds[], Map<String,Object> options)throws Exception;


	//disconnect Profile with platform in PrivateMessage
	public Profile planToRemovePrivateMessageListAsSenderWithPlatform(Profile profile, String platformId, Map<String,Object> options)throws Exception;
	public int countPrivateMessageListAsSenderWithPlatform(String profileId, String platformId, Map<String,Object> options)throws Exception;
	
	public Profile planToRemovePrivateMessageListAsReceiver(Profile profile, String privateMessageIds[], Map<String,Object> options)throws Exception;


	//disconnect Profile with platform in PrivateMessage
	public Profile planToRemovePrivateMessageListAsReceiverWithPlatform(Profile profile, String platformId, Map<String,Object> options)throws Exception;
	public int countPrivateMessageListAsReceiverWithPlatform(String profileId, String platformId, Map<String,Object> options)throws Exception;
	
	
	public SmartList<Profile> queryList(String sql, Object ... parmeters);
 
 	public SmartList<Profile> findProfileByPlatform(String platformId, Map<String,Object> options);
 	public int countProfileByPlatform(String platformId, Map<String,Object> options);
 	public Map<String, Integer> countProfileByPlatformIds(String[] ids, Map<String,Object> options);
 	public SmartList<Profile> findProfileByPlatform(String platformId, int start, int count, Map<String,Object> options);
 	public void analyzeProfileByPlatform(SmartList<Profile> resultList, String platformId, Map<String,Object> options);

 
 }


