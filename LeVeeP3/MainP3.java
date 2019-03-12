//Angela LeVee (alevee@cnm.edu)
//LeVeeP2, MainP2.java
//Contains main, used to run the quiz

import javax.swing.JFrame;

public class MainP3
{
	private QuizFrame qFrame;
	
	public static void main (String [] args)
	{
		MainP3 mainQ = new MainP3 ();
	}
	
	public MainP3 ()
	{
		qFrame = new QuizFrame ();
		qFrame.setTitle("Video Game Quiz");
		qFrame.setSize(325, 210);
		qFrame.setLocationRelativeTo(null); // Center the frame
		qFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		qFrame.setVisible(true);	
	}
}