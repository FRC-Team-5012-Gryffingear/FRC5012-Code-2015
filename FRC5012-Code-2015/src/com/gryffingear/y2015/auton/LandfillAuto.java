package com.gryffingear.y2015.auton;

import com.gryffingear.y2015.auton.commands.ArcadeDriveCommand;
import com.gryffingear.y2015.auton.commands.ElevatorPositionCommand;
import com.gryffingear.y2015.auton.commands.ElevatorResetCommand;
import com.gryffingear.y2015.auton.commands.ElevatorRunCommand;
import com.gryffingear.y2015.auton.commands.InitializeElevatorSequence;
import com.gryffingear.y2015.auton.commands.IntakeCommand;
import com.gryffingear.y2015.auton.commands.IntakeWiggleSequence;
import com.gryffingear.y2015.auton.commands.TrapezoidalDriveCommand;
import com.gryffingear.y2015.auton.commands.WaitCommand;
import com.gryffingear.y2015.config.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LandfillAuto extends CommandGroup {

  public LandfillAuto() {


    // Initialize elevator, bring to height, run intakes
    //this.addSequential(new InitializeElevatorSequence());
    
    this.addSequential(new IntakeCommand(-1.0, -1.0, false));

    this.addParallel(new ElevatorRunCommand(15.0));
    this.addSequential(new ElevatorPositionCommand(Constants.Elevator.Setpoints.OVER_PICKUP, 1.5));
    
    this.addSequential(new ArcadeDriveCommand(0.45, 0, 1.2));
    this.addSequential(new WaitCommand(0.5));
    this.addSequential(new ArcadeDriveCommand(-0.5, 0, 1.0));
    //this.addSequential(new TrapezoidalDriveCommand(-0.50, 0.0, 1.0));
    
    // Wiggle intake motors to settle tote
    this.addSequential(new IntakeWiggleSequence(2));
    
    this.addSequential(new IntakeCommand(0.0, 0.0, true));
    this.addSequential(new ElevatorPositionCommand(-8, 2));
    
    
    this.addSequential(new ElevatorPositionCommand(Constants.Elevator.Setpoints.OVER_PICKUP +10, 1.5));

    this.addSequential(new IntakeCommand(-1.0, -1.0, false));
    this.addSequential(new ArcadeDriveCommand(0.0, -0.25,.5));
    
    this.addSequential(new ArcadeDriveCommand(0.45, 0, 1.75));
    this.addSequential(new WaitCommand(0.5));
    this.addSequential(new ArcadeDriveCommand(-0.5, 0, 1.0));
    //this.addSequential(new TrapezoidalDriveCommand(-0.50, 0.0, 1.0));
    
    // Wiggle intake motors to settle tote
    this.addSequential(new IntakeWiggleSequence(2));
    
    this.addSequential(new IntakeCommand(0.0, 0.0, true));
    
    //this.addSequential(new ElevatorPositionCommand(Constants.Elevator.Setpoints.OVER_PICKUP, 2.0));
    
  }
}
