package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import models.ObstacleFactory.ObstacleType;

/**
 * Backend data for the game.
 * 
 * @author Ethan Parks
 */
public class Model implements Serializable {

	/**
	 * Number of lives the user starts with.
	 */
	public static final int STARTING_LIVES = 1;
	
	public static final int ROAD_TICK_COUNT = 8;
	
	public static final int BASE_UPDATE_DISTANCE = 1;
	
	
	private long nextSpawnTime;
	/**
	 * Current score in the game.
	 */
	private float score;
	/**
	 * Amount of time that has passed in the game.
	 */
	private long time;
	/**
	 * Number of lives the player has remaining.
	 */
	private int lives;
	
	private boolean pushingPlayer;

	private ArrayList<RoadTick> ticks;
	
	private ArrayList<Obstacle> obstacles;
	
	private Player player;
	
	/**
	 * Create model with empty no towers or enemies.
	 * 
	 * The path is not reset.
	 */
	public Model() {


		reset();
	}

	/**
	 * Clear out all enemies and towers. Reset all counters.
	 */
	public void reset() {
		lives = STARTING_LIVES;
		time = 0;
		nextSpawnTime = 0;
		score = 0;
		ticks = new ArrayList<RoadTick>();
		player = new Player(256);
		pushingPlayer = false;
		obstacles = new ArrayList<Obstacle>();
		for (int i = 0; i < ROAD_TICK_COUNT; i++){
			ticks.add(new RoadTick(i * 512/8));
		}
	}

	/**
	 * Updates all game entities one frame. Updates the total time passed.
	 * 
	 * @param deltaT Amount of time passed since last update.
	 */
	public void update(float deltaT) {

		
		if (time >= nextSpawnTime) {
			spawnObstacle();
		}
		
		updateTicks();
		updatePlayer();
		updateObstacles();
		
		
		if(checkCollisions())lives--;

		
		time += deltaT;
		score = time/1000;
	}
	
	public boolean checkCollisions(){
		for (Obstacle obs : obstacles) {
			obs.checkPassedPlayer(player.getY() + player.getDimension().getHeight());
			
			if (obs.collidesWith(player)) {
				return true;
			}
		}
		
		return false;
	}
	
	private void spawnObstacle() {
		
		System.out.println("time: " + time + " next: " + nextSpawnTime);
		nextSpawnTime = time + 2500;
		obstacles.add(ObstacleFactory.generate(ObstacleType.SLIDING));
	}
	
	private void updateObstacles() {

		for (Iterator<Obstacle> obs_it = obstacles.iterator(); obs_it.hasNext();) {
			Obstacle obs = obs_it.next();
			
			boolean wrapped = obs.slideDown();
			obs.slide();

			//System.out.println(obs.getY());
			
			if (wrapped) {
				obs_it.remove();
			}
		}
	}
	
	private void updatePlayer() {
		if (player.slideDown()) {
			lives = 0;
		}
		
		if (pushingPlayer) {
			player.push();
		}
	}
	
	private void updateTicks() {
		for(RoadTick tick : ticks) {
			tick.slideDown();
		}
	}
	
	public ArrayList<Obstacle> getObstacles(){
		return obstacles;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public ArrayList<RoadTick> GetRoadTicks(){
		return ticks;
	}
	
	/**
	 * Returns the current score.
	 * 
	 * @return The current score.
	 */
	public float getScore() {
		return score;
	}

	/**
	 * Set the current score.
	 * 
	 * @param score The new score value.
	 */
	public void setScore(float score) {
		this.score = score;
	}

	/**
	 * Returns the current game time.
	 * 
	 * @return The current game time.
	 */
	public long getTime() {
		return time;
	}

	/**
	 * Set the current time.
	 * 
	 * @param time The new time value.
	 */
	public void setTime(long time) {
		this.time = time;
	}


	/**
	 * Add to the current score value.
	 * 
	 * @param scoreValue Amount to add to the current score value.
	 */
	public void addScore(float scoreValue) {
		score += scoreValue;
	}

	/**
	 * Returns the number of lives remaining.
	 * 
	 * @return The number of lives remaining.
	 */
	public int getLives() {
		return lives;
	}

	/**
	 * Set the current lives.
	 * 
	 * @param lives The new lives value.
	 */
	public void setLives(int lives) {
		this.lives = lives;
	}

	public boolean isPushingPlayer() {
		return pushingPlayer;
	}

	public void setPushingPlayer(boolean pushingPlayer) {
		this.pushingPlayer = pushingPlayer;
	}
}
