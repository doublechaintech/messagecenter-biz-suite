
package com.doublechaintech.messagecenter.formfield;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.messagecenter.MessagecenterUserContext;
import com.doublechaintech.messagecenter.BaseEntity;
import com.doublechaintech.messagecenter.SmartList;

public interface FormFieldManager{

		

	public FormField createFormField(MessagecenterUserContext userContext, String label, String localeKey, String parameterName, String type, String formId, String placeholder, String defaultValue, String description, String fieldGroup, String minimumValue, String maximumValue, boolean required, boolean disabled, boolean customRendering, String candidateValues, String suggestValues) throws Exception;	
	public FormField updateFormField(MessagecenterUserContext userContext,String formFieldId, int formFieldVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public FormField loadFormField(MessagecenterUserContext userContext, String formFieldId, String [] tokensExpr) throws Exception;
	public FormField internalSaveFormField(MessagecenterUserContext userContext, FormField formField) throws Exception;
	public FormField internalSaveFormField(MessagecenterUserContext userContext, FormField formField,Map<String,Object>option) throws Exception;
	
	public FormField transferToAnotherForm(MessagecenterUserContext userContext, String formFieldId, String anotherFormId)  throws Exception;
 

	public void delete(MessagecenterUserContext userContext, String formFieldId, int version) throws Exception;
	public int deleteAll(MessagecenterUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(MessagecenterUserContext userContext, FormField newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	



}


