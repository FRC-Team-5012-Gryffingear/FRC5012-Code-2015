package com.gryffingear.y2015.utilities;

import edu.wpi.first.wpilibj.AnalogInput;

/**
 * Class representing an MA3 analog absolute encoder.
 * 
 * @author Jeremy
 *
 */
public class Ma3Encoder extends AnalogInput {

  private final double MIN_VOLT = 0.0;
  private final double MAX_VOLT = 4.76;
  private double m_prev = 0.0;
  private double m_curr = 0.0;
  private double m_rotations = 0.0;
  private double m_position = 0.0;

  public Ma3Encoder(int port) {

    super(port);
  }

  /**
   * Get relative position of encoder since reset.
   * 
   * @return
   */
  public double get() {

    m_prev = m_curr;
    m_curr = this.getVoltage();

    if (m_prev != m_curr) {
      if (m_prev > 4.5 && m_curr < 0.2) { // going up
        m_rotations += 1.0;
        // System.out.println("+1 rot");
      } else if (m_prev < 0.2 && m_curr > 4.5) {
        m_rotations -= 1.0;
        // System.out.println("-1 rot");
      }
    }

    m_position = m_rotations + (m_curr / 4.76);

    // Todo: get this to work.
    // Convert sawtooth wave of encoder signal to continuous number.

    return m_curr;// m_position;
  }

  public void reset() {

    m_position = 0;
  }

}
