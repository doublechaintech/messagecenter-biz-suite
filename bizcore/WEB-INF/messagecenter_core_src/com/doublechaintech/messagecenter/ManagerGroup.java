package com.doublechaintech.messagecenter;


import com.doublechaintech.messagecenter.platform.PlatformManager;

import com.doublechaintech.messagecenter.profile.ProfileManager;

import com.doublechaintech.messagecenter.privatemessage.PrivateMessageManager;

import com.doublechaintech.messagecenter.userdomain.UserDomainManager;

import com.doublechaintech.messagecenter.userwhitelist.UserWhiteListManager;

import com.doublechaintech.messagecenter.secuser.SecUserManager;

import com.doublechaintech.messagecenter.secuserblocking.SecUserBlockingManager;

import com.doublechaintech.messagecenter.userapp.UserAppManager;

import com.doublechaintech.messagecenter.listaccess.ListAccessManager;

import com.doublechaintech.messagecenter.objectaccess.ObjectAccessManager;

import com.doublechaintech.messagecenter.loginhistory.LoginHistoryManager;

import com.doublechaintech.messagecenter.genericform.GenericFormManager;

import com.doublechaintech.messagecenter.formmessage.FormMessageManager;

import com.doublechaintech.messagecenter.formfieldmessage.FormFieldMessageManager;

import com.doublechaintech.messagecenter.formfield.FormFieldManager;

import com.doublechaintech.messagecenter.formaction.FormActionManager;


public class ManagerGroup {

	protected PlatformManager platformManager;

	protected ProfileManager profileManager;

	protected PrivateMessageManager privateMessageManager;

	protected UserDomainManager userDomainManager;

	protected UserWhiteListManager userWhiteListManager;

	protected SecUserManager secUserManager;

	protected SecUserBlockingManager secUserBlockingManager;

	protected UserAppManager userAppManager;

	protected ListAccessManager listAccessManager;

	protected ObjectAccessManager objectAccessManager;

	protected LoginHistoryManager loginHistoryManager;

	protected GenericFormManager genericFormManager;

	protected FormMessageManager formMessageManager;

	protected FormFieldMessageManager formFieldMessageManager;

	protected FormFieldManager formFieldManager;

	protected FormActionManager formActionManager;

	

	public PlatformManager getPlatformManager(){
		return this.platformManager;
	}
	public void setPlatformManager(PlatformManager manager){
		this.platformManager = manager;
	}


	public ProfileManager getProfileManager(){
		return this.profileManager;
	}
	public void setProfileManager(ProfileManager manager){
		this.profileManager = manager;
	}


	public PrivateMessageManager getPrivateMessageManager(){
		return this.privateMessageManager;
	}
	public void setPrivateMessageManager(PrivateMessageManager manager){
		this.privateMessageManager = manager;
	}


	public UserDomainManager getUserDomainManager(){
		return this.userDomainManager;
	}
	public void setUserDomainManager(UserDomainManager manager){
		this.userDomainManager = manager;
	}


	public UserWhiteListManager getUserWhiteListManager(){
		return this.userWhiteListManager;
	}
	public void setUserWhiteListManager(UserWhiteListManager manager){
		this.userWhiteListManager = manager;
	}


	public SecUserManager getSecUserManager(){
		return this.secUserManager;
	}
	public void setSecUserManager(SecUserManager manager){
		this.secUserManager = manager;
	}


	public SecUserBlockingManager getSecUserBlockingManager(){
		return this.secUserBlockingManager;
	}
	public void setSecUserBlockingManager(SecUserBlockingManager manager){
		this.secUserBlockingManager = manager;
	}


	public UserAppManager getUserAppManager(){
		return this.userAppManager;
	}
	public void setUserAppManager(UserAppManager manager){
		this.userAppManager = manager;
	}


	public ListAccessManager getListAccessManager(){
		return this.listAccessManager;
	}
	public void setListAccessManager(ListAccessManager manager){
		this.listAccessManager = manager;
	}


	public ObjectAccessManager getObjectAccessManager(){
		return this.objectAccessManager;
	}
	public void setObjectAccessManager(ObjectAccessManager manager){
		this.objectAccessManager = manager;
	}


	public LoginHistoryManager getLoginHistoryManager(){
		return this.loginHistoryManager;
	}
	public void setLoginHistoryManager(LoginHistoryManager manager){
		this.loginHistoryManager = manager;
	}


	public GenericFormManager getGenericFormManager(){
		return this.genericFormManager;
	}
	public void setGenericFormManager(GenericFormManager manager){
		this.genericFormManager = manager;
	}


	public FormMessageManager getFormMessageManager(){
		return this.formMessageManager;
	}
	public void setFormMessageManager(FormMessageManager manager){
		this.formMessageManager = manager;
	}


	public FormFieldMessageManager getFormFieldMessageManager(){
		return this.formFieldMessageManager;
	}
	public void setFormFieldMessageManager(FormFieldMessageManager manager){
		this.formFieldMessageManager = manager;
	}


	public FormFieldManager getFormFieldManager(){
		return this.formFieldManager;
	}
	public void setFormFieldManager(FormFieldManager manager){
		this.formFieldManager = manager;
	}


	public FormActionManager getFormActionManager(){
		return this.formActionManager;
	}
	public void setFormActionManager(FormActionManager manager){
		this.formActionManager = manager;
	}


}









