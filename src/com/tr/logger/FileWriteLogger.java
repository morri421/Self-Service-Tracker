package com.tr.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Writes line to file, creates file if it doesn't exist
 *
 */
public class FileWriteLogger {

	public void writeToLog(Object[] temp, String button) {

		File file = new File("SSlog.txt");

		// creates file if it doesn't exist
		if (!file.exists()) {
			try {
				file.createNewFile();
				FileWriter writer = new FileWriter(file);
				writer.append(button + "//" + temp[0].toString() + "//" + temp[1].toString() + "//");
				writer.flush();
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			try {
				// If file does exist, just add the line to the file
				FileWriter writer = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(writer);
				PrintWriter out = new PrintWriter(bw);
				bw.newLine();
				out.write(button + "//" + temp[0].toString() + "//" + temp[1].toString() + "//");
				out.flush();
				out.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}