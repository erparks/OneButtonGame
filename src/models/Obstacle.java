package models;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import controllers.AudioController;

public abstract class Obstacle {

	public static final ArrayList<Color> COLORS = new ArrayList<Color>() {{
		//add(Color.BLACK);
		add(Color.BLUE);
		add(Color.CYAN);
		//add(Color.GREEN);
		add(Color.MAGENTA);
		add(Color.ORANGE);
		add(Color.PINK);
		add(Color.RED);
		//add(Color.YELLOW);
	}};
	
	protected double speed;
	protected ArrayList<GameRectangle> rects;
	protected ArrayList<Color> colors;
	protected AudioController audio;
	private boolean passedPlayer;
	
	public Obstacle(ArrayList<Color> colors, double speed, AudioController audio) {
		this.colors = colors;
		this.speed = speed;
		this.audio = audio;
		
		passedPlayer = false;
	}
	
	
	
	public abstract boolean slideDown();
	
	public boolean isOffScreen() {
		for (GameRectangle rect : rects) {
			if (rect.getUpperLeft().getY() > 512) return false;
		}
		return true;
	}
	
	public void checkPassedPlayer(double playerY) {
		
		if (passedPlayer) return;
		
		boolean passed = true;
		
		for (GameRectangle rect : rects) {
			if (rect.getUpperLeft().getY() < playerY) passed = false;
		}
		
		if (passed) {
			passedPlayer = true;
			
			audio.playDing();
		}
	}

	public double getY() {
		double maxY = -10000000;
		
		for(GameRectangle rect: rects) {
			if(rect.getY() > maxY) maxY = rect.getY();
		}
		
		return maxY;
	}
	
	
	public boolean collidesWith(Player player) {
		
		GameRectangle rect;
		for (Iterator<GameRectangle> rect_it = rects.iterator(); rect_it.hasNext();) {
			rect = rect_it.next();
			if (rect.intersectsRectangle(player) ) {
				System.out.println(rect.getColor()+ " " +  player.getColor());
				if (rect.getColor() == player.getColor()) {
					rect_it.remove();
					player.setColor(getRandomColor());
					audio.playZap();
					return false;
				}
				else return true;
			}
	
		}
		return false;
	}
	
	public static Color getRandomColor() {
		Random rand = new Random();
		
		return Obstacle.COLORS.get(rand.nextInt(Obstacle.COLORS.size()));
	}
	
	public ArrayList<GameRectangle >getRects() {
		return rects;
	}
	
	public abstract void slide();
}
