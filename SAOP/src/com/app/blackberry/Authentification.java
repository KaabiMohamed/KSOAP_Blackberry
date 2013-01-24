package com.app.blackberry;

import java.io.IOException;


import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import net.rim.device.api.ui.decor.Border;
import net.rim.device.api.ui.decor.BorderFactory;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransport;
import org.xmlpull.v1.XmlPullParserException;

public class Authentification extends MainScreen {
	VerticalFieldManager hfm = new VerticalFieldManager(FIELD_HCENTER);
	HorizontalFieldManager vfm = new HorizontalFieldManager(FIELD_HCENTER);
	LabelField labelLogin = new LabelField("Login :") {
		protected void paint(net.rim.device.api.ui.Graphics graphics) {
			graphics.setColor(Color.WHITE);
			super.paint(graphics);
		};
	};
	LabelField labelMDP = new LabelField("Password :") {
		protected void paint(net.rim.device.api.ui.Graphics graphics) {
			graphics.setColor(Color.WHITE);
			super.paint(graphics);
		};
	};

	ButtonField buttonAnnuler = new ButtonField("cancel");
	ButtonField AuthentificationButton = new ButtonField("valider",
			Field.FOCUSABLE);
	Bitmap imgCont1 = Bitmap.getBitmapResource("authentification.png");
	private static final String SOAP_ACTION = "http://services/findUser";
	private static final String METHOD_NAME = "findUser";
	private static final String NAMESPACE = "http://services/";
	private static final String URL = "http://localhost:2000/CoM/NewUserService/UserDAOImpl?wsdl";
	BasicEditField login, password;

	public Authentification() {
		setTitle("Authentification ");
		login = new BasicEditField("", "");
		password = new BasicEditField("", "");
		login.setBorder(BorderFactory.createRoundedBorder(new XYEdges(10, 10,
				10, 10), Color.WHITE, Border.STYLE_SOLID));
		password.setBorder(BorderFactory.createRoundedBorder(new XYEdges(10,
				10, 10, 10), Color.WHITE, Border.STYLE_SOLID));
		
		AuthentificationButton.setChangeListener(new ClickBoton());
		//buttonAnnuler.setChangeListener((FieldChangeListener) this);

		hfm.add(labelLogin);
		hfm.add(login);
		hfm.add(labelMDP);
		hfm.add(password);
		vfm.add(AuthentificationButton);
		vfm.add(buttonAnnuler);
		hfm.add(vfm);
		getMainManager().setBackground(
				BackgroundFactory.createBitmapBackground(imgCont1));
		hfm.setMargin(120, 100, 100, 100);
		add(hfm);

	}

	public class ClickBoton implements FieldChangeListener {

		public void fieldChanged(Field field, int context) {

			try {

				Verification(login.getText(), password.getText());

			} catch (IOException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}

		}

		private void Verification(String login, String password)
				throws IOException, XmlPullParserException {

			LabelField ss;

			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
			request.addProperty("arg0", login);
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.bodyOut = request;

			envelope.encodingStyle = SoapSerializationEnvelope.XSD;
			HttpTransport ht = new HttpTransport(URL);
			ht.call(SOAP_ACTION, envelope);
			final SoapObject response = (SoapObject) envelope.getResponse();
			String pass = response.getProperty("passWord").toString();
			
			if (pass.equals(password)) {
				ss = new LabelField("Connecté");
				UiApplication.getUiApplication().pushScreen(new HomeScreen());
			} else {
				ss = new LabelField("Mot de passe érroné");
			}

			add(ss);
		}
	}

	public boolean onClose() {
		Dialog.alert("Goodbye!");
		System.exit(0);
		return true;
	}

}
