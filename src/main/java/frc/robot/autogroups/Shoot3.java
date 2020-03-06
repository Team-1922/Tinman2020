/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autogroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.autocommands.DriveStraightAuto;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.KickerPneumatics;
import frc.robot.subsystems.LinearTransfer;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class Shoot3 extends SequentialCommandGroup {
  private DriveTrain m_driveTrain;
  private Shooter m_shooter;
  private KickerPneumatics m_kicker;
  private LinearTransfer m_linearTransfer;

  /**
   * Creates a new Shoot3.
   */
  public Shoot3(DriveTrain driveTrain, Shooter shooter, KickerPneumatics kickerPneumatics,
      LinearTransfer linearTransfer) {
    m_driveTrain = driveTrain;
    m_shooter = shooter;
    m_kicker = kickerPneumatics;
    m_linearTransfer = linearTransfer;
    addCommands(
        //
        new shootPara(m_shooter, m_kicker, m_linearTransfer),
        //
        new DriveStraightAuto(m_driveTrain, -.2, -.2));

  }

}