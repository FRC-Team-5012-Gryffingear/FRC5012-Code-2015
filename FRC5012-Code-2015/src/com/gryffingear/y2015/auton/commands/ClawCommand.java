package com.gryffingear.y2015.auton.commands;

import com.gryffingear.y2015.systems.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClawCommand extends Command {

  private boolean state = false;
  private double timeout = 0.0;

  public ClawCommand(boolean state) {

    this.state = state;

    this.setTimeout(timeout);
  }

  protected void initialize() {

    Robot.getInstance().claw.setClaw(state);
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
