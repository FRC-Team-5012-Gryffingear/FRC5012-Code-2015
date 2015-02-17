package com.gryffingear.y2015.auton.commands;

import com.gryffingear.y2015.systems.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorOpenLoopCommand extends Command {

  private double speed = 0.0;
  private double timeout = 0.0;

  public ElevatorOpenLoopCommand(double speed, double timeout) {

    this.speed = speed;
    this.timeout = timeout;
    this.setTimeout(timeout);
  }

  protected void initialize() {

    Robot.getInstance().elevator.set(speed);
  }

  protected boolean isFinished() {

    return this.isTimedOut();
  }

  protected void execute() {

  }

  protected void end() {

    Robot.getInstance().elevator.set(0.0);
  }

  protected void interrupted() {

    Robot.getInstance().elevator.set(0.0);
  }
}
