package com.game.code.objects;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/*This class Handles our object creation.
 * Any object that extends this class
 * and calls super() in the constructor
 * is automatically draw and updated at
 * a standard time interval defined in
 * the game loop.
 */

public abstract class GameObject extends Rectangle2D.Double {
	private static final long serialVersionUID = 1L;
	Class[] classes = getClass().getInterfaces();
	static Queue<GameObject> input = new LinkedList<GameObject>();
	protected static Queue<GameObject> output = new LinkedList<GameObject>();
	static Map<Class, ArrayList<GameObject>> objects = new HashMap<Class, ArrayList<GameObject>>(); 
	
	public GameObject() {
		input.add(this);
	}
	
	protected void removeMe() { //This removes the object from any lists it may be in.
		for(Class c : classes) {
			objects.get(c).remove(this);
		}
	}
	
	protected void addMe() { //This removes the object from any lists it may be in.
		ArrayList<GameObject> list;
		for(Class c : classes) {
			if(objects.get(c) != null)
				list = objects.get(c);
			else
				list = new ArrayList<GameObject>();
			
			list.add(this);
			objects.put(c, list);
		}
	}

	public static void emptyQueue() {
		while(output.peek() != null)
			output.remove().removeMe();
		while(input.peek() != null)
			input.remove().addMe();
	}
	
	public static void emptyAll() { //This will remove all objects from the list;
		emptyQueue();
		objects.clear();
	}
	
	public static ArrayList<?> getList(Class c) {
		return objects.get(c);
	}
}