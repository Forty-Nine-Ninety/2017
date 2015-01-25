package org.usfirst.frc.team4990.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Dashboard {
 
	public void sendValue(String name, double value) {
		SmartDashboard.putNumber(name, value);
	}
	
	public void sendValue(String name, String value) {
		SmartDashboard.putString(name, value);
	}
	
	/*public void buttonCommand(String name, Sendable data){
		SmartDashboard.putData(name, data);
	}*/
}
