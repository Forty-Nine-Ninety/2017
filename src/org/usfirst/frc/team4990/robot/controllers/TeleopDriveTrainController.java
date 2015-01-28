package org.usfirst.frc.team4990.robot.controllers;

import org.usfirst.frc.team4990.robot.Constants;
import org.usfirst.frc.team4990.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4990.robot.subsystems.F310Gamepad;

public class TeleopDriveTrainController {
	private F310Gamepad gamepad;
	private DriveTrain driveTrain;
	
	private double currentThrottleMultiplier = 1;
	private int cyclesSinceLastToggle = 0;
	
	private final double maxTurnRadius;
	private final boolean reverseTurningFlipped;
	private final double lowThrottleMultiplier;
	private final int cyclesPerToggle;
	
	public TeleopDriveTrainController(
			F310Gamepad gamepad, 
			DriveTrain driveTrain, 
			double maxTurnRadius, 
			boolean reverseTurningFlipped,
			double lowThrottleMultiplier,
			int timeUntilNextToggle) {
		this.gamepad = gamepad;
		this.driveTrain = driveTrain;
		
		this.maxTurnRadius = maxTurnRadius;
		this.reverseTurningFlipped = reverseTurningFlipped;
		this.lowThrottleMultiplier = lowThrottleMultiplier;
		this.cyclesPerToggle = timeUntilNextToggle / Constants.millisecondsPerCycle;
	}
	
	public void updateDriveTrainState() {
		boolean dpiTogglePressed = this.gamepad.getYButtonPressed();
		
		if (dpiTogglePressed && this.cyclesSinceLastToggle >= this.cyclesPerToggle) {
			if (this.currentThrottleMultiplier == 1) {
				this.currentThrottleMultiplier = this.lowThrottleMultiplier;
			} else if (this.currentThrottleMultiplier == this.lowThrottleMultiplier){
				this.currentThrottleMultiplier = 1;
			}
			
			this.cyclesSinceLastToggle = 0;
		} else if (dpiTogglePressed && this.cyclesSinceLastToggle < this.cyclesPerToggle) {
			this.cyclesSinceLastToggle++;
		}
		
		double throttle = this.gamepad.getLeftJoystickY() * this.currentThrottleMultiplier;
		double turnSteepness = this.gamepad.getRightJoystickX();
		
		if (throttle != 0 && turnSteepness != 0) {
			setArcTrajectory(throttle, turnSteepness);
		} else if (throttle != 0 && turnSteepness == 0) {
			setStraightTrajectory(throttle);
		} else if (throttle == 0 && turnSteepness != 0) {
			setTurnInPlaceTrajectory(turnSteepness);
		} else {
			this.driveTrain.setSpeed(0.0, 0.0);
		}
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
