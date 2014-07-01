package com.game.code;

import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class MusicPlayer {
	static Clip clip;
	MusicPlayer() {
	       
	}
	
	public static void playJumpSound() {
		if(clip == null) {
			try {
				clip = AudioSystem.getClip();
				clip.open(Assets.JUMP);
			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(!clip.isRunning()) {
			clip.start();
			clip.setFramePosition(0);
		}
	}
}
