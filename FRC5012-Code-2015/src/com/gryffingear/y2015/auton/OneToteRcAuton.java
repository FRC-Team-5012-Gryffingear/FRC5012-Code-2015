package com.gryffingear.y2015.auton;

import com.gryffingear.y2015.auton.commands.DriveToAngleCommand;
import com.gryffingear.y2015.auton.commands.DriveToDistanceCommand;
import com.gryffingear.y2015.auton.commands.ElevatorPositionCommand;
import com.gryffingear.y2015.auton.commands.ElevatorResetCommand;
import com.gryffingear.y2015.auton.commands.ElevatorRunCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class OneToteRcAuton extends CommandGroup {

  public OneToteRcAuton() {
    this.addSequential(new ElevatorResetCommand());
    this.addParallel(new ElevatorRunCommand(15.0));
    this.addSequential(new ElevatorPositionCommand(25.0, 2.0));
    this.addSequential(new DriveToDistanceCommand(0.5, 28.0, 1.5));
    this.addSequential(new ElevatorPositionCommand(0.0, 2.0));
    this.addSequential(new DriveToAngleCommand(0.5, 90.0, 1.0));
    this.addSequential(new DriveToDistanceCommand(0.5, 60.0, 1.5));
  }
}
