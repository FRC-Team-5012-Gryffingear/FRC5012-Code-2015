package com.gryffingear.y2015.config;

public class Constants {

  public static class Elevator {

    public static final double P_UP = 0.075;
    public static final double P_DN = 0.05;
    public static final double P_HOLD = .10;

    public static class Setpoints {

      public static final double STEP = 7.92;
      public static final double ONE_TOTE = 5.3;
      public static final double TWO_TOTE = 16.15;

      public static final double OVER_PICKUP = 15.8;
      public static final double HOLD = 2.5;
      public static final double DOWN = 12.39;
      public static final double UP = 20.5;
    }
  }

  public static class Drivetrain {

    public static final double VRAMP_RATE = 30.0;

  }

}
