package com.gryffingear.y2015.auton.commands;

import com.gryffingear.y2015.systems.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeCommand extends Command {

  private double speedL = 0.0, speedR;
  private double turn = 0.0;
  boolean state = false;

  public IntakeCommand(double speedL, double speedR, boolean state) {

    this.state = state;
    this.speedL = -speedL;
    this.speedR = speedR;

  }

  protected void initialize() {

    Robot.getInstance().intake.setMotors(speedL, speedR);
    Robot.getInstance().intake.setActuator(state);
  }

  protected boolean isFinished() {

    return true;
  }

  protected void execute() {

  }

  protected void end() {

  }

  protected void interrupted() {

  }
}
