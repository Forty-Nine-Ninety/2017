package org.usfirst.frc.team4990.robot.subsystems;

import org.usfirst.frc.team4990.robot.subsystems.motors.Motor;

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
		setLeftSpeed(leftSpeed);
		setRightSpeed(rightSpeed);
	}
	
	public void setLeftSpeed(double leftSpeed) {
		this.leftSetSpeed = leftSpeed;
	}
	
	public void setRightSpeed(double rightSpeed) {
		this.rightSetSpeed = rightSpeed;
	}
	
	public void update() {
		this.leftGearbox.setSpeed(this.leftSetSpeed);
		this.rightGearbox.setSpeed(-this.rightSetSpeed);
	}
	
	public double getLeftSetSpeed() {
		return this.leftSetSpeed;
	}
	
	public double getRightSetSpeed() {
		return this.rightSetSpeed;
	}
}
