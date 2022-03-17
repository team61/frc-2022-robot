// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.Playback;
import frc.robot.commands.AutonomousCommand;
import lib.components.LogitechJoystick;
import static frc.robot.Globals.*;
import static frc.robot.Constants.*;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private Command m_limelightCommand;
  private RobotContainer m_robotContainer;

  private ArrayList<double[]> speeds = new ArrayList<>();
  private boolean recording = false;
  private long teleopStartTime = 0;
  private boolean inAutonomous = false;
  int[][] colors = {
    new int[] {255, 0, 0},
    new int[] {255, 64, 0},
    new int[] {255, 255, 0},
    new int[] {0, 255, 0},
    new int[] {0, 0, 255},
    new int[] {255, 0, 255}
  };
  int[][] bvt = {
    new int[] {255, 0, 255},
    new int[] {255, 128, 0}
  };

  public Robot() {
    super(0.02);
  }

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
    m_limelightCommand = m_robotContainer.getLimelightCommand();
    m_robotContainer.piston1.stop();
    m_robotContainer.piston2.stop();

    addPeriodic(() -> {
      if (!inAutonomous) return;

      for (int i = 0; i < LEDStripLength; i++) {
        int index = i % colors.length;
        m_robotContainer.ledStrip.setRGB(i, colors[index][0], colors[index][1], colors[index][2]);
      }
    }, 0.2);
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

    // if (m_limelightCommand != null) {
    //   m_limelightCommand.schedule();
    // }
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
    m_robotContainer.ledStrip.turnOff();
    m_robotContainer.driveTrain.stop();
    m_robotContainer.intake.stop();
    m_robotContainer.shooter.stop();

    if (inAutonomous) inAutonomous = false;
    if (!recording) return;
    recording = false;

    String output = "speeds = new double[][]{";
    for (double[] speed : speeds) {
      output += "new double[]{" + speed[0] + "," + speed[1] + "," + speed[2] + "," + speed[3] + "," + speed[4] + "," + speed[5] + "," + speed[6] + "," + speed[7] + "},";
    }
    output += "};";
    RECORDING_OUTPUT = output;

    Playback.speeds = new double[speeds.size()][];
    for (int i = 0; i < speeds.size(); i++) {
      Playback.speeds[i] = speeds.get(i);
    }

    final String data = output;
    new Thread(() -> {
      try {
        File file = new File("/home/lvuser/recording.txt"); 
        file.createNewFile(); 
        FileOutputStream oFile = new FileOutputStream(file, false); 

        String content = data;
        oFile.write(content.getBytes()); 
        oFile.flush(); 
        oFile.close(); 
        System.out.println("Saved recording");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }).run();
  }

  @Override
  public void disabledPeriodic() {
    int total = 4;
    for (int i = 0; i < total; i++) {
      int index = (int)Math.floor(i / 2) % 2;
      m_robotContainer.ledStrip.setRGB(i, bvt[index][0], bvt[index][1], bvt[index][2]);
      index += total / 2;
      m_robotContainer.ledStrip.setRGB(i, bvt[index][0], bvt[index][1], bvt[index][2]);
    }
  }

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    m_robotContainer.driveTrain.stop();
    new AutonomousCommand(m_robotContainer.driveTrain, m_robotContainer.intake, m_robotContainer.shooter, m_limelightCommand).schedule();
    inAutonomous = true;
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    if (m_limelightCommand != null) {
      m_limelightCommand.schedule();
    }
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

    m_robotContainer.driveTrain.stop();
    m_robotContainer.intake.stop();
    m_robotContainer.shooter.stop();
    m_robotContainer.piston1.stop();
    m_robotContainer.piston2.stop();

    teleopStartTime = System.currentTimeMillis();

    if (m_robotContainer.joystick4.btn_7.get()) {
      speeds.clear();
      recording = true;
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    if (m_limelightCommand != null) {
      m_limelightCommand.schedule();
    }

    LogitechJoystick joystick1 = m_robotContainer.joystick1;
    LogitechJoystick joystick2 = m_robotContainer.joystick2;

    // Get the value and square but keep sign
    double speedPercentage1 = joystick1.getYAxis() * Math.abs(joystick1.getYAxis());
    double speedPercentage2 = joystick2.getYAxis() * Math.abs(joystick2.getYAxis());

    if (!m_robotContainer.joystick1.btn_2.get()) {
      m_robotContainer.driveTrain.drive(speedPercentage1 * MOTOR_COEFFICIENT, speedPercentage2 * MOTOR_COEFFICIENT);
    }

    long currentTimeMillis = System.currentTimeMillis();
    double secondsDiff = ((double)currentTimeMillis - (double)teleopStartTime) / 1000;
    if (secondsDiff < 75) {
      m_robotContainer.ledStrip.setStripRGB(0, 255, 0);
    } else if (secondsDiff < 105) {
      m_robotContainer.ledStrip.setStripRGB(255, 64, 0);
    } else if (secondsDiff < 120) {
      m_robotContainer.ledStrip.setStripRGB(255, 0, 0);
    } else/* if (secondsDiff < 135)*/ {
      if (Math.round(secondsDiff * 2) == Math.floor(secondsDiff * 2)) {
        m_robotContainer.ledStrip.setStripRGB(255, 0, 0);
      } else {
        m_robotContainer.ledStrip.turnOff();
      }
    }// else {
    //   if (currentTimeMillis % 2 == 1) {
    //     for (int i = 0; i < 4; i++) {
    //       if (i % 2 == 0) {
    //         m_robotContainer.ledStrip.setRGB(i, 255, 0, 255);
    //       } else {
    //         m_robotContainer.ledStrip.setRGB(i, 255, 128, 0);
    //       }
    //     }
    //   }
    // }

    // green until 1 min left
    // orange until 30s left
    // red until 15s left
    // blinking red till end

    speeds.add(new double[] {
      m_robotContainer.driveTrain.driveLeft.getVoltage1(), m_robotContainer.driveTrain.driveRight.getVoltage2(),
      m_robotContainer.intake.getVoltage1(), m_robotContainer.intake.getVoltage2(),
      m_robotContainer.shooter.shooter.getVoltage1(),
      m_robotContainer.sensor1.isTriggered() ? 1.0 : 0.0, m_robotContainer.sensor2.isTriggered() ? 1.0 : 0.0,
      joystick1.btn_2.get() ? 1.0 : 0.0
    });
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
    speeds.clear();
    recording = true;
    m_robotContainer.driveTrain.stop();
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

    boolean intakePressed = joystick1.getRawButton(1);
    boolean shooterPressed = joystick2.getRawButton(1);

    if (shooterPressed) {
      m_robotContainer.shooter.shoot();
    } else {
      m_robotContainer.shooter.stop();
    }

    if (intakePressed) {
      m_robotContainer.intake.intake();
    } else {
      m_robotContainer.intake.stop();
    }

    boolean stopped1 = false;

    if (m_robotContainer.sensor1.isTriggered()) {
      m_robotContainer.intake.setSpeed1(-0.05);
      stopped1 = true;
    }

    if (m_robotContainer.sensor2.isTriggered() && stopped1) {
      m_robotContainer.intake.setSpeed2(-0.05);
    }

    speeds.add(new double[] {
      m_robotContainer.driveTrain.driveLeft.getSpeed1(), m_robotContainer.driveTrain.driveRight.getSpeed1(),
      m_robotContainer.intake.getSpeed1(), m_robotContainer.intake.getSpeed2(),
      m_robotContainer.shooter.shooter.getSpeed1(),
      m_robotContainer.sensor1.isTriggered() ? 1.0 : 0.0, m_robotContainer.sensor2.isTriggered() ? 1.0 : 0.0
    });
  }
}
