// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.Constants.*;

public class ShootTimeCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ShooterSubsystem m_subsystem;
  private final String direction;
  private final double delay;
  private boolean finished = false;

  /**
   * Creates a new ShootTimeCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ShootTimeCommand(ShooterSubsystem subsystem, String dir, double time) {
    m_subsystem = subsystem;
    direction = dir;
    delay = time;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (direction == OUT) {
      m_subsystem.shoot();
    } else if (direction == STOP) {
      m_subsystem.stop();
    }

    Timer.delay(delay);
    end(false);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    finished = true;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  }
}
