package com.game.code;

import com.game.code.graphics.RunnableObject;
import com.game.code.graphics.Screen;

public class GameLoop implements Runnable {
	private boolean running = false;
	private boolean readyToDraw = false;
	private long lastTime;
	private long timer;
	private final double fps = 1000000000.0 / 60.0;
	private double delta;
	private int updates; //Number of updates per second
	public static int frames; //Number of renders per second
	private long now;
	Screen screen;
	Control control;
	
	GameLoop(Screen screen, Control control) {
		this.screen = screen;
		this.control = control;
	}
	
	void startGame() {
		running = true;
		readyToDraw = true;
	}
	
	void stopGame() {
		running = false;
	}
	
	boolean isRunning() {
		return running;
	}
	
	void startDrawing() {
		readyToDraw = true;
	}
	
	void stopDrawing() {
		readyToDraw = false;
	}
	
	public void run() {
		lastTime = System.nanoTime();
		timer = System.currentTimeMillis();
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime)/fps;
			lastTime = now;
			RunnableObject.emptyQueue();
			while(delta >= 1) {
				if(GameState.gameRunning)
					PlatformControls.newPlatform();
				control.update(); //To update the controls
				RunnableObject.updateObjects(); //To update the objects
				delta --;
			}
			updates ++;
			if(readyToDraw)
				screen.renderScreen(); //Render the screen
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frames = updates;
				updates = 0;
			}
		}
	}
}
