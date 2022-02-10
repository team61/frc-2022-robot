// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

public class IntakeSubsystem extends SubsystemBase {
  private DoubleMotors intake;
  private SensorSubsystem sensor1;

  private boolean stopped1 = false;
  private boolean stopped2 = false;

  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem(SensorSubsystem s1, int id1, int id2) {
    intake = new DoubleMotors(id1, id2, false);
    sensor1 = s1;
  }

  public void stop() {
    intake.setSpeed(0.0);
  }

  public double getSpeed1() {
    return intake.getSpeed1();
  }

  public double getSpeed2() {
    return intake.getSpeed2();
  }

  public void setSpeed1(double value) {
    if (value == getSpeed1()) return;

    intake.setSpeed1(value);
  }

  public void setSpeed2(double value) {
    if (value == getSpeed2()) return;

    intake.setSpeed2(value);
  }

  public void intake() {
    if (!stopped1) {
      setSpeed1(INTAKE_SPEED);
    }

    if (!stopped2) {
      setSpeed2(INTAKE_SPEED);
    }
  }

  public void intake(boolean force) {
    if (force) {
      setSpeed1(INTAKE_SPEED);
      setSpeed2(INTAKE_SPEED);
    } else {
      intake();
    }
  }

  public void eject() {
    setSpeed1(-INTAKE_SPEED);
    setSpeed2(-INTAKE_SPEED);
  }

  @Override
  public void periodic() {
    if (sensor1.isTriggered()/* && getSpeed1() > 0.08*/) {
      setSpeed1(-0.05);
      stopped1 = true;
    } else if (!sensor1.isTriggered()) {
      stopped1 = false;
    }
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
