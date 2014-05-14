package com.game.code.objects;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.game.code.GameLoop;
import com.game.code.GameState;
import com.game.code.objects.interfaces.Drawable;
import com.game.code.objects.interfaces.Updatable;


/* This is the screen. It is what we render to.
 * The screen holds a graphics context, and is
 * set to any initial size and title.
*/

public class Screen extends Canvas{
	private static final long serialVersionUID = 1L;
	public static JFrame frame = new JFrame();
	public static int width = 0, height = 0;
	private Rectangle rect;
	
	//Just in case we want a blank screen
	public Screen() {
		this("Untitled", 0, 0);
	}
	
	//Sets up the Frame
	public Screen(String title, int width, int height) {
		Screen.width = width; Screen.height = height;
		rect = new Rectangle(0, 0, width, height);
		setBounds(rect);
		frame.setResizable(false);
		frame.setTitle(title);
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public void renderScreen() {
		BufferStrategy bs = getBufferStrategy(); //This is a complex java operation that basically prepares the screen to render.
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		requestFocus(); // Gets the window to be focused so that we can interact with it.
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.GREEN);
		g.drawString("Height: " + (int)(GameState.heightReached / 60), 0, 20);
		
		if(GameObject.getList(Drawable.class) != null)
		for(Drawable o : (ArrayList<Drawable>) GameObject.getList(Drawable.class)) {
			o.draw(g);
		}
		
		g.drawString("ups: " + GameLoop.frames, 0, 10);
		
		g.dispose(); //Disposes of the graphics context to save memory space.
		bs.show(); //displays the next frame in the buffer strategy.
	} 
}