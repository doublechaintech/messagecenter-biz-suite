
package com.doublechaintech.messagecenter.secuser;
//import com.doublechaintech.messagecenter.EntityNotFoundException;
import com.doublechaintech.messagecenter.MessagecenterException;
import com.doublechaintech.messagecenter.Message;
import java.util.List;

public class SecUserManagerException extends MessagecenterException {
	private static final long serialVersionUID = 1L;
	public SecUserManagerException(String string) {
		super(string);
	}
	public SecUserManagerException(Message message) {
		super(message);
	}
	public SecUserManagerException(List<Message> messageList) {
		super(messageList);
	}

}


