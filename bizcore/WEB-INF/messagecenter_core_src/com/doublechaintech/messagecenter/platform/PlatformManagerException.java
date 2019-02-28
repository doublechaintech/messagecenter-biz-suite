
package com.doublechaintech.messagecenter.platform;
//import com.doublechaintech.messagecenter.EntityNotFoundException;
import com.doublechaintech.messagecenter.MessagecenterException;
import com.doublechaintech.messagecenter.Message;
import java.util.List;

public class PlatformManagerException extends MessagecenterException {
	private static final long serialVersionUID = 1L;
	public PlatformManagerException(String string) {
		super(string);
	}
	public PlatformManagerException(Message message) {
		super(message);
	}
	public PlatformManagerException(List<Message> messageList) {
		super(messageList);
	}

}


