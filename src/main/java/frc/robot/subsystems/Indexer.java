/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * Indexer Subsystem
 */
public class Indexer extends SubsystemBase {
  private WPI_TalonSRX m_leftIndexer = new WPI_TalonSRX(Constants.leftIndexer);
  private WPI_TalonSRX m_rightIndexer = new WPI_TalonSRX(Constants.rightIndexer);

  public Indexer() {
    super();
    m_rightIndexer.setInverted(false);

    m_leftIndexer.set(ControlMode.Follower, m_rightIndexer.getDeviceID());
    m_leftIndexer.setInverted(InvertType.OpposeMaster);
  }

  public void drive(double speed) {
    m_rightIndexer.set(speed);
  }

}
