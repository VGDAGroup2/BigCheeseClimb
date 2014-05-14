package com.game.code.objects.menu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.game.code.Assets;
import com.game.code.Control;
import com.game.code.objects.GameObject;
import com.game.code.objects.interfaces.Drawable;
import com.game.code.objects.interfaces.Updatable;

public class CustomButton extends GameObject implements Updatable, Drawable{
	private static final long serialVersionUID = 1L;
	
	BufferedImage original, hovered;
	boolean hover;
	int hoverMod = 20;
	
	public CustomButton(double x, double y, double w, double h, BufferedImage i) {
		setRect(x, y, w, h);
		hovered = Assets.getScaledInstance(i, (int)width + hoverMod, (int)height + hoverMod, true);
		original = Assets.getScaledInstance(i, (int)width, (int)height, true);
	}
	
	public void update() {
		if(contains(Control.mouseX, Control.mouseY)) {
			hover = true;
			if(contains(Control.clickX, Control.clickY) && Control.pressed)
				Menu.runAction(this);
		} else {
			hover = false;
		}
	}

	public void draw(Graphics g) {
		if(hover) {
			g.drawImage(hovered, (int)x - 10, (int)y - 10, (int)width + hoverMod, (int)height + hoverMod, null);
		} else {
			g.drawImage(original, (int)x, (int)y, (int)width, (int)height, null);
		}
	}

	public int getZ() {
		return 0;
	}
}
