// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.CommandBase;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;
import static frc.robot.Globals.*;

public class AdjustPistonCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DoubleSolenoid pistonAdjuster;
  private boolean finished = false;

  /**
   * Creates a new AdjustPistonCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AdjustPistonCommand(DoubleSolenoid solenoid) {
    pistonAdjuster = solenoid;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    String codeToSave = "";

    if (pistonAdjuster.get() == kOff) {
      pistonAdjuster.set(kReverse);
      codeToSave = "pistonAdjuster.set(kReverse);\n";
    } else if (pistonAdjuster.get() == kReverse) {
      pistonAdjuster.set(kForward);
      codeToSave = "pistonAdjuster.set(kForward);\n";
    } else {
      pistonAdjuster.set(kReverse);
      codeToSave = "pistonAdjuster.set(kReverse);\n";
    }

    if (!PNEUMATICS_RECORDING.equals("")) {
      long timeSincePneumaticInput = System.currentTimeMillis() - LAST_PNEUMATIC_INPUT_TIME;
      PNEUMATICS_RECORDING += "Thread.sleep(" + timeSincePneumaticInput + ");\n";
    }
    LAST_PNEUMATIC_INPUT_TIME = System.currentTimeMillis();
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
