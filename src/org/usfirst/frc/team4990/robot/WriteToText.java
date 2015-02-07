package org.usfirst.frc.team4990.robot;

import java.io.*;
//a basic to setting up writing to text;
public class WriteToText {
	private String pathtotext;
	private boolean writetotext;
	
	WriteToText(String path, boolean write){
		this.pathtotext = path;
		this.writetotext = write;
	}
	private void write(String text) throws FileNotFoundException{
//temporary path. Going to change it later. Currently going to /usr/games
		Printwriter print = new Printwriter(new Filewriter("/usr/games"));
		print.println("test");
		print.close();
		
	}
}