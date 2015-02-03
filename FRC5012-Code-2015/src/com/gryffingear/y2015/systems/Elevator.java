package com.gryffingear.y2015.systems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;

public class Elevator {

	private CANTalon elevatorMotor = null;
	private DigitalInput lowerlimit_switch;
	private DigitalInput upperlimit_switch;

	private CANTalon configureTalon(CANTalon in) {

		in.clearStickyFaults();
		in.changeControlMode(CANTalon.ControlMode.PercentVbus);
		in.enableControl();
		return in;
	}

	public Elevator(int elevator_port, int lowerlimit_port, int upperlimit_port) {

		upperlimit_switch = new DigitalInput(upperlimit_port);
		lowerlimit_switch = new DigitalInput(lowerlimit_port);
		elevatorMotor = configureTalon(new CANTalon(elevator_port));
	}

	public void setElevator(double value) {
		if (value < 0.0 && !lowerlimit_switch.get()) {
			value = 0.0;
		} else if (value < 1.0 && !upperlimit_switch.get()) {
			value = 0.0;
			elevatorMotor.set(value);
		}

	}
}
