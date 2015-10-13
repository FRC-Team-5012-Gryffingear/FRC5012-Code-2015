package com.gryffingear.y2015.auton.commands;

import com.gryffingear.y2015.systems.Elevator;
import com.gryffingear.y2015.systems.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorPositionCommand extends Command {

  private double speed = 0.0;
  private double timeout = 0.0;
  private double position = 0.0;

  public ElevatorPositionCommand(double position, double timeout) {

    this.position = position;
    this.timeout = timeout;
    this.setTimeout(timeout);
  }

  protected void initialize() {

    Robot.getInstance().elevator.set(speed);
  }

  protected boolean isFinished() {

    return this.isTimedOut() || Robot.getInstance().elevator.epc.isAtTarget();
  }

  protected void execute() {

    Robot.getInstance().elevator.setPosition(position);
    Robot.getInstance().elevator.setState(Elevator.States.CLOSED_LOOP);

    //Robot.getInstance().elevator.run();
  }

  protected void end() {

    Robot.getInstance().elevator.set(0.0);
  }

  protected void interrupted() {

    Robot.getInstance().elevator.set(0.0);
  }
}
