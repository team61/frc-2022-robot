// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import frc.robot.commands.AutonomousCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.PistonCommand;
import frc.robot.commands.ShootCommand;
import frc.robot.subsystems.DoubleMotors;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LEDStripSubsystem;
import frc.robot.subsystems.PistonSubsystem;
import frc.robot.subsystems.SensorSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import lib.components.LogitechJoystick;
import edu.wpi.first.wpilibj2.command.Command;
import static frc.robot.Constants.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  public final LogitechJoystick joystick1 = new LogitechJoystick(joystickport1);
  public final LogitechJoystick joystick2 = new LogitechJoystick(joystickport2);
  private final LogitechJoystick joystick3 = new LogitechJoystick(joystickport3);
  private final LogitechJoystick joystick4 = new LogitechJoystick(joystickport4);

  public final PowerDistribution pdp = new PowerDistribution(10, ModuleType.kRev);
  public final PneumaticHub m_pneumaticHub = new PneumaticHub(11);
  // private final CompressorSubsystem m_compressorSubsystem = new CompressorSubsystem();
  
  public final PistonSubsystem piston1 = new PistonSubsystem(m_pneumaticHub, 0, 1, 2, 3, 4, 5);
  public final DoubleMotors driveLeft = new DoubleMotors(18, 19, true);
  public final DoubleMotors driveRight = new DoubleMotors(0, 1, false);
  public final DriveTrain driveTrain = new DriveTrain(driveLeft, driveRight);

  public final SensorSubsystem sensor1 = new SensorSubsystem(0);
  public final SensorSubsystem sensor2 = new SensorSubsystem(1);
  public final IntakeSubsystem intake = new IntakeSubsystem(sensor1, 3, 2);
  public final ShooterSubsystem shooter = new ShooterSubsystem(intake, 4, 17);

  public final LEDStripSubsystem ledStrip = new LEDStripSubsystem(LEDStripPort, LEDStripLength);

  private final AutonomousCommand m_autoCommand = new AutonomousCommand(driveTrain);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    joystick1.btn_1.whenPressed(new IntakeCommand(intake, IN))
                   .whenReleased(new IntakeCommand(intake, STOP));
    joystick1.btn_2.whenPressed(new IntakeCommand(intake, OUT))
                   .whenReleased(new IntakeCommand(intake, STOP));

    joystick2.btn_1.whenPressed(new ShootCommand(shooter, OUT))
                   .whenReleased(new ShootCommand(shooter, STOP));
    
    joystick3.btn_7.whileHeld(() -> { shooter.setSpeed(SHOOTER_SPEED); })
                   .whenReleased(() -> { shooter.setSpeed(0.0); });
    joystick3.btn_8.whileHeld(() -> { shooter.setSpeed(-SHOOTER_SPEED); })
                   .whenReleased(() -> { shooter.setSpeed(0.0); });
    joystick3.btn_9.whileHeld(() -> { intake.setSpeed1(INTAKE_SPEED); })
                   .whenReleased(() -> { intake.setSpeed1(0.0); });
    joystick3.btn_10.whileHeld(() -> { intake.setSpeed1(-INTAKE_SPEED); })
                    .whenReleased(() -> { intake.setSpeed1(0.0); });
    joystick3.btn_11.whileHeld(() -> { intake.setSpeed2(INTAKE_SPEED); })
                    .whenReleased(() -> { intake.setSpeed2(0.0); });
    joystick3.btn_12.whileHeld(() -> { intake.setSpeed2(-INTAKE_SPEED); })
                    .whenReleased(() -> { intake.setSpeed2(0.0); });

    joystick4.btn_3.whenPressed(new PistonCommand(piston1, STOP));
    joystick4.btn_4.whenPressed(new PistonCommand(piston1, STOP));
    joystick4.btn_5.whenPressed(new PistonCommand(piston1, UP));
    joystick4.btn_6.whenPressed(new PistonCommand(piston1, DOWN));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An AutonomousCommand will run in autonomous
    return m_autoCommand;
  }
}
