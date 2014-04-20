package com.game.code.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.code.Control;
import com.game.code.collision.Collidable;

/*Pretty self explanatory, this is
 * our main character!
 */

public class MainCharacter extends RunnableObject implements Collidable {
	
	public MainCharacter() {
		super();
		x = Screen.width*.5;
		y = Screen.height - 50;
	}
	
	public void update() {
		if(Control.up) {y--;}
		if(Control.down){y++;}
		if(Control.left){x--;}
		if(Control.right){x++;}
	}

	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.drawRect((int)x, (int)y, 10, 10);
	}

	public boolean isColliding(Rectangle r) {
		return false;
	}
}
