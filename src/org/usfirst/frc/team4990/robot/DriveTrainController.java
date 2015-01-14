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
			double leftWheelSpeed = throttle;
			double rightWheelSpeed = calculateInsideWheelSpeed(throttle, turnSteepness);
			
			//the robot should turn to the left, so left wheel is inside, right wheel is outside
			if (turnSteepness < 0) {
				leftWheelSpeed = calculateInsideWheelSpeed(throttle, -turnSteepness);
			}
			
			this.driveTrain.setSpeed(leftWheelSpeed, rightWheelSpeed);
		} else if (throttle != 0 && turnSteepness == 0) {
			this.driveTrain.setSpeed(throttle, throttle);
		} else if (throttle == 0 && turnSteepness != 0) {
			this.driveTrain.setSpeed(turnSteepness, -turnSteepness);
		}
	}
	
	public double calculateInsideWheelSpeed(double outsideWheelSpeed, double turnSteepness) {
		double turnRadius = turnSteepness * Constants.maxTurnRadius;
		
		return outsideWheelSpeed * ((turnRadius + Constants.robotWidth) / turnRadius);
	}
}
