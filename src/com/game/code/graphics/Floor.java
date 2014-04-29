package com.game.code.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import com.game.code.GameState;
import com.game.code.collision.Collidable;

public class Floor extends RunnableObject implements Collidable{
	
	private int fHeight = 10;
	
	public Floor(){
		super();
		setRect(0, Screen.height - fHeight, Screen.width, fHeight);
	}


	public boolean isColliding(Rectangle2D.Double r) {
		return intersects(r);
	}

	public void update() {
		if(GameState.floorTrigger)
			output.add(this);
	}

	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect((int)x, (int)y, (int)width, (int)height);
	}
}
