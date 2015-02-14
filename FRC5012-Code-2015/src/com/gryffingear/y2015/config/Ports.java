package com.gryffingear.y2015.config;

public class Ports {

  public static class Controls {

    public static int DRIVER_PORT = 0;

    public static int OPERATOR_PORT = 1;
  }

  public static class Drivetrain {

    public static int DRIVE_LEFT_A_PORT = 0;
    public static int DRIVE_LEFT_B_PORT = 2;
    public static int DRIVE_RIGHT_A_PORT = 3;
    public static int DRIVE_RIGHT_B_PORT = 4;
    public static int GYRO_PORT = 0;
  }

  public static class Elevator {

    public static int ELEVATOR_MOTOR_PORT = 1;
    public static int ELEVATOR_LOWER_LIMIT_SWITCH = 1;
    public static int ELEVATOR_UPPER_LIMIT_SWITCH = 0;
    public static int ELEVATOR_ENCODER_PORT = 1;
  }

  public static class Claw {

    public static int CLAW_SOLENOID_PORT = 5;
  }

  public static class Pneumatics {

    public static int PCM_CAN_ID = 6;
  }

  public static class Led {

    public static int LEFT_PORT = 7;
    public static int RIGHT_PORT = 4;
  }

}
