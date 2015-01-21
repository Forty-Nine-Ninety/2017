package org.usfirst.frc.team4990.robot;

import org.usfirst.frc.team4990.robot.motors.Motor;

public class DriveTrain {
	private Gearbox leftGearbox;
	private double leftSetSpeed;
	
	private Gearbox rightGearbox;
	private double rightSetSpeed;
	
	public DriveTrain(Motor leftMotor1, Motor leftMotor2, Motor rightMotor1, Motor rightMotor2) {
		this.leftGearbox = new Gearbox(leftMotor1, leftMotor2);
		this.rightGearbox = new Gearbox(rightMotor1, rightMotor2);
	}
	
	public void setSpeed(double leftSpeed, double rightSpeed) {
		this.leftGearbox.setSpeed(leftSpeed);
		this.rightGearbox.setSpeed(-rightSpeed);
		
		this.leftSetSpeed = leftSpeed;
		this.rightSetSpeed = rightSpeed;
	}
	
	public double getLeftSetSpeed() {
		return this.leftSetSpeed;
	}
	
	public double getRightSetSpeed() {
		return this.rightSetSpeed;
	}
}
