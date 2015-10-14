package com.gryffingear.y2015.auton.commands;

import java.util.ArrayList;

import com.gryffingear.y2015.systems.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TrapezoidalDriveCommand extends Command {

  private double speed = 0.0;
  private double turn = 0.0;
  private double timeout = 0.0;

  public TrapezoidalDriveCommand(double speed, double turn, double timeout) {

    this.speed = -speed;
    this.turn = -turn;
    this.timeout = timeout;
    this.setTimeout(timeout);
  }

  // ArrayList containing output values.
  ArrayList<Double> trajectory = new ArrayList();
  double timeStep = 0.05;
  // Constant representing maximum acceleration
  double maxA = 0.050;

  long startTime = 0;


  protected void initialize() {

    double tempSpeed = (speed);

    // Generate profile

    double timeToV = tempSpeed / maxA;
    double out = 0.0;
    for (double t = 0; t < timeout; t += timeStep) {

      if (timeout < 2.0 * timeToV) {
        if (t <= timeout / 2) {
          out += maxA * timeStep;
        } else {
          out += -maxA * timeStep;
        }
      } else {
        if (t <= timeToV) {
          out += maxA * timeStep;
        } else if (t > timeToV && t < (timeout - timeToV)) {
          out = tempSpeed;
        } else {
          out += -maxA * timeStep;
        }

      }
      //out *= Math.signum(speed);

      trajectory.add(new Double(out) );
    }

    startTime = System.currentTimeMillis();
  }

  protected boolean isFinished() {

    return this.isTimedOut();
  }


  protected void execute() {

    double elapsedTime = ((double) (System.currentTimeMillis() - startTime)) / 1000.0;

    int frame = (int) (elapsedTime / timeStep);

    // read the current frame's output data

    double out = 0.0;
    if (frame < trajectory.size()) {
      out = trajectory.get(frame).doubleValue();
    } else {
      out = 0.0;
    }

    Robot.getInstance().drive.tankDrive(out, out);

  }

  protected void end() {

    Robot.getInstance().drive.tankDrive(0.0, 0.0);
  }

  protected void interrupted() {

    Robot.getInstance().drive.tankDrive(0.0, 0.0);
  }
}
