package org.usfirst.frc.team4990.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;

import org.usfirst.frc.team4990.robot.controllers.SimpleAutoDriveTrainScripter;
import org.usfirst.frc.team4990.robot.controllers.TeleopDriveTrainController;
//import org.usfirst.frc.team4990.robot.lib.MotionProfile;
import org.usfirst.frc.team4990.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4990.robot.subsystems.F310Gamepad;
import org.usfirst.frc.team4990.robot.subsystems.motors.TalonMotorController;
//import org.usfirst.frc.team4990.robot.subsystems.motors.TalonSRXMotorController;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	private Preferences prefs;
	private F310Gamepad driveGamepad;
	private DriveTrain driveTrain;
	
	private SimpleAutoDriveTrainScripter autoScripter;
	
	private boolean eStopTriggered;
	
	private TeleopDriveTrainController teleopDriveTrainController;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	System.out.println("Version 1.3.20.9.03");
    	this.prefs = Preferences.getInstance();
    	
    	this.driveGamepad = new F310Gamepad(1);
    	
    	this.driveTrain = new DriveTrain( 
    		new TalonMotorController(0),
    		new TalonMotorController(1),
    		new TalonMotorController(2),
    		new TalonMotorController(3),
    		0, 1, 2, 3);

    	
    	//this.eStopTriggered = false;
    }

    public void autonomousInit() {
    	autoScripter = new SimpleAutoDriveTrainScripter(driveTrain);
    }
    
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	
    	autoScripter.update();
    	driveTrain.update();
    }
    
    public void teleopInit() {
    	this.teleopDriveTrainController = new TeleopDriveTrainController(
        		this.driveGamepad, 
        		this.driveTrain, 
        		this.prefs.getDouble("maxTurnRadius", Constants.defaultMaxTurnRadius),
        		this.prefs.getBoolean("reverseTurningFlipped", false),
        		this.prefs.getDouble("smoothDriveAccTime", Constants.defaultAccelerationTime),
        		this.prefs.getDouble("lowThrottleMultiplier", .25),
        		this.prefs.getDouble("maxThrottle", 1.0));
    }
     
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	
    	if (!this.eStopTriggered) {
	        this.teleopDriveTrainController.updateDriveTrainState();
    	} else {
    		this.driveTrain.setSpeed(0.0, 0.0);
    	}
    	
    	this.driveTrain.update();
    }
}
