// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DoubleMotors extends SubsystemBase {
  public WPI_TalonFX motor1;
  public WPI_TalonFX motor2;

  /** Creates a new DoubleMotors. */
  public DoubleMotors(int id1, int id2, boolean invert) {
    motor1 = new WPI_TalonFX(id1);
    motor2 = new WPI_TalonFX(id2);

    motor1.setInverted(invert);
    motor2.setInverted(invert);

    enableBrakes();
  }

  public void enableBrakes() {
    motor1.setNeutralMode(NeutralMode.Brake);
    motor2.setNeutralMode(NeutralMode.Brake);
  }

  public void disableBrakes() {
    motor1.setNeutralMode(NeutralMode.Coast);
    motor2.setNeutralMode(NeutralMode.Coast);
  }

  public double getVoltage1() {
    return motor1.getMotorOutputVoltage();
  }

  public double getVoltage2() {
    return motor2.getMotorOutputVoltage();
  }

  public double getSpeed1() {
    return motor1.getMotorOutputPercent();
  }

  public double getSpeed2() {
    return motor2.getMotorOutputPercent();
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

  public void setVolts(double v) {
    setVolts1(v);
    setVolts2(v);
  }

  public void setVolts1(double v) {
    motor1.setVoltage(v);
  }

  public void setVolts2(double v) {
    motor2.setVoltage(v);
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
