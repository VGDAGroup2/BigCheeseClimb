package com.game.code.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.code.collision.Collidable;

public class Floor extends RunnableObject implements Collidable{
	
	private int fWidth = 100;
	private int fHeight = 20;
	
	public Floor(){
		super();
		x = Screen.width *.5;
		y = Screen.height - (fHeight + 10);
		hitBox = new Rectangle((int)x,(int)y,fWidth,fHeight);
	}


	public boolean isColliding(Rectangle r) {
		return false;
	}

	public void update() {
		//Nothing here
	}


	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect((int)x, (int)y, fWidth, fHeight);
		
	}

}
