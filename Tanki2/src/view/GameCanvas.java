package view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;

public class GameCanvas extends Canvas {
	public class RenderTask extends TimerTask{
		private Canvas canvas;

		RenderTask(Canvas canvas) {
			this.canvas = canvas;
		}
		
		public void run() {
			canvas.repaint();
		}
	}
	
	private List<Sprite> sprites;
	
	/** Animations to draw one after another */
	private Queue<Animation> animationsQ;
	private Animation animation;
	private Image bufferImage;
	RenderTask render;
	
	/**
	 * Create new canvas
	 * @param w Width of the canvas
	 * @param h Height of the canvas
	 */
	public GameCanvas(int w, int h) {
		this.setSize(w, h);
		sprites = new ArrayList<Sprite>();
		render = new RenderTask(this);
		animationsQ = new LinkedBlockingQueue<Animation>();
		animation = null;
	}
	
	/**
	 * Flush frame buffer and recreate it
	 */
	public void resetBuffer() {
		if (bufferImage != null) {
			bufferImage.flush();
			bufferImage = null;
		}
		
		bufferImage = createImage(getWidth(), getHeight());
	}
	
	/**
	 * Draw all elements on frame buffer
	 */
	public void fillBuffer() {
		Graphics g = bufferImage.getGraphics();

		bufferImage.flush();
		g.setColor(Color.gray);
		g.fillRect (0, 0, getWidth(), getHeight());
		
		for(Iterator<Sprite> i = sprites.iterator(); i.hasNext(); ) {
		    Sprite item = i.next();
		    g.drawImage(item.getImg(), item.getX(), item.getY(), null);
		}
		
		if (animation != null) {
			animation.paint(g);
		}
	}
	
	/**
	 * Update this canvas, repaint it, but without cleaning itself (buffer will handle it)
	 */
	public void update(Graphics g) {
		if (animation == null || animation.done()) {
			animation = animationsQ.poll();
			if (animation != null) {
				animation.start();
			}
		}
		
		paint(g);
	}
	
	/**
	 * Paint everything to be painted using frame buffer
	 */
	public void paint (Graphics g)
	{
		resetBuffer();
		fillBuffer();
		getGraphics().drawImage(bufferImage, 0, 0, null);
	}
	
	/**
	 * Add another sprite to this canvas, will be painted always
	 * @param s Sprite to be added
	 */
	public void addSprite(Sprite s) {
		sprites.add(s);
	}
	
	/**
	 * Get task which do the rendering of this canvas
	 * @return Rendering task
	 */
	public TimerTask getRenderTask() {
		return render;
	}
	
	/**
	 * Schedule animation to draw
	 * @param a Animation
	 */
	public void addAnimation(Animation a) {
		animationsQ.add(a);
	}
}
