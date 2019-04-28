package models;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import controllers.AudioController;

public class SlidingObstacle extends Obstacle {

	public static enum DIRECTION {
		LEFT(1), 
		RIGHT(-1);
		
		public final int value;
		 
	    private DIRECTION(int value) {
	        this.value = value;
	    }
	}
	

	private static final ArrayList<DIRECTION> allDirections = new ArrayList<DIRECTION>() {{
		add(DIRECTION.LEFT);
		add(DIRECTION.RIGHT);
	}};
	
	private DIRECTION direction;

	
	public SlidingObstacle( AudioController audio, ArrayList<Color> colors, int height, double speed, ArrayList<Integer> spacings, ArrayList<Integer> widths) 
	{
		super(colors, speed, audio);
		Random rand = new Random();
		direction = allDirections.get(rand.nextInt(allDirections.size()));;
		
		rects = new ArrayList<GameRectangle>(widths.size());
		
		int widthUsed = 0;
		for(int i = 0; i < widths.size(); i++) {
			GameRectangle rect = null;
			if(direction == DIRECTION.RIGHT) {
				double x = 512 - (widthUsed) - widths.get(i);
				
				rect = new GameRectangle(new Point.Double(x, -height), new Dimension(widths.get(i), height));

			}
			else rect = new GameRectangle(new Point.Double(widthUsed, -height), new Dimension(widths.get(i), height));
				
				
			widthUsed += widths.get(i) + spacings.get(i);
			rect.setColor(colors.get(i));
			rects.add(rect);
		}
		
//		System.out.println("===================== New obstacle spawned - sliding");
//		for(GameRectangle rect : rects) {
//			System.out.println("Width: " + rect.getDimension().getWidth());
//			System.out.println("Starting x: " + rect.getLocation().getX());
//		}
		
		
	}

	public boolean slideDown() {
		
		boolean wrapped = false;
		for (GameRectangle rect : rects) {
			if (rect.slideDown()) wrapped = true;
		}
		return wrapped;
	}
	
	public void slide() {
		double delta = direction == DIRECTION.LEFT ? -speed : speed;
		
		for(GameRectangle rect: rects) {
			rect.setLocation(new Point.Double(rect.getLocation().getX() + delta, rect.getY()));
		}
	}
}
