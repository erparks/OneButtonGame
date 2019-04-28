package models;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;


public class Player extends GameRectangle{

	private static final int size = 16;
	
	private Color color;
	private int pushDistance;
	
	public Player(double y) {
		super(new Point.Double(256 - size/ 2, y), new Dimension(size, size));
		
		color = Obstacle.getRandomColor();
		
		pushDistance = 2;
	}
	
	public void push() {
		double y = getY();
		
		y -= pushDistance;
		
		if (y < 0) {
			y = 0;
		}
		
		setLocation(new Point.Double(getLocation().getX(), y));
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
