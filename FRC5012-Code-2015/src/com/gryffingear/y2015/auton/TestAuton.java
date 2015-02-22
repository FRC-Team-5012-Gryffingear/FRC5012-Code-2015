package com.gryffingear.y2015.auton;

import com.gryffingear.y2015.auton.commands.ElevatorPositionCommand;
import com.gryffingear.y2015.auton.commands.ElevatorResetCommand;
import com.gryffingear.y2015.auton.commands.ElevatorRunCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestAuton extends CommandGroup {

  public TestAuton() {

    this.addSequential(new ElevatorResetCommand());
    this.addParallel(new ElevatorRunCommand(15.0));
    this.addSequential(new ElevatorPositionCommand(10.0, 4.0));
    this.addSequential(new ElevatorPositionCommand(20.0, 4.0));
    this.addSequential(new ElevatorPositionCommand(30.0, 4.0));
    this.addSequential(new ElevatorPositionCommand(35.0, 4.0));

  }
}
