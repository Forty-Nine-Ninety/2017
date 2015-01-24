package org.usfirst.frc.team4990.robot;

import org.usfirst.frc.team4990.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4990.robot.subsystems.Gearbox;


public class Logger {
	private Dashboard dashboard;
	
	public Logger(Dashboard dashboard) {
		this.dashboard = dashboard;
	}
	
	public void profileDriveTrain(DriveTrain driveTrain) {
		this.dashboard.sendValue("leftSetSpeed", driveTrain.getLeftSetSpeed());
		this.dashboard.sendValue("rightSetSpeed", driveTrain.getRightSetSpeed());
		
		this.dashboard.sendValue("leftSpeed", driveTrain.getLeftVelocity());
		this.dashboard.sendValue("rightSpeed", driveTrain.getRightVelocity());
	}
	
	public void profileEncoderSpeed(Gearbox gearbox){
		this.dashboard.sendValue("encoderDistance", gearbox.getDistanceTraveled());
		this.dashboard.sendValue("encoderVelocity", gearbox.getCurrentVelocity());
	}
	
}