package org.usfirst.frc.team4990.robot.subsystems;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;

public class LimitSwitch {
	private DigitalInput limitSwitch;
	private Counter counter;
	
	private int currentCount = 0;
	private int lastCount = 0;
	
	private int counterSensitivity;
	
	public LimitSwitch(int digitalIOChannel, int counterSensitivity) {
		this.limitSwitch = new DigitalInput(digitalIOChannel);
		this.counter = new Counter(this.limitSwitch);
		this.counterSensitivity = counterSensitivity;
	}
	
	public void update() {
		this.lastCount = this.currentCount;
		this.currentCount = this.counter.get();
	}
	
	public boolean isSwitched() {
		return this.currentCount - this.lastCount > this.counterSensitivity;
	}
}
