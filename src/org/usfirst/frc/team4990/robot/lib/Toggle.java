package org.usfirst.frc.team4990.robot.lib;

import java.util.Date;

public class Toggle {
	private boolean toggleState;
	private Date timeLastToggled;
	
	private final int timeUntilNextToggle;
	
	public Toggle(int timeUntilNextToggle) {
		this.timeUntilNextToggle = timeUntilNextToggle;
	}
	
	public void toggle() {
		long timeSinceLastToggle = (new Date()).getTime() - this.timeLastToggled.getTime();
		if (timeSinceLastToggle >= timeUntilNextToggle) {
			this.toggleState = !this.toggleState;
			this.timeLastToggled = new Date();
		}
	}
	
	public boolean isToggled() {
		return this.toggleState;
	}
}
