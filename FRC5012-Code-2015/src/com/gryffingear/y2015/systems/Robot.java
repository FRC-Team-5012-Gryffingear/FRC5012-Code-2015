package com.gryffingear.y2015.systems;

import com.gryffingear.y2015.config.Ports;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot {

  private static Robot instance = null;

  public Drivetrain drive = null;

  public Compressor compressor = null;

  public Elevator elevator = null;

  public Claw claw = null;

  public LedStrips led = null;

  public Wings wings = null;

  public Intake intake = null;
  PowerDistributionPanel pdp = new PowerDistributionPanel();
  private Robot() {

    drive = new Drivetrain(Ports.Drivetrain.DRIVE_LEFT_A_PORT, Ports.Drivetrain.DRIVE_LEFT_B_PORT,
        Ports.Drivetrain.DRIVE_RIGHT_A_PORT, Ports.Drivetrain.DRIVE_RIGHT_B_PORT,
        Ports.Drivetrain.GYRO_PORT, Ports.Drivetrain.ENCODER_A_PORT,
        Ports.Drivetrain.ENCODER_B_PORT);

    claw = new Claw(Ports.Claw.CLAW_SOLENOID_PORT);

    elevator = new Elevator(Ports.Elevator.ELEVATOR_MOTOR_PORT,
        Ports.Elevator.ELEVATOR_LOWER_LIMIT_SWITCH, Ports.Elevator.ELEVATOR_UPPER_LIMIT_SWITCH,
        Ports.Elevator.ELEVATOR_ENCODER_PORT);// Todo:

    led = new LedStrips(Ports.Led.LEFT_PORT, Ports.Led.RIGHT_PORT);

    Wings wings = new Wings(Ports.Wings.WING_SOLENOID_PORT);

    Intake intake = new Intake(Ports.Intake.INTAKE_SOLENOID_PORT, Ports.Intake.INTAKE_LEFT_PORT,
        Ports.Intake.INTAKE_RIGHT_PORT);

    Compressor compressor = new Compressor(Ports.Pneumatics.PCM_CAN_ID);
    compressor.setClosedLoopControl(true);


  }

  public static Robot getInstance() {

    if (instance == null) {
      instance = new Robot();
    }
    return instance;
  }

  public double getCurrent() {

    return 0.0;
    // Todo: make this return the total robot current
  }

  public void updateDashboard() {
    SmartDashboard.putNumber("yaw", this.drive.getYaw());
    SmartDashboard.putNumber("drive_current", this.drive.getCurrent());
    SmartDashboard.putNumber("lift_current", this.elevator.getCurrent());
    // SmartDashboard.putNumber("compressor_current",
    // this.compressor.getCompressorCurrent());
    SmartDashboard.putNumber("total_current", this.drive.getCurrent() + this.elevator.getCurrent());
    SmartDashboard.putBoolean("lift_upper", this.elevator.getUpperLimitSwitch());
    SmartDashboard.putBoolean("lift_lower", this.elevator.getLowerLimitSwitch());
    SmartDashboard.putNumber("lift_pos", this.elevator.getEncoder());
    SmartDashboard.putNumber("drive_encoder", this.drive.getDistance());
  }
}