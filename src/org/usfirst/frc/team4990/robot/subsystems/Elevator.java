package org.usfirst.frc.team4990.robot.subsystems;

import org.usfirst.frc.team4990.robot.subsystems.motors.Motor;

public class Elevator {
	private Motor elevatorMotor;
	private double currMotorPower;
	
	private LimitSwitch topSwitch;
	private boolean lastTopSwitched = false;
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
			this.currMotorPower = power;
			this.elevatorMotor.setPower(power);
		}
	}
	
	public void checkSafety() {
		if (this.topSwitch.isSwitched() && !this.lastTopSwitched) {
			this.isAbove = !this.isAbove;
			this.topSwitch.reset();
		}
		
		this.lastTopSwitched = this.topSwitch.isSwitched();
	}
}
