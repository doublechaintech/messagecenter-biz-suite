package com.doublechaintech.messagecenter.profile;
import com.doublechaintech.messagecenter.BaseForm;
import com.doublechaintech.messagecenter.genericform.GenericForm;
import com.doublechaintech.messagecenter.formfield.FormField;
import com.doublechaintech.messagecenter.formaction.FormAction;
import com.doublechaintech.messagecenter.formmessage.FormMessage;
import com.doublechaintech.messagecenter.formfieldmessage.FormFieldMessage;



public class ProfileForm extends BaseForm {
	
	
	public ProfileForm withTitle(String title){
		this.setId(System.currentTimeMillis()+"");
		this.setTitle(title);
		return this;
	}
	
	
	

	public ProfileForm profileIdField(String parameterName, String initValue){
		FormField field = idFromProfile(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ProfileForm profileIdField(String initValue){
		return profileIdField("profileId",initValue);
	}
	public ProfileForm profileIdField(){
		return profileIdField("profileId","");
	}


	public ProfileForm nameField(String parameterName, String initValue){
		FormField field = nameFromProfile(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ProfileForm nameField(String initValue){
		return nameField("name",initValue);
	}
	public ProfileForm nameField(){
		return nameField("name","");
	}


	public ProfileForm platformIdField(String parameterName, String initValue){
		FormField field = platformIdFromProfile(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ProfileForm platformIdField(String initValue){
		return platformIdField("platformId",initValue);
	}
	public ProfileForm platformIdField(){
		return platformIdField("platformId","");
	}

	
	


	public ProfileForm platformIdFieldOfPlatform(String parameterName, String initValue){
		FormField field =  idFromPlatform(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public ProfileForm platformIdFieldOfPlatform(String initValue){
		return platformIdFieldOfPlatform("platformId",initValue);
	}
	public ProfileForm platformIdFieldOfPlatform(){
		return platformIdFieldOfPlatform("platformId","");
	}


	public ProfileForm nameFieldOfPlatform(String parameterName, String initValue){
		FormField field =  nameFromPlatform(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public ProfileForm nameFieldOfPlatform(String initValue){
		return nameFieldOfPlatform("name",initValue);
	}
	public ProfileForm nameFieldOfPlatform(){
		return nameFieldOfPlatform("name","");
	}


	public ProfileForm introductionFieldOfPlatform(String parameterName, String initValue){
		FormField field =  introductionFromPlatform(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public ProfileForm introductionFieldOfPlatform(String initValue){
		return introductionFieldOfPlatform("introduction",initValue);
	}
	public ProfileForm introductionFieldOfPlatform(){
		return introductionFieldOfPlatform("introduction","");
	}


	public ProfileForm currentVersionFieldOfPlatform(String parameterName, String initValue){
		FormField field =  currentVersionFromPlatform(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public ProfileForm currentVersionFieldOfPlatform(String initValue){
		return currentVersionFieldOfPlatform("currentVersion",initValue);
	}
	public ProfileForm currentVersionFieldOfPlatform(){
		return currentVersionFieldOfPlatform("currentVersion","");
	}

	



	public ProfileForm privateMessageIdFieldForPrivateMessage(String parameterName, String initValue){
		FormField field =  idFromPrivateMessage(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ProfileForm privateMessageIdFieldForPrivateMessage(String initValue){
		return privateMessageIdFieldForPrivateMessage("privateMessageId",initValue);
	}
	public ProfileForm privateMessageIdFieldForPrivateMessage(){
		return privateMessageIdFieldForPrivateMessage("privateMessageId","");
	}


	public ProfileForm titleFieldForPrivateMessage(String parameterName, String initValue){
		FormField field =  titleFromPrivateMessage(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ProfileForm titleFieldForPrivateMessage(String initValue){
		return titleFieldForPrivateMessage("title",initValue);
	}
	public ProfileForm titleFieldForPrivateMessage(){
		return titleFieldForPrivateMessage("title","");
	}


	public ProfileForm contentFieldForPrivateMessage(String parameterName, String initValue){
		FormField field =  contentFromPrivateMessage(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ProfileForm contentFieldForPrivateMessage(String initValue){
		return contentFieldForPrivateMessage("content",initValue);
	}
	public ProfileForm contentFieldForPrivateMessage(){
		return contentFieldForPrivateMessage("content","");
	}


	public ProfileForm sendTimeFieldForPrivateMessage(String parameterName, String initValue){
		FormField field =  sendTimeFromPrivateMessage(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ProfileForm sendTimeFieldForPrivateMessage(String initValue){
		return sendTimeFieldForPrivateMessage("sendTime",initValue);
	}
	public ProfileForm sendTimeFieldForPrivateMessage(){
		return sendTimeFieldForPrivateMessage("sendTime","");
	}


	public ProfileForm readTimeFieldForPrivateMessage(String parameterName, String initValue){
		FormField field =  readTimeFromPrivateMessage(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ProfileForm readTimeFieldForPrivateMessage(String initValue){
		return readTimeFieldForPrivateMessage("readTime",initValue);
	}
	public ProfileForm readTimeFieldForPrivateMessage(){
		return readTimeFieldForPrivateMessage("readTime","");
	}


	public ProfileForm senderIdFieldForPrivateMessage(String parameterName, String initValue){
		FormField field =  senderIdFromPrivateMessage(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ProfileForm senderIdFieldForPrivateMessage(String initValue){
		return senderIdFieldForPrivateMessage("senderId",initValue);
	}
	public ProfileForm senderIdFieldForPrivateMessage(){
		return senderIdFieldForPrivateMessage("senderId","");
	}


	public ProfileForm receiverIdFieldForPrivateMessage(String parameterName, String initValue){
		FormField field =  receiverIdFromPrivateMessage(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ProfileForm receiverIdFieldForPrivateMessage(String initValue){
		return receiverIdFieldForPrivateMessage("receiverId",initValue);
	}
	public ProfileForm receiverIdFieldForPrivateMessage(){
		return receiverIdFieldForPrivateMessage("receiverId","");
	}


	public ProfileForm statusFieldForPrivateMessage(String parameterName, String initValue){
		FormField field =  statusFromPrivateMessage(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ProfileForm statusFieldForPrivateMessage(String initValue){
		return statusFieldForPrivateMessage("status",initValue);
	}
	public ProfileForm statusFieldForPrivateMessage(){
		return statusFieldForPrivateMessage("status","");
	}


	public ProfileForm platformIdFieldForPrivateMessage(String parameterName, String initValue){
		FormField field =  platformIdFromPrivateMessage(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public ProfileForm platformIdFieldForPrivateMessage(String initValue){
		return platformIdFieldForPrivateMessage("platformId",initValue);
	}
	public ProfileForm platformIdFieldForPrivateMessage(){
		return platformIdFieldForPrivateMessage("platformId","");
	}

	

	
 	public ProfileForm transferToAnotherPlatformAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("transferToAnotherPlatform/profileId/");
		this.addFormAction(action);
		return this;
	}

 

	public ProfileForm showAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("genericFormManager/show/title/desc/");
		this.addFormAction(action);
		return this;
	}
	
	
}


