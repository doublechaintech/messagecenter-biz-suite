
package com.doublechaintech.messagecenter.userapp;
import com.doublechaintech.messagecenter.EntityNotFoundException;

public class UserAppVersionChangedException extends UserAppManagerException {
	private static final long serialVersionUID = 1L;
	public UserAppVersionChangedException(String string) {
		super(string);
	}


}


