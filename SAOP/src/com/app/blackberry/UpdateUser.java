package com.app.blackberry;

import java.io.IOException;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.decor.BackgroundFactory;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransport;
import org.xmlpull.v1.XmlPullParserException;

public class UpdateUser extends MainScreen {

	private static final String SOAP_ACTION_find = "http://services/findUser";
	private static final String SOAP_ACTION_update = "http://services/updateUser";
    private static final String METHOD_NAME_find = "findUser";
    private static final String METHOD_NAME_update = "updateUser";
    private static final String NAMESPACE = "http://services/";
    private static final String URL = "http://localhost:2000/CoM/NewUserService/UserDAOImpl?wsdl";
	BasicEditField login, password,skills,interest,firstname,lastname,experience;
	String user_log;
	Bitmap background = Bitmap.getBitmapResource("home.png");

	public UpdateUser(String log) throws IOException, XmlPullParserException{
		getMainManager().setBackground(BackgroundFactory.createBitmapBackground(background));

		user_log =log;
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_find);
		request.addProperty("arg0", log);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.bodyOut = request;

		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		HttpTransport ht = new HttpTransport(URL);
		ht.call(SOAP_ACTION_find, envelope);
		final SoapObject response = (SoapObject) envelope.getResponse();
		login=new BasicEditField("Login :", response.getProperty("login").toString());
		password = new BasicEditField("Password :", response.getProperty("passWord").toString());
		ButtonField updateButton = new ButtonField("valider",
				Field.FOCUSABLE);
		updateButton.setChangeListener(new ClickBoton());
		add(login);
		add(password);
		add(updateButton);
		setMargin(120, 100, 100, 100);

		
	}
	public class ClickBoton implements FieldChangeListener {

		public void fieldChanged(Field field, int context) {
			try {
				update();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
	
	public void update() throws IOException, XmlPullParserException {
    	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_update);
    	
    	
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		User Object = new User(user_log, password.getText());
		request.addProperty("arg0", Object);
		envelope.setOutputSoapObject(Object);
		envelope.addMapping("http://services", User.class.getName(), User.class);
		envelope.bodyOut = request;
		//envelope.dotNet = true;
		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		HttpTransport ht = new HttpTransport(URL);
		ht.call(SOAP_ACTION_update, envelope);	
	}
}
