package controllers;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class AudioController {

	private Clip zap;
	private Clip start;
	private Clip ding;
	private Clip fail;
	
	public AudioController() {
		zap = createSound("resources/zap.wav");
		start = createSound("resources/start.wav");
		ding = createSound("resources/ding.wav");
		fail = createSound("resources/fail.wav");
	}
	
	public void playZap() {
		System.out.println("Zap");
		zap.setFramePosition(0);
		zap.start();
	
	}
	
	public void playStart() {
		start.setFramePosition(0);
		start.start();
	}
	
	public void playDing() {
		ding.setFramePosition(0);
		ding.start();
	}
	
	public void playFail() {
		fail.setFramePosition(0);
		fail.start();
	}
	
	private Clip createSound(String fileLocation) {

	    try {
	    File file = new File(fileLocation);	
	    
		AudioInputStream stream = AudioSystem.getAudioInputStream(file);

	    AudioFormat format = stream.getFormat();
	    DataLine.Info info = new DataLine.Info(Clip.class, format);
	    Clip clip = (Clip) AudioSystem.getLine(info);

	    clip.open(stream);
	    return clip;
	    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    return null;
	}
}
