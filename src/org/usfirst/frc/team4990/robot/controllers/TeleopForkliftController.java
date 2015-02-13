package org.usfirst.frc.team4990.robot.controllers;

import org.usfirst.frc.team4990.robot.subsystems.F310Gamepad;
import org.usfirst.frc.team4990.robot.subsystems.Forklift;

public class TeleopForkliftController {
	private F310Gamepad gamepad;
	private Forklift forklift;
	
	private boolean elevatorStasisOn;
	
	private final double safetyElevatorPower;
	
	public TeleopForkliftController(
			F310Gamepad gamepad, 
			Forklift forklift, 
			double safetyElevatorPower) {
		this.gamepad = gamepad;
		this.forklift = forklift;
		
		this.elevatorStasisOn = false;
		
		this.safetyElevatorPower = safetyElevatorPower;
	}

	public void updateForkliftState() {
		double elevatorInput = this.gamepad.getLeftJoystickY();
		boolean elevatorStasisToggled = this.gamepad.getAButtonPressed();
		
		if (elevatorInput == 0 && elevatorStasisToggled) {
			this.elevatorStasisOn = true;
		} else if (elevatorInput > 0) {
			this.forklift.setElevatorPower(elevatorInput);
			this.elevatorStasisOn = false;
		} else if (this.elevatorStasisOn && elevatorInput < 0) {
			this.forklift.setElevatorPower(this.safetyElevatorPower);
			this.elevatorStasisOn = false;
		}
	}
}
