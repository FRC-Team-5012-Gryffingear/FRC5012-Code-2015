package com.gryffingear.y2015.auton;

import com.gryffingear.y2015.auton.commands.DriveToAngleCommand;
import com.gryffingear.y2015.auton.commands.DriveToDistanceCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ThreeTotePushAuton extends CommandGroup {

  public ThreeTotePushAuton() {

    this.addSequential(new DriveToDistanceCommand(0.75, -70, 3));
    this.addSequential(new DriveToDistanceCommand(1.0, 70.0, 3));
    this.addSequential(new DriveToAngleCommand(0.75, 90.0, 0.5));
  }
}
