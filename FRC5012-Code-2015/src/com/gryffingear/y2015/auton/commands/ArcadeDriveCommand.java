package com.gryffingear.y2015.auton.commands;

import com.gryffingear.y2015.systems.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ArcadeDriveCommand extends Command {

  private double speed = 0.0;
  private double turn = 0.0;
  private double timeout = 0.0;

  public ArcadeDriveCommand(double speed, double turn, double timeout) {

    this.speed = -speed;
    this.turn = -turn;
    this.timeout = timeout;
    this.setTimeout(timeout);
  }

  protected void initialize() {

    Robot.getInstance().drive.tankDrive(speed + turn, speed - turn);
  }

  protected boolean isFinished() {

    return this.isTimedOut();
  }

  protected void execute() {

  }

  protected void end() {

    Robot.getInstance().drive.tankDrive(0.0, 0.0);
  }

  protected void interrupted() {

    Robot.getInstance().drive.tankDrive(0.0, 0.0);
  }
}
