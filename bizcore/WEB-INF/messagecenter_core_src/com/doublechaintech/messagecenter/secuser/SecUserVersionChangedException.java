
package com.doublechaintech.messagecenter.secuser;
import com.doublechaintech.messagecenter.EntityNotFoundException;

public class SecUserVersionChangedException extends SecUserManagerException {
	private static final long serialVersionUID = 1L;
	public SecUserVersionChangedException(String string) {
		super(string);
	}


}


