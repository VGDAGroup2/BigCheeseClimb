package com.game.code;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.game.code.objects.GameObject;

/*
 * This is where we load all the assets.
 * If you want to load multiple assets other than an image create a new method for it.
 */

public class Assets {
	
	public static BufferedImage PLATFORM_LEAF;
	public static BufferedImage PLATFORM_CLOUD;
	public static BufferedImage PLATFORM_SPACE;
	public static BufferedImage BACKGROUND;
	public static BufferedImage MOUSE;
	public static BufferedImage BUTTON_START;
	public static BufferedImage BUTTON_CONTROLS;
	public static BufferedImage BUTTON_CREDITS;
	public static AudioInputStream JUMP;
	
	public void load() {
		PLATFORM_LEAF = loadImage("/leafPlatform.png");
		PLATFORM_CLOUD = loadImage("/cloudPlatform.png");
		PLATFORM_SPACE = loadImage("/spacePlatform.png");
		BACKGROUND = loadImage("/background.png");
		MOUSE = loadImage("/mouse.png");
		BUTTON_START = loadImage("/startButton.png");
		BUTTON_CONTROLS = loadImage("/controlsButton.png");
		BUTTON_CREDITS = loadImage("/creditsButton.png");
		try {
			JUMP = loadMusic("/jumpSound.wav");
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(GameObject.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static AudioInputStream loadMusic(String path) throws UnsupportedAudioFileException, IOException {
		InputStream url = GameObject.class.getResourceAsStream(path);
		BufferedInputStream stream = new BufferedInputStream(url);
		return AudioSystem.getAudioInputStream(stream);
	}
	
	public static BufferedImage getScaledInstance(BufferedImage img, int targetWidth, int targetHeight, boolean higherQuality) {
		int type = (img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
		BufferedImage ret = (BufferedImage)img;
		int w, h;
		if (higherQuality) {
			// Use multi-step technique: start with original size, then
			// scale down in multiple passes with drawImage()
			// until the target size is reached
			w = img.getWidth();
			h = img.getHeight();
		} else {
			// Use one-step technique: scale directly from original
			// size to target size with a single drawImage() call
			w = targetWidth;
			h = targetHeight;
		}

		do { //half the size, buffer and render. Keep doing this until you reach the target size.
			if (higherQuality && w > targetWidth) {
				w *= .5;
				if (w < targetWidth)
					w = targetWidth;
			}

			if (higherQuality && h > targetHeight) {
				h *= .5;
				if (h < targetHeight)
				h = targetHeight;
			}

			BufferedImage tmp = new BufferedImage(w, h, type);
			Graphics2D g2 = tmp.createGraphics();
			g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			//g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			g2.drawImage(ret, 0, 0, w, h, null);
			g2.dispose();

			ret = tmp;
		} while (w != targetWidth || h != targetHeight);
		return ret;
	}

	public static BufferedImage flipImage(BufferedImage img){						//This method will take an image and flip it by scaling it
		AffineTransform trans1 = AffineTransform.getScaleInstance(-1, 1);			//horizontally by the value of its negative width.
		trans1.translate(-img.getWidth(), 0);										
		AffineTransformOp trans2 = new AffineTransformOp(trans1, null);
		return trans2.filter(img, null);
		
	}
}
