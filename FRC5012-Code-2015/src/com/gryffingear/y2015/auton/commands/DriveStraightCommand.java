package com.gryffingear.y2015.auton.commands;

import com.gryffingear.y2015.systems.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveStraightCommand extends Command {

  private double speed = 0.0;
  private double angle = 0.0;
  private double timeout = 0.0;

  public DriveStraightCommand(double speed, double timeout) {

    this.speed = -speed;
    this.angle = 0;
    this.timeout = timeout;
    this.setTimeout(timeout);
  }

  protected void initialize() {

    Robot.getInstance().drive.resetGyro();
  }

  protected boolean isFinished() {

    return this.isTimedOut();
  }

  protected void execute() {

    double p = 0.025;
    double error = Robot.getInstance().drive.getYaw() - this.angle;
    double outL = speed + (p * error);
    double outR = speed - (p * error);

    if (Math.abs(outL) >= speed) {
      outL = speed * Math.signum(outL);
    }

    if (Math.abs(outR) >= speed) {
      outR = speed * Math.signum(outR);
    }

    Robot.getInstance().drive.tankDrive(outL, outR);
  }

  protected void end() {

    Robot.getInstance().drive.tankDrive(0.0, 0.0);
  }

  protected void interrupted() {

    Robot.getInstance().drive.tankDrive(0.0, 0.0);
  }
}
