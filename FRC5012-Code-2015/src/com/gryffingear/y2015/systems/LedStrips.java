package com.gryffingear.y2015.systems;

import com.gryffingear.y2015.config.Ports;

import edu.wpi.first.wpilibj.Solenoid;

public class LedStrips {

  Solenoid left = null;
  Solenoid right = null;

  public LedStrips(int leftChannel, int rightChannel) {

    left = new Solenoid(Ports.Pneumatics.PCM_CAN_ID, leftChannel);
    right = new Solenoid(Ports.Pneumatics.PCM_CAN_ID, rightChannel);
  }

  public void setLeft(boolean state) {

    left.set(state);
  }

  public void setRight(boolean state) {

    right.set(state);
  }
}
