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
	
	private boolean isFalling = false;
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
		if(CollisionDetection.detectColission(this) != null){
			isFalling = false;
		}
		else if(!isJumping){
			isFalling = true;
		}
		
		if(!isFalling){	
			if(Control.jump){
				if(!isJumping)
					jump = 5;
				isJumping = true;
			}
			if(isJumping){
				if(jump < maxJump && jump > 0){
					y-= jump;
					jump += jumpSpeed;
					if(jump < (maxJump / 2) && !slowed)
						jumpSpeed += 0.05;
					else if(jump > (maxJump / 2)){
						slowed = true;
						jumpSpeed -= 0.05;
					}
				}
				else{
					jumpSpeed = 0;
					isFalling = true;
				}
			}
				
		}
		else{
			y+=jump;
			if(isJumping){
				jump += jumpSpeed;
				if(jumpSpeed < 0.2)
					jumpSpeed += 0.05;
			}
			if(CollisionDetection.detectColission(this) !=null){
				isJumping = false;
				jumpSpeed = 0.2;
				jump = 1.1;
			}
			else if(jump == 1.1)
				jump = 5;
		}
		
		if(Control.left){x-=speed;}
		if(Control.right){x+=speed;}
		if(x <= 0 || x >= Screen.width)
			x = Screen.width - x;
	}

	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.drawRect((int)x, (int)y, (int)width, (int)height);
	}
}
