package com.gryffingear.y2015.config;

public class Ports {

  public static class Controls {

    public static int LEFT_JOY_PORT = 0;
    public static int RIGHT_JOY_PORT = 1;
    public static int OPERATOR_JOY_PORT = 2;
  }

  public static class Drivetrain {

    public static int DRIVE_LEFT_A_PORT = 0;
    public static int DRIVE_LEFT_B_PORT = 2;
    public static int DRIVE_RIGHT_A_PORT = 3;
    public static int DRIVE_RIGHT_B_PORT = 4;
  }

  public static class Elevator {

    public static int ELEVATOR_MOTOR_PORT = 1;
    public static int ELEVATOR_LOWER_LIMIT_SWITCH = 0;
    public static int ELEVATOR_UPPER_LIMIT_SWITCH = 0;
  }

  public static class Claw {

    public static int CLAW_SOLENOID_PORT = 4;
  }

  public static class Pneumatics {

    public static int PCM_CAN_ID = 6;
  }

}
