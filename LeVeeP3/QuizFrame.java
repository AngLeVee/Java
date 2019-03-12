//Angela LeVee (alevee@cnm.edu)
//LeVeeP3, QuizFrame.java
//Shows output and takes user input for use by quiz.java

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.border.Border;

public class QuizFrame extends JFrame
{
	private Quiz quizQs;
	private JTextPane jTxtPInfo;
	private JTextField jTxtInput;
	private Border jTxtBorder;
	private String name;
	private String theme;
	private String answer;
	private String result;
	private String grade;
	private int i;
	private int iRef;
	
	//Panels
	private JPanel p1;
	private JPanel p2;
	private GridLayout grid;
	private FlowLayout flow;
	
	//Buttons
	private JButton jbtNextI;
	private JButton jbtNextQ;
	private JButton jbtDone;
	private JButton jbtQuit;
	private JButton jbtReset;

	public QuizFrame ()
	{
		theme = "Video Games";
		quizQs = new Quiz ();
		
		initialize ();
		
		QRListener listen = new QRListener ();
		
		jbtNextI.addActionListener(new NextIListener ());
		jbtNextQ.addActionListener(new NextQListener ());
		jbtDone.addActionListener(new DoneListener ());
		jbtQuit.addActionListener(listen);
		jbtReset.addActionListener(listen);
	}	
	
	private void initialize ()
	{		
		//Text Pane
		StyledDocument doc = new DefaultStyledDocument();
		Style defaultStyle = doc.getStyle(StyleContext.DEFAULT_STYLE);
		StyleConstants.setAlignment(defaultStyle, StyleConstants.ALIGN_CENTER);
		jTxtPInfo =	new JTextPane (doc);
		jTxtPInfo.setEditable (false);
		jTxtPInfo.setOpaque (false);
		jTxtPInfo.setText ("Angela LeVee (alevee@cnm)\nLeVeeP3\n\nWelcome to my Java quiz on " + theme);
		
		//Text Field and Buttons
		jTxtBorder = BorderFactory.createLineBorder(Color.PINK, 1);
		jTxtInput =	new JTextField ();		
		jTxtInput.setBorder (jTxtBorder);
		jbtNextI = 	new JButton ("Next");
		jbtNextQ = 	new JButton ("Continue");
		jbtDone = 	new JButton ("Stop Quiz");
		jbtQuit =	new JButton ("Quit");
		jbtReset =	new JButton ("Restart");
		
		//Set up panel 1
		grid = new GridLayout (2, 1);
		p1 = new JPanel (grid);
		p1.add (jTxtPInfo);
		
		//Set up panel 2
		flow = new FlowLayout(FlowLayout.CENTER);
		p2 = new JPanel (flow);
		p2.add (jbtNextI);
		
		//Add panels
		add(p1, BorderLayout.CENTER);
		add(p2, BorderLayout.SOUTH);
	}
	
	private void grade () //Method to consolidate dual use of grading
	{
		p1.remove (jTxtInput);
		remove(p1);
		p1 = new JPanel (flow);
		add (p1, BorderLayout.CENTER);
		p1.add (jTxtPInfo);
		p2.remove (jbtNextQ);
		p2.remove (jbtDone);
		p2.add (jbtQuit);
		p2.add (jbtReset);
		p2.revalidate ();
		p2.repaint ();
		
		jTxtPInfo.setText (quizQs.gradeQuiz ());
	}
	
	private class NextIListener implements ActionListener
	{
		@Override 
		public void actionPerformed(ActionEvent e)
		{
			if (i == 0)
			{
				jTxtPInfo.setText ("Please use correct spelling with no abbreviations in your answers.");
				i++;
			}
			else if (i == 1)
			{
				jTxtPInfo.setText ("What is your name?");
				p1.add (jTxtInput);	
				p1.revalidate ();
				p1.repaint ();
				i++;
			}
			else
			{
				name = jTxtInput.getText ();
				quizQs.setUserInput (name, theme);
				i = 0;
				jTxtInput.setText ("");
				p2.remove (jbtNextI);
				p2.add (jbtNextQ);
				p2.add (jbtDone);
				p2.revalidate ();
				p2.repaint ();
				jTxtPInfo.setText (quizQs.getQuestion ());
				jTxtInput.setEnabled (true);
			}
		}
	}
	
	private class NextQListener implements ActionListener
	{
		@Override 
		public void actionPerformed(ActionEvent e)
		{
			if (iRef == i)
			{
				quizQs.setAnswer (jTxtInput.getText ());
				jTxtInput.setEnabled (false);
				jTxtInput.setOpaque (false);
				jTxtInput.setBorder (null);
				jTxtInput.setText ("");
				jTxtPInfo.setText (quizQs.getResult ());
				i++;
			}
			else if (i == 20)
			{
				grade ();
			}
			else
			{
				jTxtPInfo.setText (quizQs.getQuestion ());
				jTxtInput.setEnabled (true);				
				jTxtInput.setOpaque (true);
				jTxtInput.setBorder (jTxtBorder);
				iRef++;
			}
		}
	}
	
	private class DoneListener implements ActionListener
	{
		@Override 
		public void actionPerformed(ActionEvent e)
		{
			grade ();
		}
	}
	
	private class QRListener implements ActionListener
	{
		@Override
		public void actionPerformed (ActionEvent e)
		{
			if (e.getSource() == jbtQuit)
			{
				System.exit (0);
			}
			else if (e.getSource () == jbtReset) //Restart button
			{
				//Reset quiz instance
				quizQs = new Quiz ();
		
				//Reset panels
				p2.remove (jbtQuit);
				p2.remove (jbtReset);		
				remove (p1);
				remove (p2);
				p1 = new JPanel (grid);
				p1.add (jTxtPInfo);
				p2.add (jbtNextI);
		
				//Reset pane and field
				jTxtPInfo.setText ("Angela LeVee (alevee@cnm)\nLeVeeP3\n\nWelcome to my Java quiz on " + theme);
				jTxtInput.setEnabled (true);				
				jTxtInput.setOpaque (true);
				jTxtInput.setBorder (jTxtBorder);
		
				//Add panels back to frame
				add(p1, BorderLayout.CENTER);
				add(p2, BorderLayout.SOUTH);
				p2.revalidate ();
				p2.repaint ();
		
				//Reset variables
				i = 0;
				iRef = 0;
			}
		}
		
	}
}