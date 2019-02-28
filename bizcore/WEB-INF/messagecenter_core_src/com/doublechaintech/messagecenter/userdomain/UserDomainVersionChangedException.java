
package com.doublechaintech.messagecenter.userdomain;
import com.doublechaintech.messagecenter.EntityNotFoundException;

public class UserDomainVersionChangedException extends UserDomainManagerException {
	private static final long serialVersionUID = 1L;
	public UserDomainVersionChangedException(String string) {
		super(string);
	}


}


