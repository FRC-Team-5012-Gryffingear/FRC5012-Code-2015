package com.gryffingear.y2015.season;

import com.gryffingear.y2015.systems.Drivetrain;
import com.gryffingear.y2015.systems.Robot;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import com.gryffingear.y2015.config.Ports;



public class Main extends IterativeRobot{

	Joystick leftstick = new Joystick(Ports.LEFT_JOY_PORT);
	Joystick rightstick = new Joystick(Ports.RIGHT_JOY_PORT);
	
	Robot bot = Robot.getInstance();
	PowerDistributionPanel pdp = new PowerDistributionPanel(); 

	public void robotInit() {
		
	}

	public void autonomousInit() {

	}

	public void autonomousPeriodic() {

	}

	public void teleopInit() {

		pdp.clearStickyFaults();
		
	}

	public void teleopPeriodic() {
		//double throttle = (leftstick.getRawAxis(0) + rightstick.getRawAxis(1)) /2;
		//double turning = (leftstick.getRawAxis(0) - rightstick.getRawAxis(1))  /2;
		
		bot.drive.tankDrive(leftstick.getRawAxis(1), rightstick.getRawAxis(1));

	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {

	}

	public void disabledPeriodic() {

	}

}
