/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autocommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveStraightAuto extends CommandBase {
  private DriveTrain m_driveTrain;
  private double m_speed;
  private Timer m_timer;
  private double m_time;
  double error = 0;
  double errorPrior = 0;
  double initAngle = 0;

  /**
   * Takes the current angle through the gyroscope, and keep the bot poining in that direction
   */
  public DriveStraightAuto(DriveTrain driveTrain, double speed, double time) {
    m_driveTrain = driveTrain;
    m_speed = speed;
    m_timer = new Timer();
    m_time = time;
    addRequirements(m_driveTrain);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_timer.start();
    initAngle = m_driveTrain.getAngle();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double p = .02;
    double d = 0.0001;
    double derivative;

    error = initAngle - m_driveTrain.getAngle();
    derivative = (error - errorPrior) / .02;
    double responce = p * error + (d * derivative);
    m_driveTrain.drive(m_speed + responce, m_speed - responce, m_driveTrain.getFLip());
    errorPrior = error;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_timer.get() > m_time;
  }
}
