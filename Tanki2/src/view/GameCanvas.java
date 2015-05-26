package view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
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
	RenderTask render;
	
	public GameCanvas(int w, int h) {
		this.setSize(w, h);
		sprites = new ArrayList<Sprite>();
		render = new RenderTask(this);
	}
	
	public void paint (Graphics g)
	{
		g.setColor(Color.white);
		g.fillRect (0, 0, getWidth(), getHeight());
		
		for(Iterator<Sprite> i = sprites.iterator(); i.hasNext(); ) {
		    Sprite item = i.next();
		    
		    getGraphics().drawImage(item.getImg(), item.getX(), item.getY(), null);
		    System.out.println("drawing!");
		}
	}
	
	public void addSprite(Sprite s) {
		sprites.add(s);
	}
	
	public TimerTask getRenderTask() {
		return render;
	}
}
