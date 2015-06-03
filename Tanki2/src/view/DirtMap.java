package view;

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
		img = new BufferedImage(w , h, BufferedImage.TYPE_INT_RGB);
	}
	
	public boolean occupied(int x, int y) {
		if (x > width || y > height || x < 0 || y < 0)
			return true;

		int [] arr = new int[] { 0xff, 0xff, 0xff, 0xff};
		raster = img.getRaster();
		arr = raster.getPixel(x, y, arr);
	    return arr[3] == 0x00;
	}
	
	public void paint (Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
}
