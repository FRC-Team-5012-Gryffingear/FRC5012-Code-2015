package com.gryffingear.y2015.systems;

import com.gryffingear.y2015.config.Ports;

public class Robot {

	private static Robot instance = null;

	public Drivetrain drive = null;

	private Robot() {
		drive = new Drivetrain(Ports.DRIVE_LEFT_A_PORT,
							   Ports.DRIVE_LEFT_B_PORT, 
							   Ports.DRIVE_RIGHT_A_PORT,
							   Ports.DRIVE_RIGHT_B_PORT);
	}

	public static Robot getInstance() {
		if (instance == null) {
			instance = new Robot();
		}
		return instance;
	}
}