// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.AutonomousCommand;
import frc.robot.commands.PistonCommand;
import frc.robot.commands.PistonStopCommand;
import frc.robot.subsystems.CompressorSubsystem;
import frc.robot.subsystems.PistonSubsystem;
import lib.components.LogitechJoystick;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final CompressorSubsystem m_compressorSubsystem = new CompressorSubsystem();
  private final AutonomousCommand m_autoCommand = new AutonomousCommand(m_compressorSubsystem);

  private final LogitechJoystick joystick1 = new LogitechJoystick(Constants.joystickport1);
  private final LogitechJoystick joystick2 = new LogitechJoystick(Constants.joystickport2);
  private final LogitechJoystick joystick3 = new LogitechJoystick(Constants.joystickport3);
  private final LogitechJoystick joystick4 = new LogitechJoystick(Constants.joystickport4);

  public final PistonSubsystem piston1 = new PistonSubsystem(0, 1, 2, 3, 4, 5);

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
    joystick4.btn_3.whenPressed(new PistonStopCommand(piston1));
    joystick4.btn_4.whenPressed(new PistonStopCommand(piston1));
    joystick4.btn_5.whenPressed(new PistonCommand(piston1, Constants.UP));
    joystick4.btn_6.whenPressed(new PistonCommand(piston1, Constants.DOWN));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
