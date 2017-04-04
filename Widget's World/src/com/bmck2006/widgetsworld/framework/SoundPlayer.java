package com.bmck2006.widgetsworld.framework;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundPlayer {
	
	private AudioInputStream audioInputStream, audioInputStream2;
	private Clip music;
	
	public SoundPlayer() {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File("res/loop.wav").getAbsoluteFile());
			music = AudioSystem.getClip();
		} catch (Exception ex){
			System.out.println("Error with loading sounds.");
	        ex.printStackTrace();
		}
    }

	public void playMusic() {
	    try {
	        music.open(audioInputStream);
	        music.loop(500); //
	    } catch(Exception ex) {
	        System.out.println("Error with playing music.");
	        ex.printStackTrace();
	    }
	}
}
