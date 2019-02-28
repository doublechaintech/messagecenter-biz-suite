
package com.doublechaintech.messagecenter.formmessage;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.terapico.caf.DateTime;
import com.doublechaintech.messagecenter.MessagecenterUserContext;
import com.doublechaintech.messagecenter.BaseEntity;
import com.doublechaintech.messagecenter.SmartList;

public interface FormMessageManager{

		

	public FormMessage createFormMessage(MessagecenterUserContext userContext, String title, String formId, String level) throws Exception;	
	public FormMessage updateFormMessage(MessagecenterUserContext userContext,String formMessageId, int formMessageVersion, String property, String newValueExpr,String [] tokensExpr) throws Exception;
	public FormMessage loadFormMessage(MessagecenterUserContext userContext, String formMessageId, String [] tokensExpr) throws Exception;
	public FormMessage internalSaveFormMessage(MessagecenterUserContext userContext, FormMessage formMessage) throws Exception;
	public FormMessage internalSaveFormMessage(MessagecenterUserContext userContext, FormMessage formMessage,Map<String,Object>option) throws Exception;
	
	public FormMessage transferToAnotherForm(MessagecenterUserContext userContext, String formMessageId, String anotherFormId)  throws Exception;
 

	public void delete(MessagecenterUserContext userContext, String formMessageId, int version) throws Exception;
	public int deleteAll(MessagecenterUserContext userContext, String secureCode ) throws Exception;
	public void onNewInstanceCreated(MessagecenterUserContext userContext, FormMessage newCreated)throws Exception;

	/*======================================================DATA MAINTENANCE===========================================================*/
	



}


