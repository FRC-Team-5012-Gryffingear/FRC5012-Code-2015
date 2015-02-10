package com.gryffingear.y2015.season;

import com.gryffingear.y2015.config.Ports;
import com.gryffingear.y2015.systems.Robot;
import com.gryffingear.y2015.utilities.FileLogger;
import com.gryffingear.y2015.utilities.GryffinMath;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

public class Main extends IterativeRobot {

  Joystick driver = new Joystick(Ports.Controls.DRIVER_PORT);
  Joystick operator = new Joystick(Ports.Controls.OPERATOR_PORT);

  Robot bot = Robot.getInstance();
  PowerDistributionPanel pdp = new PowerDistributionPanel();
  
  FileLogger log = new FileLogger("/home/lvuser/logs/");

  @Override
  public void robotInit() {
  }

  @Override
  public void autonomousInit() {

  }

  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopInit() {

  }

  @Override
  public void teleopPeriodic() {

    // Driver Controls

    bot.drive.tankDrive(GryffinMath.gryffinDriveArcade(driver.getRawAxis(1), driver.getRawAxis(2),
        driver.getRawButton(8)));

    bot.led.setLeft(Math.abs(driver.getRawAxis(1)) > 0.5);
    bot.led.setRight(Math.abs(driver.getRawAxis(3)) > 0.5);

    // Operator Controls
    double elevatorOut = 0.0;

    if (operator.getRawButton(1)) {
      elevatorOut = 1.0;
    } else if (operator.getRawButton(2)) {
      elevatorOut = -1.0;
    } else {
      elevatorOut = operator.getRawAxis(1);
    }

    bot.claw.setClaw(operator.getRawButton(6));

    bot.elevator.set(elevatorOut);
    System.out.println("e:" + bot.elevator.getEncoder());

  }

  public void testInit() {

    bot.elevator.resetEncoder();
  }
  /**
   * This function is called periodically during test mode
   */
  @Override
  public void testPeriodic() {

    bot.led.setLeft(true);
    bot.led.setRight(true);
  }

  @Override
  public void disabledPeriodic() {

    System.out.println("e:" + bot.elevator.getEncoder());
  }

}
