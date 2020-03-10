/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autogroups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Constants;
import frc.robot.autocommands.AutoShoot;
import frc.robot.subsystems.KickerPneumatics;
import frc.robot.subsystems.LinearTransfer;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class shootPara extends ParallelCommandGroup {
  private Shooter m_shooter;
  private KickerPneumatics m_kicker;
  private LinearTransfer m_linearTransfer;

  /**
   * Creates a new shootPara.
   */
  public shootPara(Shooter shooter, KickerPneumatics kickerPneumatics,
      LinearTransfer linearTransfer) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());super();
    m_shooter = shooter;
    m_kicker = kickerPneumatics;
    m_linearTransfer = linearTransfer;
    addCommands(
        //
        new AutoShoot(m_shooter, Constants.autoSpeed, 6.0),
        // new AutoShoot(m_shooter, Constants.shooterSpeedMid, 6.0),
        //
        new KickAfterDelay(m_kicker, m_shooter, m_linearTransfer)
    //
    );

  }
}
