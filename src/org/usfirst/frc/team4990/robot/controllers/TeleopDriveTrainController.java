package org.usfirst.frc.team4990.robot.controllers;

import org.usfirst.frc.team4990.robot.Constants;
import org.usfirst.frc.team4990.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4990.robot.subsystems.F310Gamepad;

public class TeleopDriveTrainController {
	private F310Gamepad gamepad;
	private DriveTrain driveTrain;
	
	private double maxTurnRadius;
	private boolean reverseTurningFlipped;
	private boolean printToFile;
	
	public TeleopDriveTrainController(F310Gamepad gamepad, DriveTrain driveTrain, double maxTurnRadius, boolean reverseTurningFlipped) {
		this.gamepad = gamepad;
		this.driveTrain = driveTrain;
		
		this.maxTurnRadius = maxTurnRadius;
		this.reverseTurningFlipped = reverseTurningFlipped;
	}
	
	public void updateDriveTrainState() {
		//throttle is to Left Joystick. Forwards and Backwards
		double throttle = this.gamepad.getLeftJoystickY();
		//turnSteepness is to Right Joystick. Changes Direction. 
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
		if ((turnSteepness < 0 && throttle > 0)) {
			leftWheelSpeed = calculateInsideWheelSpeed(throttle, -turnSteepness);
			rightWheelSpeed = throttle;
			
		}
		
		//if statement to reverse directions going backwards?
		//probably should check if this works
		if((turnSteepness > 0 && throttle < 0) && Robot.teleopDriveTrainController.isTurningReversed()){
			rightWheelSpeed = calculateInsideWheelSpeed(throttle, -turnSteepness);
			leftWheelSpeed = throttle;
		}
		//runs if reversed is not enabled
		if((turnSteepness > 0 && throttle < 0) && !Robot.teleopDriveTrainController.isTurningReversed()){
			rightWheelSpeed = throttle;
			leftWheelSpeed = calculateInsideWheelSpeed(throttle, -turnSteepness);
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
	public boolean isTurningReversed(){
	//basic get method for reversed turning
		return this.reverseTurningFlipped;
	}
	public boolean printToFile(){
		return this.printToFile;
	}
}
