package org.usfirst.frc.team4990.robot.controllers;

import org.usfirst.frc.team4990.robot.subsystems.Forklift;

import edu.wpi.first.wpilibj.Joystick;

public class TeleopForkliftController {
	private Joystick joystick;
	private Forklift forklift;
	
	private final double maxPowerPercent;
	
	private boolean forkIsOpen = false;
	private boolean lastForkStateToggleInput = false;
	
	private boolean isElevatorEStopTriggered = false;
	
	public TeleopForkliftController(
			Joystick joystick, 
			Forklift forklift,
			double maxPowerPercent) {
		this.joystick = joystick;
		this.forklift = forklift;
		this.maxPowerPercent = maxPowerPercent;
	}

	public void updateForkliftState() {
		// untrigger forklift e-stop
		if (this.joystick.getRawButton(8)) {
			this.isElevatorEStopTriggered = false;
			this.forklift.reset();
		}
		
		// trigger forklift e-stop
		if (this.joystick.getRawButton(7)) {
			this.isElevatorEStopTriggered = true;
		}
		
		updateElevatorState(this.joystick.getY(), this.joystick.getRawButton(1));
		updateForkState(this.joystick.getRawButton(2));
	}
	
	private void updateElevatorState(double elevatorInput, boolean elevatorStasisToggled) {
		if (!this.isElevatorEStopTriggered) {
			double elevatorPower = elevatorInput * this.maxPowerPercent;
			
			this.forklift.setElevatorPower(elevatorPower);
		} else {
			this.forklift.setElevatorPower(0.0);
		}
	}
	
	private void updateForkState(boolean forkStateToggled) {
		if (forkStateToggled && !this.lastForkStateToggleInput) {
			if (!this.forkIsOpen) {
				this.forklift.setForkToOpen();
				this.forkIsOpen = true;
			} else {
				this.forklift.setForkToClosed();
				this.forkIsOpen = false;
			}
		}
		
		this.lastForkStateToggleInput = forkStateToggled;
	}
}
