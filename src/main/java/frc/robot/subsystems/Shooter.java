/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * Add your docs here.
 */
public class Shooter extends SubsystemBase {
    private WPI_TalonSRX shooterLeft = new WPI_TalonSRX(Constants.shooterLeft);
    private WPI_TalonSRX shooterRight = new WPI_TalonSRX(Constants.shooterRight);

    public Shooter(){
        super();
        shooterRight.set(ControlMode.Follower, shooterLeft.getDeviceID());
        shooterLeft.setInverted(false);
        shooterRight.setInverted(true);
    }

    public void shoot(double speed){
        shooterLeft.set(speed);
        shooterRight.set(speed);

    }
}
