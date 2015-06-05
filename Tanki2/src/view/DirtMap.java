package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

import model.Grid;
import model.Shot;

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
		int [] dirtColor = new int[] { 0x10, 0xe0, 0x01, 0xff };
		raster.setPixel(x, y, dirtColor);
	}
	
	public void rmTile(int x, int y) {
		int [] emptyness = new int[] { 0x00, 0x00, 0x00, 0x00 };
		raster.setPixel(x, y, emptyness);
	}
	
	/**
	 * Accept all changes made so far
	 */
	public void updateImg() {
		img.setData(raster);
	}
	
	public boolean occupied(int x, int y) {
		if (x >= width || y >= height || x <= 0 || y <= 0)
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
	
	/**
	 * Make a hole after explosion
	 * @param shot Shot which made this hole
	 * @return List of rows to be dynamically shifted
	 */
	List<Integer> dirtExplode(Shot shot) {
		List<Integer> ret = new ArrayList<Integer>();
		
		Queue<Point> Q = new LinkedBlockingQueue<Point>();
		Point start = new Point(shot.getX(), shot.getY());
		System.out.println("blowing : " + shot.getRadius() + ", " + shot.getX() + ", " + shot.getY());
		Q.add(start);
		while (!Q.isEmpty()) {
			int x = Q.peek().x;
			int y = Q.peek().y;
			Q.poll();
			if (Math.sqrt((x-start.x) * (x-start.x) + (y-start.y) * (y-start.y)) >= shot.getRadius())
				continue;

			rmTile(x, y);
			
			if (occupied(x + 1, y))
				Q.offer(new Point(x + 1, y));
			if (occupied(x - 1, y))
				Q.offer(new Point(x - 1, y));
			if (occupied(x, y + 1))
				Q.offer(new Point(x, y + 1));
			if (occupied(x, y - 1))
				Q.offer(new Point(x, y - 1));
		}
		
		return ret;
	}
}
