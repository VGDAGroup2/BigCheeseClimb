package com.game.code.graphics.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.game.code.GameState;
import com.game.code.graphics.RunnableObject;
import com.game.code.graphics.Screen;

/*
 * This class runs our background image; 
 */

public class Background extends RunnableObject {
	private static final long serialVersionUID = 1L;
	BufferedImage image;
	double percent = 0;
	int sHeight = -5000 + Screen.height;
	
	public Background(BufferedImage i) {
		image = i;
		setRect(0, sHeight, Screen.width, 5000);
	}
	
	public void update() {
		percent = (1 - (GameState.heightReached/GameState.MAX_HEIGHT));
		y = sHeight * percent;
	}
	
	public void draw(Graphics g) {
		g.drawImage(image, (int)x, (int)y, (int)(x + width), (int)(y + height), (int)0, (int)0, (int)(image.getWidth()), (int)(image.getHeight()), null);
	}
}
