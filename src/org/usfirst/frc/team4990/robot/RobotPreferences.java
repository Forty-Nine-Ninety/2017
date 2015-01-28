package org.usfirst.frc.team4990.robot;

import edu.wpi.first.wpilibj.Preferences;
public class RobotPreferences {
	
	Preferences prefs;
	
	boolean reverseTurningFlipped;
	double maxTurnRadius;
	double smoothDriveAccTime;
	
	//method to load preferences
	void startupSetPref(){
		reverseTurningFlipped = prefs.getBoolean("reverseTurningFlipped", false);
		maxTurnRadius = prefs.getDouble("maxTurnRadius", 1.0);
		smoothDriveAccTime = prefs.getDouble("smoothDriveAccTime", 125);
	}
}