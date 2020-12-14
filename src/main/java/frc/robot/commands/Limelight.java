/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class Limelight extends CommandBase {
  private final DriveTrain m_DriveTrain;
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");
  private double center, derivative, errorPrior;
  private double p = .15;
  private double d = 0.03;
  private Joystick m_leftStick, m_rightStick;

  /**
   * Creates a new Limelight.
   */
  public Limelight(final DriveTrain subsystem, Joystick joystickLeft, Joystick joystickRight) {
    m_DriveTrain = subsystem;
    m_leftStick = joystickLeft;
    m_rightStick = joystickRight;
    addRequirements(m_DriveTrain);

    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    double x = tx.getDouble(0.0);
    // double y = ty.getDouble(0.0);
    // double area = ta.getDouble(0.0);

    final double error = x;
    derivative = (error - errorPrior);
    double responce = p * error + (d * derivative);


    if (responce < -.5) {
      responce = -.5;
    } else if (responce > .5) {
      responce = .5;
    }

    m_DriveTrain.drive(-responce + m_leftStick.getY(), responce + m_rightStick.getY());
    SmartDashboard.putNumber("error", error);
    SmartDashboard.putNumber("center", center);
    SmartDashboard.putNumber("responce", responce);
    errorPrior = error;

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
