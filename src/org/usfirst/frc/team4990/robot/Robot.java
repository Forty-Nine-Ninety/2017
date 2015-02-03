package org.usfirst.frc.team4990.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;

import org.usfirst.frc.team4990.robot.controllers.TeleopDriveTrainController;
import org.usfirst.frc.team4990.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4990.robot.subsystems.F310Gamepad;
import org.usfirst.frc.team4990.robot.subsystems.motors.TalonMotorController;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	Preferences prefs;
	private Logger logger;
	
	private F310Gamepad gamepad;
	private DriveTrain driveTrain;
	
	private TeleopDriveTrainController teleopDriveTrainController;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	this.logger = new Logger(new Dashboard());
    	this.prefs = Preferences.getInstance();
    	
    	this.gamepad = new F310Gamepad(1);
    	
    	this.driveTrain = new DriveTrain( 
    		new TalonMotorController(6),
    		new TalonMotorController(7),
    		new TalonMotorController(4),
    		new TalonMotorController(5),
    		0, 1, 2, 3);
    	
    	this.teleopDriveTrainController = new TeleopDriveTrainController(
    		this.gamepad, 
    		this.driveTrain, 
    		this.prefs.getDouble("maxTurnRadius", Constants.defaultMaxTurnRadius),
    		this.prefs.getBoolean("reverseTurningFlipped", true),
    		this.prefs.getDouble("smoothDriveAccTime", Constants.defaultAccelerationTime),
    		this.prefs.getDouble("lowThrottleMultiplier", .25),
    		this.prefs.getInt("timePerToggle", 150));
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        this.teleopDriveTrainController.updateDriveTrainState();
        
        this.driveTrain.update();
        
        this.logger.profileDriveTrain(this.driveTrain);
    }
    
    private int stage = 0;
    private double currPower = 0.0;
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	/*boolean pressed = this.gamepad.getAButtonPressed();
    	
    	if (pressed) {
    		this.driveTrain.setSpeed(0.1, 0.1);
    	} else {
    		this.driveTrain.setSpeed(0.0, 0.0);
    	}
    	
    	this.driveTrain.update();
    	this.logger.profileDriveTrain(this.driveTrain);*/
    	double change = 0;
    	switch (stage) {
    	case 0:
    		if (currPower <= 1.0) {
        		this.driveTrain.setSpeed(currPower, currPower);
        		change = 0.01;
        	} else if (currPower > 1.00) {
        		stage = 1;
        	}
    		break;
    	case 1:
    		if (currPower >= -1.0) {
    			this.driveTrain.setSpeed(currPower, currPower);
        		change = -0.01;
    		} else if (currPower < -1.0) {
    			stage = 2;
    		}
    		break;
    	case 2:
    		if (currPower <= 1.0) {
        		this.driveTrain.setSpeed(currPower, currPower);
        		change = 0.01;
        	} else if (currPower > 0.0) {
        		stage = 3;
        	}
    		break;
    	default:
    		this.driveTrain.setSpeed(0.0, 0.0);
    		break;
    	}
    	
    	this.driveTrain.update();
    	System.out.println(currPower + ", " + this.driveTrain.getLeftVelocity() + ", " + this.driveTrain.getRightVelocity());
    	currPower += change;
    }
    
}
