package com.gryffingear.y2015.utilities;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogTrigger;
import edu.wpi.first.wpilibj.Counter;


/**
 * Class representing an MA3 analog absolute encoder.
 * 
 * @author Jeremy
 *
 */
public class Ma3Encoder {

  private final double MIN_VOLT = 0.1;
  private final double MAX_VOLT = 4.65;
  private double m_prev = 0.0;
  private double m_curr = 0.0;
  private double m_offset = 0.0;
  private double m_position = 0.0;

  private AnalogInput m_channel = null;
  private AnalogTrigger m_trig = null;
  private Counter m_count = null;

  public Ma3Encoder(int port) {
    m_channel = new AnalogInput(port);
  }

  /**
   * Get relative position of encoder since reset.
   * 
   * @return
   */
  public double get() {

    return ((m_channel.getVoltage() - m_offset) / 4.55) * 45.5;// * 45.5;// +
                                                               // m_offset;
  }

  public void reset() {

    m_offset = m_channel.getVoltage();
  }

  public void setOffset(double offset) {

    m_offset = offset;
  }


}
