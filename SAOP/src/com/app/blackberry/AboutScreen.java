package com.app.blackberry;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.decor.BackgroundFactory;

public class AboutScreen extends MainScreen{
	Bitmap background = Bitmap.getBitmapResource("home.png");
LabelField about = new LabelField("test about ...."){
	protected void paint(net.rim.device.api.ui.Graphics graphics) {graphics.setColor(Color.WHITE);
	super.paint(graphics);};};
	public AboutScreen() {
		getMainManager().setBackground(BackgroundFactory.createBitmapBackground(background));
		
		about.setMargin(120,120,120,120);
		add(about);
	}
	
}