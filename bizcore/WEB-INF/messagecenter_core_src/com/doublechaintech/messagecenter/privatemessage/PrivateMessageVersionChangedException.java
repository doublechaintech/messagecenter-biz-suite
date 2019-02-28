
package com.doublechaintech.messagecenter.privatemessage;
import com.doublechaintech.messagecenter.EntityNotFoundException;

public class PrivateMessageVersionChangedException extends PrivateMessageManagerException {
	private static final long serialVersionUID = 1L;
	public PrivateMessageVersionChangedException(String string) {
		super(string);
	}


}


