/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autocommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LinearTransfer;

public class AutoLinear extends CommandBase {
  private LinearTransfer m_subsystem;
  private Timer m_timer;
  private double m_speed, m_time = 0;

  /**
   * Creates a new AutoLinear.
   */
  public AutoLinear(LinearTransfer subsysytem, double speed, double time) {
    m_subsystem = subsysytem;
    m_speed = speed;
    m_time = time;

    addRequirements(m_subsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_subsystem.drive(m_speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_timer.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_timer.get() > m_time;
  }
}
