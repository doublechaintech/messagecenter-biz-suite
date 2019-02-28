
package com.doublechaintech.messagecenter.secuser;
import com.doublechaintech.messagecenter.EntityNotFoundException;
public class SecUserNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 1L;
	public SecUserNotFoundException(String string) {
		super(string);
	}

}

