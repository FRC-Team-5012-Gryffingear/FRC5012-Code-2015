package com.gryffingear.y2015.auton;

import com.gryffingear.y2015.auton.commands.TrapezoidalDriveCommand;
import com.gryffingear.y2015.auton.commands.WaitCommand;
import com.gryffingear.y2015.auton.commands.WingCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class WingAuton extends CommandGroup {

  public WingAuton() {

    // this.addSequential(new DriveStraightCommand(-.7, 5));


    this.addSequential(new WingCommand(true));
    this.addSequential(new WaitCommand(3.0));
    this.addSequential(new TrapezoidalDriveCommand(1.0, 0.0, 1.0));
    this.addSequential(new WingCommand(false));
  }
}
