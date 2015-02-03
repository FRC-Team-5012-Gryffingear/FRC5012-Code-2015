package com.gryffingear.y2015.systems;

import edu.wpi.first.wpilibj.Solenoid;

public class Claw {

	private Solenoid clawSolenoid = null;

	public Claw(int clawSol) {
		clawSolenoid = new Solenoid(clawSol);
	}

	public void setClaw(boolean state) {
		clawSolenoid.set(state);
	}

}
