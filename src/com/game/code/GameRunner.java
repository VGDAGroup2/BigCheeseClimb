package com.game.code;

import com.game.code.objects.GameObject;
import com.game.code.objects.Screen;
import com.game.code.objects.game.Background;
import com.game.code.objects.game.Floor;
import com.game.code.objects.game.MainCharacter;
import com.game.code.objects.menu.Menu;

public class GameRunner {
	static GameLoop loop;
	static PlatformControls platforms;
	
	//The init method will setup the game. I.E. getting a frame and controls running
	public static void init() {
		Screen screen = new Screen("Big Cheese Climb", 400, 600);
		new Assets().load();
		Control controls = new Control();
		screen.addKeyListener(controls);
		screen.addMouseMotionListener(controls);
		screen.addMouseListener(controls);
		loop = new GameLoop(screen, controls);
		Thread t = new Thread(loop);
		loop.startGame();
		t.start();
	}
	
	public static void resetGame() { //This method resets the game
		loop.stopDrawing();
		GameObject.emptyAll();
		GameState.recordHeight = GameState.heightReached; // Record the record height!
		GameState.resetGame();
		System.gc(); //Every time the game is complete we dump the trash created.
		loop.startDrawing(); //Tell the loop we are ready to draw again.
	}
	
	//Self explanatory, this runs the menu
	public static void runMenu() {
		new Menu();
	}
	
	public static void runGame() {
		GameState.gameRunning = true;
		new Background(Assets.BACKGROUND);
		new Floor();
		new MainCharacter(Assets.MOUSE);
		if(platforms == null)
			platforms = new PlatformControls();
		else
			platforms.reset();
	}

	public static void exitMenu() { // This will destroy the Menu
		loop.stopDrawing();
		GameObject.emptyAll();
		System.gc();
		loop.startDrawing();
		runGame();
	}
}
