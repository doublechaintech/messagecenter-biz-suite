package com.doublechaintech.messagecenter;

public interface MessagecenterUserContext extends UserContext{
    //define the domain specific user model
	String getLocaleKey(String subject);
	void setChecker(MessagecenterChecker checker);
	MessagecenterChecker getChecker();
	
	void saveAccessInfo(String beanName, String methodName, Object[] parameters);
	void addFootprint(FootprintProducer helper) throws Exception;
	Object getPreviousViewPage() throws Exception;
	Object getLastViewPage() throws Exception;
	Object goback() throws Exception;
}

