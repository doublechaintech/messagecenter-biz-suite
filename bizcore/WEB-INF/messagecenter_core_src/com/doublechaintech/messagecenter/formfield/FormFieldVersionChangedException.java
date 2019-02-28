
package com.doublechaintech.messagecenter.formfield;
import com.doublechaintech.messagecenter.EntityNotFoundException;

public class FormFieldVersionChangedException extends FormFieldManagerException {
	private static final long serialVersionUID = 1L;
	public FormFieldVersionChangedException(String string) {
		super(string);
	}


}


