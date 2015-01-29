package org.usfirst.frc.team4990.robot.subsystems;

import org.usfirst.frc.team4990.robot.Constants;
import org.usfirst.frc.team4990.robot.subsystems.motors.Motor;

import edu.wpi.first.wpilibj.Encoder;

public class Gearbox {
	private Motor motor1;
	private Motor motor2;
	
	private Encoder encoder;
	
	public enum RobotSide {
		Left, Right
	}
	
	public Gearbox(Motor motor1, Motor motor2, int encoderChannelA, int encoderChannelB, RobotSide robotSide) {
		this.motor1 = motor1;
		this.motor2 = motor2;
																	//not sure if left or right should be reversed
		this.encoder = new Encoder(encoderChannelA, encoderChannelB, robotSide == RobotSide.Left);
		//TODO: implement gear ratio math to find out how far the wheel travels per pulse
		
		double outputToEncoderGearRatio = Constants.numTeethOnEncoderShaftGear / Constants.numTeethOnOutputShaftGear;
		this.encoder.setDistancePerPulse(outputToEncoderGearRatio * Constants.feetPerWheelRevolution);
		this.encoder.setMinRate(Constants.gearboxEncoderMinRate);
		this.encoder.setSamplesToAverage(Constants.gearboxEncoderSamplesToAvg);
	}
	
	public void setSpeed(double speed) {
		this.motor1.setPower(speed);
		this.motor2.setPower(speed);
	}
	
	/*
	 * returned in ft
	 */
	public double getDistanceTraveled() {
		return this.encoder.getDistance();
	}
	
	/*
	 * returned in ft/s
	 */
	public double getCurrentVelocity() {
		return this.encoder.getRate();
	}
	
	public void resetDistanceTraveled() {
		this.encoder.reset();
	}
}
