package org.usfirst.frc.team4990.robot.subsystems;

import org.usfirst.frc.team4990.robot.subsystems.motors.Motor;

public class Forklift {
	private Elevator elevator;
	private double elevatorSetPower;
	
	private Fork fork;
	private boolean forkState;
	
	public Forklift(Motor elevatorMotor) {
		this.elevator = new Elevator(elevatorMotor);
		this.fork = new Fork();
	}

	public void setElevatorPower(double power) {
		this.elevatorSetPower = power;
	}
	
	public double getElevatorSetPower() {
		return this.elevatorSetPower;
	}
	
	public void setForkToOpen() {
		this.forkState = true;
	}
	
	public void setForkToClosed() {
		this.forkState = false;
	}
	
	public void update() {
		this.elevator.setElevatorPower(this.elevatorSetPower);
		this.fork.setForkState(this.forkState);
	}
}
