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
  private static final double minimumResponse = 0.35;

  private DriveTrain m_subsystem;
  private double m_angle;
  double error = 0;
  double errorPrior = 0;
  double initAngle = 0;
  double p = .006;
  double d = 0.0001;
  double derivative;
  boolean isPositive = true;

  /**
   * Turns the robot an X amount of degrees
   */
  public AutoTurn(DriveTrain subsystem, double angle) {
    m_subsystem = subsystem;
    m_angle = angle;
    addRequirements(m_subsystem);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    initAngle = m_subsystem.getAngle();
    if (m_angle < 0) {
      isPositive = false;
    } else {
      isPositive = true;
    }

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    error = initAngle - (m_subsystem.getAngle() + m_angle);
    derivative = (error - errorPrior) / .02;
    double responce = p * error + (d * derivative);
    if (responce <= minimumResponse && isPositive == true) {
      responce = minimumResponse;
    } else if (responce >= minimumResponse && isPositive == false) {
      responce = -minimumResponse;
    }
    m_subsystem.driveUnflipped(responce, -responce);
    errorPrior = error;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    if (isPositive) {
      return (initAngle + m_angle + 5) >= m_subsystem.getAngle();
      
    } else {
      return (initAngle + m_angle - 5) <= m_subsystem.getAngle();
    }
    
  }
}
