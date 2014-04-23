package com.game.code;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/* So this class is pretty simple. It takes a
 * key events and turns the associated key value
 * (1 - 256 of the keys on your keyboard) in the
 * array of keys to true. If the key is released
 * it turns to false; 
*/

public class Control implements KeyListener {
	private boolean[] keys = new boolean[256];
	public static boolean up, down, left, right, jump;
	
	public void update() {
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		jump = keys[KeyEvent.VK_SPACE];
	}

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {}
}