package org.usfirst.frc.team4990.robot.subsystems;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;

public class LimitSwitch {
	private DigitalInput limitSwitch;
	private Counter counter;
	
	public LimitSwitch(int digitalIOChannel) {
		this.limitSwitch = new DigitalInput(digitalIOChannel);
		this.counter = new Counter(this.limitSwitch);
	}
	
	public boolean isSwitched() {
		return this.counter.get() > 0;
	}
	
	public void reset() {
		this.counter.reset();
	}
}
