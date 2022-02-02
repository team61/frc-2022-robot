// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DoubleMotors extends SubsystemBase {
  public TalonFX motor1;
  public TalonFX motor2;

  /** Creates a new DoubleMotors. */
  public DoubleMotors(int id1, int id2) {
    motor1 = new TalonFX(id1);
    motor2 = new TalonFX(id2);
  }

  public void setSpeed1(double s) {
    motor1.set(ControlMode.PercentOutput, s);
  }

  public void setSpeed2(double s) {
    motor2.set(ControlMode.PercentOutput, s);
  }

  public void setSpeed(double s) {
    setSpeed1(s);
    setSpeed2(s);
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
