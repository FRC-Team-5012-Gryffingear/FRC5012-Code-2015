package com.gryffingear.y2015.season;

import com.gryffingear.y2015.auton.TestAuton;
import com.gryffingear.y2015.config.Ports;
import com.gryffingear.y2015.systems.Elevator;
import com.gryffingear.y2015.systems.Robot;
import com.gryffingear.y2015.utilities.FileLogger;
import com.gryffingear.y2015.utilities.GryffinMath;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Main extends IterativeRobot {

  Joystick driver = new Joystick(Ports.Controls.DRIVER_PORT);
  Joystick operator = new Joystick(Ports.Controls.OPERATOR_PORT);

  Robot bot = Robot.getInstance();
  PowerDistributionPanel pdp = new PowerDistributionPanel();// Todo: maybe move
                                                            // this to robot?
  
  FileLogger log = new FileLogger("/home/lvuser/logs/");

  @Override
  public void robotInit() {
  }

  @Override
  public void autonomousInit() {

    Scheduler.getInstance().removeAll();
    Scheduler.getInstance().enable();
    Scheduler.getInstance().add(new TestAuton());
  }

  @Override
  public void autonomousPeriodic() {

    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {

  }

  boolean resetting = false;
  int eState = 0;
  @Override
  public void teleopPeriodic() {

    // Driver Controls

    bot.drive.tankDrive(GryffinMath.gryffinDriveArcade(driver.getRawAxis(1), driver.getRawAxis(2),
        driver.getRawButton(8)));

    bot.led.setLeft(true);
    bot.led.setRight(true);

    // Operator Controls
    double elevatorOut = 0.0;

    // if (!resetting) {
      if (operator.getRawButton(1)) {
        eState = Elevator.States.RESET_DOWN;
        resetting = true;
      } else if (operator.getRawButton(2)) {
        eState = Elevator.States.OPEN_LOOP;
        elevatorOut = 1.0;
      } else if (operator.getRawButton(4)) {
        eState = Elevator.States.CLOSED_LOOP;
        bot.elevator.setPosition(5.0);
      } else if (operator.getRawButton(3)) {
        eState = Elevator.States.CLOSED_LOOP;
        bot.elevator.setPosition(7.5);
      } else {
        eState = Elevator.States.OPEN_LOOP;
        elevatorOut = -operator.getRawAxis(1);
      }
    // / } else {
    // if (Math.abs(operator.getRawAxis(1)) > .5) {
    // resetting = false;
    // eState = Elevator.States.OPEN_LOOP;
    // }
    // }

    if (bot.elevator.getLowerLimitSwitch()) {
      resetting = false;
    }


    bot.elevator.setOpenLoop(elevatorOut);
    bot.elevator.setState(eState);

    bot.elevator.run();

    // Todo: framework for closed-loop elevator operator controls
    //

    bot.claw.setClaw(operator.getRawButton(6));


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
