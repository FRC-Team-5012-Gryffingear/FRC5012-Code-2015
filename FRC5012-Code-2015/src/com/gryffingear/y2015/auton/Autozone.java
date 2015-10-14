package com.gryffingear.y2015.auton;

import com.gryffingear.y2015.auton.commands.ArcadeDriveCommand;
import com.gryffingear.y2015.auton.commands.TrapezoidalDriveCommand;
import com.gryffingear.y2015.auton.commands.WaitCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Autozone extends CommandGroup {

  public Autozone() {

    // this.addSequential(new DriveToDistanceCommand(.5, 96, 5));
    //this.addSequential(new WingCommand(false));
    this.addSequential(new WaitCommand(2.0));
    this.addSequential(new TrapezoidalDriveCommand(0.6, 0.0, 1.0));

    //this.addSequential(new ArcadeDriveCommand(0.5, 0, 2.0));
    this.addSequential(new ArcadeDriveCommand(0.0, -0.35, 1.0));
  }
}
