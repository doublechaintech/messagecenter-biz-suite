
package com.doublechaintech.messagecenter.profile;
import com.doublechaintech.messagecenter.EntityNotFoundException;

public class ProfileVersionChangedException extends ProfileManagerException {
	private static final long serialVersionUID = 1L;
	public ProfileVersionChangedException(String string) {
		super(string);
	}


}


