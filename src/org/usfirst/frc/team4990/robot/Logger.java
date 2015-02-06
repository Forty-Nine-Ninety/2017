package org.usfirst.frc.team4990.robot;

import java.io.*;
import java.text.*;

import org.usfirst.frc.team4990.robot.subsystems.DriveTrain;

public class Logger {
	
	private String path;
	
	public Logger(String path){
		this.path = path;
	}
	
	public void toFile(DriveTrain driveTrain){
		//runs write method and prints the values we want
		write(path, "Left Speed", String.valueOf(driveTrain.getLeftSetSpeed()));
		write(path, "Right Speed", String.valueOf(driveTrain.getRightSetSpeed()));
		write(path, "Left Velocity", String.valueOf(driveTrain.getLeftVelocity()));
		write(path, "Right Velocity", String.valueOf(driveTrain.getRightVelocity()));
		write(path, "Left Distance", String.valueOf(driveTrain.getLeftDistanceTraveled()));
		write(path, "Right Distance", String.valueOf(driveTrain.getRightDistanceTraveled()));
		
	}
	
	private void write(String path, String name, String text) {
		//takes in a path and prints text to file
		try{
		File file = new File(path);
		if(!file.exists()){
			file.createNewFile();
			}
		PrintWriter print = new PrintWriter(new BufferedWriter(new FileWriter(file,true)));
		print.println(getDate() + " " + name + ": " + text);
		print.close();
		}catch(IOException ioe){
			System.out.println("Exception occurred");
			ioe.printStackTrace();
		}
		
	}
	
	private String getDate(){
		DateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd/ HH:mm:ss:SSS");
		java.util.Date date = new java.util.Date();
		
		return dateformat.format(date);
	}

	public void logInit() {
		//prints start up log init file!
		write(path, "Startup", "Log system has started and loaded");
	}
}