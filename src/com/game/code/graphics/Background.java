package com.game.code.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.game.code.GameState;

public class Background extends RunnableObject {
	BufferedImage image;
	double percent = 0;
	int sHeight = -5000 + Screen.height;
	int eHeight = 5000;
	
	public Background(BufferedImage i) {
		image = i;
		setRect(0, sHeight, Screen.width, eHeight);
	}
	
	public void update() {
		percent = (1 - (GameState.heightReached/GameState.MAX_HEIGHT));
		y = sHeight * percent;
		System.out.println(percent * 100);
	}
	
	public void draw(Graphics g) {
		g.drawImage(image, (int)x, (int)y, (int)(x + width), (int)(y + height), (int)0, (int)0, (int)(image.getWidth()), (int)(image.getHeight()), null);
	}
}
