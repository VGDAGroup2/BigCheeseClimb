package com.game.code.objects.interfaces;

import java.awt.geom.Rectangle2D;

/*
 * We use this to set up the collision
 * detection. If implemented it will
 * make any of our runnable objects
 * easily handled by collision detection.
 * This will be implemented later.
 */

public interface Collidable {
	public boolean isColliding(Rectangle2D.Double r);
	//if collidable add this: return intersects(r);
}
