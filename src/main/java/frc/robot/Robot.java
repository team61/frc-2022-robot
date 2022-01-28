// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.Autonomous;
import frc.robot.commands.DriveCommand;
import lib.components.LogitechJoystick;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  private ArrayList<double[]> speeds = new ArrayList<>();
  private int speedIndex = 0;
  private boolean recording = false;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
    if (!recording) return;

    String output = "speeds = new double[][]{";
    for (double[] speed : speeds) {
      output += "new double[]{" + speed[0] + "," + speed[1] + "},";
    }
    output += "};";

    recording = false;

    System.out.println("\n");
    System.out.print(output);
    System.out.println("\n");
  }

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    /*
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
    */

    speedIndex = 0;
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    Autonomous.run(m_robotContainer.driveTrain, speedIndex);
    speedIndex++;
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }

    m_robotContainer.piston1.release();
    m_robotContainer.piston1.retract();
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    LogitechJoystick joystick1 = m_robotContainer.joystick1;
    LogitechJoystick joystick2 = m_robotContainer.joystick2;

    // Get the value and square but keep sign
    double speedPercentage1 = joystick1.getYAxis() * Math.abs(joystick1.getYAxis());
    double speedPercentage2 = joystick2.getYAxis() * Math.abs(joystick2.getYAxis());

    new DriveCommand(m_robotContainer.driveTrain, speedPercentage1, speedPercentage2).schedule();
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
    speeds.clear();
    recording = true;
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
    LogitechJoystick joystick1 = m_robotContainer.joystick1;
    LogitechJoystick joystick2 = m_robotContainer.joystick2;

    // Get the value and square but keep sign
    double speedPercentage1 = joystick1.getYAxis() * Math.abs(joystick1.getYAxis());
    double speedPercentage2 = joystick2.getYAxis() * Math.abs(joystick2.getYAxis());

    m_robotContainer.driveTrain.drive(speedPercentage1, speedPercentage2);

    speeds.add(new double[] {speedPercentage1, speedPercentage2});
  }
}
