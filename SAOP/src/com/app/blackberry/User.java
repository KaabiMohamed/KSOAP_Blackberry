package com.app.blackberry;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class User implements KvmSerializable {

	public String login;
	public String passWord;
	
	public User(String login, String password) {
		
		this.login = login;
		this.passWord = password;
	}

	public Object getProperty(int arg0) {
switch(arg0){
case 0:return login;
case 1:return passWord;
}
		
		return null;
	}

	public int getPropertyCount() {
		
		return 2;
	}

	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo arg2) {
		switch(arg0){
		case 0:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name ="login";
			break;
		case 1:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name ="passWord";
			break;
		}
		
	}

	public void setProperty(int arg0, Object arg1) {
		switch(arg0){
		case 0:
			login=arg1.toString();
			break;
		case 1:
			passWord=arg1.toString();
			break;
			default:
				break;
		}
		
	}

}
