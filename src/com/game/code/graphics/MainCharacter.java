package com.game.code.graphics;

import java.awt.Color;
import java.awt.Graphics;

import com.game.code.Control;
import com.game.code.GameState;
import com.game.code.collision.CollisionDetection;

/*
 * Pretty self explanatory, this is
 * our main character!
 */

public class MainCharacter extends RunnableObject{
	
	private int IV = 4;		//Edit this variable to change the instantaneous velocity of the player's jump.
	private boolean isFalling = false;		
	private boolean isJumping = false;
	private double speed = 2;
	private double jump = 5;		//Affects how high the player can jump.
	private double fall = 1.1;
	private int startingHeight = 19;
	private int pHeight = 0;		//The players height before each jump.
	private double maxJump = pHeight - jump;	//Maximum jump is calculated by the player's current height subtracted by how high he can jump.
	private double jumpTime = 0;		//Used for calculating velocity.
	private double curTime = 0;			//Used for calculating velocity.
	private double acceleration = 0.2 / 1000000000; 		//How fast the player accelerates moving up and down during a jump.
	private int instantV = IV;
	private double velocity = acceleration*curTime + instantV; //Formula V(t)= at+v where a is acceleration and v is the instant velocity.
	
	public MainCharacter() {
		super();
		setRect(Screen.width*.5, Screen.height - startingHeight, 10, 10);
	}
	
	public void update() {
		
		//System.out.println("Jumping: " + isJumping + " Falling: " + isFalling + " Velocity: " + velocity);
		//System.out.println("Velocity: " + velocity + " Instananeous Velocity: " + instantV + " Acceleration: " + acceleration);
		//System.out.println("Player Height: " + pHeight + " Max Jump: " + maxJump);
		//System.out.println("Jump Time: " + jumpTime + " Current Time: " + curTime);
		
		curTime += System.currentTimeMillis() / 1000;
		if(CollisionDetection.detectColission(this) != null){
			instantV = IV;
			isFalling = false;
			if(!(CollisionDetection.detectColission(this) instanceof FallingPlatform)){ //Only calls these two if it isn't colliding with a platform.
				pHeight = (int)y;
				jumpTime = 0;
			}
		}
		else if(!isJumping){
			isFalling = true; 
		}
		
		//Player Control -- Jump
		velocity = acceleration * jumpTime + instantV;		//Calculates the velocity during the jump.
		if(!isFalling){	
			if(Control.jump && !isJumping){             
				isJumping = true;
				maxJump = pHeight - jump;
				curTime = 0;
			}
			if(isJumping){										
					y -= velocity;									
					pHeight -= velocity;
					jumpTime = curTime - jumpTime;
					if(pHeight < maxJump && acceleration > 0 && CollisionDetection.detectColission(this) == null){	//If the player's height is higher than
						acceleration = -acceleration;																//how high he can jump then start decelerating.
						curTime = 0;								//Velocity is recalculated					
						jumpTime = 0;								//starting from the velocity at the peak of the
						instantV = (int)velocity;					//player's jump.
					}
					else if(CollisionDetection.detectColission(this) !=null){
						if(velocity <= 0){					//If the player is on the way down then
							acceleration =-acceleration;	//reset the variables
							isJumping = false;				
							pHeight = (int)y;
							jumpTime = 0;
						}
					}
			}
				
		}
		else{
			y+=fall; 													//Player falls
			if(CollisionDetection.detectColission(this) != null){		//If it detects an object the fall speed is 1.1 (.1 faster than falling platform)
				fall = 1.1;
			}
			else if(fall == 1.1){										//if it moves off the falling object then set its fall speed to velocity.
				fall = velocity;
			}
		}
		//Player Control -- Move Left and Right
		if(Control.left){x-=speed;}
		if(Control.right){x+=speed;}
		if(x <= 0 || x >= Screen.width) //Moves between left and right sides of the screen.
			x = Screen.width - x;
	}

	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.drawRect((int)x, (int)y, (int)width, (int)height);
	}
}
