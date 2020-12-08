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
public class LinearTransfer extends SubsystemBase {
  private WPI_TalonSRX linearTransfer = new WPI_TalonSRX(Constants.linearTransfer);

  public LinearTransfer() {
    super();
  }

  public void drive(double speed) {
    linearTransfer.set(speed);
  }

  public int getEncoders() {
    return linearTransfer.getSensorCollection().getQuadraturePosition();
  }

}
