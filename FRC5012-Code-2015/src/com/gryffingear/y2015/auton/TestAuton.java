package com.gryffingear.y2015.auton;

import com.gryffingear.y2015.auton.commands.ArcadeDriveCommand;
import com.gryffingear.y2015.auton.commands.IntakeCommand;
import com.gryffingear.y2015.auton.commands.TrapezoidalDriveCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestAuton extends CommandGroup {

  public TestAuton() {

    this.addSequential(new IntakeCommand(0.0, 0.0, true));
    this.addSequential(new ArcadeDriveCommand(0, 0.5, 0.25));

    this.addSequential(new IntakeCommand(1.0, -1.0, false));
    this.addSequential(new TrapezoidalDriveCommand(0.5, 0.0, 2.5));

  }
}
