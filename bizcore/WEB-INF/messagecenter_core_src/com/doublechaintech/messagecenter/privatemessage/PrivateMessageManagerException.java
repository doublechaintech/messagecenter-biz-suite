
package com.doublechaintech.messagecenter.privatemessage;
//import com.doublechaintech.messagecenter.EntityNotFoundException;
import com.doublechaintech.messagecenter.MessagecenterException;
import com.doublechaintech.messagecenter.Message;
import java.util.List;

public class PrivateMessageManagerException extends MessagecenterException {
	private static final long serialVersionUID = 1L;
	public PrivateMessageManagerException(String string) {
		super(string);
	}
	public PrivateMessageManagerException(Message message) {
		super(message);
	}
	public PrivateMessageManagerException(List<Message> messageList) {
		super(messageList);
	}

}


