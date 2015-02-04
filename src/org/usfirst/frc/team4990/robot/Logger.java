package org.usfirst.frc.team4990.robot;

import java.io.*;
import java.text.*;

import org.usfirst.frc.team4990.robot.subsystems.DriveTrain;

public class Logger {
	
	private String path;
	
	public Logger(String path){
		this.path = path;
	}
	
	/*
	private Dashboard dashboard;
	
	public Logger(Dashboard dashboard, String path) {
		this.dashboard = dashboard;
	}
	
	
	public void profileDriveTrain(DriveTrain driveTrain) {
		this.dashboard.sendValue("leftSetSpeed", driveTrain.getLeftSetSpeed());
		this.dashboard.sendValue("rightSetSpeed", driveTrain.getRightSetSpeed());
		
		this.dashboard.sendValue("leftSpeed", driveTrain.getLeftVelocity());
		this.dashboard.sendValue("rightSpeed", driveTrain.getRightVelocity());
		
		this.dashboard.sendValue("leftDistanceTraveled", driveTrain.getLeftDistanceTraveled());
		this.dashboard.sendValue("rightDistanceTraveled", driveTrain.getLeftDistanceTraveled());
	}
	*/
	
	public void toFile(DriveTrain driveTrain){
		//runs write method and prints the values we want
		write(path, String.valueOf(driveTrain.getLeftSetSpeed()));
		write(path, String.valueOf(driveTrain.getRightSetSpeed()));
		write(path, String.valueOf(driveTrain.getLeftVelocity()));
		write(path, String.valueOf(driveTrain.getRightVelocity()));
		write(path, String.valueOf(driveTrain.getLeftDistanceTraveled()));
		write(path, String.valueOf(driveTrain.getRightDistanceTraveled()));
		
	}
	
	private void write(String path, String text) {
		//takes in a path and prints text to file
		try{
		File file = new File(path);
		if(!file.exists()){
			file.createNewFile();
			}
		PrintWriter print = new PrintWriter(new BufferedWriter(new FileWriter(file,true)));
		print.println(getDate() + text);
		print.close();
		}catch(IOException ioe){
			System.out.println("Exception occurred");
			ioe.printStackTrace();
		}
		
	}
	
	private String getDate(){
		DateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd/ HH:mm:ss");
		java.util.Date date = new java.util.Date();
		
		return dateformat.format(date);
	}

	public void logInit() {
		//prints start up log init file!
		write(path, "Log system has started and loaded");
	}
}