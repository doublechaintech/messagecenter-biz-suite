
package com.doublechaintech.messagecenter.listaccess;
import com.doublechaintech.messagecenter.EntityNotFoundException;
public class ListAccessNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 1L;
	public ListAccessNotFoundException(String string) {
		super(string);
	}

}

