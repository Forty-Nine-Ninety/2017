package org.usfirst.frc.team4990.robot.controllers;

import org.usfirst.frc.team4990.robot.lib.Toggle;
import org.usfirst.frc.team4990.robot.subsystems.Forklift;

import edu.wpi.first.wpilibj.Joystick;

public class TeleopForkliftController {
	private Joystick joystick;
	private Forklift forklift;
	
	private boolean elevatorStasisOn;
	
	private Toggle forkStateToggle;
	
	private final double safetyElevatorPower;
	
	public TeleopForkliftController(
			Joystick joystick, 
			Forklift forklift, 
			double safetyElevatorPower,
			int timeUntilNextToggle) {
		this.joystick = joystick;
		this.forklift = forklift;
		
		this.elevatorStasisOn = false;
		
		this.forkStateToggle = new Toggle(timeUntilNextToggle);
		
		this.safetyElevatorPower = safetyElevatorPower;
	}

	public void updateForkliftState() {
		updateElevatorState(this.joystick.getY(), this.joystick.getRawButton(1));
		updateForkState(this.joystick.getRawButton(2));
	}
	
	private void updateElevatorState(double elevatorInput, boolean elevatorStasisToggled) {
		if (elevatorInput == 0 && elevatorStasisToggled) {
			this.elevatorStasisOn = true;
		} else if (this.elevatorStasisOn && elevatorInput < 0) {
			this.forklift.setElevatorPower(this.safetyElevatorPower);
			this.elevatorStasisOn = false;
		} else {
			this.forklift.setElevatorPower(elevatorInput);
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
			
			System.out.println("isToggled: " + this.forkStateToggle.isToggled());
		}
	}
}
