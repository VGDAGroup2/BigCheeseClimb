package com.game.code;

import com.game.code.graphics.Background;
import com.game.code.graphics.Floor;
import com.game.code.graphics.MainCharacter;
import com.game.code.graphics.RunnableObject;
import com.game.code.graphics.Screen;

public class GameRunner {
	static GameLoop loop;
	//The init method will setup the game. I.E. getting a frame and controls running
	public static void init() {
		Screen screen = new Screen("Big Cheese Climb", 400, 600);
		new Assets().load();
		Control controls = new Control();
		screen.addKeyListener(controls);
		loop = new GameLoop(screen, controls);
		Thread t = new Thread(loop);
		loop.startGame();
		t.start();
	}
	
	public static void resetGame() { //This method resets the game
		loop.stopDrawing();
		RunnableObject.emptyAll();
		GameState.recordHeight = GameState.heightReached; // Record the record height!
		GameState.resetGame();
		System.gc(); //Every time the game is complete we dump the trash created.
		loop.startDrawing(); //Tell the loop we are ready to draw again.
	}
	
	//Self explanatory, this runs the menu
	public static void runMenu() {
		System.out.println("The Menu is running");
	}
	
	public static void runGame() {
		new Background(Assets.BACKGROUND);
		new Floor();
		new MainCharacter(Assets.MOUSE);
		new PlatformControls();
	}
}
