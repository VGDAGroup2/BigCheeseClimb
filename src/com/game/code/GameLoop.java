package com.game.code;

public class GameLoop implements Runnable{
	private boolean running = false;
	private boolean readyToDraw = false;
	private long lastTime;
	private long timer;
	private final double fps = 1000000000.0 / 60.0;
	private double delta;
	private int updates; //Number of updates per second
	public static int frames; //Number of renders per second
	private long now;
	//Screen screen;
	//Control control;
	
	GameLoop(/*Screen screen, Control control*/) {
		//this.screen = screen;
		//this.control = control;
	}
	
	void startGame() {
		running = true;
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
	
	public void run() {
		lastTime = System.nanoTime();
		timer = System.currentTimeMillis();
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime)/fps;
			lastTime = now;
			//RunnableObject.emptyQueue(); This will be implemented later when we add objects to the game
			while(delta >= 1) {
				//control.update(); To update the controls
				//screen.update(); To update the screen
				//RunnableObject.updateObjects(); To update the 
				delta --;
			}
			updates ++;
			if(readyToDraw)
				//screen.renderScreen(); To render the screen
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frames = updates;
				updates = 0;
			}
		}
	}
}
