package com.app.blackberry;

import java.io.IOException;
import java.util.Date;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.container.MainScreen;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransport;
import org.xmlpull.v1.XmlPullParserException;

public class NotifyUser extends MainScreen {
	private static final String SOAP_ACTION = "http://services/sendMessage";
	private static final String METHOD_NAME = "sendMessage";
	private static final String NAMESPACE = "http://services/";
	private static final String URL = "http://localhost:2000/CoM/NewMessageService/MessageDAOImpl?wsdl";
	BasicEditField  challenge, receptorLogin;
	String senderL;

	public NotifyUser(String senderLogin) {
		senderL = senderLogin;
		receptorLogin = new BasicEditField("To :", "");
		
		challenge = new BasicEditField("Challenge Name :", "");
		ButtonField sendButton = new ButtonField("Envoyer", Field.FOCUSABLE);
		sendButton.setChangeListener(new ClickBoton());
		add(receptorLogin);
		add(challenge);
		add(sendButton);

	}

	public class ClickBoton implements FieldChangeListener {

		public void fieldChanged(Field field, int context) {

			try {
				sendMessage();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public User findUser(String login) throws IOException,
			XmlPullParserException {
		String SOAP_ACTION = "http://services/findUser";
		String METHOD_NAME = "findUser";
		String NAMESPACE = "http://services/";
		String URL = "http://localhost:2000/CoM/NewUserService/UserDAOImpl?wsdl";
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		request.addProperty("arg0", login);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.bodyOut = request;

		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		HttpTransport ht = new HttpTransport(URL);
		ht.call(SOAP_ACTION, envelope);
		final SoapObject response = (SoapObject) envelope.getResponse();
		User u = new User(response.getProperty("login").toString(), response
				.getProperty("passWord").toString());
		return u;
	}

	public void sendMessage() throws IOException, XmlPullParserException {
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		User receptor = findUser(receptorLogin.getText());
		User sender = findUser(senderL);
		Date dateSend = new Date();
		String obj = "Notification";
		String msg = "You Are Invited To subscribe to the Challenge : " +challenge.toString();
		Message Object = new Message(receptor, sender, dateSend, obj, msg);
		request.addProperty("arg0", Object);
		envelope.setOutputSoapObject(Object);
		envelope.addMapping("http://services", Message.class.getName(),
				Message.class);
		envelope.bodyOut = request;
		// envelope.dotNet = true;
		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		HttpTransport ht = new HttpTransport(URL);
		ht.call(SOAP_ACTION, envelope);

	}
}
