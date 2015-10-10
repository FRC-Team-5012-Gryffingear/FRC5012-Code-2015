package com.gryffingear.y2015.auton.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class IntakeWiggleSequence extends CommandGroup {

  public IntakeWiggleSequence(int number) {
    
    for(int i = 0; i < number; i++) {
    this.addSequential(new IntakeCommand(-1.0, -1.0, false));
    this.addSequential(new WaitCommand(0.25));
    this.addSequential(new IntakeCommand(1.0, 1.0, false));
    this.addSequential(new WaitCommand(0.25));
    }
    this.addSequential(new WaitCommand(0.5));
  }

}
