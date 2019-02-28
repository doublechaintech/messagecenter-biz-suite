
package com.doublechaintech.messagecenter.profile;
import com.doublechaintech.messagecenter.CommonTokens;
import java.util.Map;
public class ProfileTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="profile";
	
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
	protected ProfileTokens(){
		//ensure not initialized outside the class
	}
	public  static  ProfileTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		ProfileTokens tokens = new ProfileTokens(options);
		return tokens;
		
	}
	protected ProfileTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public ProfileTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static ProfileTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected ProfileTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static ProfileTokens start(){
		return new ProfileTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static ProfileTokens allTokens(){
		
		return start()
			.withPlatform()
			.withPrivateMessageListAsSender()
			.withPrivateMessageListAsReceiver();
	
	}
	public static ProfileTokens withoutListsTokens(){
		
		return start()
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
	
	public ProfileTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String PLATFORM = "platform";
	public String getPlatform(){
		return PLATFORM;
	}
	public ProfileTokens withPlatform(){		
		addSimpleOptions(PLATFORM);
		return this;
	}
	
	
	protected static final String PRIVATE_MESSAGE_LIST_AS_SENDER = "privateMessageListAsSender";
	public String getPrivateMessageListAsSender(){
		return PRIVATE_MESSAGE_LIST_AS_SENDER;
	}
	public ProfileTokens withPrivateMessageListAsSender(){		
		addSimpleOptions(PRIVATE_MESSAGE_LIST_AS_SENDER);
		return this;
	}
	public ProfileTokens analyzePrivateMessageListAsSender(){		
		addSimpleOptions(PRIVATE_MESSAGE_LIST_AS_SENDER+".anaylze");
		return this;
	}
	public boolean analyzePrivateMessageListAsSenderEnabled(){		
		
		if(checkOptions(this.options(), PRIVATE_MESSAGE_LIST_AS_SENDER+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public ProfileTokens extractMoreFromPrivateMessageListAsSender(String idsSeperatedWithComma){		
		addSimpleOptions(PRIVATE_MESSAGE_LIST_AS_SENDER+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int privateMessageListAsSenderSortCounter = 0;
	public ProfileTokens sortPrivateMessageListAsSenderWith(String field, String descOrAsc){		
		addSortMoreOptions(PRIVATE_MESSAGE_LIST_AS_SENDER,privateMessageListAsSenderSortCounter++, field, descOrAsc);
		return this;
	}
	private int privateMessageListAsSenderSearchCounter = 0;
	public ProfileTokens searchPrivateMessageListAsSenderWith(String field, String verb, String value){		
		addSearchMoreOptions(PRIVATE_MESSAGE_LIST_AS_SENDER,privateMessageListAsSenderSearchCounter++, field, verb, value);
		return this;
	}
	
	public ProfileTokens searchAllTextOfPrivateMessageListAsSender(String verb, String value){	
		String field = "id|title|content|status";
		addSearchMoreOptions(PRIVATE_MESSAGE_LIST_AS_SENDER,privateMessageListAsSenderSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ProfileTokens rowsPerPageOfPrivateMessageListAsSender(int rowsPerPage){		
		addSimpleOptions(PRIVATE_MESSAGE_LIST_AS_SENDER+"RowsPerPage",rowsPerPage);
		return this;
	}
	public ProfileTokens currentPageNumberOfPrivateMessageListAsSender(int currentPageNumber){		
		addSimpleOptions(PRIVATE_MESSAGE_LIST_AS_SENDER+"CurrentPage",currentPageNumber);
		return this;
	}
	public ProfileTokens retainColumnsOfPrivateMessageListAsSender(String[] columns){		
		addSimpleOptions(PRIVATE_MESSAGE_LIST_AS_SENDER+"RetainColumns",columns);
		return this;
	}
	public ProfileTokens excludeColumnsOfPrivateMessageListAsSender(String[] columns){		
		addSimpleOptions(PRIVATE_MESSAGE_LIST_AS_SENDER+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String PRIVATE_MESSAGE_LIST_AS_RECEIVER = "privateMessageListAsReceiver";
	public String getPrivateMessageListAsReceiver(){
		return PRIVATE_MESSAGE_LIST_AS_RECEIVER;
	}
	public ProfileTokens withPrivateMessageListAsReceiver(){		
		addSimpleOptions(PRIVATE_MESSAGE_LIST_AS_RECEIVER);
		return this;
	}
	public ProfileTokens analyzePrivateMessageListAsReceiver(){		
		addSimpleOptions(PRIVATE_MESSAGE_LIST_AS_RECEIVER+".anaylze");
		return this;
	}
	public boolean analyzePrivateMessageListAsReceiverEnabled(){		
		
		if(checkOptions(this.options(), PRIVATE_MESSAGE_LIST_AS_RECEIVER+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public ProfileTokens extractMoreFromPrivateMessageListAsReceiver(String idsSeperatedWithComma){		
		addSimpleOptions(PRIVATE_MESSAGE_LIST_AS_RECEIVER+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int privateMessageListAsReceiverSortCounter = 0;
	public ProfileTokens sortPrivateMessageListAsReceiverWith(String field, String descOrAsc){		
		addSortMoreOptions(PRIVATE_MESSAGE_LIST_AS_RECEIVER,privateMessageListAsReceiverSortCounter++, field, descOrAsc);
		return this;
	}
	private int privateMessageListAsReceiverSearchCounter = 0;
	public ProfileTokens searchPrivateMessageListAsReceiverWith(String field, String verb, String value){		
		addSearchMoreOptions(PRIVATE_MESSAGE_LIST_AS_RECEIVER,privateMessageListAsReceiverSearchCounter++, field, verb, value);
		return this;
	}
	
	public ProfileTokens searchAllTextOfPrivateMessageListAsReceiver(String verb, String value){	
		String field = "id|title|content|status";
		addSearchMoreOptions(PRIVATE_MESSAGE_LIST_AS_RECEIVER,privateMessageListAsReceiverSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public ProfileTokens rowsPerPageOfPrivateMessageListAsReceiver(int rowsPerPage){		
		addSimpleOptions(PRIVATE_MESSAGE_LIST_AS_RECEIVER+"RowsPerPage",rowsPerPage);
		return this;
	}
	public ProfileTokens currentPageNumberOfPrivateMessageListAsReceiver(int currentPageNumber){		
		addSimpleOptions(PRIVATE_MESSAGE_LIST_AS_RECEIVER+"CurrentPage",currentPageNumber);
		return this;
	}
	public ProfileTokens retainColumnsOfPrivateMessageListAsReceiver(String[] columns){		
		addSimpleOptions(PRIVATE_MESSAGE_LIST_AS_RECEIVER+"RetainColumns",columns);
		return this;
	}
	public ProfileTokens excludeColumnsOfPrivateMessageListAsReceiver(String[] columns){		
		addSimpleOptions(PRIVATE_MESSAGE_LIST_AS_RECEIVER+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	
	public  ProfileTokens searchEntireObjectText(String verb, String value){
		
		searchAllTextOfPrivateMessageListAsSender(verb, value);	
		searchAllTextOfPrivateMessageListAsReceiver(verb, value);	
		return this;
	}
}

