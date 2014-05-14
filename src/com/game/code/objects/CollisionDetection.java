package com.game.code.objects;

import java.awt.Rectangle;
import java.util.ArrayList;

import com.game.code.objects.interfaces.Collidable;

public class CollisionDetection {	
	public static Collidable detectColission(Rectangle.Double r) {
		for(Collidable o : (ArrayList<Collidable>) GameObject.getList(Collidable.class))
			if(o.isColliding(r))
				return o;
		return null;
	}
}
