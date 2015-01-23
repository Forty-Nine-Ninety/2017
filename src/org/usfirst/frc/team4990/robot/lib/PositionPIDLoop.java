package org.usfirst.frc.team4990.robot.lib;

import java.util.Date;

public class PositionPIDLoop {
	private double Kp;
	private double Kd;
	private double Kv;
	private double Ka;
	
	private double goalPos;
	private double goalVel;
	private double goalAcc;
	
	private double lastPos;
	private Date lastUpdate;
	
	public PositionPIDLoop(double Kp, double Kd, double Kv, double Ka) {
		this.Kp = Kp;
		this.Kd = Kd;
		this.Kv = Kv;
		this.Ka = Ka;
		
		this.lastPos = 0;
		this.lastUpdate = new Date();
	}
	
	public void setGoal(double goalPos, double goalVel, double goalAcc) {
		this.goalPos = goalPos;
		this.goalVel = goalVel;
		this.goalAcc = goalAcc;
	}
	
	public double getNextPower(double currPos) {
		double posError = this.goalPos - currPos;
		
		long dt = ((new Date()).getTime() - this.lastUpdate.getTime()) / 1000; //divide by 1000 to convert to seconds
		double velocity = (currPos - this.lastPos) / dt;
		double velError = this.goalVel - velocity;
				
		this.lastPos = currPos;
		this.lastUpdate = new Date();
		
		return this.Kp * posError + Kd * velError + Kv * this.goalVel + Ka * this.goalAcc;
	}
}
