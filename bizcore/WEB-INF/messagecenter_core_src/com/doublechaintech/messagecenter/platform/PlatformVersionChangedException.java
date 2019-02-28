
package com.doublechaintech.messagecenter.platform;
import com.doublechaintech.messagecenter.EntityNotFoundException;

public class PlatformVersionChangedException extends PlatformManagerException {
	private static final long serialVersionUID = 1L;
	public PlatformVersionChangedException(String string) {
		super(string);
	}


}


