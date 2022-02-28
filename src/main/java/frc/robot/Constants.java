// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final int joystickport1 = 0;
    public static final int joystickport2 = 1;
    public static final int joystickport3 = 2;
    public static final int joystickport4 = 3;

    public static final String UP = "up";
    public static final String DOWN = "down";
    public static final String IN = "in";
    public static final String OUT = "out";
    public static final String SLOW = "slow";
    public static final String STOP = "stop";
    public static final String RESET = "reset";

    public static final double INTAKE_1_SPEED = 1.0;
    public static final double INTAKE_2_SPEED = 0.4;
    public static final double SHOOTER_SPEED = 1.0;

    public static final int LEDStripPort = 0;
    public static final int LEDStripLength = 68;
}
