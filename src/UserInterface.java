import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

public class UserInterface implements ActionListener {

	public JFrame frame;
	
	public int intstreak = 0;
	public int totalmentions = 0;
	public int totalcalls = 0;
	public float percentagecalls = 0;
	public int feedbackcount;
	
	JLabel snumber = new JLabel(Integer.toString(intstreak));
	JLabel tcallsnum = new JLabel(Integer.toString(totalcalls));
	JLabel percent = new JLabel(Float.toString(percentagecalls));
	JLabel feedback = new JLabel(Integer.toString(feedbackcount));
	
	public Boolean firstclick = true;
	
	public Boolean samecall = false;
	
	public void init() {
        frame = new JFrame("Self-Service");
        frame.setPreferredSize(new Dimension(750, 130));
        frame.setLayout(new MigLayout());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
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
        
        JButton voidcall = new JButton("Void Call");
		voidcall.addActionListener(voidAction);
		frame.add(voidcall, "gapbottom 10");
		
		JLabel streak = new JLabel("Streak:");
		frame.add(streak, "split 2");
		
		//JLabel snumber = new JLabel("0");
		frame.add(snumber, "gapleft 20, split 2");
		
		JLabel tcalls = new JLabel("Total Calls Taken:");
		frame.add(tcalls, "split 2, gapleft 15, gapright 20");
		
		//JLabel tcallsnum = new JLabel("0");
		frame.add(tcallsnum,"split 2");
		
		JLabel mentionpercent = new JLabel("Percentage of Mentions:");
		frame.add(mentionpercent, "split 2");
		
		//JLabel percent = new JLabel("0");
		frame.add(percent, "gapleft 20");
		
		JLabel feedbacksubmit = new JLabel("Feedback Submitted:");
		frame.add(feedbacksubmit);
		
		frame.add(feedback, "gapleft 20");
	
		JButton userbulletin = new JButton("User Bulletin");
		userbulletin.addActionListener(this);
		frame.add(userbulletin, "cell 0 2");
		
		JButton alertsnotices = new JButton("Alerts and Notices");
		alertsnotices.addActionListener(this);
		frame.add(alertsnotices);
		
		JButton f1 = new JButton("F1");
		f1.addActionListener(this);
		frame.add(f1, "split 2");
		
		JButton hhtc = new JButton("Help-and-How-to-Center");
		hhtc.addActionListener(this);
		frame.add(hhtc, "split 2");
		
		JButton help = new JButton("Help article");
		help.addActionListener(this);
		frame.add(help, "split 2");
		
		JButton ideas = new JButton("Ideas Community");
		ideas.addActionListener(this);
		frame.add(ideas, "split 2");
		
		//JButton irs = new JButton("IRS");
		//irs.addActionListener(this);
		//frame.add(irs);
	
        frame.getContentPane();

        frame.pack();
        frame.setVisible(true);
        
         
        
        ActionListener timerActions = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //...Perform a task...
            	ProcessCheck test = new ProcessCheck();
        		Integer temp = test.testforCall();
        		
        		if (temp > 80000 && samecall == false) {
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
        			
        		} else if (temp < 50000 && samecall == true) {
        			samecall = false;
        			
        		}
            }
        };
        Timer timer = new Timer(1000 ,timerActions);
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
