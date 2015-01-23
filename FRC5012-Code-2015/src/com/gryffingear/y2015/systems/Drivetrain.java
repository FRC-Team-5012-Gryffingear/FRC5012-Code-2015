package com.gryffingear.y2015.systems;

import edu.wpi.first.wpilibj.Victor;

public class Drivetrain {
	private Victor lefta = null;
	private Victor leftb = null;
	private Victor righta = null;
	private Victor rightb = null;

	public Drivetrain(int la, int lb, int ra, int rb) {
		lefta = new Victor(la);
		leftb = new Victor(lb);
		righta = new Victor(ra);
		rightb = new Victor(rb);
	}

	public void tankDrive(double leftv, double rightv) {
		lefta.set(-leftv);
		leftb.set(-leftv);

		righta.set(rightv);
		rightb.set(rightv);
	}
	
	
	/**
	 * method to loosely couple teleop drive logic from drive control logic. 
	 * @param leftIn
	 * @param rightIn
	 */
	public void teleopDrive(double leftIn, double rightIn) {
		
		// Todo: implement negative-negative inertia or similar ramping here. -JPG
		tankDrive(leftIn, rightIn);
	}
}