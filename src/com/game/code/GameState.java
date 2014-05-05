package com.game.code;

/*
 * This class will hold our game state. If a field is needed in many classes
 * it should go in here. Other things like score will be here as well.
 * The point is to make all fields static for quick access.
 */

public  class GameState {
	public static double fallRate = .5;
	public static double heightReached;
	public static boolean jumped = false;
	public static boolean floorTrigger = false;
	public static final int MAX_HEIGHT = 1000;
	public static double recordHeight = 0;
	public static boolean gameRunning = false;
	public static boolean playerFaceLeft = false;
	
	public static void resetGame() {
		heightReached = 0;
		jumped = false;
		floorTrigger = false;
		playerFaceLeft = false;
	}
}
