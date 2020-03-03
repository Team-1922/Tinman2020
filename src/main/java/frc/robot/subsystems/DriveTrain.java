/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * manages the speed of the wheels
 */
public class DriveTrain extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  // objects
  private WPI_TalonSRX frontLeft = new WPI_TalonSRX(Constants.frontLeft);
  private WPI_TalonSRX rearLeft = new WPI_TalonSRX(Constants.rearLeft);
  private WPI_TalonSRX frontRight = new WPI_TalonSRX(Constants.frontRight);
  private WPI_TalonSRX rearRight = new WPI_TalonSRX(Constants.rearRight);
  private AHRS ahrs = new AHRS(SPI.Port.kMXP);
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");
  private double x = tx.getDouble(0.0);
  private double y = ty.getDouble(0.0);
  private double area = ta.getDouble(0.0);
  private boolean isFlipped = false;

  public DriveTrain() {
    super();

    // configuring talons
    rearLeft.set(ControlMode.Follower, frontLeft.getDeviceID());
    rearRight.set(ControlMode.Follower, frontRight.getDeviceID());

    frontRight.setInverted(false);
    rearRight.setInverted(InvertType.FollowMaster);
    frontLeft.setInverted(true);
    rearLeft.setInverted(InvertType.FollowMaster);
  }

  public void drive(double left, double right) {
    if (getFLip()) {
      frontLeft.set(-right);
      frontRight.set(-left);
    } else {
      frontLeft.set(left);
      frontRight.set(right);
    }

    // SmartDashboard.putNumber("gyro debug", ahrs.getAngle());

  }

  public double getAngle() {
    return ahrs.getAngle();
  }

  public void refreshLimelight() {
    x = tx.getDouble(0.0);
    y = ty.getDouble(0.0);
    area = ta.getDouble(0.0);

    // post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightArea", area);
  }

  public void setFlip(boolean f) {
    isFlipped = f;
  }

  public boolean getFLip() {
    return isFlipped;
  }

  public void toggleFlip() {
    isFlipped = !isFlipped;
  }

  public double getLeftEncoder() {
    return frontLeft.getSensorCollection().getQuadraturePosition();
  }

  public double getRightEncoder() {
    return frontRight.getSensorCollection().getQuadraturePosition();
  }
/**
 * @param state State of the light
 *
 */
  public void setLimelightLight(int state) {
    // 1 off
    // 3 on
    table.getEntry("ledMode").setNumber(state);
  }

  public void setLimelightMode(int state) {
    table.getEntry("camMode").setNumber(state);
  }

}
