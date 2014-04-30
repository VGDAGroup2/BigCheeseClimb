package com.game.code.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.game.code.GameState;
import com.game.code.collision.Collidable;

public class FallingPlatform extends RunnableObject implements Collidable{
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	
	public FallingPlatform(BufferedImage i, double rand, double pWidth){
		setRect(rand, 0, pWidth, 1);
		image = i;
	}

	public void update() {
		y += GameState.fallRate;
		if(y >= Screen.height) {
			removeMe();
		}
	}
	
	public void draw(Graphics g) {
	    g.drawImage(image, (int)x, (int)y, (int)(x + width), (int)(y + height * 15), (int)0, (int)0, (int)(image.getWidth()), (int)(image.getHeight()), null);
	}
	
	public void moveMore(double yMod) {
		y += yMod;
	}

	public boolean isColliding(Double r) {
		return intersects(r);
	}
}
