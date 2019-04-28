package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

import models.Model;
import models.ObstacleFactory;
import views.View;

/**
 * Handles all direct interaction with the main window.
 *         Updates the game every time step.
 * @author Ethan Parks 
 */
public class Controller implements ActionListener {

	/**
	 * Time step between frames.
	 */
	private static long DELTA_T = 10;

	/**
	 * True if game is currently playing and not paused.
	 */
	private boolean isPlaying;

	/**
	 * True if the user has lost the game and not yet restarted.
	 */
	private boolean gameOver;


	/**
	 * Back end data for the game.
	 */
	private Model model;
	/**
	 * Front end display for the game.
	 */
	private View view;

	private InputController inputController;
	private AudioController audio;

	/**
	 * Initialize all necessary classes for the game to run. Starts a thread to
	 * listen for user input on the command line.
	 */
	public Controller() {
		audio = new AudioController();
		
		
		model = new Model();
		view = new View();

		inputController = new InputController(model);
		
		isPlaying = true;
		
		ObstacleFactory.setAudioController(audio);
		
		
		audio.playStart();
	}

	/**
	 * Set this class as the listener for the view created in the constructor. Load
	 * map into the model. Start the timer for the update function.
	 * 
	 */
	public void control() {

		view.getPauseBtn().addActionListener(this);
		view.getGamePanel().addKeyListener(inputController);


		Timer timerObj = new Timer(true);
		timerObj.scheduleAtFixedRate(new TimeController(this), 0, DELTA_T);
	}

	/**
	 * The model and view are updated one time step.
	 * 
	 * If the game ends this time step, a window is shown to alert the user and the
	 * game is stopped.
	 */
	protected void update() {

		if (!isPlaying)
			return;

		if (model.getLives() == 0) {
			gameOver = true;
			isPlaying = false;
			new GameOverController(this, (int)model.getScore()).control();
			audio.playFail();
		}

		model.update(DELTA_T);

		view.draw(model.GetRoadTicks(), model.getPlayer(), model.getObstacles());

		view.setScore(model.getScore());
		view.setLives(model.getLives());
	}

	/**
	 * Reset to the game to a blank slate.
	 */
	public void reset() {
		model.reset();
		gameOver = false;
		isPlaying = true;
		audio.playStart();
	}


	public void setModel(Model model) {
		this.model = model;
	}

	/**
	 * Responds to button presses in the main window.
	 *  
	 * If "Buy Tower" is clicked, the game begins the potential tower placement. if
	 * "Pause" is clicked, the game switches from paused to playing or vice versa.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == view.getPauseBtn()) {
			if (!gameOver)
				isPlaying = !isPlaying;
		}
	}
}
