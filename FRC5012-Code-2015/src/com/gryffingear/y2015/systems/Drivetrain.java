package com.gryffingear.y2015.systems;

import com.gryffingear.y2015.config.Constants;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;

public class Drivetrain {

  private CANTalon lefta = null;
  private CANTalon leftb = null;
  private CANTalon righta = null;
  private CANTalon rightb = null;

  private Gyro yaw = null;
  private Encoder enc = null;

  // Todo: comments.

  public Drivetrain(int la, int lb, int ra, int rb, int gyro, int encA, int encB) {

    lefta = configureTalon(new CANTalon(la));
    leftb = configureTalon(new CANTalon(lb));
    righta = configureTalon(new CANTalon(ra));
    rightb = configureTalon(new CANTalon(rb));

    yaw = new Gyro(gyro);
    yaw.initGyro();
    // yaw.reset();

    enc = new Encoder(encA, encB);
    enc.setDistancePerPulse(0.050264);
  }

  private CANTalon configureTalon(CANTalon in) {

    in.clearStickyFaults();
    in.changeControlMode(CANTalon.ControlMode.PercentVbus);
    in.setVoltageRampRate(Constants.Drivetrain.VRAMP_RATE);
    in.enableControl();
    System.out.println("[CANTalon]" + in.getDescription() + " Initialized at device ID: "
        + in.getDeviceID());
    return in;
  }

  public double getYaw() {

    return yaw.getAngle();
  }

  public void resetGyro() {
    yaw.reset();
  }

  public double getDistance() {

    return enc.getDistance();
  }

  public void resetEncoder() {

    enc.reset();
  }

  public void tankDrive(double leftv, double rightv) {
    lefta.set(-leftv);
    leftb.set(-leftv);

    righta.set(-rightv);
    rightb.set(rightv);
  }

  public void tankDrive(double[] input) {

    tankDrive(input[0], input[1]);
  }

  public double getCurrent() {

    return lefta.getOutputCurrent() + leftb.getOutputCurrent() + righta.getOutputCurrent()
        + rightb.getOutputCurrent();
  }
}