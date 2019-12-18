package com.doublechaintech.messagecenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.terapico.caf.form.ImageInfo;
import com.terapico.utils.DebugUtil;

public class BaseMessagecenterFormProcessor extends BaseFormProcessor{
	protected MessagecenterUserContext userContext;
	
	public MessagecenterUserContext getUserContext() {
		return userContext;
	}
	public void setUserContext(MessagecenterUserContext userContext) {
		this.userContext = userContext;
	}
	protected void addMessageToException(MessagecenterException e, String msg) {
		Message message = new Message();
		message.setBody(msg);
		e.addErrorMessage(message);
	}
	public Map<String, Object> mapToUiForm(MessagecenterUserContext userContext) {
		return null; 
	}
}















