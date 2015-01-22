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
	private Logger logger;
	
	private F310Gamepad gamepad;
	private DriveTrain driveTrain;
	
	private TeleopDriveTrainController teleopDriveTrainController;
	
	Preferences prefs;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	"bla"
    	this.logger = new Logger(new Dashboard());
    	this.prefs = Preferences.getInstance();
    	
    	this.gamepad = new F310Gamepad(0);
    	
    	this.driveTrain = new DriveTrain( 
    		new TalonMotorController(0),
    		new TalonMotorController(1),
    		new TalonMotorController(2),
    		new TalonMotorController(3));
    	
    	this.teleopDriveTrainController = new TeleopDriveTrainController(
    		this.gamepad, 
    		this.driveTrain, 
    		this.prefs.getDouble("maxTurnRadius", Constants.defaultMaxTurnRadius),
    		this.prefs.getBoolean("reverseTurningFlipped", true));
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
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	
    }
    
}
