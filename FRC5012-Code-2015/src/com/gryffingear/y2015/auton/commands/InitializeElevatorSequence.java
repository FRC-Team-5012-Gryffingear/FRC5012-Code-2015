package com.gryffingear.y2015.auton.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class InitializeElevatorSequence extends CommandGroup {

  public InitializeElevatorSequence() {
    //this.addSequential(new ElevatorResetCommand());
    this.addParallel(new ElevatorRunCommand(15.0));
  }

}
