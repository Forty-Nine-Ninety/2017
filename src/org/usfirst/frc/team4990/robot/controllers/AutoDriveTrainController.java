package org.usfirst.frc.team4990.robot.controllers;

import java.util.Date;

import org.usfirst.frc.team4990.robot.lib.MotionProfile;
import org.usfirst.frc.team4990.robot.lib.PositionPIDLoop;
import org.usfirst.frc.team4990.robot.subsystems.DriveTrain;

public class AutoDriveTrainController {
	private DriveTrain driveTrain;
	
	private PositionPIDLoop pidLoop;
	private MotionProfile motionProfile;
	
	private Date motionProfileStart;
	
	public AutoDriveTrainController(DriveTrain driveTrain, double Kp, double Kd, double Kv, double Ka) {
		this.driveTrain = driveTrain;
		
		this.pidLoop = new PositionPIDLoop(Kp, Kd, Kv, Ka);
	}
	
	public void setMotionProfile(MotionProfile motionProfile) {
		this.motionProfile = motionProfile;
		this.motionProfileStart = new Date();
	}
	
	public void updateDriveTrainState() {
		MotionProfile.ProfileValues nextVelAndAcc = this.motionProfile.getProfileValuesAt(this.motionProfileStart.getTime());
		double nextVelocity = nextVelAndAcc.velocity, 
			   nextAcceleration = nextVelAndAcc.acceleration;
		
		/*
		 * use of the left distance and velocity is arbitrary--in theory both sides should be 
		 * driving at the same speed.
		 */
		double currentDistanceTraveled = this.driveTrain.getLeftDistanceTraveled();
		double currentVelocity = this.driveTrain.getLeftVelocity();
		
		this.pidLoop.setGoal(this.motionProfile.getDistanceToTravel(), nextVelocity, nextAcceleration);
		double newVelocity = this.pidLoop.getNextVelocity(currentDistanceTraveled);
		
		//TODO: call scaleToPower on newVelocity to turn it into power
		double newPower = newVelocity;
		
		this.driveTrain.setSpeed(newPower, newPower);
	}
}
