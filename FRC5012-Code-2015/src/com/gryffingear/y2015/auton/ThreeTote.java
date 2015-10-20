package com.gryffingear.y2015.auton;

import com.gryffingear.y2015.auton.commands.ArcadeDriveCommand;
import com.gryffingear.y2015.auton.commands.ElevatorPositionCommand;
import com.gryffingear.y2015.auton.commands.ElevatorResetCommand;
import com.gryffingear.y2015.auton.commands.ElevatorRunCommand;
import com.gryffingear.y2015.auton.commands.IntakeCommand;
import com.gryffingear.y2015.auton.commands.IntakeWiggleSequence;
import com.gryffingear.y2015.auton.commands.WaitCommand;
import com.gryffingear.y2015.config.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ThreeTote extends CommandGroup {

  public ThreeTote() {


    this.addSequential(new IntakeCommand(0, 0, true));
    this.addParallel(new ElevatorRunCommand(15.0));
    this.addSequential(new ElevatorPositionCommand(Constants.Elevator.Setpoints.OVER_PICKUP +3, .25));
    this.addSequential(new ArcadeDriveCommand(0.0, 0.4, 0.5));
    this.addSequential(new IntakeCommand(0, 0, false));
    
    this.addSequential(new ArcadeDriveCommand(0.5, 0.0, .75));
    this.addSequential(new ArcadeDriveCommand(0.0, -0.5, 0.5));

    this.addSequential(new IntakeCommand(-1, -1, true));

    this.addSequential(new WaitCommand(.125));

    this.addSequential(new ArcadeDriveCommand(0.75, 0.0, 1));
    this.addSequential(new IntakeCommand(-1, -1, false));

    this.addSequential(new ArcadeDriveCommand(-0.3, 0.0, .1));
    this.addSequential(new IntakeWiggleSequence(2));

    this.addSequential(new IntakeCommand(0.0, 0.0, true));
    this.addSequential(new ElevatorPositionCommand(0, .5));
    this.addSequential(new ElevatorPositionCommand(Constants.Elevator.Setpoints.OVER_PICKUP +3, .25));
    
    this.addSequential(new WaitCommand(.5));

    this.addSequential(new ArcadeDriveCommand(-0.4, 0.0, .75));
    this.addSequential(new ArcadeDriveCommand(0.0, 0.4, 0.37));

    this.addSequential(new ArcadeDriveCommand(0.5, 0.0, 1));
    this.addSequential(new ArcadeDriveCommand(0.0, -0.6, 0.5));
    this.addSequential(new IntakeCommand(-1, -1, true));

    this.addSequential(new WaitCommand(.125));

    this.addSequential(new ArcadeDriveCommand(0.75, 0.0, 1));
    this.addSequential(new IntakeCommand(-1, -1, false));

    this.addSequential(new ArcadeDriveCommand(-0.3, 0.0, .1));

  }
}
