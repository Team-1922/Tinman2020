/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autocommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.Constants;

public class DriveForward extends CommandBase {
  private DriveTrain m_driveTrain;
 private double m_distance;
 private double m_encoderStartValue;
  /**
   * Creates a new DriveForward.
   */
  public DriveForward(DriveTrain driveTrain, double distance) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_driveTrain = driveTrain;
    addRequirements(m_driveTrain);
    m_distance = distance*Constants.encoderInchConversion ;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_encoderStartValue = m_driveTrain.getLeftEncoder();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_driveTrain.drive(.25, .25, false);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveTrain.drive(0, 0, false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double leftEncoder = m_driveTrain.getLeftEncoder();
    if (m_distance <= leftEncoder - m_encoderStartValue){
      return true;
    }
    return false;
  }
}
