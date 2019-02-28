
package com.doublechaintech.messagecenter.objectaccess;
import com.doublechaintech.messagecenter.EntityNotFoundException;
public class ObjectAccessNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 1L;
	public ObjectAccessNotFoundException(String string) {
		super(string);
	}

}

