package com.gryffingear.y2015.systems;

import com.gryffingear.y2015.systems.controllers.ElevatorPositionController;
import com.gryffingear.y2015.utilities.Ma3Encoder;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;

public class Elevator {

  private CANTalon elevatorMotor = null;
  private DigitalInput lowerlimit_switch;
  private DigitalInput upperlimit_switch;
  private Ma3Encoder posRef = null;
  private ElevatorPositionController epc = null;

  public Elevator(int elevator_port, int lowerlimit_port, int upperlimit_port, int encoderPort) {

    upperlimit_switch = new DigitalInput(upperlimit_port);
    lowerlimit_switch = new DigitalInput(lowerlimit_port);
    elevatorMotor = configureTalon(new CANTalon(elevator_port));

    posRef = new Ma3Encoder(encoderPort);
    epc = new ElevatorPositionController(posRef);
    epc.setEnabled(true);
    epc.setGains(0.5);
  }

  private CANTalon configureTalon(CANTalon in) {

    in.clearStickyFaults();
    in.changeControlMode(CANTalon.ControlMode.PercentVbus);
    in.enableControl();
    return in;
  }

  public void set(double value) {

    if (value < 0.0 && !lowerlimit_switch.get()) {
      value = 0.0;
    } else if (value > 0.0 && !upperlimit_switch.get()) {
      value = 0.0;
    }

    elevatorMotor.set(value);
  }

  private double openLoopInput = 0.0;

  public void setOpenLoop(double in) {

    openLoopInput = in;
  }

  private double position = 0.0; // Todo set to default position

  public void setPosition(double position) {

    this.position = position;
  }

  private int state = 0;

  public void setState(int in) {

    this.state = in;
  }

  public static class States {

    public static final int DISABLED = 0;
    public static final int OPEN_LOOP = 1;
    public static final int CLOSED_LOOP = 2;
    public static final int MOVING_UP = 3;
    public static final int MOVING_DN = 4;
    public static final int HOLDING = 5;
  }

  public void run() {

    double output = 0.0;
    epc.setPosition(position);
    switch (state) {
    case States.DISABLED: // Disabled state. Stop everything
      output = 0.0;
      epc.setEnabled(false);

      break;

    case States.OPEN_LOOP: // Open loop state. send input to output
      epc.setEnabled(false);
      output = openLoopInput;

      break;
    case States.CLOSED_LOOP: // Open loop state. send input to output

      epc.setEnabled(true);
      if (epc.isUnder())
        state = States.MOVING_UP;
      else if (!epc.isUnder())
        state = States.MOVING_DN;

      break;
    case States.MOVING_UP: // Closed loop moving up. Higher gains = moar
      // power

      epc.setEnabled(true);
      epc.setGains(1.0); // Todo: move to constants.
      if (epc.isNearTarget())
        state = States.HOLDING;

      break;
    case States.MOVING_DN: // Closed loop moving down, lower gains = slower
      // to fall

      epc.setEnabled(true);
      epc.setGains(0.5);
      if (epc.isNearTarget())
        state = States.HOLDING;

      break;
    case States.HOLDING: // Closed loop holding position, super high gains =
      // super holding power

      epc.setEnabled(true);
      epc.setGains(2.0);
      // Exit holding state if
      if ((!epc.isNearTarget()) && epc.isUnder())
        state = States.MOVING_UP;
      if ((!epc.isNearTarget()) && (!epc.isUnder()))
        state = States.MOVING_DN;

      break;
    }

    if (epc.getEnabled()) {
      output = epc.get();
    }

    elevatorMotor.set(output);
  }
}
