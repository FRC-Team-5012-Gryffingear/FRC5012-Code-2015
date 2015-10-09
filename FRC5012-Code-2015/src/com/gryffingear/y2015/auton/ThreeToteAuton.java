package com.gryffingear.y2015.auton;

import com.gryffingear.y2015.auton.commands.DriveToDistanceCommand;
import com.gryffingear.y2015.auton.commands.ElevatorPositionCommand;
import com.gryffingear.y2015.auton.commands.InitializeElevatorSequence;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ThreeToteAuton extends CommandGroup {

  public ThreeToteAuton() {
    this.addSequential(new InitializeElevatorSequence());
    this.addSequential(new ElevatorPositionCommand(20.0, 2.0));
    this.addSequential(new DriveToDistanceCommand(0.5, 24.0, 1.5));
    this.addSequential(new ElevatorPositionCommand(0.0, 2.0));

  }

}
