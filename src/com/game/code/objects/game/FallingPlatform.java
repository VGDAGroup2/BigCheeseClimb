package com.game.code.objects.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.game.code.Assets;
import com.game.code.GameState;
import com.game.code.objects.GameObject;
import com.game.code.objects.Screen;
import com.game.code.objects.interfaces.Collidable;
import com.game.code.objects.interfaces.Drawable;
import com.game.code.objects.interfaces.Updatable;

public class FallingPlatform extends GameObject implements Collidable, Drawable, Updatable {
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private int platformScale = 40;
	
	public FallingPlatform(BufferedImage i, double rand, double pWidth) {
		setRect(rand, 0, pWidth, 1);
		image = Assets.getScaledInstance(i, (int)width, (int)height * platformScale, true);
	}

	public void update() {
		y += GameState.fallRate;
		if(y >= Screen.height) {
			output.add(this);
		}
	}
	
	public void draw(Graphics g) {
		g.drawImage(image, (int)x, (int)y, null);
	}
	
	public void moveMore(double yMod) {
		y += yMod;
	}

	public boolean isColliding(Double r) {
		return intersects(r);
	}
}
