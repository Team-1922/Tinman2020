/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.autocommands.AutoTurn;
import frc.robot.commands.CollectorDown;
import frc.robot.commands.CollectorUp;
import frc.robot.commands.DriveStraight;
import frc.robot.commands.FlipCommand;
import frc.robot.commands.Limelight;
import frc.robot.commands.ShootingCommand;
import frc.robot.commands.TankDriveCommand;
import frc.robot.commands.ToggleHoodCommand;
import frc.robot.commands.TransferCommand;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.LinearTransfer;
import frc.robot.subsystems.Shooter;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
        // The robot's subsystems and commands are defined here...
        private final DriveTrain m_driveTrain = new DriveTrain();
        private final Shooter m_Shooter = new Shooter();
        private final LinearTransfer m_lTransfer = new LinearTransfer();
        private final Collector m_Collector = new Collector();

        private final Joystick m_joystickLeft = new Joystick(1);
        private final Joystick m_joystickRight = new Joystick(0);

        private final XboxController m_XBoxController = new XboxController(2);

        private final TankDriveCommand m_TankDrive = new TankDriveCommand(m_driveTrain, m_joystickLeft,
                        m_joystickRight);
        private final TransferCommand m_TransferPassive = new TransferCommand(m_lTransfer, 0);
        private final CollectorUp m_CollectorUp = new CollectorUp(m_Collector);
        private final ShootingCommand m_ShootingCommand = new ShootingCommand(m_Shooter, 0);
        // private final CollectorDown m_CollectorDown =
        // private final DriveStraightAuto m_autoCommand = new
        // DriveStraightAuto(m_driveTrain, .2, 2);
        private final AutoTurn m_autoCommand = new AutoTurn(m_driveTrain, 90);
        // private final DefaultAuto m_autoCommand = new DefaultAuto(m_driveTrain);

        /**
         * The container for the robot. Contains subsystems, OI devices, and commands.
         */
        public RobotContainer() {
                // Configure the button bindings
                configureButtonBindings();
                m_driveTrain.setDefaultCommand(m_TankDrive);
                m_lTransfer.setDefaultCommand(m_TransferPassive);
                m_Collector.setDefaultCommand(m_CollectorUp);
                m_Shooter.setDefaultCommand(m_ShootingCommand);
        }

        /**
         * Use this method to define your button->command mappings. Buttons can be
         * created by instantiating a {@link GenericHID} or one of its subclasses
         * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
         * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
         */
        private void configureButtonBindings() {
                new JoystickButton(m_joystickLeft, 1)
                                //
                                .whileHeld(new DriveStraight(m_driveTrain, m_joystickLeft, m_joystickRight));

                // new JoystickButton(m_joystickLeft, 3)
                // //
                // .whenPressed(new AutoTurn(m_driveTrain, -90));

                // new JoystickButton(m_joystickLeft, 4)
                // //
                // .whenPressed(new AutoTurn(m_driveTrain, 90));

                new JoystickButton(m_XBoxController, 2)
                                //
                                .toggleWhenPressed(new ShootingCommand(m_Shooter, -.5));

                new JoystickButton(m_XBoxController, 3)
                                //
                                .toggleWhenPressed(new CollectorDown(m_Collector, .5));

                new JoystickButton(m_XBoxController, 5) // left bumper
                                //
                                .whileHeld(new TransferCommand(m_lTransfer, .5));

                new JoystickButton(m_XBoxController, 6) // right bumper
                                //
                                .whileHeld(new TransferCommand(m_lTransfer, -.4));

                new JoystickButton(m_XBoxController, 4) // right bumper
                                //
                                .whenPressed(new ToggleHoodCommand(m_Shooter));
               
                                new JoystickButton(m_joystickRight, 1)
                                //
                                .whileHeld(new Limelight(m_driveTrain, m_joystickLeft, m_joystickRight));
                new JoystickButton(m_joystickRight, 4)
                                //
                                .whenPressed(new FlipCommand(m_driveTrain));

                
        }

        /**
         * Use this to pass the autonomous command to the main {@link Robot} class.
         *
         * @return the command to run in autonomous
         */
        public Command getAutonomousCommand() {
                // An ExampleCommand will run in autonomous
                return m_autoCommand;
        }

}
