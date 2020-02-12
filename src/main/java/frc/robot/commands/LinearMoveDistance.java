/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LinearTransfer;

public class LinearMoveDistance extends CommandBase {
  private LinearTransfer m_subsystem;
  private int m_distance;
  private int startValue = 0;
  private double m_speed;
  /**
   * Creates a new LinearMoveDistance.
   */
  public LinearMoveDistance(LinearTransfer subsystem, int distance, double speed) {
    m_subsystem = subsystem;
    m_distance = distance;
    m_speed = speed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startValue = m_subsystem.getEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_subsystem.drive(m_speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (m_subsystem.getEncoders() >= m_distance + startValue);
  }
}
