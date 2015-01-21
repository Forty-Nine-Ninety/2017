package org.usfirst.frc.team4990.robot;

public class Logger {
	private Dashboard dashboard;
	
	public Logger(Dashboard dashboard) {
		this.dashboard = dashboard;
	}
	
	public void profileDriveTrain(DriveTrain driveTrain) {
		this.dashboard.sendValue("leftSetSpeed", driveTrain.getLeftSetSpeed());
		this.dashboard.sendValue("rightSetSpeed", driveTrain.getRightSetSpeed());
	}
}