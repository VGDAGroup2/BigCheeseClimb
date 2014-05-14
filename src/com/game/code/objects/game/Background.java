package com.game.code.objects.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.game.code.GameRunner;
import com.game.code.GameState;
import com.game.code.objects.GameObject;
import com.game.code.objects.Screen;
import com.game.code.objects.interfaces.Drawable;
import com.game.code.objects.interfaces.Updatable;

/*
 * This class runs our background image; 
 */

public class Background extends GameObject implements Updatable, Drawable {
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
		//this code ends the game
		
		if((percent * 100) <= 0) {	//changing the 0 to other numbers changes distance to victory
			//add code to run victory screen here
			
			//reset game
			GameRunner.resetGame();
			GameRunner.runMenu();
		}
	}
	
	public void draw(Graphics g) {
		g.drawImage(image, (int)x, (int)y, (int)(x + width), (int)(y + height), (int)0, (int)0, (int)(image.getWidth()), (int)(image.getHeight()), null);
	}
}
