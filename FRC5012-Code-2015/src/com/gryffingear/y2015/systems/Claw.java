package com.gryffingear.y2015.systems;

import com.gryffingear.y2015.config.Ports;

import edu.wpi.first.wpilibj.Solenoid;

public class Claw {

  // Todo: comments
  private Solenoid clawSolenoid = null;

  public Claw(int clawSol) {

    clawSolenoid = new Solenoid(Ports.Pneumatics.PCM_CAN_ID, clawSol);
  }

  boolean prevIn = false;
  boolean out = false;

  public void toggleClaw(boolean in) {

    if (in && (prevIn != in)) {
      out = !out;
    }

    setClaw(out);

    prevIn = in;
  }

  public void setClaw(boolean state) {

    clawSolenoid.set(state);
  }

}
