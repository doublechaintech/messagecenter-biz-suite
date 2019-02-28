
package com.doublechaintech.messagecenter.profile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import com.terapico.caf.DateTime;
import com.doublechaintech.messagecenter.BaseEntity;
import com.doublechaintech.messagecenter.SmartList;
import com.doublechaintech.messagecenter.KeyValuePair;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.doublechaintech.messagecenter.platform.Platform;
import com.doublechaintech.messagecenter.privatemessage.PrivateMessage;

@JsonSerialize(using = ProfileSerializer.class)
public class Profile extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String NAME_PROPERTY                  = "name"              ;
	public static final String PLATFORM_PROPERTY              = "platform"          ;
	public static final String VERSION_PROPERTY               = "version"           ;

	public static final String PRIVATE_MESSAGE_LIST_AS_SENDER           = "privateMessageListAsSender";
	public static final String PRIVATE_MESSAGE_LIST_AS_RECEIVER         = "privateMessageListAsReceiver";

	public static final String INTERNAL_TYPE="Profile";
	public String getInternalType(){
		return INTERNAL_TYPE;
	}
	
	public String getDisplayName(){
	
		String displayName = getName();
		if(displayName!=null){
			return displayName;
		}
		
		return super.getDisplayName();
		
	}

	private static final long serialVersionUID = 1L;
	

	protected		String              	mId                 ;
	protected		String              	mName               ;
	protected		Platform            	mPlatform           ;
	protected		int                 	mVersion            ;
	
	
	protected		SmartList<PrivateMessage>	mPrivateMessageListAsSender;
	protected		SmartList<PrivateMessage>	mPrivateMessageListAsReceiver;
	
		
	public 	Profile(){
		//lazy load for all the properties
	}
	//disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){
		setPlatform( null );

		this.changed = true;
	}
	
	public 	Profile(String name, Platform platform)
	{
		setName(name);
		setPlatform(platform);

		this.mPrivateMessageListAsSender = new SmartList<PrivateMessage>();
		this.mPrivateMessageListAsReceiver = new SmartList<PrivateMessage>();	
	}
	
	//Support for changing the property
	
	public void changeProperty(String property, String newValueExpr) {
     	
		if(NAME_PROPERTY.equals(property)){
			changeNameProperty(newValueExpr);
		}

      
	}
    
    
	protected void changeNameProperty(String newValueExpr){
		String oldValue = getName();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateName(newValue);
		this.onChangeProperty(NAME_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			


	
	
	
	public void setId(String id){
		this.mId = trimString(id);;
	}
	public String getId(){
		return this.mId;
	}
	public Profile updateId(String id){
		this.mId = trimString(id);;
		this.changed = true;
		return this;
	}
	public void mergeId(String id){
		if(id != null) { setId(id);}
	}
	
	
	public void setName(String name){
		this.mName = trimString(name);;
	}
	public String getName(){
		return this.mName;
	}
	public Profile updateName(String name){
		this.mName = trimString(name);;
		this.changed = true;
		return this;
	}
	public void mergeName(String name){
		if(name != null) { setName(name);}
	}
	
	
	public void setPlatform(Platform platform){
		this.mPlatform = platform;;
	}
	public Platform getPlatform(){
		return this.mPlatform;
	}
	public Profile updatePlatform(Platform platform){
		this.mPlatform = platform;;
		this.changed = true;
		return this;
	}
	public void mergePlatform(Platform platform){
		if(platform != null) { setPlatform(platform);}
	}
	
	
	public void clearPlatform(){
		setPlatform ( null );
		this.changed = true;
	}
	
	public void setVersion(int version){
		this.mVersion = version;;
	}
	public int getVersion(){
		return this.mVersion;
	}
	public Profile updateVersion(int version){
		this.mVersion = version;;
		this.changed = true;
		return this;
	}
	public void mergeVersion(int version){
		setVersion(version);
	}
	
	

	public  SmartList<PrivateMessage> getPrivateMessageListAsSender(){
		if(this.mPrivateMessageListAsSender == null){
			this.mPrivateMessageListAsSender = new SmartList<PrivateMessage>();
			this.mPrivateMessageListAsSender.setListInternalName (PRIVATE_MESSAGE_LIST_AS_SENDER );
			//有名字，便于做权限控制
		}
		
		return this.mPrivateMessageListAsSender;	
	}
	public  void setPrivateMessageListAsSender(SmartList<PrivateMessage> privateMessageListAsSender){
		for( PrivateMessage privateMessage:privateMessageListAsSender){
			privateMessage.setSender(this);
		}

		this.mPrivateMessageListAsSender = privateMessageListAsSender;
		this.mPrivateMessageListAsSender.setListInternalName (PRIVATE_MESSAGE_LIST_AS_SENDER );
		
	}
	
	public  void addPrivateMessageAsSender(PrivateMessage privateMessage){
		privateMessage.setSender(this);
		getPrivateMessageListAsSender().add(privateMessage);
	}
	public  void addPrivateMessageListAsSender(SmartList<PrivateMessage> privateMessageListAsSender){
		for( PrivateMessage privateMessage:privateMessageListAsSender){
			privateMessage.setSender(this);
		}
		getPrivateMessageListAsSender().addAll(privateMessageListAsSender);
	}
	public  void mergePrivateMessageListAsSender(SmartList<PrivateMessage> privateMessageListAsSender){
		if(privateMessageListAsSender==null){
			return;
		}
		if(privateMessageListAsSender.isEmpty()){
			return;
		}
		addPrivateMessageListAsSender( privateMessageListAsSender );
		
	}
	public  PrivateMessage removePrivateMessageAsSender(PrivateMessage privateMessageIndex){
		
		int index = getPrivateMessageListAsSender().indexOf(privateMessageIndex);
        if(index < 0){
        	String message = "PrivateMessage("+privateMessageIndex.getId()+") with version='"+privateMessageIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        PrivateMessage privateMessage = getPrivateMessageListAsSender().get(index);        
        // privateMessage.clearSender(); //disconnect with Sender
        privateMessage.clearFromAll(); //disconnect with Sender
		
		boolean result = getPrivateMessageListAsSender().planToRemove(privateMessage);
        if(!result){
        	String message = "PrivateMessage("+privateMessageIndex.getId()+") with version='"+privateMessageIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return privateMessage;
        
	
	}
	//断舍离
	public  void breakWithPrivateMessageAsSender(PrivateMessage privateMessage){
		
		if(privateMessage == null){
			return;
		}
		privateMessage.setSender(null);
		//getPrivateMessageListAsSender().remove();
	
	}
	
	public  boolean hasPrivateMessageAsSender(PrivateMessage privateMessage){
	
		return getPrivateMessageListAsSender().contains(privateMessage);
  
	}
	
	public void copyPrivateMessageAsSenderFrom(PrivateMessage privateMessage) {

		PrivateMessage privateMessageInList = findThePrivateMessageAsSender(privateMessage);
		PrivateMessage newPrivateMessage = new PrivateMessage();
		privateMessageInList.copyTo(newPrivateMessage);
		newPrivateMessage.setVersion(0);//will trigger copy
		getPrivateMessageListAsSender().add(newPrivateMessage);
		addItemToFlexiableObject(COPIED_CHILD, newPrivateMessage);
	}
	
	public  PrivateMessage findThePrivateMessageAsSender(PrivateMessage privateMessage){
		
		int index =  getPrivateMessageListAsSender().indexOf(privateMessage);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "PrivateMessage("+privateMessage.getId()+") with version='"+privateMessage.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getPrivateMessageListAsSender().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpPrivateMessageListAsSender(){
		getPrivateMessageListAsSender().clear();
	}
	
	
	


	public  SmartList<PrivateMessage> getPrivateMessageListAsReceiver(){
		if(this.mPrivateMessageListAsReceiver == null){
			this.mPrivateMessageListAsReceiver = new SmartList<PrivateMessage>();
			this.mPrivateMessageListAsReceiver.setListInternalName (PRIVATE_MESSAGE_LIST_AS_RECEIVER );
			//有名字，便于做权限控制
		}
		
		return this.mPrivateMessageListAsReceiver;	
	}
	public  void setPrivateMessageListAsReceiver(SmartList<PrivateMessage> privateMessageListAsReceiver){
		for( PrivateMessage privateMessage:privateMessageListAsReceiver){
			privateMessage.setReceiver(this);
		}

		this.mPrivateMessageListAsReceiver = privateMessageListAsReceiver;
		this.mPrivateMessageListAsReceiver.setListInternalName (PRIVATE_MESSAGE_LIST_AS_RECEIVER );
		
	}
	
	public  void addPrivateMessageAsReceiver(PrivateMessage privateMessage){
		privateMessage.setReceiver(this);
		getPrivateMessageListAsReceiver().add(privateMessage);
	}
	public  void addPrivateMessageListAsReceiver(SmartList<PrivateMessage> privateMessageListAsReceiver){
		for( PrivateMessage privateMessage:privateMessageListAsReceiver){
			privateMessage.setReceiver(this);
		}
		getPrivateMessageListAsReceiver().addAll(privateMessageListAsReceiver);
	}
	public  void mergePrivateMessageListAsReceiver(SmartList<PrivateMessage> privateMessageListAsReceiver){
		if(privateMessageListAsReceiver==null){
			return;
		}
		if(privateMessageListAsReceiver.isEmpty()){
			return;
		}
		addPrivateMessageListAsReceiver( privateMessageListAsReceiver );
		
	}
	public  PrivateMessage removePrivateMessageAsReceiver(PrivateMessage privateMessageIndex){
		
		int index = getPrivateMessageListAsReceiver().indexOf(privateMessageIndex);
        if(index < 0){
        	String message = "PrivateMessage("+privateMessageIndex.getId()+") with version='"+privateMessageIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        PrivateMessage privateMessage = getPrivateMessageListAsReceiver().get(index);        
        // privateMessage.clearReceiver(); //disconnect with Receiver
        privateMessage.clearFromAll(); //disconnect with Receiver
		
		boolean result = getPrivateMessageListAsReceiver().planToRemove(privateMessage);
        if(!result){
        	String message = "PrivateMessage("+privateMessageIndex.getId()+") with version='"+privateMessageIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return privateMessage;
        
	
	}
	//断舍离
	public  void breakWithPrivateMessageAsReceiver(PrivateMessage privateMessage){
		
		if(privateMessage == null){
			return;
		}
		privateMessage.setSender(null);
		//getPrivateMessageListAsReceiver().remove();
	
	}
	
	public  boolean hasPrivateMessageAsReceiver(PrivateMessage privateMessage){
	
		return getPrivateMessageListAsReceiver().contains(privateMessage);
  
	}
	
	public void copyPrivateMessageAsReceiverFrom(PrivateMessage privateMessage) {

		PrivateMessage privateMessageInList = findThePrivateMessageAsReceiver(privateMessage);
		PrivateMessage newPrivateMessage = new PrivateMessage();
		privateMessageInList.copyTo(newPrivateMessage);
		newPrivateMessage.setVersion(0);//will trigger copy
		getPrivateMessageListAsReceiver().add(newPrivateMessage);
		addItemToFlexiableObject(COPIED_CHILD, newPrivateMessage);
	}
	
	public  PrivateMessage findThePrivateMessageAsReceiver(PrivateMessage privateMessage){
		
		int index =  getPrivateMessageListAsReceiver().indexOf(privateMessage);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "PrivateMessage("+privateMessage.getId()+") with version='"+privateMessage.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getPrivateMessageListAsReceiver().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpPrivateMessageListAsReceiver(){
		getPrivateMessageListAsReceiver().clear();
	}
	
	
	


	public void collectRefercences(BaseEntity owner, List<BaseEntity> entityList, String internalType){

		addToEntityList(this, entityList, getPlatform(), internalType);

		
	}
	
	public List<BaseEntity>  collectRefercencesFromLists(String internalType){
		
		List<BaseEntity> entityList = new ArrayList<BaseEntity>();
		collectFromList(this, entityList, getPrivateMessageListAsSender(), internalType);
		collectFromList(this, entityList, getPrivateMessageListAsReceiver(), internalType);

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
		listOfList.add( getPrivateMessageListAsSender());
		listOfList.add( getPrivateMessageListAsReceiver());
			

		return listOfList;
	}

	
	public List<KeyValuePair> keyValuePairOf(){
		List<KeyValuePair> result =  super.keyValuePairOf();

		appendKeyValuePair(result, ID_PROPERTY, getId());
		appendKeyValuePair(result, NAME_PROPERTY, getName());
		appendKeyValuePair(result, PLATFORM_PROPERTY, getPlatform());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());
		appendKeyValuePair(result, PRIVATE_MESSAGE_LIST_AS_SENDER, getPrivateMessageListAsSender());
		if(!getPrivateMessageListAsSender().isEmpty()){
			appendKeyValuePair(result, "privateMessageAsSenderCount", getPrivateMessageListAsSender().getTotalCount());
			appendKeyValuePair(result, "privateMessageAsSenderCurrentPageNumber", getPrivateMessageListAsSender().getCurrentPageNumber());
		}
		appendKeyValuePair(result, PRIVATE_MESSAGE_LIST_AS_RECEIVER, getPrivateMessageListAsReceiver());
		if(!getPrivateMessageListAsReceiver().isEmpty()){
			appendKeyValuePair(result, "privateMessageAsReceiverCount", getPrivateMessageListAsReceiver().getTotalCount());
			appendKeyValuePair(result, "privateMessageAsReceiverCurrentPageNumber", getPrivateMessageListAsReceiver().getCurrentPageNumber());
		}

		
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Profile){
		
		
			Profile dest =(Profile)baseDest;
		
			dest.setId(getId());
			dest.setName(getName());
			dest.setPlatform(getPlatform());
			dest.setVersion(getVersion());
			dest.setPrivateMessageListAsSender(getPrivateMessageListAsSender());
			dest.setPrivateMessageListAsReceiver(getPrivateMessageListAsReceiver());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof Profile){
		
			
			Profile dest =(Profile)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergePlatform(getPlatform());
			dest.mergeVersion(getVersion());
			dest.mergePrivateMessageListAsSender(getPrivateMessageListAsSender());
			dest.mergePrivateMessageListAsReceiver(getPrivateMessageListAsReceiver());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("Profile{");
		stringBuilder.append("\tid='"+getId()+"';");
		stringBuilder.append("\tname='"+getName()+"';");
		if(getPlatform() != null ){
 			stringBuilder.append("\tplatform='Platform("+getPlatform().getId()+")';");
 		}
		stringBuilder.append("\tversion='"+getVersion()+"';");
		stringBuilder.append("}");

		return stringBuilder.toString();
	}
	
	//provide number calculation function
	

}

