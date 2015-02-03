package com.gryffingear.y2015.utilities;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;

/**
 * Usage: new FileLogger("/home/lvuser/logs/"); 
 * Todo: better comments.
 * @author Jeremy
 *
 */
public class FileLogger
{

	public FileLogger(String logDir)
	{
		// Get a copy of the existing output streams
		PrintStream out = System.out;
		PrintStream err = System.err;
		// Create a filename
		Date today = new Date();
		DriverStation ds = DriverStation.getInstance();
		String alliance = (ds.getAlliance() == Alliance.Red) ? "RED":"BLUE";
		String FMS = (ds.isFMSAttached()) ? "MATCH" : "PRACTICE";
		// Replace the existing output streams with the file writers
		System.setOut(new PrintStream(new FileWriter(logDir+"out/" + FMS + "_" + alliance + "_" + today.toString() + ".txt", out)));
		System.setErr(new PrintStream(new FileWriter(logDir+"err/" + FMS + "_" + alliance + "_" + today.toString()+ ".txt", err)));
	}
	
	private class FileWriter extends OutputStream
	{
		private PrintStream consoleout;
		private BufferedOutputStream fileout;
		public FileWriter(String filename, PrintStream original)
		{
			consoleout = original;
			try {
				fileout = new BufferedOutputStream(new FileOutputStream(filename));
			} catch (Throwable e) {
				// If we cannot write to the file (eg out of space, incorrect permissions, etc)
				// Print to the existing error stream, and don't attempt further writes to the file
				e.printStackTrace();
				fileout = null;
			}
		}

		@Override
		public void write(int b) throws IOException {
			
			// If we are able to write to a file, do so.
			if(fileout != null)
			{
				fileout.write(b);
				// if the character is a newline, force the file to be written.
				// This will help ensure that we keep as much data as pssible, even if the robot is suddenly powered off
				if(b == '\n')
				{
					fileout.flush();
				}
			}
			
			// Write to the original output stream (eg Netconsole)
			consoleout.write(b);
		}

	}
	
}