package com.gryffingear.y2015.season;

import com.gryffingear.y2015.config.Ports;
import com.gryffingear.y2015.systems.Robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

public class Main extends IterativeRobot {

  Joystick driver = new Joystick(Ports.Controls.DRIVER_PORT);
  Joystick operator = new Joystick(Ports.Controls.OPERATOR_PORT);

  Robot bot = Robot.getInstance();
  PowerDistributionPanel pdp = new PowerDistributionPanel();
  
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

    pdp.clearStickyFaults();
    pdp.getTotalCurrent();
  }

  @Override
  public void teleopPeriodic() {

    // Driver Controls
    bot.drive.tankDrive(driver.getRawAxis(1), driver.getRawAxis(3));

    // Operator Controls

    bot.claw.setClaw(operator.getRawButton(6));

    if (operator.getRawButton(7)) {
      bot.elevator.set(-1);
    } else if (operator.getRawButton(8)) {
      bot.elevator.set(.5);
    } else {
      bot.elevator.set(0);
    }
    System.out.println(pdp.getTotalCurrent());
    //test

  }

  /**
   * This function is called periodically during test mode
   */
  @Override
  public void testPeriodic() {

  }

  @Override
  public void disabledPeriodic() {

  }

}
