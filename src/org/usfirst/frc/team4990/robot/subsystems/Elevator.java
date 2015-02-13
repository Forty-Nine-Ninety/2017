package org.usfirst.frc.team4990.robot.subsystems;

import org.usfirst.frc.team4990.robot.subsystems.motors.Motor;

public class Elevator {
	private Motor elevatorMotor;
	
	public Elevator(Motor elevatorMotor) {
		this.elevatorMotor = elevatorMotor;
	}

	public void setElevatorPower(double power) {
		this.elevatorMotor.setPower(power);
	}
}
