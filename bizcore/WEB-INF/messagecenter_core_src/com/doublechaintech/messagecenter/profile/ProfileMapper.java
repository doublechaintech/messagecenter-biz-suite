
package com.doublechaintech.messagecenter.profile;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.messagecenter.BaseRowMapper;
import com.doublechaintech.messagecenter.platform.Platform;

public class ProfileMapper extends BaseRowMapper<Profile>{
	
	protected Profile internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		Profile profile = getProfile();		
		 		
 		setId(profile, rs, rowNumber); 		
 		setName(profile, rs, rowNumber); 		
 		setPlatform(profile, rs, rowNumber); 		
 		setVersion(profile, rs, rowNumber);

		return profile;
	}
	
	protected Profile getProfile(){
		return new Profile();
	}		
		
	protected void setId(Profile profile, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String id = rs.getString(ProfileTable.COLUMN_ID);
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		profile.setId(id);
	}
		
	protected void setName(Profile profile, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		String name = rs.getString(ProfileTable.COLUMN_NAME);
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		profile.setName(name);
	}
		 		
 	protected void setPlatform(Profile profile, ResultSet rs, int rowNumber) throws SQLException{
 		String platformId = rs.getString(ProfileTable.COLUMN_PLATFORM);
 		if( platformId == null){
 			return;
 		}
 		if( platformId.isEmpty()){
 			return;
 		}
 		Platform lplatform = profile.getPlatform();
 		if( lplatform != null ){
 			//if the root object 'profile' already have the property, just set the id for it;
 			lplatform.setId(platformId);
 			
 			return;
 		}
 		profile.setPlatform(createEmptyPlatform(platformId));
 	}
 	
	protected void setVersion(Profile profile, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		Integer version = rs.getInt(ProfileTable.COLUMN_VERSION);
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		profile.setVersion(version);
	}
		
		

 	protected Platform  createEmptyPlatform(String platformId){
 		Platform platform = new Platform();
 		platform.setId(platformId);
 		platform.setVersion(Integer.MAX_VALUE);
 		return platform;
 	}
 	
}


