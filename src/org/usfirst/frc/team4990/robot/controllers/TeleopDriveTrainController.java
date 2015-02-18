package org.usfirst.frc.team4990.robot.controllers;

import org.usfirst.frc.team4990.robot.Constants;
import org.usfirst.frc.team4990.robot.lib.Toggle;
import org.usfirst.frc.team4990.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4990.robot.subsystems.F310Gamepad;

import java.util.*;

public class TeleopDriveTrainController {
	private F310Gamepad gamepad;
	private DriveTrain driveTrain;
	
	private double lastThrottle = 0;
	private double lastTurnSteepness = 0;
	private Date lastUpdate;
	
	private Toggle dpiToggle;
	private boolean lowDpiToggled = true;
	private boolean lastDpiToggleInput = false;
	private double currentThrottleMultiplier = 1;
	
	private final double maxTurnRadius;
	private final boolean reverseTurningFlipped;
	private final double accelerationTime;
	private final double lowThrottleMultiplier;
	
	public TeleopDriveTrainController(
			F310Gamepad gamepad, 
			DriveTrain driveTrain, 
			double maxTurnRadius, 
			boolean reverseTurningFlipped,
			double accelerationTime,
			double lowThrottleMultiplier,
			int timeUntilNextToggle) {
		this.gamepad = gamepad;
		this.driveTrain = driveTrain;
		
		this.lastUpdate = new Date();
		
		this.dpiToggle = new Toggle(timeUntilNextToggle);
		
		this.maxTurnRadius = maxTurnRadius;
		this.reverseTurningFlipped = reverseTurningFlipped;
		this.accelerationTime = accelerationTime;
		this.lowThrottleMultiplier = lowThrottleMultiplier;
	}
	
	public void updateDriveTrainState() {
		boolean dpiTogglePressed = this.gamepad.getYButtonPressed();
		
		if (dpiTogglePressed && !this.lastDpiToggleInput) {
			if (this.currentThrottleMultiplier == 1.0) {
				this.currentThrottleMultiplier = this.lowThrottleMultiplier;
			} else {
				this.currentThrottleMultiplier = 1.0;
			}
		}
		
		this.lastDpiToggleInput = dpiTogglePressed;
		
		double throttleInput = this.gamepad.getLeftJoystickY();
		double turnSteepnessInput = this.gamepad.getRightJoystickX();
		
		Date currentUpdate = new Date();
		
		double throttle = getNextThrottle(
				throttleInput * this.currentThrottleMultiplier, 
				this.lastThrottle, 
				this.lastUpdate, 
				currentUpdate, 
				this.accelerationTime);
		
		double turnSteepness = getNextThrottle(
				turnSteepnessInput * this.currentThrottleMultiplier,
				this.lastTurnSteepness,
				this.lastUpdate,
				currentUpdate,
				this.accelerationTime);
		
		if (throttleInput != 0 && turnSteepnessInput != 0) {
			setArcTrajectory(throttle, turnSteepnessInput);
		} else if (throttleInput != 0 && turnSteepnessInput == 0) { 
			setStraightTrajectory(throttle);
		} else if (throttleInput == 0 && turnSteepnessInput != 0) {
			setTurnInPlaceTrajectory(turnSteepness);
				//robot was last driving straight
		} else if (this.lastTurnSteepness == 0) {
			setStraightTrajectory(throttle);
				//robot was last turning in place
		} else {
			setTurnInPlaceTrajectory(turnSteepness);
		}
		
		this.lastThrottle = throttle;
		this.lastTurnSteepness = turnSteepness;
		this.lastUpdate = currentUpdate;
	}
	
	public double getNextThrottle(double throttleInput, double lastThrottle, Date lastUpdate, Date currentUpdate, double accelerationTime) {
		double acceleration = (throttleInput - lastThrottle) / accelerationTime;
		double deltaTime = currentUpdate.getTime() - lastUpdate.getTime();
		
		double deltaThrottle = deltaTime * acceleration;
		return lastThrottle + deltaThrottle;
	}
	
	public void setArcTrajectory(double throttle, double turnSteepness) {
		double leftWheelSpeed = throttle;
		double rightWheelSpeed = calculateInsideWheelSpeed(throttle, turnSteepness);
		
		/* the robot should turn to the left, so left wheel is on the inside
		 * of the turn, and the right wheel is on the outside of the turn
		 */
		if ((turnSteepness < 0 && throttle > 0) || (turnSteepness > 0 && throttle < 0)) {
			leftWheelSpeed = calculateInsideWheelSpeed(throttle, -turnSteepness);
			rightWheelSpeed = throttle;
		}
		
		this.driveTrain.setSpeed(leftWheelSpeed, rightWheelSpeed);
	}
	
	private double calculateInsideWheelSpeed(double outsideWheelSpeed, double turnSteepness) {
		double turnRadius = this.maxTurnRadius - (turnSteepness * this.maxTurnRadius);
		
		return outsideWheelSpeed * (turnRadius / (turnRadius + Constants.robotWidth));
	}
	
	public void setStraightTrajectory(double throttle) {
		/* both motors should spin forward. */
		this.driveTrain.setSpeed(throttle, throttle);
	}
	
	public void setTurnInPlaceTrajectory(double turningSpeed) {
		/* the right motor's velocity has the opposite sign of the the left motor's
		 * since the right motor will spin in the opposite direction from the left
		 */
		this.driveTrain.setSpeed(turningSpeed, -turningSpeed);
	}
}
