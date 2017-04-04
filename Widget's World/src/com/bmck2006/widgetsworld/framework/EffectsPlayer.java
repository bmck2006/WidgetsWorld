package com.bmck2006.widgetsworld.framework;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class EffectsPlayer {
	
	public static synchronized void playBark() {
		new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					Clip clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(java.lang.String.class.getClass().getResourceAsStream("res/Woof.wav"));
					clip.open(inputStream);
					clip.start();
				} catch (Exception e) {
					System.out.println("Play error");
					
				}
			}
		}).start();
	}

	
	

}
