package view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

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
	private Image bufferImage;
	RenderTask render;
	
	public GameCanvas(int w, int h) {
		this.setSize(w, h);
		sprites = new ArrayList<Sprite>();
		render = new RenderTask(this);
	}
	
	public void resetBuffer() {
		if (bufferImage != null) {
			bufferImage.flush();
			bufferImage = null;
		}
		
		bufferImage = createImage(getWidth(), getHeight());
	}
	
	public void fillBuffer() {
		Graphics g = bufferImage.getGraphics();

		bufferImage.flush();
		g.setColor(Color.white);
		g.fillRect (0, 0, getWidth(), getHeight());
		
		for(Iterator<Sprite> i = sprites.iterator(); i.hasNext(); ) {
		    Sprite item = i.next();
		    
		    g.drawImage(item.getImg(), item.getX(), item.getY(), null);
		    item.setPosition(item.getX()+1, item.getY()+1);
		}
	}
	
	public void update(Graphics g) {
		paint(g);
	}
	
	public void paint (Graphics g)
	{
		resetBuffer();
		fillBuffer();
		getGraphics().drawImage(bufferImage, 0, 0, null);
	}
	
	public void addSprite(Sprite s) {
		sprites.add(s);
	}
	
	public TimerTask getRenderTask() {
		return render;
	}
}
