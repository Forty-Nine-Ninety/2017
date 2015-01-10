package org.usfirst.frc.team4990.robot;

import edu.wpi.first.wpilibj.Joystick;

public class F310Gamepad extends Joystick {
	public F310Gamepad(int joystickNumber) {
		super(joystickNumber);
	}
	
	/* the following methods provide semantic sugar over the raw
	 * axis and button inputs from the F310 Gamepad, as discovered
	 * by using the FRC Driver Station software.
	 */
	
	public double getLeftJoystickX() {
		return this.getRawAxis(0);
	}
	
	public double getLeftJoystickY() {
		return this.getRawAxis(1);
	}
	
	//According to Austin Chen, this method should have been named getMoneyFromLeftJ0y$tick
	public boolean getLeftJoystickPressed() {
		return this.getRawButton(11);
	}
	
	public double getRightJoystickX() {
		return this.getRawAxis(2);
	}
	
	public double getRightJoystickY() {
		return this.getRawAxis(3);
	}

	public boolean getRightJoystickPressed() {
		return this.getRawButton(12);
	}
	
	public boolean getXButtonPressed() {
		return this.getRawButton(1);
	}
	
	public boolean getAButtonPressed() {
		return this.getRawButton(2);
	}
	
	public boolean getBButtonPressed() {
		return this.getRawButton(3);
	}
	
	public boolean getYButtonPressed() {
		return this.getRawButton(4);
	}
	
	public boolean getLeftBumperPressed() {
		return this.getRawButton(5);
	}
	
	public boolean getRightBumperPressed() {
		return this.getRawButton(6);
	}
	
	public boolean getLeftTriggerPressed() {
		return this.getRawButton(7);
	}
	
	public boolean getRightTriggerPressed() {
		return this.getRawButton(8);
	}
}
