package com.tr.gui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * This checks local machine for a specific process and returns the amount of
 * processing that is done.
 *
 */
public class ProcessCheck {

	public int testforCall() {

		try {

			// Pulls system processes form local machine
			String line;
			Process p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe");
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = input.readLine()) != null) {
				String[] newline = line.split(" ");

				if (newline[0].equals("DesktopRecordProcess.exe")) {

					ArrayList<String> listofStrings = new ArrayList<String>();
					for (String x : newline) {
						if (!x.equals("")) {
							listofStrings.add(x);
						}
					}
					String temp = listofStrings.get(4);
					temp = temp.replace(",", "");
					return Integer.parseInt(temp);

				}

			}
			input.close();
		} catch (Exception err) {
			err.printStackTrace();
		}
		return 0;

	}
}