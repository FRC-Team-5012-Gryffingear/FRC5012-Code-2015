package com.gryffingear.y2015.auton.commands;

import edu.wpi.first.wpilibj.command.Command;
import com.gryffingear.y2015.systems.Robot;


public class WaitCommand {

    private double timeout = 0.0;

    public WaitCommand(double timeout) {
        this.timeout = timeout;
    }

    protected void initialize() {
        this.setTimeout(timeout);
    }

    private void setTimeout(double timeout2) {

      
    }

    protected void execute() {

    }

    protected void interrupted() {

    }

    protected void end() {

    }

    protected boolean isFinished() {
        return this.isTimedOut();
    }

    private boolean isTimedOut() {

      
      return false;
    }

}

























