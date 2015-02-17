package com.gryffingear.y2015.season;

import com.gryffingear.y2015.auton.OneToteRcAuton;
import com.gryffingear.y2015.auton.TestAuton;
import com.gryffingear.y2015.config.Ports;
import com.gryffingear.y2015.systems.Elevator;
import com.gryffingear.y2015.systems.Robot;
import com.gryffingear.y2015.utilities.FileLogger;
import com.gryffingear.y2015.utilities.GryffinMath;
import com.gryffingear.y2015.utilities.PulseTriggerBoolean;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Main extends IterativeRobot {

  Joystick driver = new Joystick(Ports.Controls.DRIVER_PORT);
  Joystick operator = new Joystick(Ports.Controls.OPERATOR_PORT);

  Robot bot = Robot.getInstance();
  

  FileLogger log = new FileLogger("/home/lvuser/logs/");

  SendableChooser autonChooser = new SendableChooser();
  CommandGroup currAuton = null;

  @Override
  public void robotInit() {

    autonChooser.addObject("Test Auton", new TestAuton());
    autonChooser.addObject("One Tote + RC Auton", new OneToteRcAuton());
    SmartDashboard.putData("auton Chooser", autonChooser);
  }

  @Override
  public void autonomousInit() {

    Scheduler.getInstance().removeAll();
    if (currAuton != null) { // Cancel auton if it hasn't already ended.
      currAuton.cancel();
      currAuton = null;
    }
    Scheduler.getInstance().enable();
    Scheduler.getInstance().add(new TestAuton());
  }

  @Override
  public void autonomousPeriodic() {

    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {

    Scheduler.getInstance().removeAll();
    Scheduler.getInstance().disable();
    if (currAuton != null) { // Cancel auton if it hasn't already ended.
      currAuton.cancel();
      currAuton = null;
    }
  }

  boolean resetting = false;
  int eState = 0;
  double elevatorPos = 0.0;

  PulseTriggerBoolean bottomLimitPulse = new PulseTriggerBoolean();
  long bottomStart = 0;
  PulseTriggerBoolean positionPulse = new PulseTriggerBoolean();
  long posStart = 0;
  @Override
  public void teleopPeriodic() {

    // Driver Controls

    bot.drive.tankDrive(GryffinMath.gryffinDriveArcade(driver.getRawAxis(1), driver.getRawAxis(2),
        driver.getRawButton(8)));

    bot.led.setLeft(true);
    bot.led.setRight(true);

    // Operator Controls
    double elevatorOut = 0.0; // Elevator open loop output

    if (!resetting) { // Operator control logic
      if (operator.getRawButton(1)) {
        eState = Elevator.States.RESET_DOWN;
        resetting = true;
      } else if (operator.getRawButton(2)) {
        eState = Elevator.States.CLOSED_LOOP;
        elevatorPos = (7.92); // Above
                                                                         // step
      } else if (operator.getRawButton(4)) {
        eState = Elevator.States.CLOSED_LOOP;
        elevatorPos = (5.3); // One
                                                                        // tote
      } else if (operator.getRawButton(3)) {
        eState = Elevator.States.CLOSED_LOOP;
        elevatorPos = (16.15); // Two
                                                                          // totes
      } else if (Math.abs(operator.getRawAxis(1)) > 0.25) {
        eState = Elevator.States.OPEN_LOOP;
        elevatorOut = -operator.getRawAxis(1);
      }
     } else {
      if (Math.abs(operator.getRawAxis(1)) > .5) {
        resetting = false;
        eState = Elevator.States.OPEN_LOOP;
      }

      if (bot.elevator.getLowerLimitSwitch()) {
        resetting = false;
      }
      elevatorOut = -1.0;
    }



    bot.elevator.setOpenLoop(elevatorOut);
    bot.elevator.setPosition(Math.max(elevatorPos - (6.0 * operator.getRawAxis(3)), 0.0));
    bot.elevator.setState(eState);

    bot.elevator.run();

    bottomLimitPulse.set(bot.elevator.getLowerLimitSwitch());
    positionPulse.set(bot.elevator.epc.isAtTarget() && bot.elevator.epc.getEnabled());

    if (bottomLimitPulse.get()) {
      bottomStart = System.currentTimeMillis();
      operator.setRumble(RumbleType.kLeftRumble, 1.0f);
    }

    if (positionPulse.get()) {
      posStart = System.currentTimeMillis();
      operator.setRumble(RumbleType.kRightRumble, 1.0f);
    }

    if (System.currentTimeMillis() - bottomStart > 250) {
      operator.setRumble(RumbleType.kLeftRumble, 0.0f);
    }

    if (System.currentTimeMillis() - posStart > 250) {
      operator.setRumble(RumbleType.kRightRumble, 0.0f);
    }


    // Todo: framework for closed-loop elevator operator controls
    //

    bot.claw.setClaw(operator.getRawButton(6));


    System.out.println("e:" + bot.elevator.getEncoder());
    bot.updateDashboard();
  }

  public void testInit() {

    Scheduler.getInstance().removeAll();
    Scheduler.getInstance().disable();
    if (currAuton != null) { // Cancel auton if it hasn't already ended.
      currAuton.cancel();
      currAuton = null;
    }
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

    bot.updateDashboard();
  }

}
