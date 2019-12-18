
package com.doublechaintech.messagecenter.genericform;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.doublechaintech.messagecenter.BaseEntity;
import com.doublechaintech.messagecenter.SmartList;
import com.doublechaintech.messagecenter.MultipleAccessKey;
import com.doublechaintech.messagecenter.MessagecenterUserContext;

import com.doublechaintech.messagecenter.formfield.FormField;
import com.doublechaintech.messagecenter.formfieldmessage.FormFieldMessage;
import com.doublechaintech.messagecenter.formaction.FormAction;
import com.doublechaintech.messagecenter.formmessage.FormMessage;

import com.doublechaintech.messagecenter.formmessage.FormMessageDAO;
import com.doublechaintech.messagecenter.formfieldmessage.FormFieldMessageDAO;
import com.doublechaintech.messagecenter.formfield.FormFieldDAO;
import com.doublechaintech.messagecenter.formaction.FormActionDAO;


public interface GenericFormDAO{

	
	public GenericForm load(String id, Map<String,Object> options) throws Exception;
	public void enhanceList(List<GenericForm> genericFormList);
	public void collectAndEnhance(BaseEntity ownerEntity);
	
	public void alias(List<BaseEntity> entityList);
	
	
	
	
	public GenericForm present(GenericForm genericForm,Map<String,Object> options) throws Exception;
	public GenericForm clone(String id, Map<String,Object> options) throws Exception;
	
	
	
	public GenericForm save(GenericForm genericForm,Map<String,Object> options);
	public SmartList<GenericForm> saveGenericFormList(SmartList<GenericForm> genericFormList,Map<String,Object> options);
	public SmartList<GenericForm> removeGenericFormList(SmartList<GenericForm> genericFormList,Map<String,Object> options);
	public SmartList<GenericForm> findGenericFormWithKey(MultipleAccessKey key,Map<String, Object> options);
	public int countGenericFormWithKey(MultipleAccessKey key,Map<String, Object> options);
	public Map<String, Integer> countGenericFormWithGroupKey(String groupKey, MultipleAccessKey filterKey,
			Map<String, Object> options);
	public void delete(String genericFormId, int version) throws Exception;
	public GenericForm disconnectFromAll(String genericFormId, int version) throws Exception;
	public int deleteAll() throws Exception;

	public FormMessageDAO getFormMessageDAO();
		
	public FormFieldMessageDAO getFormFieldMessageDAO();
		
	public FormFieldDAO getFormFieldDAO();
		
	public FormActionDAO getFormActionDAO();
		
	
 	public SmartList<GenericForm> requestCandidateGenericFormForFormMessage(MessagecenterUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<GenericForm> requestCandidateGenericFormForFormFieldMessage(MessagecenterUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<GenericForm> requestCandidateGenericFormForFormField(MessagecenterUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
 	public SmartList<GenericForm> requestCandidateGenericFormForFormAction(MessagecenterUserContext userContext, String ownerClass, String id, String filterKey, int pageNo, int pageSize) throws Exception;
		
	
	public GenericForm planToRemoveFormMessageList(GenericForm genericForm, String formMessageIds[], Map<String,Object> options)throws Exception;


	public GenericForm planToRemoveFormFieldMessageList(GenericForm genericForm, String formFieldMessageIds[], Map<String,Object> options)throws Exception;


	public GenericForm planToRemoveFormFieldList(GenericForm genericForm, String formFieldIds[], Map<String,Object> options)throws Exception;


	public GenericForm planToRemoveFormActionList(GenericForm genericForm, String formActionIds[], Map<String,Object> options)throws Exception;


	
	public SmartList<GenericForm> queryList(String sql, Object ... parmeters);

	// 需要一个加载引用我的对象的enhance方法:FormMessage的form的FormMessageList
	public SmartList<FormMessage> loadOurFormMessageList(MessagecenterUserContext userContext, List<GenericForm> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:FormFieldMessage的form的FormFieldMessageList
	public SmartList<FormFieldMessage> loadOurFormFieldMessageList(MessagecenterUserContext userContext, List<GenericForm> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:FormField的form的FormFieldList
	public SmartList<FormField> loadOurFormFieldList(MessagecenterUserContext userContext, List<GenericForm> us, Map<String,Object> options) throws Exception;
	
	// 需要一个加载引用我的对象的enhance方法:FormAction的form的FormActionList
	public SmartList<FormAction> loadOurFormActionList(MessagecenterUserContext userContext, List<GenericForm> us, Map<String,Object> options) throws Exception;
	
}


