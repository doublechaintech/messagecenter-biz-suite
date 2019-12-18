
package com.doublechaintech.messagecenter.formaction;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.messagecenter.MessagecenterUserContext;
import com.doublechaintech.messagecenter.BaseEntity;
import com.doublechaintech.messagecenter.SmartList;

public interface FormActionManager{

		

	public FormAction createFormAction(MessagecenterUserContext userContext, String label, String localeKey, String actionKey, String level, String url, String formId) throws Exception;	
	public FormAction updateFormAction(MessagecenterUserContext userContext,String formActionId, int formActionVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public FormAction loadFormAction(MessagecenterUserContext userContext, String formActionId, String [] tokensExpr) throws Exception;
	public FormAction internalSaveFormAction(MessagecenterUserContext userContext, FormAction formAction) throws Exception;
	public FormAction internalSaveFormAction(MessagecenterUserContext userContext, FormAction formAction,Map<String,Object>option) throws Exception;
	
	public FormAction transferToAnotherForm(MessagecenterUserContext userContext, String formActionId, String anotherFormId)  throws Exception;
 

	public void delete(MessagecenterUserContext userContext, String formActionId, int version) throws Exception;
	public int deleteAll(MessagecenterUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(MessagecenterUserContext userContext, FormAction newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	



}











