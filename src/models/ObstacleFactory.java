package models;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import controllers.AudioController;

public class ObstacleFactory {

	public static enum ObstacleType {SLIDING, BLOCKING}
	
	private static AudioController audio;
	
	private ObstacleFactory() {}
	
	public static Obstacle generate(ObstacleType t) {
		
		switch(t) {
		case SLIDING:
			return generateSlidingObstacle();
		}
		
		return null;
		
	}
	
	public static void setAudioController(AudioController audio) {
		ObstacleFactory.audio = audio;
	}
	
	private static SlidingObstacle generateSlidingObstacle() {

		ArrayList<Integer> spacings = new ArrayList<Integer>();
		ArrayList<Integer> widths = new ArrayList<Integer>();
		ArrayList<Color> colors = new ArrayList<Color>();
		Random rand = new Random();
		
		int height = rand.nextInt(12) + 24;
		double speed = Math.max(0.75, rand.nextDouble() * 3);
		
		for (int i = 0; i < 20; i++) {
			colors.add(Obstacle.getRandomColor()); 
			
			int width = Math.max(16, rand.nextInt(128));
			widths.add(width);
			double spacing = Math.max(48, (speed * (rand.nextInt(24) + (int)(1.5 * height))));
			spacings.add((int) spacing);
		}
		
		return new SlidingObstacle(audio, colors, height, speed, spacings, widths);
	}
}
