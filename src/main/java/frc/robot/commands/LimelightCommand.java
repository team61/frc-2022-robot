// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import lib.components.LogitechJoystick;
import static frc.robot.Globals.*;

/** An example command that uses an example subsystem. */
public class LimelightCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final NetworkTableEntry tv;
  private final NetworkTableEntry tx;
  private final NetworkTableEntry ty;
  private final NetworkTableEntry ta;
  private final LogitechJoystick joystick;
  private final DriveTrain driveTrain;
  private boolean finished = false;

  private double limelightMountAngleDegrees = 20;
  private double limelightLensHeightInches = 30.5;
  private double goalHeightInches = 104;

  /**
   * Creates a new LimelightCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public LimelightCommand(NetworkTable table, LogitechJoystick j, DriveTrain dt) {
    tv = table.getEntry("tv");
    tx = table.getEntry("tx");
    ty = table.getEntry("ty");
    ta = table.getEntry("ta");
    driveTrain = dt;
    joystick = j;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    boolean valid = tv.getDouble(0.0) == 1.0 ? true : false;

    if (valid) {
      double horizontalOffset = tx.getDouble(0.0);
      double verticalOffset = ty.getDouble(0.0);
      double areaPercentage = ta.getDouble(0.0);

      double angleToGoalDegrees = limelightMountAngleDegrees + verticalOffset;
      double angleToGoalRadians = angleToGoalDegrees * (Math.PI / 180.0);
      double distanceToGoalInches = (goalHeightInches - limelightLensHeightInches) / Math.tan(angleToGoalRadians);
      SHOOTER_VOLTS = 0.00008 * Math.pow(distanceToGoalInches + 8, 2) + 8;
      if (SHOOTER_VOLTS < 7) SHOOTER_VOLTS = 7;
      if (SHOOTER_VOLTS > 13) SHOOTER_VOLTS = 13;
      
      // String x = String.format("%.4f", horizontalOffset);
      // String y = String.format("%.4f", verticalOffset);
      // String area = String.format("%.4f", areaPercentage);
      // System.out.println("(" + x + ", " + y + "), " + area + "%           " + distanceToGoalInches + "in");

      if (!joystick.btn_2.get()) return;
        
      double speed = 0.05 * Math.sqrt(Math.abs(horizontalOffset));
      if (horizontalOffset < -0.2) {
        driveTrain.drive(speed, -speed);
      } else if (horizontalOffset > 0.1) {
        driveTrain.drive(-speed, speed);
      }

    } else {
      // System.out.println("No target");
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

/**
 * 
 * 18in = 1% area
 * 56in = 0.1% area
 * 
 */