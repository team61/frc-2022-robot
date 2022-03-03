// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

public class PistonSubsystem extends SubsystemBase {
  public DoubleSolenoid pistonSolenoid;
  private DoubleSolenoid upStopper;
  private DoubleSolenoid downStopper;

  public String direction;
  public boolean stopped = false;
  /** Creates a new PistonSubsystem. */
  public PistonSubsystem(PneumaticHub hub, int port1, int port2, int port3, int port4, int port5, int port6) {
    pistonSolenoid = hub.makeDoubleSolenoid(port1, port2);
    upStopper = hub.makeDoubleSolenoid(port3, port4);
    downStopper = hub.makeDoubleSolenoid(port5, port6);

    releaseUp();
    releaseDown();
  }

  public void stop() {
    stopUp();
    stopDown();
  }

  public void release() {
    releaseUp();
    releaseDown();
  }

  public void toggle() {
    if (direction == UP) {
      retract();
    } else {
      extend();
    }
  }

  public void extend() {
    if (stopped) return;

    pistonSolenoid.set(Value.kForward);
    direction = UP;
  }

  public void retract() {
    if (stopped) return;

    pistonSolenoid.set(Value.kReverse);
    direction = DOWN;
  }

  private void stopDown() {
    downStopper.set(Value.kForward);
    stopped = true;
  }

  private void releaseDown() {
    downStopper.set(Value.kReverse);
    stopped = false;
  }

  private void stopUp() {
    upStopper.set(Value.kForward);
    stopped = true;
  }

  private void releaseUp() {
    upStopper.set(Value.kReverse);
    stopped = false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
