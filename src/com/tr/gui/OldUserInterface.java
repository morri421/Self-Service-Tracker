package com.tr.gui;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

import com.tr.logger.Logger;

import net.miginfocom.swing.MigLayout;



/**
 * This is an older version of the interface that is no longer in use.
 *
 */
public class OldUserInterface implements ActionListener {

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
	
	public Boolean firstclick = true;
	
	public Boolean samecall = false;
	
	public Boolean disabled = false;
	
	public void init() {
        frame = new JFrame("Self-Service");
        frame.setPreferredSize(new Dimension(930, 160));
        frame.setLayout(new MigLayout("","","nogrid"));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        //Container one = frame.getContentPane();
        //one.setLayout(new MigLayout());
        
        //Creates void action and assigns it to void button
        ActionListener voidAction = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	if (totalcalls != 0) {
            		totalcalls = totalcalls - 1;
            		String totalString = Integer.toString(totalcalls);
            		tcallsnum.setText(totalString);
            		
            		percentagecalls = (float)totalmentions /(float)totalcalls * 100; 
            		String percentString = Float.toString(percentagecalls);
            		percent.setText(percentString);
            		
            		Logger newlog = new Logger();
        			newlog.createLog("---VOIDED LAST CALL---");
        			
            	}
            }
        };
        
        JButton voidCall = new JButton("Void Call");
		voidCall.addActionListener(voidAction);
		frame.add(voidCall, "gapright 5, gapbottom 20");
		//
        //Creates add action and assigns it to add call button
		ActionListener addCallAction = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	if (totalcalls != 0) {
            		intstreak = intstreak + 1;
        			totalmentions = totalmentions + 1;
            		
        			String streakString = Integer.toString(intstreak);
        			snumber.setText(streakString);
        			
        			String totalString = Integer.toString(totalcalls);
        			tcallsnum.setText(totalString);
        			
        			percentagecalls = (float)totalmentions /(float)totalcalls * 100; 
        		
        			String percentString = Float.toString(percentagecalls);
        			percent.setText(percentString);
            		
            		Logger newlog = new Logger();
        			newlog.createLog("---Added Late Mention---");
            	}
            }
        };
		
		JButton addCall = new JButton("Add Mention");
		addCall.addActionListener(addCallAction);
		frame.add(addCall, "gapright 5, gapbottom 20");
		//
		//Creates an action to add to feedback metric and adds to button
		ActionListener addfeedback = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	
            		feedbackcount = feedbackcount + 1;
            		String totalFeedback = Integer.toString(feedbackcount);
            		feedback.setText(totalFeedback);
            }
        };
		//
		JButton submitfeedback = new JButton("Submitted Feedback");
		submitfeedback.addActionListener(addfeedback);
		frame.add(submitfeedback);
		
		JLabel streak = new JLabel("Streak:");
		frame.add(streak, "gapleft 10, gapright 10");
		
		//JLabel snumber = new JLabel("0");
		frame.add(snumber, "gapright 20");
		
		JLabel tcalls = new JLabel("Total Calls Taken:");
		frame.add(tcalls, "gapright 10");
		
		//JLabel tcallsnum = new JLabel("0");
		frame.add(tcallsnum, "gapright 20");
		
		JLabel mentionpercent = new JLabel("Percentage of Mentions:");
		frame.add(mentionpercent, "gapright 10");
		
		//JLabel percent = new JLabel("0");
		frame.add(percent, "gapright 20");
		
		JLabel feedbacksubmit = new JLabel("Feedback Submitted:");
		frame.add(feedbacksubmit, "gapright 10");
		
		frame.add(feedback, "gapright 20");
	
		JButton f1 = new JButton("F1");
		f1.addActionListener(this);
		frame.add(f1, "newline");
		
		JButton userbulletin = new JButton("User Bulletin");
		userbulletin.addActionListener(this);
		frame.add(userbulletin);
		
		JButton alertsnotices = new JButton("Alerts and Notices");
		alertsnotices.addActionListener(this);
		frame.add(alertsnotices);
		
		JButton hhtc = new JButton("Help-and-How-to-Center");
		hhtc.addActionListener(this);
		frame.add(hhtc);
		
		JButton help = new JButton("Help article");
		help.addActionListener(this);
		frame.add(help);
		
		JButton ideas = new JButton("Ideas Community");
		ideas.addActionListener(this);
		frame.add(ideas);
		
		JButton irs = new JButton("IRS");
		irs.addActionListener(this);
		frame.add(irs, "newline");
		
		JButton tax = new JButton("Tax Subject Index");
		tax.addActionListener(this);
		frame.add(tax);
		
		JButton form = new JButton("Form Instructions");
		form.addActionListener(this);
		frame.add(form);
	
		JButton search = new JButton("Search for Assistance");
		search.addActionListener(this);
		frame.add(search);
		
		JButton research = new JButton("Right Click Web Research");
		research.addActionListener(this);
		frame.add(research);
		
		// Checks to see if disable toggle is checked or unchecked, needs item listener to work
		ItemListener disableCheck = new ItemListener() {
            public void itemStateChanged(ItemEvent checkE) {
            	
            	if (checkE.getStateChange() == 1) {
            		disabled = true;
            		//System.out.println(disabled);
            	} else {
            		disabled = false;
            		//System.out.println(disabled);
            	}
            	
            }
        };
		
		JCheckBox disableTracker = new JCheckBox("Disable");
        disableTracker.setSelected(false);
        disableTracker.addItemListener(disableCheck);
        frame.add(disableTracker, "gapleft 155");
		//
        
        ActionListener popupTimer = new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		if (disabled != true) {
        		JOptionPane.showMessageDialog(null, "Are you mentioning H&HTC? You are at " + percentagecalls + "% for the day.", "Self Service Status", JOptionPane.INFORMATION_MESSAGE);
        		}
        	}	
        };
       
        	//7200000
        Timer poptimer = new Timer(7200000 ,popupTimer);
        poptimer.setRepeats(true);
        poptimer.start();
        
        
        frame.getContentPane();

        frame.pack();
        frame.setVisible(true);
        
         
        
        ActionListener timerActions = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //...Perform a task...
            	if (disabled != true) { //added an if statement to disable
            		ProcessCheck test = new ProcessCheck();
            		Integer temp = test.testforCall();
            		//System.out.println(temp);
            		if (temp > 40000 && samecall == false) {
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
        			
            			percentagecalls = (float)totalmentions /(float)totalcalls * 100;
            			String percentString = Float.toString(percentagecalls);
            			percent.setText(percentString);
        			
            			Logger newlog = new Logger();
            			newlog.createLog("---NEW_CALL---");
        			
            		} else if (temp < 40000 && samecall == true && temp != 0) {
            			samecall = false;
        			
        			}
            	}
            }
        };
       
        Timer timer = new Timer(500 ,timerActions);
        timer.setRepeats(true);
        timer.start();
     
    }
	
	//private void createComponents(Container container) {
		
		//container.setLayout(new MigLayout());
		
		//MetricPanel test = new MetricPanel();
		//container.add(test);
		//container.add(new ButtonPanel(), "cell 0 1");

	
	public void actionPerformed(ActionEvent clicked) {
		// TODO Auto-generated method stub
		if (disabled != true) { //added an if statement to disable clicks
			String buttonname= ((JButton) clicked.getSource()).getText();
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
			
			percentagecalls = (float)totalmentions /(float)totalcalls * 100; 
			
			if (totalcalls != 0) {
			String percentString = Float.toString(percentagecalls);
			percent.setText(percentString);
					
			}
		}
	}
	
}
