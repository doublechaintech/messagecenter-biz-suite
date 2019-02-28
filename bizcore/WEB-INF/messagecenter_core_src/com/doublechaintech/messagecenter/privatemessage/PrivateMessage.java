
package com.doublechaintech.messagecenter.privatemessage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import com.terapico.caf.DateTime;
import com.doublechaintech.messagecenter.BaseEntity;
import com.doublechaintech.messagecenter.SmartList;
import com.doublechaintech.messagecenter.KeyValuePair;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.doublechaintech.messagecenter.profile.Profile;
import com.doublechaintech.messagecenter.platform.Platform;

@JsonSerialize(using = PrivateMessageSerializer.class)
public class PrivateMessage extends BaseEntity implements  java.io.Serializable{

	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String TITLE_PROPERTY                 = "title"             ;
	public static final String CONTENT_PROPERTY               = "content"           ;
	public static final String SEND_TIME_PROPERTY             = "sendTime"          ;
	public static final String READ_TIME_PROPERTY             = "readTime"          ;
	public static final String SENDER_PROPERTY                = "sender"            ;
	public static final String RECEIVER_PROPERTY              = "receiver"          ;
	public static final String STATUS_PROPERTY                = "status"            ;
	public static final String PLATFORM_PROPERTY              = "platform"          ;
	public static final String VERSION_PROPERTY               = "version"           ;


	public static final String INTERNAL_TYPE="PrivateMessage";
	public String getInternalType(){
		return INTERNAL_TYPE;
	}
	
	public String getDisplayName(){
	
		String displayName = getTitle();
		if(displayName!=null){
			return displayName;
		}
		
		return super.getDisplayName();
		
	}

	private static final long serialVersionUID = 1L;
	

	protected		String              	mId                 ;
	protected		String              	mTitle              ;
	protected		String              	mContent            ;
	protected		DateTime            	mSendTime           ;
	protected		DateTime            	mReadTime           ;
	protected		Profile             	mSender             ;
	protected		Profile             	mReceiver           ;
	protected		String              	mStatus             ;
	protected		Platform            	mPlatform           ;
	protected		int                 	mVersion            ;
	
	
	
		
	public 	PrivateMessage(){
		//lazy load for all the properties
	}
	//disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){
		setSender( null );
		setReceiver( null );
		setPlatform( null );

		this.changed = true;
	}
	
	public 	PrivateMessage(String title, String content, DateTime sendTime, DateTime readTime, Profile sender, Profile receiver, String status, Platform platform)
	{
		setTitle(title);
		setContent(content);
		setSendTime(sendTime);
		setReadTime(readTime);
		setSender(sender);
		setReceiver(receiver);
		setStatus(status);
		setPlatform(platform);
	
	}
	
	//Support for changing the property
	
	public void changeProperty(String property, String newValueExpr) {
     	
		if(TITLE_PROPERTY.equals(property)){
			changeTitleProperty(newValueExpr);
		}
		if(CONTENT_PROPERTY.equals(property)){
			changeContentProperty(newValueExpr);
		}
		if(SEND_TIME_PROPERTY.equals(property)){
			changeSendTimeProperty(newValueExpr);
		}
		if(READ_TIME_PROPERTY.equals(property)){
			changeReadTimeProperty(newValueExpr);
		}
		if(STATUS_PROPERTY.equals(property)){
			changeStatusProperty(newValueExpr);
		}

      
	}
    
    
	protected void changeTitleProperty(String newValueExpr){
		String oldValue = getTitle();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateTitle(newValue);
		this.onChangeProperty(TITLE_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			
	protected void changeContentProperty(String newValueExpr){
		String oldValue = getContent();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateContent(newValue);
		this.onChangeProperty(CONTENT_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			
	protected void changeSendTimeProperty(String newValueExpr){
		DateTime oldValue = getSendTime();
		DateTime newValue = parseTimestamp(newValueExpr);
		if(equalsTimestamp(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateSendTime(newValue);
		this.onChangeProperty(SEND_TIME_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			
	protected void changeReadTimeProperty(String newValueExpr){
		DateTime oldValue = getReadTime();
		DateTime newValue = parseTimestamp(newValueExpr);
		if(equalsTimestamp(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateReadTime(newValue);
		this.onChangeProperty(READ_TIME_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			
	protected void changeStatusProperty(String newValueExpr){
		String oldValue = getStatus();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateStatus(newValue);
		this.onChangeProperty(STATUS_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			


	
	
	
	public void setId(String id){
		this.mId = trimString(id);;
	}
	public String getId(){
		return this.mId;
	}
	public PrivateMessage updateId(String id){
		this.mId = trimString(id);;
		this.changed = true;
		return this;
	}
	public void mergeId(String id){
		if(id != null) { setId(id);}
	}
	
	
	public void setTitle(String title){
		this.mTitle = trimString(title);;
	}
	public String getTitle(){
		return this.mTitle;
	}
	public PrivateMessage updateTitle(String title){
		this.mTitle = trimString(title);;
		this.changed = true;
		return this;
	}
	public void mergeTitle(String title){
		if(title != null) { setTitle(title);}
	}
	
	
	public void setContent(String content){
		this.mContent = content;;
	}
	public String getContent(){
		return this.mContent;
	}
	public PrivateMessage updateContent(String content){
		this.mContent = content;;
		this.changed = true;
		return this;
	}
	public void mergeContent(String content){
		if(content != null) { setContent(content);}
	}
	
	
	public void setSendTime(DateTime sendTime){
		this.mSendTime = sendTime;;
	}
	public DateTime getSendTime(){
		return this.mSendTime;
	}
	public PrivateMessage updateSendTime(DateTime sendTime){
		this.mSendTime = sendTime;;
		this.changed = true;
		return this;
	}
	public void mergeSendTime(DateTime sendTime){
		setSendTime(sendTime);
	}
	
	
	public void setReadTime(DateTime readTime){
		this.mReadTime = readTime;;
	}
	public DateTime getReadTime(){
		return this.mReadTime;
	}
	public PrivateMessage updateReadTime(DateTime readTime){
		this.mReadTime = readTime;;
		this.changed = true;
		return this;
	}
	public void mergeReadTime(DateTime readTime){
		setReadTime(readTime);
	}
	
	
	public void setSender(Profile sender){
		this.mSender = sender;;
	}
	public Profile getSender(){
		return this.mSender;
	}
	public PrivateMessage updateSender(Profile sender){
		this.mSender = sender;;
		this.changed = true;
		return this;
	}
	public void mergeSender(Profile sender){
		if(sender != null) { setSender(sender);}
	}
	
	
	public void clearSender(){
		setSender ( null );
		this.changed = true;
	}
	
	public void setReceiver(Profile receiver){
		this.mReceiver = receiver;;
	}
	public Profile getReceiver(){
		return this.mReceiver;
	}
	public PrivateMessage updateReceiver(Profile receiver){
		this.mReceiver = receiver;;
		this.changed = true;
		return this;
	}
	public void mergeReceiver(Profile receiver){
		if(receiver != null) { setReceiver(receiver);}
	}
	
	
	public void clearReceiver(){
		setReceiver ( null );
		this.changed = true;
	}
	
	public void setStatus(String status){
		this.mStatus = trimString(status);;
	}
	public String getStatus(){
		return this.mStatus;
	}
	public PrivateMessage updateStatus(String status){
		this.mStatus = trimString(status);;
		this.changed = true;
		return this;
	}
	public void mergeStatus(String status){
		if(status != null) { setStatus(status);}
	}
	
	
	public void setPlatform(Platform platform){
		this.mPlatform = platform;;
	}
	public Platform getPlatform(){
		return this.mPlatform;
	}
	public PrivateMessage updatePlatform(Platform platform){
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
	public PrivateMessage updateVersion(int version){
		this.mVersion = version;;
		this.changed = true;
		return this;
	}
	public void mergeVersion(int version){
		setVersion(version);
	}
	
	

	public void collectRefercences(BaseEntity owner, List<BaseEntity> entityList, String internalType){

		addToEntityList(this, entityList, getSender(), internalType);
		addToEntityList(this, entityList, getReceiver(), internalType);
		addToEntityList(this, entityList, getPlatform(), internalType);

		
	}
	
	public List<BaseEntity>  collectRefercencesFromLists(String internalType){
		
		List<BaseEntity> entityList = new ArrayList<BaseEntity>();

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
			

		return listOfList;
	}

	
	public List<KeyValuePair> keyValuePairOf(){
		List<KeyValuePair> result =  super.keyValuePairOf();

		appendKeyValuePair(result, ID_PROPERTY, getId());
		appendKeyValuePair(result, TITLE_PROPERTY, getTitle());
		appendKeyValuePair(result, CONTENT_PROPERTY, getContent());
		appendKeyValuePair(result, SEND_TIME_PROPERTY, getSendTime());
		appendKeyValuePair(result, READ_TIME_PROPERTY, getReadTime());
		appendKeyValuePair(result, SENDER_PROPERTY, getSender());
		appendKeyValuePair(result, RECEIVER_PROPERTY, getReceiver());
		appendKeyValuePair(result, STATUS_PROPERTY, getStatus());
		appendKeyValuePair(result, PLATFORM_PROPERTY, getPlatform());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());

		
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof PrivateMessage){
		
		
			PrivateMessage dest =(PrivateMessage)baseDest;
		
			dest.setId(getId());
			dest.setTitle(getTitle());
			dest.setContent(getContent());
			dest.setSendTime(getSendTime());
			dest.setReadTime(getReadTime());
			dest.setSender(getSender());
			dest.setReceiver(getReceiver());
			dest.setStatus(getStatus());
			dest.setPlatform(getPlatform());
			dest.setVersion(getVersion());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof PrivateMessage){
		
			
			PrivateMessage dest =(PrivateMessage)baseDest;
		
			dest.mergeId(getId());
			dest.mergeTitle(getTitle());
			dest.mergeContent(getContent());
			dest.mergeSendTime(getSendTime());
			dest.mergeReadTime(getReadTime());
			dest.mergeSender(getSender());
			dest.mergeReceiver(getReceiver());
			dest.mergeStatus(getStatus());
			dest.mergePlatform(getPlatform());
			dest.mergeVersion(getVersion());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("PrivateMessage{");
		stringBuilder.append("\tid='"+getId()+"';");
		stringBuilder.append("\ttitle='"+getTitle()+"';");
		stringBuilder.append("\tcontent='"+getContent()+"';");
		stringBuilder.append("\tsendTime='"+getSendTime()+"';");
		stringBuilder.append("\treadTime='"+getReadTime()+"';");
		if(getSender() != null ){
 			stringBuilder.append("\tsender='Profile("+getSender().getId()+")';");
 		}
		if(getReceiver() != null ){
 			stringBuilder.append("\treceiver='Profile("+getReceiver().getId()+")';");
 		}
		stringBuilder.append("\tstatus='"+getStatus()+"';");
		if(getPlatform() != null ){
 			stringBuilder.append("\tplatform='Platform("+getPlatform().getId()+")';");
 		}
		stringBuilder.append("\tversion='"+getVersion()+"';");
		stringBuilder.append("}");

		return stringBuilder.toString();
	}
	
	//provide number calculation function
	

}

