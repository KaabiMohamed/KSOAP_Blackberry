package com.app.blackberry;

import java.io.IOException;

import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.MainScreen;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransport;
import org.xmlpull.v1.XmlPullParserException;

public class ChallengeList extends MainScreen {

	private static final String SOAP_ACTION = "http://services/challengeDescriptions";
	private static final String METHOD_NAME = "challengeDescriptions";
	private static final String NAMESPACE = "http://services/";
	private static final String URL = "http://localhost:2000/CoM/NewChallengeService/ChallengeDAOImpl?wsdl";

	public ChallengeList() throws IOException, XmlPullParserException {

		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.bodyOut = request;
		//envelope.setOutputSoapObject(request);

	//	envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		HttpTransport ht = new HttpTransport(URL);
		ht.call(SOAP_ACTION, envelope);
		// final List response;
		// response = (List) envelope.getResponse();
		SoapObject response = (SoapObject) envelope.getResponse();
		// System.out.println(response.toString());
		LabelField ss = new LabelField(response.toString());
		add(ss);

	}
}
