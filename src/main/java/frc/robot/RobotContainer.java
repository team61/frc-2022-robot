// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import frc.robot.commands.AdjustPistonCommand;
import frc.robot.commands.AutonomousCommand;
import frc.robot.commands.ClimbCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.LimelightCommand;
import frc.robot.commands.PistonCommand;
import frc.robot.commands.ShootCommand;
import frc.robot.commands.SlowdownCommand;
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
import static frc.robot.Globals.*;

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
  public final LogitechJoystick joystick3 = new LogitechJoystick(joystickport3);
  public final LogitechJoystick joystick4 = new LogitechJoystick(joystickport4);

  public final PowerDistribution pdp = new PowerDistribution(10, ModuleType.kRev);
  public final PneumaticHub m_pneumaticHub = new PneumaticHub(15);
  // private final CompressorSubsystem m_compressorSubsystem = new CompressorSubsystem();
  public final PistonSubsystem piston1 = new PistonSubsystem(m_pneumaticHub, 0, 1, 2, 3, 4, 5);
  public final PistonSubsystem piston2 = new PistonSubsystem(m_pneumaticHub, 15, 14, 12, 13, 10, 7);
  public final DoubleSolenoid pistonAdjuster = m_pneumaticHub.makeDoubleSolenoid(8, 9);

  private final DoubleMotors driveLeft = new DoubleMotors(18, 19, true);
  private final DoubleMotors driveRight = new DoubleMotors(0, 1, false);
  public final DriveTrain driveTrain = new DriveTrain(driveLeft, driveRight);

  public final SensorSubsystem sensor1 = new SensorSubsystem(0);
  public final SensorSubsystem sensor2 = new SensorSubsystem(1);
  public final IntakeSubsystem intake = new IntakeSubsystem(sensor1, sensor2, 3, 2);
  public final ShooterSubsystem shooter = new ShooterSubsystem(intake, 4, 17);
  
  public final LEDStripSubsystem ledStrip = new LEDStripSubsystem(LEDStripPort, LEDStripLength);
  
  private final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  private final LimelightCommand m_limelightCommand = new LimelightCommand(table, joystick1, driveTrain); 
  private final AutonomousCommand m_autoCommand = new AutonomousCommand(driveTrain, intake, shooter, m_limelightCommand);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    CameraServer.startAutomaticCapture();
    CvSink cvSink = CameraServer.getVideo();
    CvSource outputStream = CameraServer.putVideo("Blur", 640, 480);
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
    // joystick1.btn_2.whenPressed(new IntakeCommand(intake, OUT))
    //                .whenReleased(new IntakeCommand(intake, STOP));

    joystick2.btn_1.whenPressed(new ShootCommand(shooter, OUT))
                   .whenReleased(new ShootCommand(shooter, STOP));
    joystick2.btn_2.whenPressed(new SlowdownCommand(SLOW))
                   .whenReleased(new SlowdownCommand(RESET));
    joystick2.btn_7.whenPressed(new ShootCommand(shooter, OUT, 8))
                   .whenReleased(new ShootCommand(shooter, STOP));
    joystick2.btn_8.whenPressed(new ShootCommand(shooter, OUT, 5))
                   .whenReleased(new ShootCommand(shooter, STOP));
    joystick2.btn_9.whenPressed(new ShootCommand(shooter, OUT))
                   .whenReleased(new ShootCommand(shooter, STOP));
    joystick2.btn_11.whenPressed(new ShootCommand(shooter, OUT, 11.5))
                   .whenReleased(new ShootCommand(shooter, STOP));
    
    joystick3.btn_1.whenPressed(new ClimbCommand(piston1, pistonAdjuster));
    joystick3.btn_2.whenPressed(new IntakeCommand(intake, OUT))
                   .whenReleased(new IntakeCommand(intake, STOP));
    joystick3.btn_7.whileHeld(() -> { shooter.setSpeed(SHOOTER_VOLTS); })
                   .whenReleased(() -> { shooter.setSpeed(0.0); });
    joystick3.btn_8.whileHeld(() -> { shooter.setSpeed(-SHOOTER_VOLTS); })
                   .whenReleased(() -> { shooter.setSpeed(0.0); });
    joystick3.btn_9.whileHeld(() -> { intake.setSpeed1(INTAKE_1_SPEED); })
                   .whenReleased(() -> { intake.setSpeed1(0.0); });
    joystick3.btn_10.whileHeld(() -> { intake.setSpeed1(-INTAKE_1_SPEED); })
                    .whenReleased(() -> { intake.setSpeed1(0.0); });
    joystick3.btn_11.whileHeld(() -> { intake.setSpeed2(INTAKE_2_SPEED); })
                    .whenReleased(() -> { intake.setSpeed2(0.0); });
    joystick3.btn_12.whileHeld(() -> { intake.setSpeed2(-INTAKE_2_SPEED); })
                    .whenReleased(() -> { intake.setSpeed2(0.0); });
    
    joystick3.btn_3.whenPressed(new PistonCommand(piston2, STOP));
    joystick3.btn_4.whenPressed(new PistonCommand(piston2, STOP));
    joystick3.btn_5.whenPressed(new PistonCommand(piston2, UP));
    joystick3.btn_6.whenPressed(new PistonCommand(piston2, DOWN));

    joystick4.btn_1.whenPressed(new AdjustPistonCommand(pistonAdjuster));
    joystick4.btn_3.whenPressed(new PistonCommand(piston1, STOP));
    joystick4.btn_4.whenPressed(new PistonCommand(piston1, STOP));
    joystick4.btn_5.whenPressed(new PistonCommand(piston1, UP));
    joystick4.btn_6.whenPressed(new PistonCommand(piston1, DOWN));
    joystick4.btn_11.whenPressed(() -> { System.out.println(RECORDING_OUTPUT); });
    joystick4.btn_12.whenPressed(() -> { System.out.println(PNEUMATICS_RECORDING); });

    joystick4.btn_7.whenPressed(() -> { LIMELIGHT_OVERRIDE = true; });
  }
  
  public Command getLimelightCommand() {
    return m_limelightCommand;
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
