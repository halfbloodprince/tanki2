package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import model.Grid;

public class DirtMap implements Grid {
	private WritableRaster raster;
	private BufferedImage img;
	int width, height;
	
	public DirtMap(int w, int h) {
		width = w;
		height = h;
		img = new BufferedImage(w , h, BufferedImage.TYPE_INT_ARGB);
		raster = img.getRaster();
	}
	
	public void setTile(int x, int y) {
		int [] dirtColor = new int[] { 0x01, 0xff, 0x01, 0xff };
		raster.setPixel(x, y, dirtColor);
	}
	
	/**
	 * Accept all changes made so far
	 */
	public void updateImg() {
		img.setData(raster);
	}
	
	public boolean occupied(int x, int y) {
		if (x > width || y > height || x < 0 || y < 0)
			return true;

		int [] arr = new int[] { 0xff, 0xff, 0xff, 0xff};
		arr = raster.getPixel(x, y, arr);
	    return arr[3] == 0xFF;
	}
	
	public void paint (Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	
	public int getSurfaceHeight(int x) {
		for (int i = 0; i < height; ++i) {
			if (!occupied(x, height - i - 1))
				return i;
		}
		
		return height;
	}
}
