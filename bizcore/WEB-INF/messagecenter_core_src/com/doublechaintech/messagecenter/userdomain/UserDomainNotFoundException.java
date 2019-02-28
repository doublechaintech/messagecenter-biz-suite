
package com.doublechaintech.messagecenter.userdomain;
import com.doublechaintech.messagecenter.EntityNotFoundException;
public class UserDomainNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 1L;
	public UserDomainNotFoundException(String string) {
		super(string);
	}

}

