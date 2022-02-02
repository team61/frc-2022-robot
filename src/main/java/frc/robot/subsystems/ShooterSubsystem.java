// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.Date;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
  public DoubleMotors shooter;
  public IntakeSubsystem intakeSubsystem;
  public boolean isShooting = false;

  /** Creates a new ShooterSubsystem. */
  public ShooterSubsystem(IntakeSubsystem intake, int id1, int id2) {
    shooter = new DoubleMotors(id1, id2);
    intakeSubsystem = intake;
  }

  public void shoot() {
    shooter.setSpeed(0.8);

    Timer.delay(1);
    intakeSubsystem.eject();
  }

  public void stop() {
    isShooting = false;
    shooter.setSpeed(0.0);
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
