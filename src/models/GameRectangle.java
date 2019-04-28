package models;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

/**
 * Bounding box for entities within the game.
 * @author Ethan Parks 
 */
public class GameRectangle {

	/**
	 * Screen coordinates of the game entity.
	 */
	private Point.Double location;
	/**
	 * Size of the game entity.
	 */
	private Dimension dimension;
	
	private Color color;

	/**
	 * Create new GameRectangle with given location and dimension.
	 * @param location  Screen coordinates of the game entity.
	 * @param dimension Size of the game entity.
	 */
	public GameRectangle(Point.Double location, Dimension dimension) {
	
		this.location = location;
		this.dimension = dimension;
		
		color = Color.BLUE;
	}

	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public double getY() {
		return location.getY();
	}
	
	public boolean slideDown() {
		boolean wrapped = false;
		
		double y = location.getY();
		y += Model.BASE_UPDATE_DISTANCE;


		if (y >= 512) 
			{
				y = 0;
				wrapped = true;
			}
		
		
		location.setLocation(location.getX(), y);
		
		return wrapped;
	}
	
	/**
	 * Returns true if this GameRectangle intersects the given GameRectangle.
	 * 
	 * @param rect GameRectanlge to check for collision against.
	 * @return True if this GameRectangle intersects the given GameRectangle.
	 */
	public boolean intersectsRectangle(GameRectangle rect) {

		return intersectsPoint(rect.getUpperLeft()) || intersectsPoint(rect.getUpperRight())
				|| intersectsPoint(rect.getLowerRight()) || intersectsPoint(rect.getLowerLeft());
	}

	/**
	 * Returns true if this GameRectangle contains the given point.
	 * 
	 * @param p The point to check if the GameRectangle contains.
	 * @return True if this GameRectangle contains the given point.
	 */
	public boolean intersectsPoint(Point.Double p) {
		return ((p.x >= getLocation().x) && (p.x <= getLocation().x + dimension.getWidth()) && (p.y >= getLocation().y)
				&& (p.y <= getLocation().y + dimension.getHeight()));
	}

	/**
	 * Returns the upper left corner of the GameRectangle.
	 * 
	 * @return The upper left corner of the GameRectangle.
	 */
	public Point.Double getUpperLeft() {
		return location;
	}

	/**
	 * Returns the upper right corner of the GameRectangle.
	 * 
	 * @return The upper right corner of the GameRectangle.
	 */
	public Point.Double getUpperRight() {
		return new Point.Double(location.x + dimension.getWidth(), location.y);
	}

	/**
	 * Returns the lower left corner of the GameRectangle.
	 * 
	 * @return The lower left corner of the GameRectangle.
	 */
	public Point.Double getLowerLeft() {
		return new Point.Double(location.x, location.y + dimension.getHeight());
	}

	/**
	 * Returns the lower right corner of the GameRectangle.
	 * 
	 * @return The lower right corner of the GameRectangle.
	 */
	public Point.Double getLowerRight() {
		return new Point.Double(location.x + dimension.getWidth(), location.y + dimension.getHeight());
	}

	/**
	 * Returns the center of the GameRectangle.
	 * 
	 * @return The center of the GameRectangle.
	 */
	public Point.Double getCenter() {
		return new Point.Double(location.x + dimension.getWidth() / 2, location.y + dimension.getHeight() / 2);
	}

	/**
	 * Returns the upper left corner of the GameRectangle.
	 * 
	 * @return The upper left corner of the GameRectangle.
	 */
	public Point.Double getLocation() {
		return location;
	}

	/**
	 * Sets the upper left corner of the GameRectangle
	 * @param location New location for the upper left corner.
	 */
	public void setLocation(Point.Double location) {
		this.location = location;
	}

	/**
	 * Sets the center location of the GameRectangle.
	 * @param location New center of the GameRectangle.
	 */
	public void setCenterLocation(Point.Double location) {
		this.location.x = location.x - dimension.getWidth() / 2;
		this.location.y = location.y - dimension.getHeight() / 2;
	}

	/**
	 * Returns the size of the GameRectanlge.
	 * @return The size of the GameRectanlge.
	 */
	public Dimension getDimension() {
		return dimension;
	}
}
