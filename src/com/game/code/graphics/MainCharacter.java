package com.game.code.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import com.game.code.Control;
import com.game.code.collision.Collidable;

/*
 * Pretty self explanatory, this is
 * our main character!
 */

public class MainCharacter extends RunnableObject implements Collidable {
	
	private double speed = 2;
	private int startingHeight = 50;
	
	public MainCharacter() {
		super();
		setRect(Screen.width*.5, Screen.height - startingHeight, 10, 10);
	}
	
	public void update() {
		if(isColliding(this))
			System.out.println("Colliding");
		else
			System.out.println("Not Colliding");
			
		if(Control.up) {y-=speed;}
		if(Control.down){y+=speed;}
		if(Control.left){x-=speed;}
		if(Control.right){x+=speed;}
		if(x <= 0 || x >= Screen.width)
			x = Screen.width - x;
	}

	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.drawRect((int)x, (int)y, (int)width, (int)height);
	}

	public boolean isColliding(Rectangle2D.Double r) {
		for(RunnableObject object: collidables){
			if(!object.equals(this)){
				if(r.intersects(object)){
					return true;
				}
			}
		}
		return false;
	}
}
