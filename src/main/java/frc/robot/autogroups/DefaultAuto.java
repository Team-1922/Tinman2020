/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autogroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.autocommands.AutoStop;
import frc.robot.autocommands.AutoTurn;
import frc.robot.autocommands.Delay;
import frc.robot.autocommands.DriveStraightAuto;
import frc.robot.subsystems.DriveTrain;

/**
 * Add your docs here.
 */
public class DefaultAuto extends SequentialCommandGroup {
    private DriveTrain m_driveTrain;
    public DefaultAuto(DriveTrain driveTrain) {
        m_driveTrain = driveTrain;
        addCommands(
            new DriveStraightAuto(m_driveTrain, .2, 2),

            new AutoTurn(m_driveTrain, 90),

            new DriveStraightAuto(m_driveTrain, .2, 2),
            
            new AutoStop(m_driveTrain),

            new Delay(1),

            new DriveStraightAuto(m_driveTrain, -.2, 2),

            new AutoTurn(m_driveTrain, -90),

            new DriveStraightAuto(m_driveTrain, -.2, 2)

        );
    }
}
