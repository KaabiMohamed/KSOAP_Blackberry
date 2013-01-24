package com.app.blackberry;

import java.util.Date;
import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class Message implements KvmSerializable {
	public int id;
	public User receptor;
	public User sender;
	public Date dateSend;
	public String object;
	public String message;

	public Message() {

	}

	public Message(User receptor, User sender, Date dateSend, String object,
			String message) {

		this.receptor = receptor;
		this.sender = sender;
		
		this.object = object;
		this.message = message;
	}

	public Object getProperty(int arg0) {
		switch (arg0) {
		case 0:
			return object;
		case 1:
			return message;
		case 2:
			return sender;
		case 3:
			return receptor;
		

		}

		return null;
	}

	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo arg2) {
		switch (arg0) {
		case 0:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "object";
			break;
		case 1:
			arg2.type = PropertyInfo.STRING_CLASS;
			arg2.name = "message";
			break;
		case 2:
			arg2.type = User.class;
			arg2.name = "sender";
			break;
		case 3:
			arg2.type = User.class;
			arg2.name = "receptor";
			break;
	
		}

	}

	public void setProperty(int arg0, Object arg1) {
		switch (arg0) {
		case 0:
			object = arg1.toString();
			break;
		case 1:
			message = arg1.toString();
			break;
		case 2:
			sender = (User) arg1;
			break;
		case 3:
			receptor = (User) arg1;
			break;
		
		default:
			break;
		}

	}

}
