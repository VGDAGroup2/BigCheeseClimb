package com.game.code.objects.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import com.game.code.GameState;
import com.game.code.objects.GameObject;
import com.game.code.objects.Screen;
import com.game.code.objects.interfaces.Collidable;
import com.game.code.objects.interfaces.Drawable;
import com.game.code.objects.interfaces.Updatable;

public class Floor extends GameObject implements Collidable, Updatable, Drawable{
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
			output.add(this);
	}

	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect((int)x, (int)y, (int)width, (int)height);
	}
}
