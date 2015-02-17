package org.usfirst.frc.team4990.robot.subsystems;

import org.usfirst.frc.team4990.robot.subsystems.motors.Motor;

public class Elevator {
	private Motor elevatorMotor;
	private double currMotorPower;
	
	private LimitSwitch topSwitch;
	private boolean isAbove;
	
	public Elevator(Motor elevatorMotor, int topSwitchChannel) {
		this.elevatorMotor = elevatorMotor;
		
		this.topSwitch = new LimitSwitch(topSwitchChannel);
		this.isAbove = false;
	}

	public void setElevatorPower(double power) {
		if (this.isAbove && power > 0) {
			this.currMotorPower = 0;
			this.elevatorMotor.setPower(0.0);
		} else {
			this.currMotorPower = power * 0.5;
			this.elevatorMotor.setPower(power * 0.5);
		}
	}
	
	private Toggle limitSwitchToggle;
	
	public void checkSafety() {
		this.topSwitch.printCounter();
		if (this.topSwitch.isSwitched()) {
			this.isAbove = !this.isAbove;
			this.topSwitch.reset();
		}
	}
}
