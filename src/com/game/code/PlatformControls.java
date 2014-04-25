package com.game.code;

import java.util.ArrayList;

import com.game.code.graphics.FallingPlatform;

public class PlatformControls {	
	
	static ArrayList<FallingPlatform> platforms = new ArrayList<FallingPlatform>();
	private static int spawnRate = 60; //If a platform falls this far, it will spawn a new one. 
	
	public PlatformControls() {
		
	}
	
	public static void newPlatform() {
		if(platforms.size() > 0) {
			if(platforms.get(platforms.size() - 1).y >= spawnRate) {
				platforms.add(new FallingPlatform());
			}
		} else {
			platforms.add(new FallingPlatform());
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


	
	
	


