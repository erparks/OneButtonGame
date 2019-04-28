package controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import models.Model;

public class InputController implements KeyListener {
	private Model model;
	
	public InputController(Model model) {
		this.model = model;
	}
	
	public void handleInput(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			model.setPushingPlayer(true);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		model.setPushingPlayer(false);
	}
}
