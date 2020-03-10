/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autocommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

public class DriveDistance extends CommandBase {
  private DriveTrain m_subsystem;
  double m_distance;
  double error = 0;
  double errorPrior = 0;
  double initAngle = 0;
  double p = .02;
  double d = 0.0001;
  double derivative;
  double initEncoderValue = 0;
  double m_speed = 0;

  /**
   * Creates a new DriveDistance.
   */
  public DriveDistance(DriveTrain subsystem, double distance, double speed) {
    m_subsystem = subsystem;
    m_distance = distance;
    m_speed = speed;
    addRequirements(m_subsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    initAngle = m_subsystem.getAngle();
    initEncoderValue = m_subsystem.getLeftEncoder();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    error = initAngle - m_subsystem.getAngle();

    derivative = (error - errorPrior) / .02;
    double responce = p * error + (d * derivative);

    m_subsystem.drive(m_speed - responce, m_speed + responce);

    errorPrior = error;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    double currentValue = m_subsystem.getLeftEncoder() - initEncoderValue;
    if (m_speed >= 0) {
      // if (currentValue >= Constants.ticksPerFoot * m_distance) {
      // return true;
      // } else {
      // return false;
      // }
      return currentValue >= Constants.ticksPerFoot * m_distance;

    } else {
      // Gabe >>>> handle m_speed < 0
      return currentValue <= Constants.ticksPerFoot * m_distance;
    }
  }
}
