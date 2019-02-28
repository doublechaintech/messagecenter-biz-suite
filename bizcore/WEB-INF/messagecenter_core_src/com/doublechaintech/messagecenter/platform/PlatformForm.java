package com.doublechaintech.messagecenter.platform;
import com.doublechaintech.messagecenter.BaseForm;
import com.doublechaintech.messagecenter.genericform.GenericForm;
import com.doublechaintech.messagecenter.formfield.FormField;
import com.doublechaintech.messagecenter.formaction.FormAction;
import com.doublechaintech.messagecenter.formmessage.FormMessage;
import com.doublechaintech.messagecenter.formfieldmessage.FormFieldMessage;



public class PlatformForm extends BaseForm {
	
	
	public PlatformForm withTitle(String title){
		this.setId(System.currentTimeMillis()+"");
		this.setTitle(title);
		return this;
	}
	
	
	

	public PlatformForm platformIdField(String parameterName, String initValue){
		FormField field = idFromPlatform(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm platformIdField(String initValue){
		return platformIdField("platformId",initValue);
	}
	public PlatformForm platformIdField(){
		return platformIdField("platformId","");
	}


	public PlatformForm nameField(String parameterName, String initValue){
		FormField field = nameFromPlatform(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm nameField(String initValue){
		return nameField("name",initValue);
	}
	public PlatformForm nameField(){
		return nameField("name","");
	}


	public PlatformForm introductionField(String parameterName, String initValue){
		FormField field = introductionFromPlatform(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm introductionField(String initValue){
		return introductionField("introduction",initValue);
	}
	public PlatformForm introductionField(){
		return introductionField("introduction","");
	}


	public PlatformForm currentVersionField(String parameterName, String initValue){
		FormField field = currentVersionFromPlatform(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm currentVersionField(String initValue){
		return currentVersionField("currentVersion",initValue);
	}
	public PlatformForm currentVersionField(){
		return currentVersionField("currentVersion","");
	}

	
	

	



	public PlatformForm profileIdFieldForProfile(String parameterName, String initValue){
		FormField field =  idFromProfile(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm profileIdFieldForProfile(String initValue){
		return profileIdFieldForProfile("profileId",initValue);
	}
	public PlatformForm profileIdFieldForProfile(){
		return profileIdFieldForProfile("profileId","");
	}


	public PlatformForm nameFieldForProfile(String parameterName, String initValue){
		FormField field =  nameFromProfile(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm nameFieldForProfile(String initValue){
		return nameFieldForProfile("name",initValue);
	}
	public PlatformForm nameFieldForProfile(){
		return nameFieldForProfile("name","");
	}


	public PlatformForm platformIdFieldForProfile(String parameterName, String initValue){
		FormField field =  platformIdFromProfile(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm platformIdFieldForProfile(String initValue){
		return platformIdFieldForProfile("platformId",initValue);
	}
	public PlatformForm platformIdFieldForProfile(){
		return platformIdFieldForProfile("platformId","");
	}


	public PlatformForm privateMessageIdFieldForPrivateMessage(String parameterName, String initValue){
		FormField field =  idFromPrivateMessage(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm privateMessageIdFieldForPrivateMessage(String initValue){
		return privateMessageIdFieldForPrivateMessage("privateMessageId",initValue);
	}
	public PlatformForm privateMessageIdFieldForPrivateMessage(){
		return privateMessageIdFieldForPrivateMessage("privateMessageId","");
	}


	public PlatformForm titleFieldForPrivateMessage(String parameterName, String initValue){
		FormField field =  titleFromPrivateMessage(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm titleFieldForPrivateMessage(String initValue){
		return titleFieldForPrivateMessage("title",initValue);
	}
	public PlatformForm titleFieldForPrivateMessage(){
		return titleFieldForPrivateMessage("title","");
	}


	public PlatformForm contentFieldForPrivateMessage(String parameterName, String initValue){
		FormField field =  contentFromPrivateMessage(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm contentFieldForPrivateMessage(String initValue){
		return contentFieldForPrivateMessage("content",initValue);
	}
	public PlatformForm contentFieldForPrivateMessage(){
		return contentFieldForPrivateMessage("content","");
	}


	public PlatformForm sendTimeFieldForPrivateMessage(String parameterName, String initValue){
		FormField field =  sendTimeFromPrivateMessage(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm sendTimeFieldForPrivateMessage(String initValue){
		return sendTimeFieldForPrivateMessage("sendTime",initValue);
	}
	public PlatformForm sendTimeFieldForPrivateMessage(){
		return sendTimeFieldForPrivateMessage("sendTime","");
	}


	public PlatformForm readTimeFieldForPrivateMessage(String parameterName, String initValue){
		FormField field =  readTimeFromPrivateMessage(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm readTimeFieldForPrivateMessage(String initValue){
		return readTimeFieldForPrivateMessage("readTime",initValue);
	}
	public PlatformForm readTimeFieldForPrivateMessage(){
		return readTimeFieldForPrivateMessage("readTime","");
	}


	public PlatformForm senderIdFieldForPrivateMessage(String parameterName, String initValue){
		FormField field =  senderIdFromPrivateMessage(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm senderIdFieldForPrivateMessage(String initValue){
		return senderIdFieldForPrivateMessage("senderId",initValue);
	}
	public PlatformForm senderIdFieldForPrivateMessage(){
		return senderIdFieldForPrivateMessage("senderId","");
	}


	public PlatformForm receiverIdFieldForPrivateMessage(String parameterName, String initValue){
		FormField field =  receiverIdFromPrivateMessage(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm receiverIdFieldForPrivateMessage(String initValue){
		return receiverIdFieldForPrivateMessage("receiverId",initValue);
	}
	public PlatformForm receiverIdFieldForPrivateMessage(){
		return receiverIdFieldForPrivateMessage("receiverId","");
	}


	public PlatformForm statusFieldForPrivateMessage(String parameterName, String initValue){
		FormField field =  statusFromPrivateMessage(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm statusFieldForPrivateMessage(String initValue){
		return statusFieldForPrivateMessage("status",initValue);
	}
	public PlatformForm statusFieldForPrivateMessage(){
		return statusFieldForPrivateMessage("status","");
	}


	public PlatformForm platformIdFieldForPrivateMessage(String parameterName, String initValue){
		FormField field =  platformIdFromPrivateMessage(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PlatformForm platformIdFieldForPrivateMessage(String initValue){
		return platformIdFieldForPrivateMessage("platformId",initValue);
	}
	public PlatformForm platformIdFieldForPrivateMessage(){
		return platformIdFieldForPrivateMessage("platformId","");
	}

	



	public PlatformForm showAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("genericFormManager/show/title/desc/");
		this.addFormAction(action);
		return this;
	}
	
	
}


