package org.usfirst.frc.team4990.robot.controllers;

import java.util.Date;

import org.usfirst.frc.team4990.robot.lib.MotionProfile;
import org.usfirst.frc.team4990.robot.lib.PositionPIDLoop;
import org.usfirst.frc.team4990.robot.subsystems.DriveTrain;

public class AutoDriveTrainController {
	private DriveTrain driveTrain;
	
	private int delay;
	private int timeForward;
	private double velocity;
	
	private Date motionProfileStart;
	
	public AutoDriveTrainController(DriveTrain driveTrain) {
		this.driveTrain = driveTrain;
	}
	
	public void setMotionProfile(int delay, int timeForward, double velocity) {
		this.delay = delay;
		this.timeForward = timeForward;
		this.velocity = velocity;
		this.motionProfileStart = new Date();
	}
	
	public void updateDriveTrainState() {
		long timeSinceStart = (new Date()).getTime() - this.motionProfileStart.getTime();
		
		if (timeSinceStart > this.delay && timeSinceStart < (this.delay + this.timeForward)) {
			this.driveTrain.setSpeed(this.velocity, this.velocity);
		} else {
			this.driveTrain.setSpeed(0.0, 0.0);
		}
	}
}
