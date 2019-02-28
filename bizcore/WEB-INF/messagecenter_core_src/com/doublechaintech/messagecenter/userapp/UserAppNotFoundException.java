
package com.doublechaintech.messagecenter.userapp;
import com.doublechaintech.messagecenter.EntityNotFoundException;
public class UserAppNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 1L;
	public UserAppNotFoundException(String string) {
		super(string);
	}

}

