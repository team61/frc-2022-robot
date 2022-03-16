package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public final class Playback {
  public static double[][]
speeds = new double[][]{new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.07243401759530792,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.07243401759530792,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{-0.14486803519061583,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{-0.277663734115347,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{-0.277663734115347,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{-1.2072336265884651,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{-1.6056207233626587,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{-1.6056207233626587,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{-1.8591397849462366,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{-2.402394916911046,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{-2.4305474095796678,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{-2.6158357771261,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{-2.7139296187683284,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{-2.997311827956989,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{-2.997311827956989,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{-3.2043988269794723,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{-3.2043988269794723,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{-3.520478983382209,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{-3.520478983382209,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{-3.8365591397849466,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{-4.06544477028348,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{-4.06544477028348,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{-4.595112414467253,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{-4.716617790811339,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{-4.716617790811339,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{-4.716617790811339,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{-4.716617790811339,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{-4.716617790811339,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{-4.716617790811339,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{-4.595112414467253,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{-4.595112414467253,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{-2.7504398826979473,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{-2.7504398826979473,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{-1.3917888563049854,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{-0.7967741935483871,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.07214076246334311,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.1563049853372434,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.2404692082111437,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.4087976539589443,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.4568914956011731,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.9618768328445748,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{1.094134897360704,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{1.3947214076246335,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{1.7193548387096773,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{2.1642228739002936,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{2.536950146627566,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{2.9457478005865108,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{2.9457478005865108,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{2.9457478005865108,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{2.8922287390029324,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{3.0901759530791786,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{3.2881231671554256,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{3.8379765395894427,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{4.05791788856305,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{4.05791788856305,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{4.2998533724340176,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{4.2998533724340176,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{4.2998533724340176,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{4.2998533724340176,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{4.2998533724340176,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{4.2998533724340176,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{4.2998533724340176,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{4.2998533724340176,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{4.2998533724340176,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{4.2998533724340176,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{4.2998533724340176,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{3.5080645161290325,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{3.398093841642229,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{2.980205278592375,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{2.782258064516129,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{2.1444281524926687,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{1.5725806451612903,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.37390029325513197,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0},};

  public static boolean run(DriveTrain drivetrain, IntakeSubsystem intake, ShooterSubsystem shooter, int index, Command lc) {
    if (index >= speeds.length) return false;

    if (speeds[index][7] == 1.0) {
      lc.schedule();
      return true;
    }
    
    drivetrain.driveVolts(speeds[index][0], speeds[index][1]);

    if (speeds[index][5] == 1.0) {
      intake.setSpeed1(-0.05);
    } else {
      intake.setVolts1(speeds[index][2]);
    }
    
    if (speeds[index][5] == 1.0 && speeds[index][6] == 1.0) {
      intake.setSpeed2(-0.05);
    } else {
      intake.setVolts2(speeds[index][3]);
    }

    shooter.setVolts(speeds[index][4]);

    return true;
  }
}
