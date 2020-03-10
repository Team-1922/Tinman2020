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
import frc.robot.subsystems.Shooter;

public class AutoLinear extends CommandBase {
  private LinearTransfer m_subsystem;
  private Shooter m_shooter;
  private Timer m_timer;
  private double m_speed, m_time = 0;

  /**
   * Creates a new AutoLinear.
   */
  public AutoLinear(LinearTransfer subsysytem, Shooter shooter, double speed, double time) {
    m_subsystem = subsysytem;
    m_shooter = shooter;
    m_speed = speed;
    m_time = time;
    m_timer = new Timer();

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
    if (m_shooter.getVelocity() >= m_shooter.getTargetSpeed()*.9) {
      m_subsystem.drive(m_speed);
    }

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
