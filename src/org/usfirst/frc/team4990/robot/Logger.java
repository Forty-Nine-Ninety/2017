package org.usfirst.frc.team4990.robot;

import org.usfirst.frc.team4990.robot.subsystems.DriveTrain;

//testing 1st commit now.

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