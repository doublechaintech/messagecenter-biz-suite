
package com.doublechaintech.messagecenter.profile;
//import com.doublechaintech.messagecenter.EntityNotFoundException;
import com.doublechaintech.messagecenter.MessagecenterException;
import com.doublechaintech.messagecenter.Message;
import java.util.List;

public class ProfileManagerException extends MessagecenterException {
	private static final long serialVersionUID = 1L;
	public ProfileManagerException(String string) {
		super(string);
	}
	public ProfileManagerException(Message message) {
		super(message);
	}
	public ProfileManagerException(List<Message> messageList) {
		super(messageList);
	}

}


