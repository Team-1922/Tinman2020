/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.DriveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class SetLimeLight extends InstantCommand {
  private DriveTrain m_subsystem;
  private boolean m_light;

  public SetLimeLight(DriveTrain subsystem, boolean light) {
    m_subsystem = subsystem;
    addRequirements(m_subsystem);
    m_light = light;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
    if (m_light){
      m_subsystem.setLimelightLight(3);
      
    } else {
      m_subsystem.setLimelightLight(1);
    }
    
  }
}
