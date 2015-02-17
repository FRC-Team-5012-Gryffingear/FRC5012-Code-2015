package com.gryffingear.y2015.auton.commands;

import com.gryffingear.y2015.systems.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorResetCommand extends Command {

  private double speed = 0.0;
  private double timeout = 0.0;

  public ElevatorResetCommand() {

    this.speed = -.5;
    this.timeout = 0.5;
    this.setTimeout(timeout);
  }

  protected void initialize() {

    Robot.getInstance().elevator.set(speed);
  }

  protected boolean isFinished() {

    return this.isTimedOut() || Robot.getInstance().elevator.getLowerLimitSwitch();

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
