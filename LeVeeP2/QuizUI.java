//Angela LeVee (alevee@cnm.edu)
//LeVeeP2, QuizUI.java
//Shows output and takes user input for use by quiz.java

import javax.swing.JOptionPane;
import java.util.Random;

public class QuizUI
{
	private String name;
	private String theme;
	private String sAgain;
	private Quiz quizQs;
	private String answer;
	private String result;
	private String grade;
	private int i;

	public QuizUI ()
	{
		theme = "Video Games";
		quizQs = new Quiz ();
		i = 0;
	}	
	
	public void run ()
	{
		JOptionPane.showMessageDialog (null, "Angela LeVee (alevee@cnm)\nLeVeeP2\na Java quiz program\n\nTheme: " + theme, "LeVeeP2", JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog (null, "Please use correct spelling with no abbreviations in your answers.", "Instructions", JOptionPane.INFORMATION_MESSAGE);
		name = JOptionPane.showInputDialog ("What is your name?");
		quizQs.setUserInput (name, theme);
		
		do
		{
			quizQs.setAnswer(JOptionPane.showInputDialog(quizQs.getQuestion()));
			JOptionPane.showMessageDialog (null, quizQs.getResult());
			i++;
		} while (JOptionPane.showConfirmDialog (null, "Would you like to continue?") == 0 && i < 20);
		
		grade = quizQs.gradeQuiz();
		JOptionPane.showMessageDialog(null, grade);
	}
}