package org.usfirst.frc.team4990.robot.controllers;

import org.usfirst.frc.team4990.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4990.robot.controllers.AutoDriveTrainScripter;

public class SimpleAutoDriveTrainScripter extends AutoDriveTrainScripter {
	
	protected void init() {
		forward(1000, .5);
		
		
		//Please don't mess below if you want to change auto scripting
		super.init();
	}
	public SimpleAutoDriveTrainScripter(DriveTrain dtrain) {
		super(dtrain);
		init();
	}
	
	public void update() {
		super.update();
	}

}
