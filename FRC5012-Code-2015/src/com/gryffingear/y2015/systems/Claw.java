package com.gryffingear.y2015.systems;

import edu.wpi.first.wpilibj.Solenoid;

public class Claw {

  // Todo: comments
  private Solenoid clawSolenoid = null;

  public Claw(int clawSol) {
    clawSolenoid = new Solenoid(clawSol);
  }

  public void setClaw(boolean state) {
    clawSolenoid.set(state);
  }

}
