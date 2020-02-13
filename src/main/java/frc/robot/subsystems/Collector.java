/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * Collector Subsystem
 */
public class Collector extends SubsystemBase {
  private WPI_TalonSRX pickUp = new WPI_TalonSRX(Constants.pickUp);
  // private WPI_TalonSRX pickUpTransfer = new WPI_TalonSRX(Constants.pickUpTransfer);
  // private WPI_TalonSRX linearLoaderT = new WPI_TalonSRX(Constants.linearLoaderT);
  // private WPI_TalonSRX linearLoaderB = new WPI_TalonSRX(Constants.linearLoaderB);
  private Solenoid CollectorSolenoid;
  private double m_MaxSpeed = .5;

  public Collector() {
    super();
    CollectorSolenoid = new Solenoid(0);
  }

  public void drive(double speed) {
    double clampedSpeed = ClampSpeed(speed);
    pickUp.set (clampedSpeed);
  }

  public void CollectorUp(){
    CollectorSolenoid.set(true);
  }
  public void CollectorDown(){
    CollectorSolenoid.set(false);
  }
  public void CollectorToggle(){
    CollectorSolenoid.set(!CollectorSolenoid.get());
  }
  private double ClampSpeed(double speed){
    if(speed>0){
      if(speed>m_MaxSpeed){
        return m_MaxSpeed;
      }
      else {
        return speed;
      }
    }
    else {
      if(speed<-m_MaxSpeed){
        return -m_MaxSpeed; 
      }
      else {
        return speed;
      }
    }
  
    
  }

}
