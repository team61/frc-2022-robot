// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Globals.*;

public class ShooterSubsystem extends SubsystemBase {
  public DoubleMotors shooter;
  public IntakeSubsystem intakeSubsystem;
  public boolean isShooting = false;

  /** Creates a new ShooterSubsystem. */
  public ShooterSubsystem(IntakeSubsystem intake, int id1, int id2) {
    shooter = new DoubleMotors(id1, id2, false);
    shooter.motor2.setInverted(true);
    intakeSubsystem = intake;
  }

  public void setSpeed(double value) {
    shooter.setSpeed(value);
  }

  public void setVolts(double volts) {
    shooter.setVolts(volts);
  }

  public void shoot() {
    IS_SHOOTING = true;
    
    setVolts(SHOOTER_VOLTS);
    
    new Thread(() -> {
      try {
          Thread.sleep(1000);
          intakeSubsystem.intake(true);
      } catch (Exception e) { System.err.println(e); }
    }).start();
  }

  public void shoot(double volts) {
    IS_SHOOTING = true;
    
    setVolts(volts);
    
    new Thread(() -> {
      try {
          Thread.sleep(200);
          intakeSubsystem.intake(true);
      } catch (Exception e) { System.err.println(e); }
    }).start();
  }

  public void stop() {
    isShooting = false;
    IS_SHOOTING = false;
    setSpeed(0.0);
    intakeSubsystem.stop();
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
