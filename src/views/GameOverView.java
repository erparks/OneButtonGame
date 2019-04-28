package views;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * @author Ethan Parks
 * View for the game over window.
 */
public class GameOverView {
	/**
	 * Frame for the game over window
	 */
	private JFrame frame;
	/**
	 * Label to display that the game is over.
	 */
	private JLabel gameOverLbl;
	/**
	 * Button to be pressed to restart the game.
	 */
	private JButton playAgainBtn;
	/**
	 * Button to exit to program.
	 */
	private JButton quitBtn;
	/**
	 * Panel which holds the buttons along the bottom of the frame.
	 */
	private JPanel buttonPanel;
	
	/**
	 * Create a show the game over window.
	 */
	public GameOverView(int score) {
		frame = new JFrame("Game Over");
		frame.setSize(200, 200);
		frame.setLayout(new BorderLayout());
		
		gameOverLbl = new JLabel("Game Over\nYour score: " + score, SwingConstants.CENTER);
		
		buttonPanel = new JPanel();
		
		playAgainBtn = new JButton("Play Again");
		quitBtn = new JButton("Quit");
		
		buttonPanel.setLayout(new BorderLayout());
		
		buttonPanel.add(playAgainBtn, BorderLayout.WEST);
		buttonPanel.add(quitBtn, BorderLayout.EAST);
		
		frame.add(gameOverLbl, BorderLayout.CENTER);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Returns the "Play Again" button.
	 * @return The "play Again" button.
	 */
	public JButton getPlayAgainBtn() {
		return playAgainBtn;
	}

	/** 
	 * Returns the "Quit" button.
	 * @return The "Quit" button.
	 */
	public JButton getQuitBtn() {
		return quitBtn;
	}
	
	/**
	 * Returns the game over frame.
	 * @return The game over frame.
	 */
	public JFrame getFrame() {
		return frame;
	}
}
