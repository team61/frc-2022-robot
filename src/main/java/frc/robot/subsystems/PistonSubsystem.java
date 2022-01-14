// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class PistonSubsystem extends SubsystemBase {
  private Solenoid pistonSolenoid1;
  private Solenoid pistonSolenoid2;
  private Solenoid downStopper1;
  private Solenoid downStopper2;
  private Solenoid upStopper1;
  private Solenoid upStopper2;

  public String direction;
  public boolean stopped = false;
  /** Creates a new PistonSubsystem. */
  public PistonSubsystem(int port1, int port2, int port3, int port4, int port5, int port6) {
    pistonSolenoid1 = new Solenoid(PneumaticsModuleType.CTREPCM, port1);
    pistonSolenoid2 = new Solenoid(PneumaticsModuleType.CTREPCM, port2);

    upStopper1 = new Solenoid(PneumaticsModuleType.CTREPCM, port3);
    upStopper2 = new Solenoid(PneumaticsModuleType.CTREPCM, port4);

    downStopper1 = new Solenoid(PneumaticsModuleType.CTREPCM, port5);
    downStopper2 = new Solenoid(PneumaticsModuleType.CTREPCM, port6);

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
    if (direction == Constants.UP) {
      retract();
    } else {
      extend();
    }
  }

  public void extend() {
    if (stopped) return;

    pistonSolenoid1.set(true);
    pistonSolenoid2.set(false);

    direction = Constants.UP;
  }

  public void retract() {
    if (stopped) return;

    pistonSolenoid1.set(false);
    pistonSolenoid2.set(true);
    
    direction = Constants.DOWN;
  }

  private void stopDown() {
    downStopper1.set(false);
    downStopper2.set(true);

    stopped = true;
  }

  private void releaseDown() {
    downStopper1.set(true);
    downStopper2.set(false);

    stopped = false;
  }

  private void stopUp() {
    upStopper1.set(false);
    upStopper2.set(true);

    stopped = true;
  }

  private void releaseUp() {
    upStopper1.set(true);
    upStopper2.set(false);

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
