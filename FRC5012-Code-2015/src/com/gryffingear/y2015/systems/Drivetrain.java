package com.gryffingear.y2015.systems;

import com.gryffingear.y2015.config.Constants;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Gyro;

public class Drivetrain {

  private CANTalon lefta = null;
  private CANTalon leftb = null;
  private CANTalon righta = null;
  private CANTalon rightb = null;

  private Gyro yaw = null;

  // Todo: comments.

  public Drivetrain(int la, int lb, int ra, int rb, int gyro) {

    lefta = configureTalon(new CANTalon(la));
    leftb = configureTalon(new CANTalon(lb));
    righta = configureTalon(new CANTalon(ra));
    rightb = configureTalon(new CANTalon(rb));

    yaw = new Gyro(gyro);
    yaw.initGyro();
    // yaw.reset();

  }

  private CANTalon configureTalon(CANTalon in) {

    in.clearStickyFaults();
    in.changeControlMode(CANTalon.ControlMode.PercentVbus);
    in.setVoltageRampRate(Constants.Drivetrain.VRAMP_RATE); // Todo: move this
                                                            // into constants.
    in.enableControl();
    return in;
  }

  public double getYaw() {

    return yaw.getAngle();
  }

  public void tankDrive(double leftv, double rightv) {

    lefta.set(-leftv);
    leftb.set(-leftv);

    righta.set(-rightv);
    rightb.set(rightv);
  }
}