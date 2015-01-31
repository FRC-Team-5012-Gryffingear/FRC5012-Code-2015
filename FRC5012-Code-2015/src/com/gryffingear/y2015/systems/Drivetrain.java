package com.gryffingear.y2015.systems;

import edu.wpi.first.wpilibj.CANTalon;

public class Drivetrain {
	private CANTalon lefta = null;
	private CANTalon leftb = null;
	private CANTalon righta = null;
	private CANTalon rightb = null;
	

	public Drivetrain(int la, int lb, int ra, int rb) {
		
		lefta = configureTalon(new CANTalon(la));
		leftb = configureTalon(new CANTalon(lb));
		righta = configureTalon(new CANTalon(ra));
		rightb = configureTalon(new CANTalon(rb));
		
	}
	
	private CANTalon configureTalon(CANTalon in) {

		in.clearStickyFaults();
		in.changeControlMode(CANTalon.ControlMode.PercentVbus);
		in.setVoltageRampRate(32.0);
		in.enableControl();
		return in;
	}

	public void tankDrive(double leftv, double rightv) {
		lefta.set(-leftv);
		leftb.set(-leftv);

		righta.set(-rightv);
		rightb.set(rightv);
	}
}