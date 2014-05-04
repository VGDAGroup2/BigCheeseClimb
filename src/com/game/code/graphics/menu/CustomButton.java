package com.game.code.graphics.menu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.game.code.Control;
import com.game.code.graphics.RunnableObject;

public class CustomButton extends RunnableObject {
	private static final long serialVersionUID = 1L;
	
	BufferedImage image;
	
	public CustomButton(double x, double y, double w, double h, BufferedImage i) {
		setRect(x, y, w, h);
		image = i;
	}
	
	public void update() {
		if(contains(Control.mouseX, Control.mouseY))
			if(contains(Control.clickX, Control.clickY) && Control.pressed)
				Menu.runAction(this);
	}

	public void draw(Graphics g) {
		g.drawRect((int)x, (int)y, (int)width, (int)height);
	}
}
