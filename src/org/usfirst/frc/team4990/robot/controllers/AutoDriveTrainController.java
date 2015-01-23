package org.usfirst.frc.team4990.robot.controllers;

public class AutoDriveTrainController {
	private DriveTrain driveTrain;
	
	private MotionProfile motionProfile;
	
	public AutoDriveTrainController(DriveTrain driveTrain) {
		this.driveTrain = driveTrain;
	}
	
	public void setMotionProfile(MotionProfile motionProfile) {
		this.motionProfile = motionProfile;
	}
	
	public void updateDriveTrainState() {
		
	}
}
