package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.IOException;

import model.Tank;
import common.Constants;

public class AimArrow extends Sprite{
	Tank tank;
	
	public AimArrow() throws IOException{
		super(Constants.AimArrowImage);
	}
	
	public void setTank(Tank tank) {
		this.tank = tank;
	}
	
	@Override
	public int getX() {
		return tank.getX();
	}
	
	@Override
	public int getY() {
		return tank.getY();
	}
	
	@Override
	public void paint(Graphics g) {
		if(enabled) {
			AffineTransform trans = new AffineTransform();
			Graphics2D g2d = (Graphics2D)g;
			trans.setToTranslation(getX(), getY());
			trans.rotate(tank.getAngle());
			trans.translate(0, -img.getHeight()/2);
			//trans.scale(tank.getPower(), 1.0);
			g2d.setTransform(trans);
			int w = (int) (tank.getPower() * img.getWidth());
			g2d.drawImage(img, 0, 0, w, img.getHeight(),
							img.getWidth() - w, 0, img.getWidth(), img.getHeight(), null);
		}
	}
}
