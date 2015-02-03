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
	
	/*
	 * units: milliseconds
	 */
	public static final int millisecondsPerCycle = 20;
	
	/*
	 * units: feet/s
	 */
	public static final double gearboxEncoderMinRate = 0.0;
	
	public static final int gearboxEncoderSamplesToAvg = 1;
	
	public static final int numTeethOnEncoderShaftGear = 19;
	public static final int numTeethOnOutputShaftGear = 45;
	
	/*
	 * units: feet/revolution
	 */
	public static final double feetPerWheelRevolution = .333333333;
}
