package com.gryffingear.y2015.auton.commands;

import com.gryffingear.y2015.systems.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorRunCommand extends Command {

  private double speed = 0.0;
  private double timeout = 0.0;

  public ElevatorRunCommand(double timeout) {

    this.setTimeout(timeout);
  }

  protected void initialize() {

  }

  protected boolean isFinished() {

    return this.isTimedOut();
  }

  protected void execute() {

    Robot.getInstance().elevator.run();
  }

  protected void end() {

    Robot.getInstance().elevator.set(0.0);
  }

  protected void interrupted() {

    Robot.getInstance().elevator.set(0.0);
  }
}
