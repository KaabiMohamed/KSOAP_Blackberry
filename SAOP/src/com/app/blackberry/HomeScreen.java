package com.app.blackberry;


import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;

public class HomeScreen extends MainScreen {

	LabelField editProfile = new LabelField("Edit profil",FOCUSABLE){
		protected boolean navigationClick(int status, int time) {
			try {
				UiApplication.getUiApplication().pushScreen(new UpdateUser("mohamed") {
				});
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return true;
	}
		protected void paint(net.rim.device.api.ui.Graphics graphics) {graphics.setColor(Color.WHITE);
		super.paint(graphics);};
		
		
		
	
	};

	

	LabelField Notification = new LabelField("Send Notification",FOCUSABLE){
		protected boolean navigationClick(int status, int time) {
			UiApplication.getUiApplication().pushScreen(new NotifyUser("mohamed"));
		return true;
	}
		protected void paint(net.rim.device.api.ui.Graphics graphics) {graphics.setColor(Color.WHITE);
		super.paint(graphics);};
		
	};
	LabelField Message = new LabelField("Send a Message",FOCUSABLE){
		protected boolean navigationClick(int status, int time) {
			UiApplication.getUiApplication().pushScreen(new SendMessage("mohamed"));
		return true;
	}
		protected void paint(net.rim.device.api.ui.Graphics graphics) {graphics.setColor(Color.WHITE);
		super.paint(graphics);};
		
	};
	
	LabelField deconnexion = new LabelField("Deconnexion",FOCUSABLE){
		protected boolean navigationClick(int status, int time) {
			UiApplication.getUiApplication().pushScreen(new Authentification());
		return true;
	}
		protected void paint(net.rim.device.api.ui.Graphics graphics) {graphics.setColor(Color.WHITE);
		super.paint(graphics);};
	};
	
	VerticalFieldManager hfm = new VerticalFieldManager(FIELD_HCENTER);
	
	HorizontalFieldManager vfm= new HorizontalFieldManager(FIELD_BOTTOM|FIELD_HCENTER);
	Bitmap background = Bitmap.getBitmapResource("home.png");
	Bitmap aboutimg = Bitmap.getBitmapResource("infobb.png");
	BitmapField about = new BitmapField(aboutimg,FOCUSABLE){	
		
		protected boolean navigationClick(int status, int time) {
		UiApplication.getUiApplication().pushScreen(new AboutScreen());
		return true;
	}};
	
	Bitmap exitimg = Bitmap.getBitmapResource("exit.png");
	BitmapField exit = new BitmapField(exitimg,FOCUSABLE){	
		
		protected boolean navigationClick(int status, int time) {
		System.exit(0);
		return true;
	}};
	

	public HomeScreen() {
		hfm.add(editProfile);
		hfm.add(Message);
		hfm.add(Notification);

		

		hfm.add(deconnexion);
		hfm.setMargin(210, 100, 100, 0);
		getMainManager().setBackground(BackgroundFactory.createBitmapBackground(background));
		
		add(hfm);
		
		vfm.add(exit);
		vfm.add(about);
		add(vfm);
	}

}