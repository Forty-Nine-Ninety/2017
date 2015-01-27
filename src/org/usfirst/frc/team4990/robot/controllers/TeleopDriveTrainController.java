package org.usfirst.frc.team4990.robot.controllers;

import org.usfirst.frc.team4990.robot.Constants;
import org.usfirst.frc.team4990.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4990.robot.subsystems.F310Gamepad;

import java.util.*;

public class TeleopDriveTrainController {
	private F310Gamepad gamepad;
	private DriveTrain driveTrain;
	
	private double lastThrottle;
	private Date lastUpdate;
	
	private double maxTurnRadius;
	private boolean reverseTurningFlipped;
	
	public TeleopDriveTrainController(F310Gamepad gamepad, DriveTrain driveTrain, double maxTurnRadius, boolean reverseTurningFlipped) {
		this.gamepad = gamepad;
		this.driveTrain = driveTrain;
		
		this.lastThrottle = 0;
		this.lastUpdate = new Date();
		
		this.maxTurnRadius = maxTurnRadius;
		this.reverseTurningFlipped = reverseTurningFlipped;
	}
	
	public void updateDriveTrainState() {
		double throttleInput = this.gamepad.getLeftJoystickY();
		double turnSteepness = this.gamepad.getRightJoystickX();
		
		Date currentUpdate = new Date();
		
		double acceleration = (throttleInput - this.lastThrottle) / Constants.accelerationTime;
		double deltaTime = currentUpdate.getTime() - lastUpdate.getTime();
		
		double deltaThrottle = deltaTime * acceleration;
		double throttle = this.lastThrottle + deltaThrottle;
		
		if (throttleInput != 0 && turnSteepness != 0) {
			setArcTrajectory(throttle, turnSteepness);
		} else if (throttleInput == 0 && turnSteepness != 0) {
			setTurnInPlaceTrajectory(turnSteepness);
		} else {
			setStraightTrajectory(throttle);
		}
		
		this.lastThrottle = throttle;
		this.lastUpdate = currentUpdate;
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
