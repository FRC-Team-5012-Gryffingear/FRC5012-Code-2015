package com.gryffingear.y2015.utilities;

import edu.wpi.first.wpilibj.AnalogInput;

/**
 * Class representing an MA3 analog absolute encoder. 
 * @author Jeremy
 *
 */
public class Ma3Encoder extends AnalogInput {
	
	private final double MIN_VOLT = 0.0;
	private final double MAX_VOLT = 0.0;
	private double m_prev 		  = 0.0;
	private double m_curr 		  = 0.0;
	private double m_rotations	  = 0.0;
	private double m_position 	  = 0.0;
	
	public Ma3Encoder(int port) {
		super(port);
	}
	
	/**
	 * Get relative position of encoder since reset. 
	 * @return
	 */
	public double get() {
		// Todo: get this to work. 
		// Convert sawtooth wave of encoder signal to continuous number.
		return m_position;
	}
	
	public void reset() {
		m_position = 0;
	}

}
