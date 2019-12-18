
package com.doublechaintech.messagecenter.privatemessage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.messagecenter.BaseRowMapper;
import com.doublechaintech.messagecenter.profile.Profile;
import com.doublechaintech.messagecenter.platform.Platform;

public class PrivateMessageMapper extends BaseRowMapper<PrivateMessage>{
	
	protected PrivateMessage internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		PrivateMessage privateMessage = getPrivateMessage();		
		 		
 		setId(privateMessage, rs, rowNumber); 		
 		setTitle(privateMessage, rs, rowNumber); 		
 		setContent(privateMessage, rs, rowNumber); 		
 		setSendTime(privateMessage, rs, rowNumber); 		
 		setReadTime(privateMessage, rs, rowNumber); 		
 		setSender(privateMessage, rs, rowNumber); 		
 		setReceiver(privateMessage, rs, rowNumber); 		
 		setStatus(privateMessage, rs, rowNumber); 		
 		setPlatform(privateMessage, rs, rowNumber); 		
 		setVersion(privateMessage, rs, rowNumber);

		return privateMessage;
	}
	
	protected PrivateMessage getPrivateMessage(){
		return new PrivateMessage();
	}		
		
	protected void setId(PrivateMessage privateMessage, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String id = rs.getString(PrivateMessageTable.COLUMN_ID);
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		privateMessage.setId(id);
	}
		
	protected void setTitle(PrivateMessage privateMessage, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String title = rs.getString(PrivateMessageTable.COLUMN_TITLE);
		if(title == null){
			//do nothing when nothing found in database
			return;
		}
		
		privateMessage.setTitle(title);
	}
		
	protected void setContent(PrivateMessage privateMessage, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String content = rs.getString(PrivateMessageTable.COLUMN_CONTENT);
		if(content == null){
			//do nothing when nothing found in database
			return;
		}
		
		privateMessage.setContent(content);
	}
		
	protected void setSendTime(PrivateMessage privateMessage, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Date sendTime = rs.getTimestamp(PrivateMessageTable.COLUMN_SEND_TIME);
		if(sendTime == null){
			//do nothing when nothing found in database
			return;
		}
		
		privateMessage.setSendTime(convertToDateTime(sendTime));
	}
		
	protected void setReadTime(PrivateMessage privateMessage, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Date readTime = rs.getTimestamp(PrivateMessageTable.COLUMN_READ_TIME);
		if(readTime == null){
			//do nothing when nothing found in database
			return;
		}
		
		privateMessage.setReadTime(convertToDateTime(readTime));
	}
		 		
 	protected void setSender(PrivateMessage privateMessage, ResultSet rs, int rowNumber) throws SQLException{
 		String profileId = rs.getString(PrivateMessageTable.COLUMN_SENDER);
 		if( profileId == null){
 			return;
 		}
 		if( profileId.isEmpty()){
 			return;
 		}
 		Profile lprofile = privateMessage.getSender();
 		if( lprofile != null ){
 			//if the root object 'privateMessage' already have the property, just set the id for it;
 			lprofile.setId(profileId);
 			
 			return;
 		}
 		privateMessage.setSender(createEmptySender(profileId));
 	}
 	 		
 	protected void setReceiver(PrivateMessage privateMessage, ResultSet rs, int rowNumber) throws SQLException{
 		String profileId = rs.getString(PrivateMessageTable.COLUMN_RECEIVER);
 		if( profileId == null){
 			return;
 		}
 		if( profileId.isEmpty()){
 			return;
 		}
 		Profile lprofile = privateMessage.getReceiver();
 		if( lprofile != null ){
 			//if the root object 'privateMessage' already have the property, just set the id for it;
 			lprofile.setId(profileId);
 			
 			return;
 		}
 		privateMessage.setReceiver(createEmptyReceiver(profileId));
 	}
 	
	protected void setStatus(PrivateMessage privateMessage, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String status = rs.getString(PrivateMessageTable.COLUMN_STATUS);
		if(status == null){
			//do nothing when nothing found in database
			return;
		}
		
		privateMessage.setStatus(status);
	}
		 		
 	protected void setPlatform(PrivateMessage privateMessage, ResultSet rs, int rowNumber) throws SQLException{
 		String platformId = rs.getString(PrivateMessageTable.COLUMN_PLATFORM);
 		if( platformId == null){
 			return;
 		}
 		if( platformId.isEmpty()){
 			return;
 		}
 		Platform lplatform = privateMessage.getPlatform();
 		if( lplatform != null ){
 			//if the root object 'privateMessage' already have the property, just set the id for it;
 			lplatform.setId(platformId);
 			
 			return;
 		}
 		privateMessage.setPlatform(createEmptyPlatform(platformId));
 	}
 	
	protected void setVersion(PrivateMessage privateMessage, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Integer version = rs.getInt(PrivateMessageTable.COLUMN_VERSION);
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		privateMessage.setVersion(version);
	}
		
		

 	protected Profile  createEmptySender(String profileId){
 		Profile profile = new Profile();
 		profile.setId(profileId);
 		profile.setVersion(Integer.MAX_VALUE);
 		return profile;
 	}
 	
 	protected Profile  createEmptyReceiver(String profileId){
 		Profile profile = new Profile();
 		profile.setId(profileId);
 		profile.setVersion(Integer.MAX_VALUE);
 		return profile;
 	}
 	
 	protected Platform  createEmptyPlatform(String platformId){
 		Platform platform = new Platform();
 		platform.setId(platformId);
 		platform.setVersion(Integer.MAX_VALUE);
 		return platform;
 	}
 	
}


