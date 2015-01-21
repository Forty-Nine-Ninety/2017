package org.usfirst.frc.team4990.robot;

import org.usfirst.frc.team4990.robot.motors.Motor;

public class Gearbox {
	private Motor motor1;
	private Motor motor2;
	
	public Gearbox(Motor motor1, Motor motor2) {
		this.motor1 = motor1;
		this.motor2 = motor2;
	}
	
	public void setSpeed(double speed) {
		this.motor1.setPower(speed);
		this.motor2.setPower(speed);
	}
	
}
