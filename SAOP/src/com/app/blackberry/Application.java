package com.app.blackberry;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;



import net.rim.device.api.ui.UiApplication;

public class Application extends UiApplication{
	public static void main(String[] args) throws IOException, XmlPullParserException {
		Application app = new Application();
		app.enterEventDispatcher();
	}
	public Application() throws IOException, XmlPullParserException{
		pushScreen(new UpdateUser("ka3boss"));
		//pushScreen(new ChallengeList());
	}
	

}
