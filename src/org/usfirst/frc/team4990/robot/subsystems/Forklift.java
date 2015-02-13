package org.usfirst.frc.team4990.robot.subsystems;

import org.usfirst.frc.team4990.robot.subsystems.motors.Motor;

public class Forklift {
	private Motor elevatorMotor;
	private double elevatorMotorSetPower;
	
	public Forklift(Motor elevatorMotor) {
		this.elevatorMotor = elevatorMotor;
	}

	public void setElevatorPower(double power) {
		this.elevatorMotorSetPower = power;
	}
	
	public double getElevatorsetPower() {
		return this.elevatorMotorSetPower;
	}
	
	public void update() {
		this.elevatorMotor.setPower(this.elevatorMotorSetPower);
	}
}
