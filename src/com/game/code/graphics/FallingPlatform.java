package com.game.code.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import com.game.code.collision.Collidable;

public class FallingPlatform extends RunnableObject implements Collidable{
	
	
	public FallingPlatform(){
		super();
		Random rand = new Random();
		double random = rand.nextDouble() * Screen.width;
		setRect(random, 100, 100, 20);
		System.out.println(random);
	}


	public void update() {
		y++;
		
	}

	
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect((int)x, (int)y, (int) width,(int) height);
		
		
	}


	public boolean isColliding(Double r) {
		return intersects(r); //if collidable, add this code
	}

}
