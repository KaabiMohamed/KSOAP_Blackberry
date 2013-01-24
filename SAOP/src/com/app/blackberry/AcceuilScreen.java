package com.app.blackberry;
import net.rim.device.api.system.Bitmap;

import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.decor.BackgroundFactory;
public class AcceuilScreen extends MainScreen{


	Bitmap background = Bitmap.getBitmapResource("image.png");
	
	
		public AcceuilScreen() {
			getMainManager().setBackground(BackgroundFactory.createBitmapBackground(background));
		
		}
		protected boolean navigationClick(int status, int time) {
			// TODO Auto-generated method stub
			UiApplication.getUiApplication().pushScreen(new Authentification());
			
			return super.navigationClick(status, time);
		}

	}

