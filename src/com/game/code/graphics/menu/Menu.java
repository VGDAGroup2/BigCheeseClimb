package com.game.code.graphics.menu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.game.code.Assets;
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
		int w = 150, h = 75;
		int bX = Screen.width/2 - w/2;
		int sHeight = 125, hMod = 150;
		start = new CustomButton(bX , sHeight + hMod * 0, w, h, Assets.BUTTON_START);
		instructions = new CustomButton(bX, sHeight + hMod * 1, w, h, Assets.BUTTON_CONTROLS);
		credits = new CustomButton(bX, sHeight + hMod * 2, w, h, Assets.BUTTON_CREDITS);
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
