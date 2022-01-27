// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
  public DoubleMotors driveLeft;
  public DoubleMotors driveRight;

  /** Creates a new DriveTrain. */
  public DriveTrain(DoubleMotors l, DoubleMotors r) {
    driveLeft = l;
    driveRight = r;
  }

  public void drive(double l, double r) {
    driveLeft(l);
    driveRight(r);
  }

  public void driveLeft(double s) {
    driveLeft.setSpeed(s);
  }

  public void driveRight(double s) {
    driveRight.setSpeed(s);
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
