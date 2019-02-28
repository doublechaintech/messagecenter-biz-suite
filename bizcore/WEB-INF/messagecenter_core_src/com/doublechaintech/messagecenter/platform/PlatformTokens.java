
package com.doublechaintech.messagecenter.platform;
import com.doublechaintech.messagecenter.CommonTokens;
import java.util.Map;
public class PlatformTokens extends CommonTokens{

	static final String ALL="__all__"; //do not assign this to common users.
	static final String SELF="__self__";
	static final String OWNER_OBJECT_NAME="platform";
	
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
	protected PlatformTokens(){
		//ensure not initialized outside the class
	}
	public  static  PlatformTokens of(Map<String,Object> options){
		//ensure not initialized outside the class
		PlatformTokens tokens = new PlatformTokens(options);
		return tokens;
		
	}
	protected PlatformTokens(Map<String,Object> options){
		this.options = options;
	}
	
	public PlatformTokens merge(String [] tokens){
		this.parseTokens(tokens);
		return this;
	}
	
	public static PlatformTokens mergeAll(String [] tokens){
		
		return allTokens().merge(tokens);
	}
	
	protected PlatformTokens setOwnerObject(String objectName){
		ensureOptions();
		addSimpleOptions(getOwnerObjectKey(), objectName);
		return this;
	}
	
	
	public static PlatformTokens start(){
		return new PlatformTokens().setOwnerObject(OWNER_OBJECT_NAME);
	}
	
	protected static PlatformTokens allTokens(){
		
		return start()
			.withProfileList()
			.withPrivateMessageList();
	
	}
	public static PlatformTokens withoutListsTokens(){
		
		return start();
	
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
	
	public PlatformTokens analyzeAllLists(){		
		addSimpleOptions(ALL_LISTS_ANALYZE);
		return this;
	}

	protected static final String PROFILE_LIST = "profileList";
	public String getProfileList(){
		return PROFILE_LIST;
	}
	public PlatformTokens withProfileList(){		
		addSimpleOptions(PROFILE_LIST);
		return this;
	}
	public PlatformTokens analyzeProfileList(){		
		addSimpleOptions(PROFILE_LIST+".anaylze");
		return this;
	}
	public boolean analyzeProfileListEnabled(){		
		
		if(checkOptions(this.options(), PROFILE_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public PlatformTokens extractMoreFromProfileList(String idsSeperatedWithComma){		
		addSimpleOptions(PROFILE_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int profileListSortCounter = 0;
	public PlatformTokens sortProfileListWith(String field, String descOrAsc){		
		addSortMoreOptions(PROFILE_LIST,profileListSortCounter++, field, descOrAsc);
		return this;
	}
	private int profileListSearchCounter = 0;
	public PlatformTokens searchProfileListWith(String field, String verb, String value){		
		addSearchMoreOptions(PROFILE_LIST,profileListSearchCounter++, field, verb, value);
		return this;
	}
	
	public PlatformTokens searchAllTextOfProfileList(String verb, String value){	
		String field = "id|name";
		addSearchMoreOptions(PROFILE_LIST,profileListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens rowsPerPageOfProfileList(int rowsPerPage){		
		addSimpleOptions(PROFILE_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public PlatformTokens currentPageNumberOfProfileList(int currentPageNumber){		
		addSimpleOptions(PROFILE_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public PlatformTokens retainColumnsOfProfileList(String[] columns){		
		addSimpleOptions(PROFILE_LIST+"RetainColumns",columns);
		return this;
	}
	public PlatformTokens excludeColumnsOfProfileList(String[] columns){		
		addSimpleOptions(PROFILE_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	protected static final String PRIVATE_MESSAGE_LIST = "privateMessageList";
	public String getPrivateMessageList(){
		return PRIVATE_MESSAGE_LIST;
	}
	public PlatformTokens withPrivateMessageList(){		
		addSimpleOptions(PRIVATE_MESSAGE_LIST);
		return this;
	}
	public PlatformTokens analyzePrivateMessageList(){		
		addSimpleOptions(PRIVATE_MESSAGE_LIST+".anaylze");
		return this;
	}
	public boolean analyzePrivateMessageListEnabled(){		
		
		if(checkOptions(this.options(), PRIVATE_MESSAGE_LIST+".anaylze")){
			return true; //most of the case, should call here
		}
		//if not true, then query for global setting
		return checkOptions(this.options(), ALL_LISTS_ANALYZE);
	}
	public PlatformTokens extractMoreFromPrivateMessageList(String idsSeperatedWithComma){		
		addSimpleOptions(PRIVATE_MESSAGE_LIST+".extractIds", idsSeperatedWithComma);
		return this;
	}
	
	
	
	
	private int privateMessageListSortCounter = 0;
	public PlatformTokens sortPrivateMessageListWith(String field, String descOrAsc){		
		addSortMoreOptions(PRIVATE_MESSAGE_LIST,privateMessageListSortCounter++, field, descOrAsc);
		return this;
	}
	private int privateMessageListSearchCounter = 0;
	public PlatformTokens searchPrivateMessageListWith(String field, String verb, String value){		
		addSearchMoreOptions(PRIVATE_MESSAGE_LIST,privateMessageListSearchCounter++, field, verb, value);
		return this;
	}
	
	public PlatformTokens searchAllTextOfPrivateMessageList(String verb, String value){	
		String field = "id|title|content|status";
		addSearchMoreOptions(PRIVATE_MESSAGE_LIST,privateMessageListSearchCounter++, field, verb, value);
		return this;
	}
	
	
	
	public PlatformTokens rowsPerPageOfPrivateMessageList(int rowsPerPage){		
		addSimpleOptions(PRIVATE_MESSAGE_LIST+"RowsPerPage",rowsPerPage);
		return this;
	}
	public PlatformTokens currentPageNumberOfPrivateMessageList(int currentPageNumber){		
		addSimpleOptions(PRIVATE_MESSAGE_LIST+"CurrentPage",currentPageNumber);
		return this;
	}
	public PlatformTokens retainColumnsOfPrivateMessageList(String[] columns){		
		addSimpleOptions(PRIVATE_MESSAGE_LIST+"RetainColumns",columns);
		return this;
	}
	public PlatformTokens excludeColumnsOfPrivateMessageList(String[] columns){		
		addSimpleOptions(PRIVATE_MESSAGE_LIST+"ExcludeColumns",columns);
		return this;
	}
	
	
		
	
	public  PlatformTokens searchEntireObjectText(String verb, String value){
		
		searchAllTextOfProfileList(verb, value);	
		searchAllTextOfPrivateMessageList(verb, value);	
		return this;
	}
}

