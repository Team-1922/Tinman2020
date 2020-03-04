/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * Add your docs here.
 */
public class BeltBar extends SubsystemBase {
    private WPI_TalonSRX beltBarLeft = new WPI_TalonSRX(Constants.beltBarLeft);
    private WPI_TalonSRX beltBarRight = new WPI_TalonSRX(Constants.beltBarRight);

    public BeltBar() {
        super();
    }

    public void drive(double speed) {
        beltBarLeft.set(-speed);
        beltBarRight.set(-speed);
    }

    public void vibe() {
        // you already vibin
        // wowow
    }
}
