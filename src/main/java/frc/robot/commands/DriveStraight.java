/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveStraight extends CommandBase {
  double error = 0;
  double errorPrior = 0;
  double initAngle = 0;
  private DriveTrain m_subsystem;
  private Joystick m_joystickRight;
  private Joystick m_joystickLeft;
  double p = .02;
  double d = 0.0001;
  double derivative;

  /**
   * Hold the button and it doesn't rotate
   */
  public DriveStraight(DriveTrain subsystem, Joystick joystickRight, Joystick joystickLeft) {
    m_subsystem = subsystem;
    m_joystickLeft = joystickLeft;
    m_joystickRight = joystickRight;
    addRequirements(m_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    initAngle = m_subsystem.getAngle();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    if (m_subsystem.getFLip()) {
      error = initAngle - (m_subsystem.getAngle() + 180);
    } else {
      error = initAngle - m_subsystem.getAngle();
    }

    derivative = (error - errorPrior) / .02;
    double responce = p * error + (d * derivative);
    double RawY = (m_joystickLeft.getY() + m_joystickRight.getY()) / 2;
    
    if (m_subsystem.getFLip()) {
      m_subsystem.drive(RawY - responce, RawY + responce);
    } else {
      m_subsystem.drive(-RawY - responce, -RawY + responce);
    }

    errorPrior = error;

    SmartDashboard.putNumber("DS error", error);
    SmartDashboard.putNumber("DS responce", responce);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
