package org.usfirst.frc.team4990.robot.subsystems;

import org.usfirst.frc.team4990.robot.subsystems.motors.Motor;

public class Elevator {
	private Motor elevatorMotor;
	private double currMotorPower;
	
	private LimitSwitch topSwitch;
	private boolean isAboveSwitchInProgress = false;
	private boolean isAbove;
	
	public Elevator(Motor elevatorMotor, int topSwitchChannel, int topSwitchCounterSensitivity) {
		this.elevatorMotor = elevatorMotor;
		
		this.topSwitch = new LimitSwitch(topSwitchChannel, topSwitchCounterSensitivity);
		this.isAbove = false;
	}

	public void setElevatorPower(double power) {
		if (this.isAbove && power > 0) {
			this.currMotorPower = 0;
			this.elevatorMotor.setPower(0.0);
		} else {
			this.currMotorPower = power;
			this.elevatorMotor.setPower(power);
		}
	}
	
	public void checkSafety() {
		this.topSwitch.update();
		
		if (this.topSwitch.isSwitched()) {
			this.isAboveSwitchInProgress = true;
		//in this case, this.topSwitch.isSwitched will always be true
		} else if (this.isAboveSwitchInProgress) {
			this.isAbove = !this.isAbove;
			this.isAboveSwitchInProgress = false;
		}
		
		System.out.println("switchInProgress: " + this.isAboveSwitchInProgress + "; isSwitched: " + this.topSwitch.isSwitched() + "; isAbove: " + this.isAbove);
	}
}
