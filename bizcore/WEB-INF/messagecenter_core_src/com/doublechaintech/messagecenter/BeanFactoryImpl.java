
package com.doublechaintech.messagecenter;
import java.util.Map;

import com.doublechaintech.messagecenter.platform.Platform;
import com.doublechaintech.messagecenter.profile.Profile;
import com.doublechaintech.messagecenter.privatemessage.PrivateMessage;
import com.doublechaintech.messagecenter.userdomain.UserDomain;
import com.doublechaintech.messagecenter.userwhitelist.UserWhiteList;
import com.doublechaintech.messagecenter.secuser.SecUser;
import com.doublechaintech.messagecenter.secuserblocking.SecUserBlocking;
import com.doublechaintech.messagecenter.userapp.UserApp;
import com.doublechaintech.messagecenter.listaccess.ListAccess;
import com.doublechaintech.messagecenter.objectaccess.ObjectAccess;
import com.doublechaintech.messagecenter.loginhistory.LoginHistory;
import com.doublechaintech.messagecenter.genericform.GenericForm;
import com.doublechaintech.messagecenter.formmessage.FormMessage;
import com.doublechaintech.messagecenter.formfieldmessage.FormFieldMessage;
import com.doublechaintech.messagecenter.formfield.FormField;
import com.doublechaintech.messagecenter.formaction.FormAction;

public class BeanFactoryImpl{


	public Platform createPlatform(Map<String,Object> options){
		return new Platform();
	}


	public Profile createProfile(Map<String,Object> options){
		return new Profile();
	}


	public PrivateMessage createPrivateMessage(Map<String,Object> options){
		return new PrivateMessage();
	}


	public UserDomain createUserDomain(Map<String,Object> options){
		return new UserDomain();
	}


	public UserWhiteList createUserWhiteList(Map<String,Object> options){
		return new UserWhiteList();
	}


	public SecUser createSecUser(Map<String,Object> options){
		return new SecUser();
	}


	public SecUserBlocking createSecUserBlocking(Map<String,Object> options){
		return new SecUserBlocking();
	}


	public UserApp createUserApp(Map<String,Object> options){
		return new UserApp();
	}


	public ListAccess createListAccess(Map<String,Object> options){
		return new ListAccess();
	}


	public ObjectAccess createObjectAccess(Map<String,Object> options){
		return new ObjectAccess();
	}


	public LoginHistory createLoginHistory(Map<String,Object> options){
		return new LoginHistory();
	}


	public GenericForm createGenericForm(Map<String,Object> options){
		return new GenericForm();
	}


	public FormMessage createFormMessage(Map<String,Object> options){
		return new FormMessage();
	}


	public FormFieldMessage createFormFieldMessage(Map<String,Object> options){
		return new FormFieldMessage();
	}


	public FormField createFormField(Map<String,Object> options){
		return new FormField();
	}


	public FormAction createFormAction(Map<String,Object> options){
		return new FormAction();
	}





}







