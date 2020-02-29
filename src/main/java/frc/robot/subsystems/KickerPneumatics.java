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
 * Add your docs here.
 */
public class KickerPneumatics extends SubsystemBase {
    private Solenoid m_kicker;
    public KickerPneumatics() {
        super();
        m_kicker = new Solenoid(Constants.kickerSolenoid);
    }

    public void kick(boolean state){
        m_kicker.set(state);
    }
}
