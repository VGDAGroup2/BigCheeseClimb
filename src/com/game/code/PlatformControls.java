package com.game.code;

import java.util.ArrayList;
import java.util.Random;

import com.game.code.graphics.FallingPlatform;
import com.game.code.graphics.Screen;

public class PlatformControls {	
	
	static ArrayList<FallingPlatform> platforms = new ArrayList<FallingPlatform>();
	private static int spawnRate = 60; //If a platform falls this far, it will spawn a new one. 
	private static double pWidth = 100; 
	private static Random rand = new Random();
	
	public PlatformControls() {
		
	}
	
	public static void newPlatform() { //This will create a new platform if the last one gets far enough down the screen.
		if(platforms.size() > 0) {
			if(platforms.get(platforms.size() - 1).y >= spawnRate) {
				platforms.add(new FallingPlatform(Assets.PLATFORM_LEAF, rand.nextDouble() * (Screen.width - pWidth), pWidth));
			}
		} else { //if There are now other platforms (I.E. Game begin), this will spawn the first one.
			platforms.add(new FallingPlatform(Assets.PLATFORM_CLOUD, rand.nextDouble() * (Screen.width - pWidth), pWidth));
		}
	}
	
	public static void removePlatform(FallingPlatform fp) {
		if(platforms.contains(fp))
			platforms.remove(fp);
	}
	
	public static void movePlatforms(double y) {
		for(FallingPlatform f : platforms)
			f.moveMore(y);
	}
}


	
	
	


