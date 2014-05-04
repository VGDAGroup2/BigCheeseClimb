package com.game.code.graphics.menu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.game.code.Control;
import com.game.code.GameRunner;
import com.game.code.graphics.RunnableObject;
import com.game.code.graphics.Screen;

public class Menu extends RunnableObject {
	private static final long serialVersionUID = 1L;
	
	BufferedImage image;
	static CustomButton start;
	static CustomButton instructions;
	static CustomButton credits;
	JPanel p = new JPanel();
	
	public Menu() {
		setRect(0, 0, Screen.width, Screen.height);
		//We want the menu to be the screen size so the background image will show up correctly.
		setupGUI();
	}
	
	private void setupGUI() {
		start = new CustomButton(100, 100, 200, 100, null);
		instructions = new CustomButton(100, 300, 200, 100, null);
		credits = new CustomButton(100, 500, 200, 100, null);
	}
	
	public static void runAction(CustomButton b) {
		if(b.equals(start)) {
			GameRunner.exitMenu();
		} else if(instructions.equals(b)) {
			//Remove the button from the screen and show the instructions with a Button to exit
			//runInstructions(); This will Create a Runnable Object of Instructions that will display our instructions.
		} else if(credits.equals(b)) {
			//Remove the buttons from the screen and show the credits with a Button to exit
			//runCredits(); This will Create a Runnable Object of Credits that will display our credits.
		}
	}
	
	public void update() { //I'm not sure what this will do yet.
		
	}

	public void draw(Graphics g) { // This will display our menu background image
		
	}
}
