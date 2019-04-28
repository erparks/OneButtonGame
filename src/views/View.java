package views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.Obstacle;
import models.Player;
import models.RoadTick;


public class View {
	private JFrame frame;

	private JButton pauseBtn;

	private GamePanel gamePanel;
	private JPanel controlPanel;
	private JPanel topPanel;

	private JLabel scoreLbl;
	private JLabel livesLbl;

	private int width;
	private int height;

	public View() {
		width = 512;
		height = 512;

		frame = new JFrame("One Button Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);

	
		
		scoreLbl = new JLabel("Score: 0", JLabel.CENTER);
		livesLbl = new JLabel("Lives: " , JLabel.RIGHT);
		
		pauseBtn = new JButton("Pause");

		gamePanel = new GamePanel(width, height);
		controlPanel = new JPanel();
		topPanel = new JPanel();
		
		topPanel.setLayout(new BorderLayout());
		topPanel.add(scoreLbl, BorderLayout.CENTER);
		topPanel.add(livesLbl, BorderLayout.EAST);
		
		controlPanel.setLayout(new GridLayout(1,1));
		controlPanel.add(pauseBtn);


		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(gamePanel, BorderLayout.CENTER);
		frame.add(controlPanel, BorderLayout.SOUTH);

		frame.pack();
		frame.setVisible(true);
	}

	public JButton getPauseBtn() {
		return pauseBtn;
	}

	public JLabel getScoreLbl() {
		return scoreLbl;
	}

	public GamePanel getGamePanel()
	{
		return gamePanel;
	}
	
	public void draw(ArrayList<RoadTick> ticks, Player player, ArrayList<Obstacle> obstacles){
		gamePanel.draw(ticks, player, obstacles);
	}


	public void setScore(float score) {
		scoreLbl.setText(""+score);
	}

	public void setLives(int lives) {
		livesLbl.setText("Lives: " + lives);
	}
}
