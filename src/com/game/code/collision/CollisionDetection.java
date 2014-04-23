package com.game.code.collision;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import com.game.code.graphics.RunnableObject;

public class CollisionDetection {
	public static List<Collidable> collidables = new ArrayList<Collidable>();
	
	CollisionDetection() {
		
	}
	
	public static Collidable detectColission(Rectangle.Double r) {
		for(Collidable object: collidables){
			if(object.isColliding(r))
				return object;
		}
		return null;
	}
}
