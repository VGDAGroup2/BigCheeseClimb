package com.game.code;

import com.game.code.graphics.MainCharacter;
import com.game.code.graphics.Screen;

public class GameRunner {
	
	//The init method will setup the game. I.E. getting a frame and controls running
	public static void init() {
		Screen screen = new Screen("Big Cheese Climb", 400, 600);
		Control controls = new Control();
		screen.addKeyListener(controls);
		GameLoop loop = new GameLoop(screen, controls);
		Thread t = new Thread(loop);
		loop.startGame();
		t.start();
	}
	
	//Self explanatory, this runs the menu
	public static void runMenu() {
		System.out.println("The Menu is running");
	}
	
	//Also self explanatory, this will run the game portion.
	public static void runGame() {
		new MainCharacter();
	}
}
