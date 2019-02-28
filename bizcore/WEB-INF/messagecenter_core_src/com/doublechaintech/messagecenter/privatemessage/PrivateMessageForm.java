package com.doublechaintech.messagecenter.privatemessage;
import com.doublechaintech.messagecenter.BaseForm;
import com.doublechaintech.messagecenter.genericform.GenericForm;
import com.doublechaintech.messagecenter.formfield.FormField;
import com.doublechaintech.messagecenter.formaction.FormAction;
import com.doublechaintech.messagecenter.formmessage.FormMessage;
import com.doublechaintech.messagecenter.formfieldmessage.FormFieldMessage;



public class PrivateMessageForm extends BaseForm {
	
	
	public PrivateMessageForm withTitle(String title){
		this.setId(System.currentTimeMillis()+"");
		this.setTitle(title);
		return this;
	}
	
	
	

	public PrivateMessageForm privateMessageIdField(String parameterName, String initValue){
		FormField field = idFromPrivateMessage(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PrivateMessageForm privateMessageIdField(String initValue){
		return privateMessageIdField("privateMessageId",initValue);
	}
	public PrivateMessageForm privateMessageIdField(){
		return privateMessageIdField("privateMessageId","");
	}


	public PrivateMessageForm titleField(String parameterName, String initValue){
		FormField field = titleFromPrivateMessage(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PrivateMessageForm titleField(String initValue){
		return titleField("title",initValue);
	}
	public PrivateMessageForm titleField(){
		return titleField("title","");
	}


	public PrivateMessageForm contentField(String parameterName, String initValue){
		FormField field = contentFromPrivateMessage(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PrivateMessageForm contentField(String initValue){
		return contentField("content",initValue);
	}
	public PrivateMessageForm contentField(){
		return contentField("content","");
	}


	public PrivateMessageForm sendTimeField(String parameterName, String initValue){
		FormField field = sendTimeFromPrivateMessage(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PrivateMessageForm sendTimeField(String initValue){
		return sendTimeField("sendTime",initValue);
	}
	public PrivateMessageForm sendTimeField(){
		return sendTimeField("sendTime","");
	}


	public PrivateMessageForm readTimeField(String parameterName, String initValue){
		FormField field = readTimeFromPrivateMessage(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PrivateMessageForm readTimeField(String initValue){
		return readTimeField("readTime",initValue);
	}
	public PrivateMessageForm readTimeField(){
		return readTimeField("readTime","");
	}


	public PrivateMessageForm senderIdField(String parameterName, String initValue){
		FormField field = senderIdFromPrivateMessage(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PrivateMessageForm senderIdField(String initValue){
		return senderIdField("senderId",initValue);
	}
	public PrivateMessageForm senderIdField(){
		return senderIdField("senderId","");
	}


	public PrivateMessageForm receiverIdField(String parameterName, String initValue){
		FormField field = receiverIdFromPrivateMessage(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PrivateMessageForm receiverIdField(String initValue){
		return receiverIdField("receiverId",initValue);
	}
	public PrivateMessageForm receiverIdField(){
		return receiverIdField("receiverId","");
	}


	public PrivateMessageForm statusField(String parameterName, String initValue){
		FormField field = statusFromPrivateMessage(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PrivateMessageForm statusField(String initValue){
		return statusField("status",initValue);
	}
	public PrivateMessageForm statusField(){
		return statusField("status","");
	}


	public PrivateMessageForm platformIdField(String parameterName, String initValue){
		FormField field = platformIdFromPrivateMessage(parameterName, initValue);		
		this.addFormField(field);
		return this;
	}
	
	public PrivateMessageForm platformIdField(String initValue){
		return platformIdField("platformId",initValue);
	}
	public PrivateMessageForm platformIdField(){
		return platformIdField("platformId","");
	}

	
	


	public PrivateMessageForm profileIdFieldOfProfile(String parameterName, String initValue){
		FormField field =  idFromProfile(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public PrivateMessageForm profileIdFieldOfProfile(String initValue){
		return profileIdFieldOfProfile("profileId",initValue);
	}
	public PrivateMessageForm profileIdFieldOfProfile(){
		return profileIdFieldOfProfile("profileId","");
	}


	public PrivateMessageForm nameFieldOfProfile(String parameterName, String initValue){
		FormField field =  nameFromProfile(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public PrivateMessageForm nameFieldOfProfile(String initValue){
		return nameFieldOfProfile("name",initValue);
	}
	public PrivateMessageForm nameFieldOfProfile(){
		return nameFieldOfProfile("name","");
	}


	public PrivateMessageForm platformIdFieldOfProfile(String parameterName, String initValue){
		FormField field =  platformIdFromProfile(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public PrivateMessageForm platformIdFieldOfProfile(String initValue){
		return platformIdFieldOfProfile("platformId",initValue);
	}
	public PrivateMessageForm platformIdFieldOfProfile(){
		return platformIdFieldOfProfile("platformId","");
	}


	public PrivateMessageForm platformIdFieldOfPlatform(String parameterName, String initValue){
		FormField field =  idFromPlatform(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public PrivateMessageForm platformIdFieldOfPlatform(String initValue){
		return platformIdFieldOfPlatform("platformId",initValue);
	}
	public PrivateMessageForm platformIdFieldOfPlatform(){
		return platformIdFieldOfPlatform("platformId","");
	}


	public PrivateMessageForm nameFieldOfPlatform(String parameterName, String initValue){
		FormField field =  nameFromPlatform(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public PrivateMessageForm nameFieldOfPlatform(String initValue){
		return nameFieldOfPlatform("name",initValue);
	}
	public PrivateMessageForm nameFieldOfPlatform(){
		return nameFieldOfPlatform("name","");
	}


	public PrivateMessageForm introductionFieldOfPlatform(String parameterName, String initValue){
		FormField field =  introductionFromPlatform(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public PrivateMessageForm introductionFieldOfPlatform(String initValue){
		return introductionFieldOfPlatform("introduction",initValue);
	}
	public PrivateMessageForm introductionFieldOfPlatform(){
		return introductionFieldOfPlatform("introduction","");
	}


	public PrivateMessageForm currentVersionFieldOfPlatform(String parameterName, String initValue){
		FormField field =  currentVersionFromPlatform(parameterName, initValue);
		this.addFormField(field);	
		return this;
	}
	
	public PrivateMessageForm currentVersionFieldOfPlatform(String initValue){
		return currentVersionFieldOfPlatform("currentVersion",initValue);
	}
	public PrivateMessageForm currentVersionFieldOfPlatform(){
		return currentVersionFieldOfPlatform("currentVersion","");
	}

	


	

	
 	public PrivateMessageForm transferToAnotherSenderAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("transferToAnotherSender/privateMessageId/");
		this.addFormAction(action);
		return this;
	}

 	
 	public PrivateMessageForm transferToAnotherReceiverAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("transferToAnotherReceiver/privateMessageId/");
		this.addFormAction(action);
		return this;
	}

 	
 	public PrivateMessageForm transferToAnotherPlatformAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("transferToAnotherPlatform/privateMessageId/");
		this.addFormAction(action);
		return this;
	}

 

	public PrivateMessageForm showAction(){
		FormAction action = new FormAction();
		action.setLabel("显示");
		action.setLocaleKey("show");
		action.setUrl("genericFormManager/show/title/desc/");
		this.addFormAction(action);
		return this;
	}
	
	
}


