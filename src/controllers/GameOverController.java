package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import views.GameOverView;

/**
 * Handles input to the Game Over window.
 * @author Ethan Parks 
 * 
 */
public class GameOverController implements ActionListener {

	/**
	 * View for the Game Over window.
	 */
	private GameOverView gov;
	/**
	 * Main window controller.
	 */
	private Controller controller;

	/**
	 * Initialize the Game Over window.
	 * @param controller Main window controller.
	 */
	public GameOverController(Controller controller, int score) {
		this.controller = controller;

		gov = new GameOverView(score);
		
	}

	
	/**
	 * Set this controller as the Action Listener for the corresponding Game Over window
	 */
	public void control() {
		gov.getPlayAgainBtn().addActionListener(this);
		gov.getQuitBtn().addActionListener(this);
	}

	/**
	 * Responds to button presses in the Game Over window.
	 * 
	 * If "Play Again" is pressed, the game is reset.
	 * If "Quit" is pressed, the program exits.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == gov.getPlayAgainBtn()) {
			controller.reset();
			gov.getFrame().dispatchEvent(new WindowEvent(gov.getFrame(), WindowEvent.WINDOW_CLOSING));
		} else if (e.getSource() == gov.getQuitBtn()) {
			System.exit(1);
		}
	}

}
