package com.gryffingear.y2015.auton;

import com.gryffingear.y2015.auton.commands.TrapezoidalDriveCommand;
import com.gryffingear.y2015.auton.commands.WingCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Autozone extends CommandGroup {

  public Autozone() {

    // this.addSequential(new DriveToDistanceCommand(.5, 96, 5));
    this.addSequential(new WingCommand(false));
    this.addSequential(new TrapezoidalDriveCommand(1.0, 0.0, 1.0));
  }
}
