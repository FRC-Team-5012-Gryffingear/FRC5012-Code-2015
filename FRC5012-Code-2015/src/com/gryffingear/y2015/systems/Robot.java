package com.gryffingear.y2015.systems;

import com.gryffingear.y2015.config.Ports;

import edu.wpi.first.wpilibj.Compressor;

public class Robot {

	private static Robot instance = null;

	public Drivetrain drive = null;

	public Compressor compressor = null;
	
	public Elevator elevator = null;
	
	public Claw claw = null;

	private Robot() {
		drive = new Drivetrain(Ports.DRIVE_LEFT_A_PORT,
							   Ports.DRIVE_LEFT_B_PORT, 
				               Ports.DRIVE_RIGHT_A_PORT,
				               Ports.DRIVE_RIGHT_B_PORT);
		
		claw = new Claw(Ports.CLAW_SOLENOID_PORT);
		
		elevator= new Elevator(Ports.ELEVATOR_MOTOR_PORT,
				               Ports.ELEVATOR_LOWER_LIMIT_SWITCH,
				               Ports.ELEVATOR_UPPER_LIMIT_SWITCH);   
		
		Compressor compressor = new Compressor(0);
		compressor.setClosedLoopControl(true);
		//compressor.setClosedLoopControl(false);
		
		boolean enabled = compressor.enabled();
		boolean pressureSwitch = compressor.getPressureSwitchValue();
		float current = compressor.getCompressorCurrent();
		 
	}

	public static Robot getInstance() {
		if (instance == null) {
			instance = new Robot();
		}
		return instance;
	}
}