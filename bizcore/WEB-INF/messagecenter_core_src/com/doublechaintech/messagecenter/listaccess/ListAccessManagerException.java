
package com.doublechaintech.messagecenter.listaccess;
//import com.doublechaintech.messagecenter.EntityNotFoundException;
import com.doublechaintech.messagecenter.MessagecenterException;
import com.doublechaintech.messagecenter.Message;
import java.util.List;

public class ListAccessManagerException extends MessagecenterException {
	private static final long serialVersionUID = 1L;
	public ListAccessManagerException(String string) {
		super(string);
	}
	public ListAccessManagerException(Message message) {
		super(message);
	}
	public ListAccessManagerException(List<Message> messageList) {
		super(messageList);
	}

}


