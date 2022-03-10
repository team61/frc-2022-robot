// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.PistonSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.Globals.*;
import static frc.robot.Constants.*;

/** An example command that uses an example subsystem. */
public class PistonCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final PistonSubsystem m_subsystem;
  private final String direction;
  private boolean finished = false;

  /**
   * Creates a new PistonCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public PistonCommand(PistonSubsystem subsystem, String dir) {
    m_subsystem = subsystem;
    direction = dir;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    String codeToSave = "";

    if (direction == STOP) {
      m_subsystem.stop();
      codeToSave = "piston.stop();\n";
    } else if (direction == UP) {
      m_subsystem.release();
      m_subsystem.extend();
      codeToSave = "piston.release();\n";
      codeToSave += "piston.extend();\n";
    } else {
      m_subsystem.release();
      m_subsystem.retract();
      codeToSave = "piston.release();\n";
      codeToSave += "piston.retract();\n";
    }

    if (!PNEUMATICS_RECORDING.equals("")) {
      long timeSincePneumaticInput = System.currentTimeMillis() - LAST_PNEUMATIC_INPUT_TIME;
      LAST_PNEUMATIC_INPUT_TIME = System.currentTimeMillis();
      PNEUMATICS_RECORDING += "Thread.sleep(" + timeSincePneumaticInput + ");\n";
    }
    PNEUMATICS_RECORDING += codeToSave;

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
