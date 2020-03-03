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
import frc.robot.commands.BeltBarPassive;
import frc.robot.commands.CollectorDown;
import frc.robot.commands.CollectorPassive;
import frc.robot.commands.CollectorUp;
import frc.robot.commands.DriveStraight;
import frc.robot.commands.FlipCommand;
import frc.robot.commands.KickerPassive;
import frc.robot.commands.Limelight;
import frc.robot.commands.LinearPassive;
import frc.robot.commands.ShootVelocity;
import frc.robot.commands.ShootingCommand;
import frc.robot.commands.TankDriveCommand;
import frc.robot.commands.ToggleHood;
import frc.robot.commands.TransferCommand;
import frc.robot.commands.TriggerKicker;
import frc.robot.subsystems.BeltBar;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.KickerPneumatics;
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

        // subsytems
        private final DriveTrain m_driveTrain = new DriveTrain();
        private final Shooter m_Shooter = new Shooter();
        private final LinearTransfer m_lTransfer = new LinearTransfer();
        private final Collector m_Collector = new Collector();
        private final BeltBar m_BeltBar = new BeltBar();
        private final KickerPneumatics m_kickerPneumatics = new KickerPneumatics();

        // joysticks
        private final Joystick m_joystickLeft = new Joystick(1);
        private final Joystick m_joystickRight = new Joystick(0);

        // controller
        private final XboxController m_XBoxController = new XboxController(2);

        // pre-generated commands
        private final TankDriveCommand m_TankDrive = new TankDriveCommand(m_driveTrain, m_joystickLeft,
                        m_joystickRight);
        private final ShootingCommand m_ShootStop = new ShootingCommand(m_Shooter, 0);
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
                // default commands
                configureButtonBindings();
                m_driveTrain.setDefaultCommand(m_TankDrive);
                m_lTransfer.setDefaultCommand(new LinearPassive(m_lTransfer, m_XBoxController));
                m_Collector.setDefaultCommand(new CollectorPassive(m_Collector, m_XBoxController));
                m_BeltBar.setDefaultCommand(new BeltBarPassive(m_BeltBar, m_XBoxController));
                m_Shooter.setDefaultCommand(m_ShootStop);
                m_kickerPneumatics.setDefaultCommand(new KickerPassive(m_kickerPneumatics));

        }

        /**
         * Use this method to define your button->command mappings. Buttons can be
         * created by instantiating a {@link GenericHID} or one of its subclasses
         * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
         * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
         */
        private void configureButtonBindings() {
                new JoystickButton(m_joystickLeft, Constants.trigger)
                                // Drive Straight
                                .whileHeld(new DriveStraight(m_driveTrain, m_joystickLeft, m_joystickRight));

                new JoystickButton(m_XBoxController, Constants.a)
                                // Trigger Kicker
                                .whileHeld(new TriggerKicker(m_kickerPneumatics, true));

                new JoystickButton(m_XBoxController, Constants.b)
                                // Shoot Velocity
                                .toggleWhenPressed(new ShootVelocity(m_Shooter, Constants.shooterSpeed));

                // new JoystickButton(m_XBoxController, Constants.b)
                // Shoot
                // .toggleWhenPressed(new ShootingCommand(m_Shooter, .45));

                new JoystickButton(m_XBoxController, Constants.y)
                                // Collector Up
                                .toggleWhenPressed(new CollectorUp(m_Collector));

                new JoystickButton(m_XBoxController, Constants.x)
                                // Collector Down
                                .toggleWhenPressed(new CollectorDown(m_Collector, .5));

                new JoystickButton(m_XBoxController, Constants.lb)
                                // Run Transfer forward
                                .whileHeld(new TransferCommand(m_lTransfer, .5));

                new JoystickButton(m_XBoxController, Constants.rb)
                                // Run Transfer backward
                                .whileHeld(new TransferCommand(m_lTransfer, -.4));

                new JoystickButton(m_joystickRight, Constants.trigger)
                                // Limelight
                                .whileHeld(new Limelight(m_driveTrain, m_joystickLeft, m_joystickRight));

                new JoystickButton(m_joystickRight, 4)
                                // Flip Robot
                                .whenPressed(new FlipCommand(m_driveTrain));

                new JoystickButton(m_XBoxController, 8)
                                // legacy to be deleted
                                // toggle hood
                                .whenPressed(new ToggleHood(m_Shooter));
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
