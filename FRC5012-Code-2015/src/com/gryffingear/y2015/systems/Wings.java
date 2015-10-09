package com.gryffingear.y2015.systems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;

public class Wings {

  // Todo: comments
  private Solenoid wingSolenoid = null;
  public Relay winch = new Relay(0);

  public Wings(int wingSol) {

    // wingSolenoid = new Solenoid(Ports.Pneumatics.PCM_CAN_ID, wingSol);
  }

  public void setWings(boolean state) {

    // wingSolenoid.set(state);
  }


}
