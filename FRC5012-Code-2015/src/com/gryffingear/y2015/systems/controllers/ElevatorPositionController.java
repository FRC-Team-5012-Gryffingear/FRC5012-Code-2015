package com.gryffingear.y2015.systems.controllers;

import com.gryffingear.y2015.utilities.Debouncer;
import com.gryffingear.y2015.utilities.GryffinMath;
import com.gryffingear.y2015.utilities.Ma3Encoder;

/**
 * Controller for automatically holding/driving to elevator position.
 * 
 * @author Jeremy
 *
 */
public class ElevatorPositionController {

  private double kP = 0.0;
  private Ma3Encoder ref = null;
  private boolean en = true;
  private double pos = 0.0;

  /**
   * Contructor.
   * 
   * @param ref
   *          Encoder sensor for reference.
   */
  public ElevatorPositionController(Ma3Encoder ref) {

    this.ref = ref;
  }

  public void setEnabled(boolean en) {

    this.en = en;
  }

  public void setGains(double newP) {

    this.kP = newP;
  }

  public void setPosition(double newPos) {

    this.pos = newPos;
  }

  public boolean getEnabled() {

    return this.en;
  }

  public boolean isNearTarget() {

    return GryffinMath.equalsTolerance(pos, ref.get(), 2.0);
  }

  public boolean isUnder() {

    return pos > ref.get();
  }

  public boolean isAtTarget() {

    return atTarget && getEnabled();
  }

  Debouncer atTargetDebouncer = new Debouncer(0.2);
  boolean atTarget = false;
  public double get() {

    atTarget = atTargetDebouncer.update(GryffinMath.equalsTolerance(pos, ref.get(), 1.0));
    // Calculations here.
    return kP * (pos - ref.get());
  }

}
