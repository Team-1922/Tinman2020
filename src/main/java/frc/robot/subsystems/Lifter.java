/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * Lifter Subsystem
 */
public class Lifter extends SubsystemBase {
  private Solenoid m_lifterSolenoid;

  public Lifter() {
    super();
    m_lifterSolenoid = new Solenoid(Constants.lifter);
  }

  public void lifterUp() {
     m_lifterSolenoid.set(true);
  }

  public void lifterDown() {
     m_lifterSolenoid.set(false);
  }

  public void lifterToggle() {
  m_lifterSolenoid.set(!m_lifterSolenoid.get());
 }

}
