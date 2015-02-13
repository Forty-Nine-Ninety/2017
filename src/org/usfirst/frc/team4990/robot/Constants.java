package org.usfirst.frc.team4990.robot;

public class Constants {
	
	/*
	 * units: feet
	 */
	 public static double robotWidth = 2;
	/*
	 * units: feet
	 */
	 
	public static final double defaultMaxTurnRadius = 1.0;

	/*
	 * units: feet
	 */
	
	public static final double defaultAccelerationTime = 250;
	
	//TODO: figure out actual pulses per revolution and stop compensating by a factor of 2
	public static final int pulsesPerRevolution = 250;
	/*
	 * units: feet/s
	 */
	public static final double gearboxEncoderMinRate = 0.0;
	public static final int gearboxEncoderSamplesToAvg = 5;
	
	/*
	 * units: feet/revolution
	 */
	public static final double feetPerWheelRevolution = 4.0 / 12.0 * Math.PI;
}
