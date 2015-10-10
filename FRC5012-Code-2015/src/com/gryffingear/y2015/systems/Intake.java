package com.gryffingear.y2015.systems;

import com.gryffingear.y2015.config.Ports;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;

public class Intake {

  // Todo: comments
  private Solenoid intakeSolenoid = null;
  private CANTalon leftMotor = null;
  private CANTalon rightMotor = null;

  public Intake(int intakeSol, int leftId, int rightId) {

    intakeSolenoid = new Solenoid(Ports.Pneumatics.PCM_CAN_ID, intakeSol);
    leftMotor = configureTalon(new CANTalon(leftId));
    rightMotor = configureTalon(new CANTalon(rightId));
  }

  private CANTalon configureTalon(CANTalon in) {

    in.clearStickyFaults();
    in.changeControlMode(CANTalon.ControlMode.PercentVbus);
    in.setVoltageRampRate(144);
    in.enableBrakeMode(true);
    in.enableControl();
    return in;
  }

  public void setActuator(boolean state) {

    intakeSolenoid.set(state);
  }


  public void setMotors(double left, double right) {

    leftMotor.set(left);
    rightMotor.set(right);
  }

}
