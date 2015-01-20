package org.usfirst.frc.team4990.robot;

public class DriveTrainController {
	private F310Gamepad gamepad;
	private DriveTrain driveTrain;
	
	public DriveTrainController(F310Gamepad gamepad, DriveTrain driveTrain) {
		this.gamepad = gamepad;
		this.driveTrain = driveTrain;
	}
	
	public void translateCurrInputToDrivingInstructions() {
		double throttle = this.gamepad.getLeftJoystickY();
		double turnSteepness = this.gamepad.getRightJoystickX();
		
		if (throttle != 0 && turnSteepness != 0) {
			driveRobotInArc(throttle, turnSteepness);
		} else if (throttle != 0 && turnSteepness == 0) {
			driveRobotForward(throttle);
		} else if (throttle == 0 && turnSteepness != 0) {
			turnRobotInPlace(turnSteepness);
		}
	}
	
	public void driveRobotInArc(double throttle, double turnSteepness) {
		double leftWheelSpeed = throttle;
		double rightWheelSpeed = calculateInsideWheelSpeed(throttle, turnSteepness);
		
		/* the robot should turn to the left, so left wheel is on the inside
		 * of the turn, and the right wheel is on the outside of the turn
		 */
		if (turnSteepness < 0) {
			leftWheelSpeed = calculateInsideWheelSpeed(throttle, -turnSteepness);
			rightWheelSpeed = throttle;
		}
		
		this.driveTrain.setSpeed(leftWheelSpeed, rightWheelSpeed);
	}
	
	private double calculateInsideWheelSpeed(double outsideWheelSpeed, double turnSteepness) {
		double turnRadius = Constants.maxTurnRadius - (turnSteepness * Constants.maxTurnRadius);
		
		return outsideWheelSpeed * (turnRadius / (turnRadius + Constants.robotWidth));
	}
	
	public void driveRobotForward(double throttle) {
		/* both motors should spin forward. */
		this.driveTrain.setSpeed(throttle, throttle);
	}
	
	public void turnRobotInPlace(double turningSpeed) {
		/* the right motor's velocity has the opposite sign of the the left motor's
		 * since the right motor will spin in the opposite direction from the left
		 */
		this.driveTrain.setSpeed(-turningSpeed, turningSpeed);
	}
}
