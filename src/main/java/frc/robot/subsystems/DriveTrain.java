/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
/**
 * Add your docs here.
 */
public class DriveTrain extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private WPI_TalonSRX frontLeft = new WPI_TalonSRX(Constants.frontLeft);
  private WPI_TalonSRX rearLeft = new WPI_TalonSRX(Constants.rearLeft);
  private WPI_TalonSRX frontRight = new WPI_TalonSRX(Constants.frontRight);
  private WPI_TalonSRX rearRight = new WPI_TalonSRX(Constants.rearRight);

  public DriveTrain(){
    super();
    rearLeft.set(ControlMode.Follower, frontLeft.getDeviceID());
    rearRight.set(ControlMode.Follower, frontRight.getDeviceID());
    frontRight.setInverted(true);
    rearRight.setInverted(InvertType.FollowMaster);
    frontLeft.setInverted(false);
    rearLeft.setInverted(InvertType.FollowMaster);
    
  }

  
  public void drive(double left, double right)
  {
    frontLeft.set(left);
    frontRight.set(right);
  }
}
