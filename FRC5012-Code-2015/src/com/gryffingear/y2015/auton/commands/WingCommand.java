package com.gryffingear.y2015.auton.commands;

import com.gryffingear.y2015.systems.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class WingCommand extends Command {

  private boolean state = false;
  private double timeout = 0.0;

  public WingCommand(boolean state) {
    this.state = state;
  }

  protected void initialize() {

    Robot.getInstance().wings.setWings(state);
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
