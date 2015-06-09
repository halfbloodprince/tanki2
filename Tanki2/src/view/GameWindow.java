package view;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import common.Constants;

public class GameWindow extends Frame implements ActionListener {
	int width;
	int height;

	/**
	 * Create new game window
	 * @param w Width of the window
	 * @param h Height of the window
	 */
	public GameWindow(int w, int h)
	{
		super(Constants.getGameName());
		setVisible(true);
		width = w;
		height = h;

	    setSize(width, height);
	    setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
	}
}
