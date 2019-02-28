
package com.doublechaintech.messagecenter.privatemessage;
import com.doublechaintech.messagecenter.CommonTokens;
import java.util.Map;
public class PrivateMessageTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="privateMessage";
	
	public static boolean checkOptions(Map<String,Object> options, String optionToCheck){
		
		if(options==null){
 			return false; //completely no option here
 		}
 		if(options.containsKey(ALL)){
 			//danger, debug only, might load the entire database!, really terrible
 			return true;
 		}
		String ownerKey = getOwnerObjectKey();
		Object ownerObject =(String) options.get(ownerKey);
		if(ownerObject ==  null){
			return false;
		}
		if(!ownerObject.equals(OWNER_OBJECT_NAME)){ //is the owner? 
			return false; 
		}
		
 		if(options.containsKey(optionToCheck)){
 			//options.remove(optionToCheck);
 			//consume the key, can not use any more to extract the data with the same token.			
 			return true;
 		}
 		
 		return false;
	
	}
	protected PrivateMessageTokens(){
		//ensure not initialized outside the class
	}
	public  static  PrivateMessageTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		PrivateMessageTokens tokens = new PrivateMessageTokens(options);
		return tokens;
		
	}
	protected PrivateMessageTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public PrivateMessageTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static PrivateMessageTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected PrivateMessageTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static PrivateMessageTokens start(){
		return new PrivateMessageTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static PrivateMessageTokens allTokens(){
		
		return start()
			.withSender()
			.withReceiver()
			.withPlatform();
	
	}
	public static PrivateMessageTokens withoutListsTokens(){
		
		return start()
			.withSender()
			.withReceiver()
			.withPlatform();
	
	}
	
	public static Map <String,Object> all(){
		return allTokens().done();
	}
	public static Map <String,Object> withoutLists(){
		return withoutListsTokens().done();
	}
	public static Map <String,Object> empty(){
		return start().done();
	}
	
	public PrivateMessageTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String SENDER = "sender";
	public String getSender(){
		return SENDER;
	}
	public PrivateMessageTokens withSender(){		
		addSimpleOptions(SENDER);
		return this;
	}
	
	
	protected static final String RECEIVER = "receiver";
	public String getReceiver(){
		return RECEIVER;
	}
	public PrivateMessageTokens withReceiver(){		
		addSimpleOptions(RECEIVER);
		return this;
	}
	
	
	protected static final String PLATFORM = "platform";
	public String getPlatform(){
		return PLATFORM;
	}
	public PrivateMessageTokens withPlatform(){		
		addSimpleOptions(PLATFORM);
		return this;
	}
	
	
	
	public  PrivateMessageTokens searchEntireObjectText(String verb, String value){
		
		return this;
	}
}

