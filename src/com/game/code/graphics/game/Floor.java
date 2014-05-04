package com.game.code.graphics.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import com.game.code.GameState;
import com.game.code.collision.Collidable;
import com.game.code.graphics.RunnableObject;
import com.game.code.graphics.Screen;

public class Floor extends RunnableObject implements Collidable{
	private static final long serialVersionUID = 1L;
	private int fHeight = 10;
	
	public Floor(){
		setRect(0, Screen.height - fHeight, Screen.width, fHeight);
	}


	public boolean isColliding(Rectangle2D.Double r) {
		return intersects(r);
	}

	public void update() {
		if(GameState.floorTrigger)
			removeMe();
	}

	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect((int)x, (int)y, (int)width, (int)height);
	}
}
