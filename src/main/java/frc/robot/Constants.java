/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */

public final class Constants {

    // talon placement
    // 5 0
    // 6 1
    // 7 2
    // 8 3
    // 9 4

    /*
     * public static final int frontRight = 0; public static final int frontLeft =
     * 5; public static final int rearRight = 1; public static final int rearLeft =
     * 6; // shooter public static final int shooterRight = 2; public static final
     * int shooterLeft = 7; // collector public static final int pickUp = 3; public
     * static final int pickUpTransfer = 8; // public static final int linearLoaderT
     * = 4; // public static final int linearLoaderB = 9; // linear public static
     * final int transferFront = 4; public static final int transferRear = 9;
     */

    public static final int collectorLeft = 4;
    public static final int collectorRight = 0;
    public static final int collector = 5;

    public static final int rearLeft = 1;
    public static final int frontLeft = 2;
    public static final int rearRight = 6;
    public static final int frontRight = 7;

    public static final int linearTransfer = 8;

    public static final int leftIndexer = 9;
    public static final int rightIndexer = 3;

    public static final int shooterLeft = 11;
    public static final int shooterRight = 10;

    // pneumatics
    // public static final int collectorRight = 0;
    public static final int tensioner = 1;
    public static final int hood = 2;
    public static final int collectorP = 3;
    

}
