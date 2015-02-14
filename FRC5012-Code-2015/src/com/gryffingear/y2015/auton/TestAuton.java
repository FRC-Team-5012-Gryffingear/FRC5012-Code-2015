package com.gryffingear.y2015.auton;

import com.gryffingear.y2015.auton.commands.ArcadeDriveCommand;
import com.gryffingear.y2015.auton.commands.WaitCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestAuton extends CommandGroup {

  public TestAuton() {

    this.addSequential(new ArcadeDriveCommand(1.0, 0.0, 1.0));
    this.addSequential(new WaitCommand(1.0));
    this.addSequential(new ArcadeDriveCommand(-1.0, 0.0, 1.0));
  }
}
