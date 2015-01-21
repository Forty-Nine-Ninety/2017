package org.usfirst.frc.team4990.robot;

public class TeleopDriveTrainController {
	private F310Gamepad gamepad;
	private DriveTrain driveTrain;
	
	private double maxTurnRadius;
	private boolean reverseTurningFlipped;
	
	public TeleopDriveTrainController(F310Gamepad gamepad, DriveTrain driveTrain, double maxTurnRadius, boolean reverseTurningFlipped) {
		this.gamepad = gamepad;
		this.driveTrain = driveTrain;
		
		this.maxTurnRadius = maxTurnRadius;
		this.reverseTurningFlipped = reverseTurningFlipped;
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
		} else {
			this.driveTrain.setSpeed(0.0, 0.0);
		}
	}
	
	public void driveRobotInArc(double throttle, double turnSteepness) {
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
	
	public void driveRobotForward(double throttle) {
		/* both motors should spin forward. */
		this.driveTrain.setSpeed(throttle, throttle);
	}
	
	public void turnRobotInPlace(double turningSpeed) {
		/* the right motor's velocity has the opposite sign of the the left motor's
		 * since the right motor will spin in the opposite direction from the left
		 */
		this.driveTrain.setSpeed(turningSpeed, -turningSpeed);
	}
}
