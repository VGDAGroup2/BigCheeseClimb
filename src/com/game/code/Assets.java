package com.game.code;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import com.game.code.graphics.RunnableObject;

/*
 * This is where we load all the assets.
 * If you want to load multiple assets other than an image create a new method for it.
 */

public class Assets {
	
	public static BufferedImage PLATFORM_LEAF;
	public static BufferedImage PLATFORM_CLOUD;
	
	public static void load() {
		PLATFORM_LEAF = loadImage("/leafPlatform.png");
		PLATFORM_CLOUD = loadImage("/cloudPlatform.png");
	}
	
	private static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(RunnableObject.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
