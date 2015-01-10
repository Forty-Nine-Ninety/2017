package org.usfirst.frc.team4990.robot;

import org.usfirst.frc.team4990.robot.motors.Motor;

public class DriveTrain {
	Motor leftMotor1;
	Motor leftMotor2;
	Motor rightMotor1;
	Motor rightMotor2;
	
	public DriveTrain(Motor leftMotor1, Motor leftMotor2, Motor rightMotor1, Motor rightMotor2) {
		this.leftMotor1 = leftMotor1;
		this.leftMotor2 = leftMotor2;
		this.rightMotor1 = rightMotor1;
		this.rightMotor2 = rightMotor2;
	}
	
	public void setSpeed(double power) {
		setLeftPower(power);
		setRightPower(power);
	}
	
	private void setLeftPower(double power) {
		this.leftMotor1.set(power);
		this.leftMotor2.set(power);
	}
	
	private void setRightPower(double power) {
		this.rightMotor1.set(power);
		this.rightMotor2.set(power);
	}
}
