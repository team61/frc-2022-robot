// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.Globals.*;
import static frc.robot.Constants.*;

/** An example command that uses an example subsystem. */
public class SlowdownCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final String direction;
  private boolean finished = false;

  /**
   * Creates a new PistonCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public SlowdownCommand(String dir) {
    direction = dir;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (direction == SLOW) {
      new Thread(() -> {
        try {
          for (int i = 0; i < 10; i++) {
            Thread.sleep(100);
            MOTOR_COEFFICIENT *= 0.85;
          }
        } catch (Exception e) { System.err.println(e); }
      }).start();
    } else if (direction == RESET) {
      new Thread(() -> {
        try {
          for (int i = 0; i < 10; i++) {
            Thread.sleep(50);
            MOTOR_COEFFICIENT /= 0.85;
          }
          MOTOR_COEFFICIENT = 1;
        } catch (Exception e) { System.err.println(e); }
      }).start();
    }

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
