package com.game.code.graphics;

import java.awt.Color;
import java.awt.Graphics;

import com.game.code.Control;
import com.game.code.collision.CollisionDetection;

/*
 * Pretty self explanatory, this is
 * our main character!
 */

public class MainCharacter extends RunnableObject {
	
	private boolean isFalling = false; //May change to isGrounded instead later.
	private boolean isJumping = false;
	private double speed = 2;
	private double maxJump = 10;
	private double jumpSpeed = 0.2;
	private boolean slowed = false;
	private double jump = 5;
	private int startingHeight = 50;
	
	public MainCharacter() {
		super();
		setRect(Screen.width*.5, Screen.height - startingHeight, 10, 10);
	}
	
	public void update() {
		if(CollisionDetection.detectColission(this) != null){ //Checks to see if it is colliding with something.
			isFalling = false;
		}
		else if(!isJumping){
			isFalling = true; //If it isn't colliding with anything and it isn't jumping then it is falling.
		}
		
		//Player Control -- Jump
		if(!isFalling){	
			if(Control.jump){                       //Checks to see if it isn't falling.
				if(!isJumping)						//If it isn't falling then it checks if it isn't already jumping. If it isn't set the jump to 5.
					jump = 5;
				isJumping = true;
			}
			if(isJumping){										//If the player isJumping then
				if(jump < maxJump && jump > 0){					//if the jump amount is less than the maximum height it can jump to and jump isn't a negative number
					y-= jump;									//the player will jump.
					jump += jumpSpeed;							//jumpSpeed is to give it the effect that it slows down as it gets to the peak of the jump.
					if(jump < (maxJump / 2) && !slowed)			//if the player hasn't reached the peak(slowed) and the jump height is 
						jumpSpeed += 0.05;						//less than half the maximum jump height then the player is speed up.
					else if(jump > (maxJump / 2)){				//else if it is higher than half the maximum jump height it will start to slow down.
						slowed = true;							//slowed is set to true here
						jumpSpeed -= 0.05;
					}
				}
				else{
					jumpSpeed = 0;								//Player isn't jumping anymore so jump speed is set to 0.
					isFalling = true;							//and the player is now falling.
				}
			}
				
		}
		else{													//The player is falling
			y+=jump;			
			if(isJumping){										//If the player jumped then the second part of the jump is activated.
				jump += jumpSpeed;								//What happens is the speed at which the player falls increases until it his the cap 0.2.
				if(jumpSpeed < 0.2)
					jumpSpeed += 0.05;
			}														
			if(CollisionDetection.detectColission(this) !=null){	//If player is colliding with something then it is no longer jumping.
				isJumping = false;									//and jumpSpeed is reset to its original value.
				jumpSpeed = 0.2;
				jump = 1.1;											//jump is set to 1.1 here because if it falling at the same rate as the platforms the 
			}														//collision isn't detected.
			else if(jump == 1.1)									//if the player walks off the platform 
				jump = 5;											//then the jump is reset to 5.
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
