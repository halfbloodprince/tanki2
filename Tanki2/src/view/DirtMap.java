package view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import model.Grid;
import model.Shot;

/**
 * Pixel-based implementation of the grid for model.
 * It is based on single image. A pixel is the same as tile in grid. The pixels transparency determine tiles occupancy.
 * It can paint itself in beautiful colors, still being usable by physics.
 * @author Severus
 *
 */
public class DirtMap implements Grid {
	private WritableRaster raster;
	private BufferedImage img;
	int width, height;
	int [] dirtColor;
	
	/**
	 * Create a map
	 * @param w Width
	 * @param h Height
	 */
	public DirtMap(int w, int h) {
		width = w;
		height = h;
		img = new BufferedImage(w , h, BufferedImage.TYPE_INT_ARGB);
		raster = img.getRaster();
		dirtColor = new int[] { 0x00, 0xff, 0x00, 0xff };
	}
	
	/*
	 * (non-Javadoc)
	 * @see model.Grid#setTile(int, int)
	 */
	public void setTile(int x, int y) {
		raster.setPixel(x, y, dirtColor);
	}
	
	/**
	 * Remove tile from this map (set it transparency to 0)
	 * @param x
	 * @param y
	 */
	public void rmTile(int x, int y) {
		int [] emptyness = new int[] { 0x00, 0x00, 0x00, 0x00 };
		raster.setPixel(x, y, emptyness);
	}
	
	/*
	 * (non-Javadoc)
	 * @see model.Grid#occupied(int, int)
	 */
	public synchronized boolean occupied(int x, int y) {
		if (x >= width || y >= height || x <= 0 || y <= 0)
			return true;

		int [] arr = new int[] { 0xff, 0xff, 0xff, 0xff};
		arr = raster.getPixel(x, y, arr);
	    return arr[3] == 0xFF;
	}
	
	/**
	 * Paint the map
	 * @param g Graphics on which map should be painted
	 */
	public void paint (Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see model.Grid#getWidth()
	 */
	public int getWidth() {
		return width;
	}
	
	/*
	 * (non-Javadoc)
	 * @see model.Grid#getHeight()
	 */
	public int getHeight() {
		return height;
	}
	
	/*
	 * (non-Javadoc)
	 * @see model.Grid#getSurfaceHeight(int)
	 */
	public int getSurfaceHeight(int x) {
		for (int i = 0; i < height; ++i) {
			if (!occupied(x, height - i - 1))
				return i;
		}
		
		return height;
	}
	
	/**
	 * Make a hole after explosion
	 * @param shot Shot which made this hole
	 */
	public synchronized void dirtExplode(Shot shot) {		
		Graphics2D g = (Graphics2D)img.getGraphics();
		g.setColor(new Color(0x00, 0x00, 0x00, 0x00));
		g.setComposite(AlphaComposite.Clear);
		g.fillOval(shot.getX() - shot.getRadius(),
					shot.getY() - shot.getRadius(),
					shot.getRadius() * 2, shot.getRadius() * 2);
	}
	
	/**
	 * Paint the ground in nice colors
	 */
	public void prettyPaint() {
		int [] color = new int [4];
		double granicaWieloletniejZmarzliny = 0.75;
		
		for (int i = 0; i < width; ++i) {
			int h = getSurfaceHeight(i);
			for (int j = 0; j < height; ++j) {
				raster.getPixel(i, j, color);
				
				double p = (double)(height-j)/h;
				double q = (double)(height-j)/height;
				double r = 0.5 * p + 0.5 * q;

				color[0] = (int)(q * 0xe0);
				color[1] = (int)(0.5 * (0xff - color[0]));
				
				if (r > granicaWieloletniejZmarzliny) {
					color[2] = color[1] = color[0] = 0xf0;
				} else if (h-(height-j) < 5) {
					color[1] *= 0.7;
					color[0] *= 0.5;
				}

				raster.setPixel(i, j, color);
			}
		}
	}
}
