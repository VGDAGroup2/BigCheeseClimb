package com.game.code;

public class GameRunner {
	
	GameRunner() {
		init();
	}
	
	//The init method will run the setup the game. I.E. getting a frame up and Control Input
	public static void init() {
		System.out.println("Initialization is running");
	}
	
	//Self explanatory, this runs the menu
	public static void runMenu() {
		System.out.println("The Menu is running");
	}
	
	//Also self explanatory, this will run the game portion.
	public static void runGame() {
		System.out.println("The Game is running");
	}
}
