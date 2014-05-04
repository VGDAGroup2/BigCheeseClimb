package com.game.code;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/* So this class is pretty simple. It takes a
 * key events and turns the associated key value
 * (1 - 256 of the keys on your keyboard) in the
 * array of keys to true. If the key is released
 * it turns to false; 
*/

public class Control implements KeyListener, MouseMotionListener, MouseListener{
	private boolean[] keys = new boolean[256];
	public static boolean up, down, left, right, jump, pressed;
	public static int mouseX, mouseY, clickX, clickY;
	
	public void update() {
		jump = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W] || keys[KeyEvent.VK_SPACE];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
	}

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {}
	public void mouseDragged(MouseEvent e) {}

	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	public void mouseClicked(MouseEvent e) {}

	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	
	public void mousePressed(MouseEvent e) {
		clickX = e.getX();
		clickY = e.getY();
		pressed = true;
	}
	public void mouseReleased(MouseEvent arg0) {
		pressed = false;
	}
}