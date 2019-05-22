package com.tr.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import com.tr.logger.Logger;

import net.miginfocom.swing.MigLayout;

/**
 * User interface for vertical placement on monitor Listens for a call with
 * process check. If "call" is active, you can click a self-service terminology
 * button to register a self-service mention. This will increase the subsequent
 * labels and calculate a percentage of daily mentions.
 */
public class LongInterface implements ActionListener {

	public JFrame frame;

	public int intstreak = 0;
	public int totalmentions = 0;
	public int totalcalls = 0;
	public float percentagecalls = 0;
	public int feedbackcount = 0;

	JLabel snumber = new JLabel(Integer.toString(intstreak));
	JLabel tcallsnum = new JLabel(Integer.toString(totalcalls));
	JLabel percent = new JLabel(Float.toString(percentagecalls));
	JLabel feedback = new JLabel(Integer.toString(feedbackcount));

	public Boolean firstclick = true; // to make sure you can't report more than one self-service completion per call

	public Boolean samecall = false; // to let the program know if you're on the same call

	public Boolean disabled = false; // a toggle to disable the program from giving popups

	public void init() {
		frame = new JFrame("Self-Service");
		frame.setPreferredSize(new Dimension(215, 735));
		frame.setLayout(new MigLayout("", "", "nogrid"));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		JLabel tcalls = new JLabel("Total Calls Taken:");
		frame.add(tcalls, "gapright 10");

		frame.add(tcallsnum, "gapleft 61");

		JLabel streak = new JLabel("Streak:");
		frame.add(streak, "newline, gaptop 5");

		frame.add(snumber, "gapleft 120");

		JLabel mentionpercent = new JLabel("Percentage of Mentions:");
		frame.add(mentionpercent, "newline, gaptop 5");

		frame.add(percent, "gapleft 18");

		JLabel feedbacksubmit = new JLabel("Feedback Submitted:");
		frame.add(feedbacksubmit, "newline, gaptop 5");

		frame.add(feedback, "gapleft 42");

		JLabel tcallsadjuster = new JLabel("<html><u>Total Calls Adjustment</u></html>");
		frame.add(tcallsadjuster, "newline, gapright 10, gaptop 10");

		// Action listener for total call adjustment plus sign
		ActionListener addcallAction = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				totalcalls = totalcalls + 1;
				String totalString = Integer.toString(totalcalls);
				tcallsnum.setText(totalString);

				percentagecalls = (float) totalmentions / (float) totalcalls * 100;
				String percentString = Float.toString(percentagecalls);
				percent.setText(percentString);

				// Logger newlog = new Logger();
				// newlog.createLog("---VOIDED LAST CALL---");
			}
		};

		JButton addTotalCall = new JButton("+");
		addTotalCall.setFont(new Font("Arial", Font.BOLD, 15));
		addTotalCall.setMargin(new Insets(0, 0, 0, 0));
		addTotalCall.setPreferredSize(new Dimension(25, 25));
		addTotalCall.addActionListener(addcallAction);
		frame.add(addTotalCall, "newline");

		// Action listener for total call adjustment minus sign
		ActionListener voidAction = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				totalcalls = totalcalls - 1;
				String totalString = Integer.toString(totalcalls);
				tcallsnum.setText(totalString);

				percentagecalls = (float) totalmentions / (float) totalcalls * 100;
				String percentString = Float.toString(percentagecalls);
				percent.setText(percentString);

				// Logger newlog = new Logger();
				// newlog.createLog("---VOIDED LAST CALL---");

			}
		};

		JButton voidCall = new JButton("-");
		voidCall.setFont(new Font("Arial", Font.BOLD, 15));
		voidCall.setMargin(new Insets(0, 0, 0, 0));
		voidCall.setPreferredSize(new Dimension(23, 23));
		voidCall.addActionListener(voidAction);
		frame.add(voidCall);
		//

		JLabel mcallsadjuster = new JLabel("<html><u>Mention Adjustment</html></u>");
		frame.add(mcallsadjuster, "newline, gapright 10");

		// Action listener for mention adjustment plus sign
		ActionListener addMentionAction = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				intstreak = intstreak + 1;
				totalmentions = totalmentions + 1;

				String streakString = Integer.toString(intstreak);
				snumber.setText(streakString);

				String totalString = Integer.toString(totalcalls);
				tcallsnum.setText(totalString);

				percentagecalls = (float) totalmentions / (float) totalcalls * 100;

				String percentString = Float.toString(percentagecalls);
				percent.setText(percentString);

				// Logger newlog = new Logger();
				// newlog.createLog("---Added Late Mention---");

			}
		};

		JButton addMention = new JButton("+");
		addMention.setFont(new Font("Arial", Font.BOLD, 15));
		addMention.setMargin(new Insets(0, 0, 0, 0));
		addMention.setPreferredSize(new Dimension(23, 23));
		addMention.addActionListener(addMentionAction);
		frame.add(addMention, "newline");

		// Action listener for mention adjustment minus sign
		ActionListener removeMentionAction = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				intstreak = intstreak - 1;
				totalmentions = totalmentions - 1;

				String streakString = Integer.toString(intstreak);
				snumber.setText(streakString);

				String totalString = Integer.toString(totalcalls);
				tcallsnum.setText(totalString);

				percentagecalls = (float) totalmentions / (float) totalcalls * 100;

				String percentString = Float.toString(percentagecalls);
				percent.setText(percentString);

				// Logger newlog = new Logger();
				// newlog.createLog("---Added Late Mention---");

			}
		};

		JButton removeMentionBtn = new JButton("-");
		removeMentionBtn.setFont(new Font("Arial", Font.BOLD, 15));
		removeMentionBtn.setMargin(new Insets(0, 0, 0, 0));
		removeMentionBtn.setPreferredSize(new Dimension(23, 23));
		removeMentionBtn.addActionListener(removeMentionAction);
		frame.add(removeMentionBtn);

		// Creates an action to add to feedback metric and adds to button
		ActionListener addfeedback = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				feedbackcount = feedbackcount + 1;
				String totalFeedback = Integer.toString(feedbackcount);
				feedback.setText(totalFeedback);
			}
		};

		JButton submitfeedback = new JButton("Submitted Feedback");
		submitfeedback.addActionListener(addfeedback);
		frame.add(submitfeedback, "newline, gaptop 10");
		//

		JButton f1 = new JButton("F1");
		f1.addActionListener(this);
		frame.add(f1, "newline, gaptop 25");

		JButton userbulletin = new JButton("User Bulletin");
		userbulletin.addActionListener(this);
		frame.add(userbulletin, "newline, gaptop 5");

		JButton alertsnotices = new JButton("Alerts and Notices");
		alertsnotices.addActionListener(this);
		frame.add(alertsnotices, "newline, gaptop 5");

		JButton hhtc = new JButton("Help-and-How-to-Center");
		hhtc.addActionListener(this);
		frame.add(hhtc, "newline, gaptop 5");

		JButton help = new JButton("Help article");
		help.addActionListener(this);
		frame.add(help, "newline, gaptop 5");

		JButton ideas = new JButton("Ideas Community");
		ideas.addActionListener(this);
		frame.add(ideas, "newline, gaptop 5");

		JButton irs = new JButton("IRS");
		irs.addActionListener(this);
		frame.add(irs, "newline, gaptop 5");

		JButton tax = new JButton("Tax Subject Index");
		tax.addActionListener(this);
		frame.add(tax, "newline, gaptop 5");

		JButton form = new JButton("Form Instructions");
		form.addActionListener(this);
		frame.add(form, "newline, gaptop 5");

		JButton search = new JButton("Search for Assistance");
		search.addActionListener(this);
		frame.add(search, "newline, gaptop 5");

		JButton research = new JButton("Right Click Web Research");
		research.addActionListener(this);
		frame.add(research, "newline, gaptop 5");

		// Checks to see if disable toggle is checked or unchecked, needs item listener
		// to work
		ItemListener disableCheck = new ItemListener() {
			public void itemStateChanged(ItemEvent checkE) {

				if (checkE.getStateChange() == 1) {
					disabled = true;
					// System.out.println(disabled);
				} else {
					disabled = false;
					// System.out.println(disabled);
				}

			}
		};

		JCheckBox disableTracker = new JCheckBox("Disable");
		disableTracker.setSelected(false);
		disableTracker.addItemListener(disableCheck);
		frame.add(disableTracker, "newline, gaptop 20, gapleft 115");

		// A timed popup that will remind employees to use self-service terminology
		ActionListener popupTimer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (disabled != true) {
					JOptionPane.showMessageDialog(null,
							"Are you mentioning H&HTC? You are at " + percentagecalls + "% for the day.",
							"Self Service Status", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		};

		// 7200000
		Timer poptimer = new Timer(7200000, popupTimer);
		poptimer.setRepeats(true);
		poptimer.start();

		frame.getContentPane();

		frame.pack();
		frame.setVisible(true);

		// This is a constantly resetting timer actionlistener that checks the local
		// machine for a process
		ActionListener timerActions = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// ...Perform a task...
				if (disabled != true) { // added an if statement to disable
					ProcessCheck test = new ProcessCheck();
					Integer temp = test.testforCall();
					// System.out.println(temp);
					if (temp > 40000 && samecall == false) { // this is where you determine what process speed it looks
																// for
						if (firstclick == true) {
							intstreak = 0;
							String streakString = Integer.toString(intstreak);
							snumber.setText(streakString);
						}
						totalcalls = totalcalls + 1;
						samecall = true;
						firstclick = true;

						String totalString = Integer.toString(totalcalls);
						tcallsnum.setText(totalString);

						percentagecalls = (float) totalmentions / (float) totalcalls * 100;
						String percentString = Float.toString(percentagecalls);
						percent.setText(percentString);

						Logger newlog = new Logger();
						newlog.createLog("---NEW_CALL---");

					} else if (temp < 25000 && samecall == true && temp != 0) { // this is also where you determine what
																				// process speed it looks for
						samecall = false;

					}
				}
			}
		};

		Timer timer = new Timer(500, timerActions);
		timer.setRepeats(true);
		timer.start();

	}

	// Action for clicking the self-service terminology buttons, will add calls if
	// applicable
	public void actionPerformed(ActionEvent clicked) {
		// TODO Auto-generated method stub
		if (disabled != true) { // added an if statement to disable clicks
			String buttonname = ((JButton) clicked.getSource()).getText();
			Logger newlog = new Logger();
			newlog.createLog(buttonname);

			if (firstclick == true && samecall == true) {
				intstreak = intstreak + 1;
				totalmentions = totalmentions + 1;
				firstclick = false;
			} else {
				firstclick = false;
			}

			String streakString = Integer.toString(intstreak);
			snumber.setText(streakString);

			String totalString = Integer.toString(totalcalls);
			tcallsnum.setText(totalString);

			percentagecalls = (float) totalmentions / (float) totalcalls * 100;

			if (totalcalls != 0) {
				String percentString = Float.toString(percentagecalls);
				percent.setText(percentString);

			}
		}
	}

}
