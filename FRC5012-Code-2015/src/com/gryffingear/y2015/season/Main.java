package com.gryffingear.y2015.season;

import com.gryffingear.y2015.auton.Autozone;
import com.gryffingear.y2015.auton.TestAuton;
import com.gryffingear.y2015.auton.WingAuton;
import com.gryffingear.y2015.config.Constants;
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

  Joystick driverL = new Joystick(Ports.Controls.DRIVER_PORTL);
  Joystick driverR = new Joystick(Ports.Controls.DRIVER_PORTR);

  Joystick operator = new Joystick(Ports.Controls.OPERATOR_PORT);

  Robot bot = Robot.getInstance();
  
  // StaticWing = new Solenoid(Ports.STATIC_WING_PORT);

  FileLogger log = new FileLogger("/home/lvuser/logs/");

  SendableChooser autonChooser = new SendableChooser();
  CommandGroup currAuton = null;


  @Override
  public void robotInit() {

    autonChooser.addObject("Do Nothing", new TestAuton());

    autonChooser.addDefault("Autozone", new Autozone());
    autonChooser.addObject("WingAuton", new WingAuton());
    SmartDashboard.putData("auton Chooser", autonChooser);
  }

  @Override
  public void autonomousInit() {

    cancelAuton();
    Scheduler.getInstance().enable();
    // Scheduler.getInstance().add(new Autozone());
    boolean auton = true;
    if (auton) {
      Scheduler.getInstance().add(new TestAuton());
      // Scheduler.getInstance().add((CommandGroup) autonChooser.getSelected());
    }


  }


  @Override
  public void autonomousPeriodic() {

    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    cancelAuton();
  }

  boolean resetting = false;
  int eState = 0;
  double elevatorPos = 0.0;

  PulseTriggerBoolean bottomLimitPulse = new PulseTriggerBoolean();
  long bottomStart = 0;
  PulseTriggerBoolean positionPulse = new PulseTriggerBoolean();
  long posStart = 0;

  PulseTriggerBoolean intakeTogglePulse = new PulseTriggerBoolean();
  boolean intakePos = false;

  @Override
  public void teleopPeriodic() {

    // Driver Controls

    double[] driveOut = GryffinMath.gryffinDrive(driverL.getRawAxis(1), driverR.getRawAxis(1),
        driverL.getRawButton(1));

    if (driverR.getRawButton(1)) {
      driveOut[0] = 0.4;
      driveOut[1] = 0.4;
    }

    bot.drive.tankDrive(driveOut);

    bot.led.setLeft(true);
    bot.led.setRight(true);

    // Operator Controls
    double elevatorOut = 0.0;
    boolean clawOut = false;

    // Elevator open loop output

    if (!resetting) { // Elevator state switching logic.
      if (operator.getRawButton(1)) {
        eState = Elevator.States.RESET_DOWN;
        resetting = true;
      } else if (operator.getRawButton(2)) {
        eState = Elevator.States.CLOSED_LOOP;
        elevatorPos = Constants.Elevator.Setpoints.OVER_PICKUP;
      } else if (operator.getRawButton(4)) {
        eState = Elevator.States.CLOSED_LOOP;
        elevatorPos = Constants.Elevator.Setpoints.UP;
      } else if (operator.getRawButton(3)) {
        eState = Elevator.States.CLOSED_LOOP;
        elevatorPos = Constants.Elevator.Setpoints.HOLD;
      } else if (operator.getRawButton(5)) {
        clawOut = true;
        eState = Elevator.States.RESET_DOWN;
        resetting = true;

      } else if (Math.abs(operator.getRawAxis(1)) > 0.25) {
        eState = Elevator.States.OPEN_LOOP;
        elevatorOut = -operator.getRawAxis(1);
      } else if (operator.getRawButton(8)) {
        eState = Elevator.States.DISABLED;
      }
    } else {
      if (Math.abs(operator.getRawAxis(1)) > .5) {
        resetting = false;
        eState = Elevator.States.OPEN_LOOP;
      }

      if (bot.elevator.getLowerLimitSwitch()) {
        resetting = false;
      }
      elevatorOut = -0.750;
    }

    // Set elevator inputs: open loop, position, and state.
    bot.elevator.setOpenLoop(-operator.getRawAxis(1));
    bot.elevator.setPosition(Math.max(elevatorPos + (8.0 * operator.getRawAxis(3)), 0.0));
    bot.elevator.setState(eState);
    // Run elevator control loop!
    bot.elevator.run();

    // bot.wings.setWings(driver.getRawButton(1));

    // if (driver.getRawButton(2)) {
    // bot.wings.winch.set(Relay.Value.kForward);
    // } else if (driver.getRawButton(4)) {
    // bot.wings.winch.set(Relay.Value.kReverse);
    // } else {
    // bot.wings.winch.set(Relay.Value.kOff);
    // }

    // Force intake open if elevator is going down.

    boolean wantIntakeOpen = (operator.getRawAxis(2) > 0.25);

    intakeTogglePulse.set(wantIntakeOpen);
    if (intakeTogglePulse.get()) {
      intakePos = !intakePos;
    }


    bot.intake.setActuator(intakePos || resetting,
        operator.getRawButton(10));

    double intakeOut = operator.getRawAxis(5);

    bot.intake.setMotors(intakeOut, -intakeOut);

    // Rumble logic here:
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

    boolean brakeOut = ((bot.elevator.getEncoder() < 3.5));
    if (operator.getRawButton(8)) {
      brakeOut = !brakeOut;
    }
    bot.elevator.setBrake(brakeOut);

    clawOut = clawOut || operator.getRawButton(6);
    bot.claw.toggleClaw(clawOut); // Claw control

    bot.updateDashboard(); // Call SmartDashboard update.
    System.out.println("Elevator pos: " + bot.elevator.getEncoder());
  }


  public void testInit() {

    cancelAuton();
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

    SmartDashboard.putData("auton Chooser", autonChooser);
    System.out.println("Elevator pos: " + bot.elevator.getEncoder());
    bot.updateDashboard();
  }

  public void cancelAuton() {

    Scheduler.getInstance().removeAll();
    Scheduler.getInstance().disable();
    if (currAuton != null) { // Cancel auton if it hasn't already ended.
      currAuton.cancel();
      currAuton = null;
    }
  }

}

