
package com.doublechaintech.messagecenter.userwhitelist;
import com.doublechaintech.messagecenter.EntityNotFoundException;
public class UserWhiteListNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 1L;
	public UserWhiteListNotFoundException(String string) {
		super(string);
	}

}

