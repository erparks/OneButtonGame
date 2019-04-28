package controllers;

import java.util.TimerTask;

/**
 * Runs the update method in the main controller.
 * @author Ethan Parks
 * 
 */
public class TimeController extends TimerTask {

	/**
	 * Main controller.
	 */
	private Controller controller;

	/**
	 * Create new TimeController to update game each frame.
	 * @param controller Main controller.
	 */
	public TimeController(Controller controller) {
		this.controller = controller;
	}

	/**
	 * Calls the update method in the main controller.
	 */
	@Override
	public void run() {
		controller.update();
	}

}
