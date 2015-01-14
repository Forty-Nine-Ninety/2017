package org.usfirst.frc.team4990.robot;

import org.usfirst.frc.team4990.robot.motors.Motor;

public class DriveTrain {
	Gearbox leftGearbox;
	Gearbox rightGearbox;
	
	public DriveTrain(Motor leftMotor1, Motor leftMotor2, Motor rightMotor1, Motor rightMotor2) {
		this.leftGearbox = new Gearbox(leftMotor1, leftMotor2);
		this.rightGearbox = new Gearbox(rightMotor1, rightMotor2);
	}
	
	public void setSpeed(double speed) {
		this.leftGearbox.setSpeed(speed);
		this.rightGearbox.setSpeed(speed);
	}
}
