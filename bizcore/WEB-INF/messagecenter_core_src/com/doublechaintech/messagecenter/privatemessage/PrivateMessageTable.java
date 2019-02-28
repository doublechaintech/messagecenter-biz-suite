
package com.doublechaintech.messagecenter.privatemessage;
import com.doublechaintech.messagecenter.AccessKey;


public class PrivateMessageTable{


	public static AccessKey withId(Object value){
		AccessKey accessKey = new AccessKey();
		accessKey.setColumnName(COLUMN_ID);
		accessKey.setValue(value);
		return accessKey;
	}
	//Add extra identifiers
	

	//only this package can use this, so the scope is default, not public, not private either nor protected
	static final String TABLE_NAME="private_message_data";
	static final String COLUMN_ID = "id";
	static final String COLUMN_TITLE = "title";
	static final String COLUMN_CONTENT = "content";
	static final String COLUMN_SEND_TIME = "send_time";
	static final String COLUMN_READ_TIME = "read_time";
	static final String COLUMN_SENDER = "sender";
	static final String COLUMN_RECEIVER = "receiver";
	static final String COLUMN_STATUS = "status";
	static final String COLUMN_PLATFORM = "platform";
	static final String COLUMN_VERSION = "version";
 
	static final String []ALL_CLOUMNS = {COLUMN_ID, 
		COLUMN_TITLE, COLUMN_CONTENT, COLUMN_SEND_TIME, COLUMN_READ_TIME, COLUMN_SENDER, COLUMN_RECEIVER, COLUMN_STATUS, COLUMN_PLATFORM, 
		COLUMN_VERSION};
	static final String []NORMAL_CLOUMNS = {
		COLUMN_TITLE, COLUMN_CONTENT, COLUMN_SEND_TIME, COLUMN_READ_TIME, COLUMN_SENDER, COLUMN_RECEIVER, COLUMN_STATUS, COLUMN_PLATFORM
		};
	
	
}


