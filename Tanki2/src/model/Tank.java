package model;

import java.io.IOException;

import common.Constants;
import view.Sprite;

public class Tank extends Sprite {
	private double angle, power;

	public Tank() throws IOException{
		super(Constants.DefaultTankImage);
		angle = 0;
		power = 1.0;
	}
	
	/**
	 * @param angle New angle value to be set (rads)
	 */
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	/**
	 * @param power New power value to be set
	 */
	public void setPower(double power) {
		this.power = power;
	}
	
	/**
	 * @return Angle of the gun
	 */
	public double getAngle() {
		return this.angle;
	}
	
	/**
	 * @return Current power of the gun
	 */
	public double getPower() {
		return this.power;
	}
	
	/**
	 * Make a single shot with current weapon
	 */
	public void shoot() {
		
	}
}
