/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.subsystems.*;

public class TankDriveCommand extends CommandBase {
  private DriveTrain m_driveTrain;
  private Joystick m_joystickRight;
  private Joystick m_joystickLeft;

  /**
   * Creates a new TankDriveCommand.
   */
  public TankDriveCommand(DriveTrain driveTrain, Joystick joystickRight, Joystick joystickLeft) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_driveTrain = driveTrain;
    m_joystickLeft = joystickLeft;
    m_joystickRight = joystickRight;
    addRequirements(m_driveTrain);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_driveTrain.drive(-m_joystickLeft.getY(), -m_joystickRight.getY());
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
