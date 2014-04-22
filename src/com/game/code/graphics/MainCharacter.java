package com.game.code.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

import com.game.code.Control;
import com.game.code.collision.Collidable;

/*Pretty self explanatory, this is
 * our main character!
 */

public class MainCharacter extends RunnableObject implements Collidable {
	
	private double speed = 2;
	private int pWidth = 10;
	private int pHeight = 10;
	
	public MainCharacter() {
		super();
		x = Screen.width*.5;
		y = Screen.height - 50;
		hitBox = new Rectangle((int)x,(int)y,pWidth,pHeight);
	}
	
	public void update() {
		if(isColliding(hitBox))
			System.out.println("Colliding");
		else
			System.out.println("Not Colliding");
			
		if(Control.up) {y-=speed;}
		if(Control.down){y+=speed;}
		if(Control.left){x-=speed;}
		if(Control.right){x+=speed;}
		if(x < -pWidth)
			x = Screen.width;
		if(x > Screen.width)
			x = -pWidth;
		hitBox = new Rectangle((int)x,(int)y,pWidth,pHeight);
	}

	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.drawRect((int)x, (int)y, pWidth, pHeight);
	}

	public boolean isColliding(Rectangle r) {
		List<RunnableObject> collidables = super.getCollidables();
		for(RunnableObject object: collidables){
			if(!object.equals(this)){
				if(r.intersects(object.hitBox)){
					return true;
				}
			}
		}
		return false;
	}

}
