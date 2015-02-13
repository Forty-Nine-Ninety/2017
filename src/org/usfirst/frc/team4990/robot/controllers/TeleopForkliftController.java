package org.usfirst.frc.team4990.robot.controllers;

import org.usfirst.frc.team4990.robot.lib.Toggle;
import org.usfirst.frc.team4990.robot.subsystems.F310Gamepad;
import org.usfirst.frc.team4990.robot.subsystems.Forklift;

public class TeleopForkliftController {
	private F310Gamepad gamepad;
	private Forklift forklift;
	
	private boolean elevatorStasisOn;
	
	private Toggle forkStateToggle;
	
	private final double safetyElevatorPower;
	
	public TeleopForkliftController(
			F310Gamepad gamepad, 
			Forklift forklift, 
			double safetyElevatorPower,
			int timeUntilNextToggle) {
		this.gamepad = gamepad;
		this.forklift = forklift;
		
		this.elevatorStasisOn = false;
		
		this.forkStateToggle = new Toggle(timeUntilNextToggle);
		
		this.safetyElevatorPower = safetyElevatorPower;
	}

	public void updateForkliftState() {
		updateElevatorState(this.gamepad.getLeftJoystickY(), this.gamepad.getAButtonPressed());
		updateForkState(this.gamepad.getBButtonPressed());
	}
	
	private void updateElevatorState(double elevatorInput, boolean elevatorStasisToggled) {
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
	
	private void updateForkState(boolean forkStateToggled) {
		if (forkStateToggled) {
			this.forkStateToggle.toggle();
			
			if (this.forkStateToggle.isToggled()) {
				this.forklift.setForkToOpen();
			} else {
				this.forklift.setForkToClosed();
			}
		}
	}
}
