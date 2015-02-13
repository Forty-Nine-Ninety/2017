package org.usfirst.frc.team4990.robot.subsystems;

import org.usfirst.frc.team4990.robot.subsystems.motors.Motor;

public class Forklift {
	private Elevator elevator;
	private double elevatorSetPower;
	
	public Forklift(Motor elevatorMotor) {
		this.elevator = new Elevator(elevatorMotor);
	}

	public void setElevatorPower(double power) {
		this.elevatorSetPower = power;
	}
	
	public double getElevatorsetPower() {
		return this.elevatorSetPower;
	}
	
	public void update() {
		this.elevator.setElevatorPower(this.elevatorSetPower);
	}
}
