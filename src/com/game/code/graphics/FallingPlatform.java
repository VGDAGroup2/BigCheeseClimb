package com.game.code.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

import com.game.code.GameState;
import com.game.code.PlatformControls;
import com.game.code.collision.Collidable;

public class FallingPlatform extends RunnableObject implements Collidable{
	
	private BufferedImage image;
	
	public FallingPlatform(String path){
		super();
		Random rand = new Random();
		double random = rand.nextDouble() * (Screen.width - 100);
		setRect(random, 0, 100, 1);
		try {
			image = ImageIO.read(RunnableObject.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		y += GameState.fallRate;
		if(y >= Screen.height) {
			output.add(this);
			PlatformControls.removePlatform(this);
		}
	}
	
	public void draw(Graphics g) {
	    g.drawImage(image, (int)x, (int)y, (int)(x + width), (int)(y + height * 20), (int)0, (int)0, (int)(image.getWidth()), (int)(image.getHeight()), null);
	}
	
	public void moveMore(double yMod) {
		y += yMod;
	}

	public boolean isColliding(Double r) {
		return intersects(r);
	}
}
