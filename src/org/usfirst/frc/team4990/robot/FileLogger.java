package org.usfirst.frc.team4990.robot;

import java.io.*;

public class FileLogger {

	private File log;
	
	FileLogger(String filePath)
	{
		log = new File(filePath);
		if(!log.isFile() )
		{
			try {
				log.createNewFile();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
		}
			
	}
	
	public void writeToLog(String text)
	{
		try {
			PrintWriter pw = new PrintWriter(
					new BufferedWriter(
							new FileWriter(log)));
			
			pw.println(text);
		
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
