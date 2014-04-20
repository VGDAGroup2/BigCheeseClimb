package com.game.code.collision;

import java.awt.Rectangle;

/*
 * We use this to set up the collision
 * detection. If implemented it will
 * make any of our runnable objects
 * easily handled by collision detection.
 * This will be implemented later.
 */

public interface Collidable {
	public boolean isColliding(Rectangle r);
}
