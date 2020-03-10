/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autogroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.autocommands.AutoLinear;
import frc.robot.commands.TriggerKicker;
import frc.robot.subsystems.KickerPneumatics;
import frc.robot.subsystems.LinearTransfer;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class KickAfterDelay extends SequentialCommandGroup {
  private KickerPneumatics m_kicker;
  private LinearTransfer m_linearTransfer;
  private Shooter m_shooter;

  /**
   * Creates a new KickAfterDelay.
   */
  public KickAfterDelay(KickerPneumatics kickerPneumatics, Shooter shooter, LinearTransfer linearTransfer) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    m_kicker = kickerPneumatics;
    m_shooter = shooter;
    m_linearTransfer = linearTransfer;
    addCommands(
        //
        new TriggerKicker(m_kicker, true),
        //
        new AutoLinear(m_linearTransfer, m_shooter, -.5, 3.0));
  }
}
