/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class TankDriveCommand extends CommandBase {
  private DriveTrain m_subsystem;
  private Joystick m_joystickRight;
  private Joystick m_joystickLeft;

  /**
   * it do the drive thign
   */
  public TankDriveCommand(DriveTrain subsystem, Joystick joystickRight, Joystick joystickLeft) {
    m_subsystem = subsystem;
    m_joystickLeft = joystickLeft;
    m_joystickRight = joystickRight;
    addRequirements(m_subsystem);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_subsystem.setLimelightMode(1);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_subsystem.drive(-m_joystickLeft.getY(), -m_joystickRight.getY());

    // SmartDashboard.putNumber("LeftEncoder", m_driveTrain.getLeftEncoder());
    // SmartDashboard.putNumber("RightEncoder", m_driveTrain.getRightEncoder());
    // m_driveTrain.drive(-m_joystickLeft.getThrottle(), -m_joystickRight.getThrottle());
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
