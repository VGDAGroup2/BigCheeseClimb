package com.game.code.graphics;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.game.code.collision.Collidable;
import com.game.code.collision.CollisionDetection;

/*This class Handles our object creation.
 * Any object that extends this class
 * and calls super() in the constructor
 * is automatically draw and updated at
 * a standard time interval defined in
 * the game loop.
 */

public abstract class RunnableObject extends Rectangle2D.Double {
	private static final long serialVersionUID = 1L;
	static List<RunnableObject> list = new ArrayList<RunnableObject>();
	static Queue<RunnableObject> input = new LinkedList<RunnableObject>(); //This is here as a buffer to prevent concurrent modification exceptions. (Two threads working on the same object).
	static Queue<RunnableObject> output = new LinkedList<RunnableObject>(); //This is here for a removal buffer
	
	public RunnableObject() {
		input.add(this);
		if(this instanceof Collidable) {
			CollisionDetection.collidables.add((Collidable)this);
		}
	}
	
	public abstract void update(); //Write object behaviors in this
	public abstract void draw(Graphics g); //Write the how the object renders in this.
	
	protected void removeMe() { //This removes the object from any lists it may be in.
		output.add(this);
		if(this instanceof Collidable) {
			CollisionDetection.collidables.remove(this);
		}
	}

	public static void emptyQueue() {
		while(output.peek() != null)
			list.remove(output.remove());
		while(input.peek() != null)
			list.add(input.remove());
	}
	
	public static void emptyAll() { //This will remove all objects from the list;
		for(RunnableObject r: list) {
			r.removeMe();
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