/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autocommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class AutoTurn extends CommandBase {
  private DriveTrain m_driveTrain;
  private double m_angle;
  double error = 0;
  double errorPrior = 0;
  double initAngle = 0;
  double p = .006;
  double d = 0.0001;
  double derivative;
  boolean isNegative = false;

  /**
   * Creates a new AutoTurn.
   */
  public AutoTurn(DriveTrain driveTrain, double angle) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_driveTrain = driveTrain;
    m_angle = angle;
    addRequirements(m_driveTrain);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    initAngle = m_driveTrain.getAngle();
    if (m_angle < 0) {
      isNegative = true;
    } else {
      isNegative = false;
    }

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    error = initAngle - (m_driveTrain.getAngle()) + m_angle;
    derivative = (error - errorPrior) / .02;
    double responce = p * error + (d * derivative);
    if (responce <= 0.25 && isNegative == false) {
      responce = 0.25;
    } else if (responce >= 0.25 && isNegative == true) {
      responce = -0.25;
    }
    m_driveTrain.drive(responce, -responce);
    errorPrior = error;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    if (isNegative) {
      return (initAngle + m_angle + 5) >= m_driveTrain.getAngle();
      
    } else {
      return (initAngle + m_angle - 5) <= m_driveTrain.getAngle();
    }
    // return false;
  }
}
