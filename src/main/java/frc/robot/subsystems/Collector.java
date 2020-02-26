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
  private WPI_TalonSRX pickUp = new WPI_TalonSRX(Constants.collector);
  private WPI_TalonSRX linearIntake = new WPI_TalonSRX(Constants.linearIntake);
  private Solenoid CollectorSolenoid;
  private Solenoid CollectorSolenoid2;

  public Collector() {
    super();
    CollectorSolenoid = new Solenoid(Constants.collectorP);
    CollectorSolenoid2 = new Solenoid(Constants.collectorP2);
  }

  public void drive(double speed) {
    pickUp.set(speed);
    linearIntake.set(speed);
  }

  public void CollectorUp() {
    CollectorSolenoid.set(true);
    CollectorSolenoid2.set(true);
    // CollectorSolenoidRight.set(true);
  }

  public void CollectorDown() {
    CollectorSolenoid.set(false);
    CollectorSolenoid2.set(false);
    // CollectorSolenoidRight.set(false);
  }

  public void ToggleCollector() {
    CollectorSolenoid.set(! CollectorSolenoid.get());
    CollectorSolenoid2.set(! CollectorSolenoid2.get());
    // CollectorSolenoidRight.set(!CollectorSolenoidRight.get());
  }

}
