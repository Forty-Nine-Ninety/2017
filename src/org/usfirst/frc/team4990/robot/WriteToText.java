package org.usfirst.frc.team4990.robot;

import java.io.*;

public class WriteToText {
	private String pathtotext;
	private boolean writetotext;
	
	WriteToText(String path, boolean write){
		this.pathtotext = path;
		this.writetotext = write;
	}
	private void write(String text) throws FileNotFoundException{
		File file = new File("C:/Users/student/Desktop/test.txt");
		file.getParentFile().mkdirs();
		PrintWriter print = new PrintWriter(file);
		print.println("test");
		print.close();
		
	}
}