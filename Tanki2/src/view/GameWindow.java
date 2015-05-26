package view;

import java.awt.Frame;
import java.awt.Graphics;

import common.Constants;

public class GameWindow extends Frame {
	int width;
	int height;

	public GameWindow(int w, int h)
	{
		super(Constants.getGameName());
	
		width = w;
		height = h;

	    setSize(width, height);
	    setResizable(false);
	}
}
