// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import static frc.robot.Globals.*;

import com.ctre.phoenix.motorcontrol.ControlMode;

import static frc.robot.Constants.*;

/** An example command that uses an example subsystem. */
public class AutonomousCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveTrain drivetrain;
  private final IntakeSubsystem intake;
  private final ShooterSubsystem shooter;
  private final Command limelightCommand;
  private int speedIndex = 0;
  private boolean playbackReady = false;
  private boolean startedScriptedAutonomous = false;
  private boolean finished = false;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AutonomousCommand(DriveTrain d, IntakeSubsystem i, ShooterSubsystem s, Command lc) {
    drivetrain = d;
    intake = i;
    shooter = s;
    limelightCommand = lc;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(d, i, s);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    playbackReady = true;
    startedScriptedAutonomous = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (!playbackReady) return;
    // boolean res = Playback.run(drivetrain, intake, shooter, speedIndex, limelightCommand);
    boolean res = false;

    if (res) {
      speedIndex++;
    } else if (!startedScriptedAutonomous) {
      startedScriptedAutonomous = true;
      runScriptedAutonomous();
      end(false);
    }
  }

  private void runScriptedAutonomous() {
    new IntakeCommand(intake, "auto").andThen(
    new DriveTimeCommand(drivetrain, -4.0, -4.0, 1.2)).andThen(
    new DriveTimeCommand(drivetrain, 0.0, 0.0, 0.5)).andThen(
    new DriveTimeCommand(drivetrain, -2.5, 2.5, 1.8)).andThen(
    new DriveCommand(drivetrain, 0.0, 0.0)).andThen(
    new WaitCommand(0.5)).andThen(
    () -> SHOULD_USE_LIMELIGHT = true).andThen(
    new WaitCommand(2.0)).andThen(
    () -> SHOULD_USE_LIMELIGHT = false).andThen(
    new DriveTimeCommand(drivetrain, -2.0, -2.0, 1.0)).andThen(
    new DriveTimeCommand(drivetrain, 0.0, 0.0, 0.5)).andThen(
    new ShootTimeCommand(shooter, OUT, 4.0)).andThen(
    new ShootCommand(shooter, STOP))
    .schedule();
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
