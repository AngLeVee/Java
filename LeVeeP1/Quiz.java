//Angela LeVee (alevee@cnm.edu)
//LeVeeP1, Quiz.java

import javax.swing.JOptionPane;
import java.util.Random;

public class Quiz
{
	public static void main (String [] args)
	{
		String [] question = {"What game company makes the Harvest Moon and Rune Factory series?",
							  "Does the numbering system for the Final Fantasy series use Latin numbers or Roman numerals?",
							  "What is the highest number currently used for a Final Fantasy game?",
							  "What company makes the Halo series?",
							  "What is the most popular PC gaming platform that uses DRM?",
							  "What is the most popular DRM-free PC gaming platform?",
							  "What is the newest Post-Apocalyptic game scheduled for release by Bethesda?",
							  "What company made the Elder Scrolls series?",
							  "What is the name of the last console that Sega releasing in North America?",
							  "In Final Fantasy VII, what is Cloud's last name?",
							  "What is the name of Mario's brother?",
							  "What system of Nintendo's is least known and sold the worst?",
							  "What does the J in JRPG stand for?",
							  "True or False: there are Dungeons and Dragons games for the computer.",
							  "Who is the main character in all of the Zelda games?",
							  "What Atari game was dug up in the New Mexican desert?",
							  "What sport is played in Final Fantasy X?",
							  "How many female characters are playable in the first Mortal Kombat game (ex: 5)",
							  "What console series is Ratchet and Clank exclusive to?",
							  "What is the most popular subscription-based MMO?"};
		String [] answers = {"Natsume",
							 "Roman numerals",
							 "14",
							 "Bungie",
							 "Steam",
							 "GOG Galaxy",
							 "Fallout 4",
							 "Bethesda",
							 "Dreamcast",
							 "Strife",
							 "Luigi",
							 "Virtual Boy",
							 "Japanese",
							 "True",
							 "Link",
							 "E.T.",
							 "Blitzball",
							 "1",
							 "Playstation",
							 "World of Warcraft"};
		
		String name;
		JOptionPane.showMessageDialog (null, "Angela LeVee (alevee@cnm)\nQuiz.java\nJava quiz program", "LeVeeP1", JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog (null, "Please use correct spelling with no abbreviations in your answers.");
		name = JOptionPane.showInputDialog ("What is your name?");
		
		do
		{
			//variables
			String answer;
			Random newNum = new Random ();
			int questions = question.length;
			int rightAnswers = 0;
			int wrongAnswers = 0;
			double pGrade = 0;
			char lGrade;
			int currentQ = 0;
			
			//variables for scrambling of arrays
			int position;
			String temp1;
			String temp2;
			for (int i = 0; i < question.length; i++) //scramble array
			{
				position = newNum.nextInt(question.length);
				temp1 = question[i];
				temp2 = answers[i];
				question[i] = question[position];
				answers[i] = answers[position];
				question[position] = temp1;
				answers[position] = temp2;
			}
		
			
			//Ask questions
			for (int i = 0; i<question.length;i++)
			{
				currentQ ++;
				answer = JOptionPane.showInputDialog(question[i]);
				if (answer.equals(answers[i]))
				{
					JOptionPane.showMessageDialog(null,"Correct!");
					rightAnswers ++;
				}
				else
				{			
					JOptionPane.showMessageDialog(null,"Wrong, the real answer is:\n" + answers[i]);
					wrongAnswers ++;
				}
			}
			
			//Grade quiz
			pGrade =((double)rightAnswers/questions) * 100;
			if (pGrade < 61)
			{
				lGrade = 'F';
			}
			else if (pGrade < 71)
			{
				lGrade = 'D';
			}
			else if (pGrade < 81)
			{
				lGrade = 'C';
			}
			else if (pGrade < 90.5)
			{
				lGrade = 'B';
			}
			else
			{
				lGrade = 'A';
			}
			
			//Results
			JOptionPane.showMessageDialog
					(null, "Name: " + name + "\nTotal Questions: " + questions + "\nCorrect Answers: " + rightAnswers 
					 + "\nTotal missed: " + wrongAnswers + "\nPercent grade: " + pGrade + "\nLetter grade: " + lGrade);
			
		} while (JOptionPane.showConfirmDialog (null, "Would you like to try again?") == 0);
		
		JOptionPane.showMessageDialog (null, "Thank you, goodbye!");
		
		System.exit ( 0 );
	}
}