package com.gryffingear.y2015.systems;

import com.gryffingear.y2015.config.Ports;

import edu.wpi.first.wpilibj.Compressor;

public class Robot {

  private static Robot instance = null;

  public Drivetrain drive = null;

  public Compressor compressor = null;

  public Elevator elevator = null;

  public Claw claw = null;

  public LedStrips led = null;

  private Robot() {

    drive = new Drivetrain(Ports.Drivetrain.DRIVE_LEFT_A_PORT, Ports.Drivetrain.DRIVE_LEFT_B_PORT,
        Ports.Drivetrain.DRIVE_RIGHT_A_PORT, Ports.Drivetrain.DRIVE_RIGHT_B_PORT,
        Ports.Drivetrain.GYRO_PORT);

    claw = new Claw(Ports.Claw.CLAW_SOLENOID_PORT);

    elevator = new Elevator(Ports.Elevator.ELEVATOR_MOTOR_PORT,
        Ports.Elevator.ELEVATOR_LOWER_LIMIT_SWITCH, Ports.Elevator.ELEVATOR_UPPER_LIMIT_SWITCH,
        Ports.Elevator.ELEVATOR_ENCODER_PORT);// Todo:
    led = new LedStrips(Ports.Led.LEFT_PORT, Ports.Led.RIGHT_PORT);

    Compressor compressor = new Compressor(Ports.Pneumatics.PCM_CAN_ID);
    compressor.setClosedLoopControl(true);


  }

  public static Robot getInstance() {

    if (instance == null) {
      instance = new Robot();
    }
    return instance;
  }
}