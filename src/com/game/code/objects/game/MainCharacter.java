package com.game.code.objects.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.game.code.Assets;
import com.game.code.Control;
import com.game.code.GameRunner;
import com.game.code.GameState;
import com.game.code.MusicPlayer;
import com.game.code.PlatformControls;
import com.game.code.objects.CollisionDetection;
import com.game.code.objects.GameObject;
import com.game.code.objects.Screen;
import com.game.code.objects.interfaces.Drawable;
import com.game.code.objects.interfaces.Updatable;

/*
 * Pretty self explanatory, this is
 * our main character!
 */

public class MainCharacter extends GameObject implements Updatable, Drawable {
	private static final long serialVersionUID = 1L;
	private int IV = 7;		//Edit this variable to change the instantaneous velocity of the player's jump.
	private boolean isFalling = false;		
	private boolean isJumping = false;
	private double speed = 2;
	private double fall = 1.1;
	private int startingHeight = 40;	//Maximum jump is calculated by the player's current height subtracted by how high he can jump.
	private double jumpTime = 0;		//Used for calculating velocity.
	private double curTime = 0;			//Used for calculating velocity.
	private double acceleration = 0.3 / 1000000000; 		//How fast the player accelerates moving up and down during a jump.
	private int instantV = IV;
	private double velocity = acceleration*curTime + instantV; //Formula V(t)= at+v where a is acceleration and v is the instant velocity.

	BufferedImage image;
	
	public MainCharacter(BufferedImage i) {
		setRect(Screen.width*.5, Screen.height - startingHeight, 30, 35);
		image = Assets.getScaledInstance(i, (int)width, (int)height, true);
	}

	public void update() {
		curTime += System.currentTimeMillis() / 1000;
		if(CollisionDetection.detectColission(this) != null) {
			instantV = IV;
			isFalling = false;
			if(!(CollisionDetection.detectColission(this) instanceof FallingPlatform)) { //Only calls these two if it isn't colliding with a platform.
				jumpTime = 0;
			}
		} else if(!isJumping) {
			isFalling = true; 
		}

		//Player Control -- Jump
		velocity = acceleration * jumpTime + instantV;		//Calculates the velocity during the jump.
		if(!isFalling) {	
			if(Control.jump && !isJumping) {
				isJumping = true;
				curTime = 0;
			}
			if(isJumping) {																		
					y -= velocity;
					jumpTime = curTime - jumpTime;
					if(acceleration > 0 && CollisionDetection.detectColission(this) == null) {	//If the player's height is higher than
						acceleration = -acceleration;																//how high he can jump then start decelerating.
						curTime = 0;								//Velocity is recalculated					
						jumpTime = 0;								//starting from the velocity at the peak of the
						instantV = (int)velocity;					//player's jump.
					} else if(CollisionDetection.detectColission(this) !=null) {
						if(velocity <= 0) {					//If the player is on the way down then
							acceleration =-acceleration;	//reset the variables
							isJumping = false;				
							jumpTime = 0;
						}
					}
			}	
		} else {
			y+=fall; 													//Player falls
			if(CollisionDetection.detectColission(this) != null)	//If it detects an object the fall speed is 1.1 (.1 faster than falling platform)
				fall = GameState.fallRate + .1;
			else if(fall == GameState.fallRate + .1)										//if it moves off the falling object then set its fall speed to velocity.
				fall = velocity;
		}

		if(y < Screen.height*.5) { //Moves the platforms and character down if character goes above 50% of the screen.
			GameState.floorTrigger = true;
			double hMod = ((Screen.height * .5) - y);
			PlatformControls.movePlatforms(hMod);
			y += (hMod);
			GameState.heightReached += hMod/60;
		}
		
		if(y > Screen.height) { //DEATH!!!
			GameRunner.resetGame();
			GameRunner.runMenu();
		}
		
		if(Control.jump) {
			MusicPlayer.playJumpSound();
		}

		//Player Control -- Move Left and Right
		if(Control.left){x-=speed;}
		if(Control.right){x+=speed;}
		if(x <= 0 || x >= Screen.width) //Moves between left and right sides of the screen.
			x = Screen.width - x;
		
		//Changes direction player is facing.
		if(Control.left) {
			if(!GameState.playerFaceLeft)
				image = Assets.flipImage(image);
			GameState.playerFaceLeft = true;
		} else if(Control.right) {
			if(GameState.playerFaceLeft)
				image = Assets.flipImage(image);
			GameState.playerFaceLeft = false;
		}	
	}

	public void draw(Graphics g) {
		g.drawImage(image, (int)x, (int)y, (int)width, (int)height, null);
	}
}