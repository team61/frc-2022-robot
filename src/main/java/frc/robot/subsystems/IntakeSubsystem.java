// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
  public DoubleMotors intake;

  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem(int id1, int id2) {
    intake = new DoubleMotors(id1, id2);
  }

  public void stop() {
    intake.setSpeed(0.0);
  }

  public void intake() {
    // Check sensor 1
    intake.setSpeed1(0.8);
    
    // Check sensor 2
    intake.setSpeed2(0.8);
  }

  public void eject() {
    intake.setSpeed1(-0.8);
    intake.setSpeed2(-0.8);
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
