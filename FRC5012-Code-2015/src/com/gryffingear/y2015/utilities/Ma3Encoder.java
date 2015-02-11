package com.gryffingear.y2015.utilities;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogTrigger;
import edu.wpi.first.wpilibj.AnalogTriggerOutput.AnalogTriggerType;
import edu.wpi.first.wpilibj.Counter;


/**
 * Class representing an MA3 analog absolute encoder.
 * 
 * @author Jeremy
 *
 */
public class Ma3Encoder {

  private final double MIN_VOLT = 0.05;
  private final double MAX_VOLT = 4.7;
  private double m_prev = 0.0;
  private double m_curr = 0.0;
  private double m_rotations = 0.0;
  private double m_position = 0.0;

  private AnalogInput m_channel = null;
  private AnalogTrigger m_trig = null;
  private Counter m_count = null;

  public Ma3Encoder(int port) {
    m_channel = new AnalogInput(port);
    m_trig = new AnalogTrigger(m_channel);
    m_count = new Counter();

    m_trig.setLimitsVoltage(MIN_VOLT, MAX_VOLT);
    m_count.setUpDownCounterMode();
    m_count.setUpSource(m_trig, AnalogTriggerType.kRisingPulse);
    m_count.setDownSource(m_trig, AnalogTriggerType.kFallingPulse);

  }

  /**
   * Get relative position of encoder since reset.
   * 
   * @return
   */
  public double get() {

    m_prev = m_curr;
    m_curr = this.m_channel.getVoltage();

    m_position = m_count.get() + (m_curr / 4.76);

    // Todo: get this to work.
    // Convert sawtooth wave of encoder signal to continuous number.

    return m_curr;// m_position;
  }

  public void reset() {

    m_count.reset();
    m_position = 0;
  }

}
