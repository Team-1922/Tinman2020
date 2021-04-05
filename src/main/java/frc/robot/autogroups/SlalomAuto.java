/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autogroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.autocommands.DriveForward;
import frc.robot.subsystems.DriveTrain;

/**
 * Creates a short set of auto commands to run in a sequence
 */
public class SlalomAuto extends SequentialCommandGroup {
    private DriveTrain m_driveTrain;

    public SlalomAuto(DriveTrain driveTrain) {
        m_driveTrain = driveTrain;
        /*
        Straight 25 in
        45 deg left turn
        Straight 95 in 
        45 deg right turn
        Straight 110 in
        45 deg right Turn
        Straight 100 in
        90 deg left
        Straight 70 in
        90 deg left turn
        Straight 51 in
        90 deg left turn
        Straight 117 in
        45 deg right turn
        straight 108 in
        45 degree right turn
        Straight 85 in
        45 degree left turn
        Straight 25 in
        */
        double InchCalculation = 4096/6;

        addCommands(new DriveForward(m_driveTrain, 25));
    }
}
