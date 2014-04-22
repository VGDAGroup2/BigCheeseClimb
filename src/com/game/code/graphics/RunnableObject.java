package com.game.code.graphics;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.game.code.collision.Collidable;

/*This class Handles our object creation.
 * Any object that extends this class
 * and calls super() in the constructor
 * is automatically draw and updated at
 * a standard time interval defined in
 * the game loop.
 */

public abstract class RunnableObject extends Rectangle2D.Double {
	static List<RunnableObject> list = new ArrayList<RunnableObject>();
	static List<RunnableObject> collidables = new ArrayList<RunnableObject>();// We should probably move this to a collision detection class once it is setup.
	static Queue<RunnableObject> input = new LinkedList<RunnableObject>(); //This is here as a buffer to prevent concurrent modification exceptions. (Two threads working on the same object).
	
	public RunnableObject() {
		input.add(this);
		if(this instanceof Collidable) {
			collidables.add(this);
		}
	}
	
	public abstract void update(); //Write object behaviors in this
	public abstract void draw(Graphics g); //Write the how the object renders in this.

	public static void emptyQueue() { //Empties the buffer queue
		while(input.peek() != null) {
			list.add(input.remove());
		}
	}
	
	public static void updateObjects() { //Updates all runnable objects
		for(RunnableObject j : list) {
			j.update();
		}
	}
	
	public static void drawObjects(Graphics g) { //Draw all runnable objects
		for(RunnableObject j : list) {
			j.draw(g);
		}
	}
	
}