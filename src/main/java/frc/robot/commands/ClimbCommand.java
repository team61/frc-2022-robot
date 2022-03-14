// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.PistonSubsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.CommandBase;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;
import static frc.robot.Constants.*;

/** An example command that uses an example subsystem. */
public class ClimbCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final PistonSubsystem piston1;
  private final DoubleSolenoid pistonAdjuster;
  private boolean finished = false;

  private final int PISTON_TIME = 4600;
  private final int ADJUSTER_TIME = 2200;
  private final int PARTIAL_TIME = 1000;

  /**
   * Creates a new DriveCommand.
   *
   * @param subsystem1 The subsystem used by this command.
   */
  public ClimbCommand(PistonSubsystem subsystem1, DoubleSolenoid solenoid) {
    piston1 = subsystem1;
    pistonAdjuster = solenoid;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem1);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    /*
    * up      
    *         back
    * down    
    * up      front
    *         back
    * down    
    * up      front
    *         back
    * down    
    */

    new Thread(() -> {
      try {
        pistonAdjuster.set(kReverse);
        Thread.sleep(ADJUSTER_TIME);
        piston1.extend();
        Thread.sleep(PISTON_TIME);
        pistonAdjuster.set(kForward);
        Thread.sleep(ADJUSTER_TIME);
        piston1.retract();
        Thread.sleep(PISTON_TIME);
        piston1.extend();
        Thread.sleep(PARTIAL_TIME);
        pistonAdjuster.set(kReverse);
        Thread.sleep(ADJUSTER_TIME);
        pistonAdjuster.set(kForward);
        Thread.sleep(ADJUSTER_TIME);
        piston1.retract();
        Thread.sleep(PISTON_TIME);
        piston1.extend();
        Thread.sleep(PARTIAL_TIME);
        pistonAdjuster.set(kReverse);
        Thread.sleep(ADJUSTER_TIME);
        pistonAdjuster.set(kForward);
        Thread.sleep(ADJUSTER_TIME);
        piston1.retract();
        Thread.sleep(PARTIAL_TIME);
        piston1.stop();
      } catch (Exception e) { System.err.println(e); }
    }).start();

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
