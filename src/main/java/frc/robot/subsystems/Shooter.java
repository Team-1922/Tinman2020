/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * shhoot shoot bang bagn
 */
public class Shooter extends SubsystemBase {
    private WPI_TalonFX shooterLeft = new WPI_TalonFX(Constants.shooterLeft);
    private WPI_TalonFX shooterRight = new WPI_TalonFX(Constants.shooterRight);
    
    private Solenoid hoodSolenoid;
    // private WPI_TalonFX shooterLeft = new WPI_TalonFX(Constants.shooterLeft);
    // private WPI_TalonFX shooterRight = new WPI_TalonFX(Constants.shooterRight);

    public Shooter() {
        super();
        shooterRight.set(ControlMode.Follower, shooterLeft.getDeviceID());
        shooterLeft.setInverted(false);
        shooterRight.setInverted(true);
        hoodSolenoid = new Solenoid(Constants.hood);
    }

    public void shoot(double speed) {
        shooterLeft.set(speed);
        // shooterRight.set(speed);

    }

    public void setVelocity(double speed) {
        shooterLeft.set(ControlMode.Velocity, speed);
    }

    public void hoodUp() {
        hoodSolenoid.set(true);
    }

    public void hoodDown() {
        hoodSolenoid.set(false);

    }
    public void toggleHood(){
        hoodSolenoid.set(!hoodSolenoid.get());
    }
}
