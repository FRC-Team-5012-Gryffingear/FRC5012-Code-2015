
package com.gryffingear.y2015.auton.commands;

import com.gryffingear.y2015.systems.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveToDistanceCommand extends Command {

  private double speed = 0.0;
  private double dist = 0.0;
  private double timeout = 0.0;

  public DriveToDistanceCommand(double speed, double dist, double timeout) {

    this.speed = speed;
    this.dist = dist;
    this.timeout = timeout;
    this.setTimeout(timeout);
  }

  protected void initialize() {

    Robot.getInstance().drive.resetEncoder();
  }

  protected boolean isFinished() {

    return this.isTimedOut();
  }

  protected void execute() {

    double tp = 0.025;
    double t_error = Robot.getInstance().drive.getYaw();
    double p = 0.1;
    double error = Robot.getInstance().drive.getDistance() - this.dist;
    Robot.getInstance().drive.tankDrive(((p * error) + (tp * t_error)) * speed,
        ((p * error) + (tp * t_error)) * speed);
  }

  protected void end() {

    Robot.getInstance().drive.tankDrive(0.0, 0.0);
  }

  protected void interrupted() {

    Robot.getInstance().drive.tankDrive(0.0, 0.0);
  }
}
