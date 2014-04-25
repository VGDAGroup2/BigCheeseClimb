package com.game.code.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import com.game.code.GameState;
import com.game.code.PlatformControls;
import com.game.code.collision.Collidable;

public class FallingPlatform extends RunnableObject implements Collidable{
	
	public FallingPlatform(){
		super();
		Random rand = new Random();
		double random = rand.nextDouble() * (Screen.width - 100);
		setRect(random, 0, 100, 1);
	}

	public void update() {
		y += GameState.fallRate;
		if(y >= Screen.height) {
			output.add(this);
			PlatformControls.removePlatform(this);
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect((int)x, (int)y, (int) width,(int) height);
	}
	
	public void moveMore(double yMod) {
		y += yMod;
	}

	public boolean isColliding(Double r) {
		return intersects(r);
	}
}
