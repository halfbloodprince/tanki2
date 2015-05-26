package view;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
	private BufferedImage img;
	private int x, y;

	public Sprite(String filename) throws IOException {
		img = ImageIO.read(new File(filename));
		img.setAccelerationPriority(1.0f);
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Image getImg() {
		return img;
	}
}
