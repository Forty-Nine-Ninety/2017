package org.usfirst.frc.team4990.robot.controllers;

import org.usfirst.frc.team4990.robot.lib.Toggle;
import org.usfirst.frc.team4990.robot.subsystems.Forklift;

import edu.wpi.first.wpilibj.Joystick;

public class TeleopForkliftController {
	private Joystick joystick;
	private Forklift forklift;
	
	private Toggle elevatorStasisToggle;
	private double elevatorStasisSpeed;
	private boolean lastElevatorStasisToggled;
	
	private Toggle forkStateToggle;
	private boolean lastForkStateToggleInput = false;
	
	private boolean isElevatorEStopTriggered = false;
	
	public TeleopForkliftController(
			Joystick joystick, 
			Forklift forklift,
			int timeUntilNextToggle) {
		this.joystick = joystick;
		this.forklift = forklift;
		
		this.forkStateToggle = new Toggle(timeUntilNextToggle);
	}

	public void updateForkliftState() {
		if (this.joystick.getRawButton(8)) {
			this.isElevatorEStopTriggered = false;
		}
		
		if (this.joystick.getRawButton(7)) {
			this.isElevatorEStopTriggered = true;
		}
		
		updateElevatorState(this.joystick.getY(), this.joystick.getRawButton(1));
		updateForkState(this.joystick.getRawButton(2));
	}
	
	private void updateElevatorState(double elevatorInput, boolean elevatorStasisToggled) {
		if (!this.isElevatorEStopTriggered) {
			double elevatorPower = elevatorInput * 0.4;
			
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
		}
	}
	
	private void updateForkState(boolean forkStateToggled) {
		if (forkStateToggled && !this.lastForkStateToggleInput) {
			this.forkStateToggle.toggle();
			
			if (this.forkStateToggle.isToggled()) {
				this.forklift.setForkToOpen();
			} else {
				this.forklift.setForkToClosed();
			}
			
			System.out.println("isToggled: " + this.forkStateToggle.isToggled());
		}
		
		this.lastForkStateToggleInput = forkStateToggled;
	}
}
