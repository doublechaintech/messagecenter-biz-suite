package com.doublechaintech.messagecenter.privatemessage;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.doublechaintech.messagecenter.MessagecenterObjectPlainCustomSerializer;
public class PrivateMessageSerializer extends MessagecenterObjectPlainCustomSerializer<PrivateMessage>{

	@Override
	public void serialize(PrivateMessage privateMessage, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
			
		this.writeBaseEntityField(jgen, null, privateMessage, provider);
		
	}
}


