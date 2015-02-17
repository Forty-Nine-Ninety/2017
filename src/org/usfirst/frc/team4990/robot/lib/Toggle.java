package org.usfirst.frc.team4990.robot.lib;

import java.util.Date;

public class Toggle {
	private boolean toggleState;
	private Date timeLastToggled;
	
	private final int millisUntilNextToggle;
	
	public Toggle(int millisUntilNextToggle) {
		this.millisUntilNextToggle = millisUntilNextToggle;
		this.timeLastToggled = new Date(java.lang.System.currentTimeMillis() - this.millisUntilNextToggle);
	}
	
	public void setState(boolean newState) {
		long timeSinceLastToggle = (new Date()).getTime() - this.timeLastToggled.getTime();
		if (timeSinceLastToggle >= this.millisUntilNextToggle) {
			this.toggleState = newState;
			this.timeLastToggled = new Date();
		}
	}
	
	public void toggle() {
		setState(!this.toggleState);
	}
	
	public boolean isToggled() {
		return this.toggleState;
	}
}
