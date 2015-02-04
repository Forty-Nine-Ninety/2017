package org.usfirst.frc.team4990.robot;

import java.io.*;
//a basic thing to write and append text
public class WriteToText {
	private String pathtotext;
	private boolean writetotext;
	
	WriteToText(String path, boolean write){
		this.pathtotext = path;
		this.writetotext = write;
	}
	private void write(String path){
		try{
		File file = new File(path);
		if(!file.exists()){
			file.createNewFile();
			}
		PrintWriter print = new PrintWriter(new BufferedWriter(new FileWriter(file,true)));
		print.println("test");
		print.close();
		}catch(IOException ioe){
			System.out.println("Exception occurred");
			ioe.printStackTrace();
		}
		
	}
}
