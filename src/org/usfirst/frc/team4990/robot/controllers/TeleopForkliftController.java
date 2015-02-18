package org.usfirst.frc.team4990.robot.controllers;

import org.usfirst.frc.team4990.robot.subsystems.Forklift;

import edu.wpi.first.wpilibj.Joystick;

public class TeleopForkliftController {
	private Joystick joystick;
	private Forklift forklift;
	
	private double elevatorStasisSpeed;
	private boolean lastElevatorStasisToggled;
	
	private boolean forkIsOpen = false;
	private boolean lastForkStateToggleInput = false;
	
	private boolean isElevatorEStopTriggered = false;
	
	public TeleopForkliftController(
			Joystick joystick, 
			Forklift forklift) {
		this.joystick = joystick;
		this.forklift = forklift;
	}

	public void updateForkliftState() {
		if (this.joystick.getRawButton(8)) {
			this.isElevatorEStopTriggered = false;
			this.forklift.reset();
		}
		
		if (this.joystick.getRawButton(7)) {
			this.isElevatorEStopTriggered = true;
		}
		
		updateElevatorState(this.joystick.getY(), this.joystick.getRawButton(1));
		updateForkState(this.joystick.getRawButton(2));
	}
	
	private void updateElevatorState(double elevatorInput, boolean elevatorStasisToggled) {
		if (!this.isElevatorEStopTriggered) {
			double elevatorPower = elevatorInput * 0.6;
			
			this.forklift.setElevatorPower(elevatorPower);
			
			/*if (elevatorStasisToggled && !this.lastElevatorStasisToggled) {
				this.elevatorStasisToggle.toggle();
				
				if (this.elevatorStasisToggle.isToggled()) {
					this.elevatorStasisSpeed = elevatorInput;
				}
			}
			
			this.lastElevatorStasisToggled = elevatorStasisToggled;
			
			if (this.elevatorStasisToggle.isToggled()) {
				this.forklift.setElevatorPower(this.elevatorStasisSpeed);
			} else {
				this.forklift.setElevatorPower(elevatorPower);
			}*/
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
			
			System.out.println("isToggled: " + this.forkIsOpen);
		}
		
		this.lastForkStateToggleInput = forkStateToggled;
	}
}
