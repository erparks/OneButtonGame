package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import models.GameRectangle;
import models.Obstacle;
import models.Player;
import models.RoadTick;


/**
 * @author Ethan Parks Panel for the game to be drawn on.
 */
public class GamePanel extends JPanel {
	/**
	 * Buffered image to drawn the game on.
	 */
	private BufferedImage img;
	/**
	 * Graphics object to draw to.
	 */
	private Graphics2D imgG2;

	/**
	 * Texture for the non-path portion of the panel.
	 */
	private BufferedImage backgroundTile;
	/**
	 * Texture to be used as the path.
	 */
	private BufferedImage pathTile;

	/**
	 * Panel width.
	 */
	private int width;
	/**
	 * Panel height.
	 */
	private int height;
	/**
	 * Size of the path and background textures.
	 */
	private int tileSize;

	/**
	 * Loads textures, initializes panel, initializes images.
	 * 
	 * @param width  Panel width.
	 * @param height Panel height.
	 */
	public GamePanel(int width, int height) {
		this.width = width;
		this.height = height;
		this.tileSize = 64;

		setPreferredSize(new Dimension(width, height));
		setFocusable(true);
		setDoubleBuffered(true);

		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		imgG2 = img.createGraphics();
		imgG2.setFont(new Font("default", Font.BOLD, 20));

	}

	/**
	 * Draws all game compnents to the buffered image and the paints to the screen.
	 * 
	 * @param path           GameRectangles representing the path point locations
	 *                       and sizes.
	 * @param enemies        Active enemies to be drawn.
	 * @param towers         Towers to be drawn.
	 * @param potentialTower PotentialTower to draw.
	 */
	public void draw(ArrayList<RoadTick> ticks, Player player, ArrayList<Obstacle> obstacles) {
		drawBackground();
		
		drawTicks(ticks);
		
		drawPlayer(player);
		
		drawObstacles(obstacles);
		
		repaint();
	}

	private void drawObstacles(ArrayList<Obstacle> obstacles) {
		for(Obstacle obs: obstacles) {
			for(GameRectangle rect : obs.getRects()) {
			imgG2.setColor(rect.getColor());
			
			imgG2.fillRect(
					(int)rect.getLocation().x, 
					(int)rect.getLocation().y, 
					(int)rect.getDimension().getWidth(), 
					(int)rect.getDimension().getHeight());
			}
		}
	}
	
	private void drawPlayer(Player player) {
		imgG2.setColor(player.getColor());

		imgG2.fillOval((int)(256 - player.getDimension().getWidth()/2), 
		   (int)player.getY(), 
		   (int)player.getDimension().getWidth(), 
		   (int)player.getDimension().getWidth());
	}
	
	private void drawTicks(ArrayList<RoadTick> ticks) {
		imgG2.setColor(Color.GREEN);
		
		for(RoadTick tick : ticks) {
			imgG2.drawLine(0, (int)tick.getY(), 512, (int)tick.getY());
		}
	}
	
	/**
	 * Draw the background texture over the whole panel.
	 */
	private void drawBackground() {
		imgG2.setColor(Color.BLACK);
		imgG2.fillRect(0,0,512, 512);
		
		imgG2.setColor(Color.BLACK);
		imgG2.fillRect(128, 0, 256, 512);
	}

	/**
	 * Draw the image to the screen.
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g.create();
		g2.drawImage(img, 0, 0, width, height, null);
	}

}
