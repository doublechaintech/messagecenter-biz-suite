package com.doublechaintech.messagecenter;


import com.terapico.caf.viewpage.SerializeScope;

public class MessagecenterViewScope extends MessagecenterBaseViewScope{

	static {
		// 定制化本项目的序列化scope的代码放在这里
		System.out.println("**************************************************************\n定制序列化\n");
	}
	
	protected static MessagecenterViewScope instance = null;
	public static MessagecenterViewScope getInstance() {
		if (instance != null) {
			return instance;
		}
		synchronized (MessagecenterViewScope.class) {
			instance = new MessagecenterViewScope();
		}
		return instance;
	}
}







