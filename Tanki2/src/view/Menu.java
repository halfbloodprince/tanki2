package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;

import common.Constants;
import controller.GameController;
import controller.event.NewGameEvent;

public class Menu extends Panel implements ActionListener {
	/// Controller to push event from menu
	private GameController controller;
	
	public Menu(GameController controller) {
		this.controller = controller;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		final Label title = new Label(Constants.GameName);
		add(title, BorderLayout.NORTH);
		
		final TextField input = new TextField(8);
		input.addActionListener(this);
		add(input, BorderLayout.CENTER);
		
		final Button join = new Button("Join the game");
		join.addActionListener(this);
		add(join, BorderLayout.CENTER);
		
		final Button start = new Button("New game");
		start.addActionListener(this);
		add(start, BorderLayout.CENTER);

		final Button exit = new Button("Exit");
		exit.addActionListener(this);
		add(exit, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "New game":
			controller.AddEvent(new NewGameEvent());
			break;
		case "Exit":
			System.exit(0);
			break;
		}
	}

	public void setController(GameController c) {
		this.controller = c;
	}
}
