package com.gryffingear.y2015.systems.controllers;

import com.gryffingear.y2015.utilities.GryffinMath;
import com.gryffingear.y2015.utilities.Ma3Encoder;

/**
 * Controller for automatically holding/driving to elevator position. 
 * @author Jeremy
 *
 */
public class ElevatorPositionController {
	
	private double kP = 0.0;
	private Ma3Encoder ref = null;
	private boolean en = true;
	private double pos = 0.0;
	
	
	/**
	 * Contructor. 
	 * @param ref Encoder sensor for reference. 
	 */
	public ElevatorPositionController(Ma3Encoder ref) {
		this.ref = ref;
	}
	
	public void setEnabled(boolean en) {
		this.en = en;
	}
	
	public void setGains(double newP) {
		this.kP = newP;
	}
	
	public void setPosition(double newPos) {
		this.pos = newPos;
	}
	
	public boolean isNearTarget() {
		return GryffinMath.equalsTolerance(pos, ref.get(), 0.5);
	}
	
	public boolean isUnder() {
		return pos > ref.get();
	}
	
	public boolean isAtTarget() {
		return GryffinMath.equalsTolerance(pos, ref.get(), 0.1);
	}
	
	public double get() {
		// Calculations here.
		return kP * (pos - ref.get());
	}
	
}
