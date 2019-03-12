//Angela LeVee (alevee@cnm.edu)
//LeVeeP2, Quiz.java
//Contains all data for use by QuizUI and does all calculations

import javax.swing.JOptionPane;
import java.util.Random;
import java.text.DecimalFormat;

public class Quiz
{	
	DecimalFormat form = new DecimalFormat ("#0.00");
	private Random newNum;
	private String [] questions = new String [20];
	private String [] answers = new String [20];
	private int [] index = new int [20];
	private String currentQ;
	private String currentA;
	private String name;
	private String theme;
	private String answer;
	private String result;
	private String grade;
	private int i;
	private int rightAnswers;
	private int wrongAnswers;
	private int question;
	private double pGrade;
	private char lGrade;
	
	public Quiz ()
	{	
		questions [0] = "What game company makes the Harvest Moon and Rune Factory series?";
		questions [1] = "Does the numbering system for the Final Fantasy series use Latin numbers or Roman numerals?";
		questions [2] = "What is the highest number currently used for a Final Fantasy game?";
		questions [3] = "What company makes the Halo series?";
		questions [4] = "What is the most popular PC gaming platform that uses DRM?";
		questions [5] = "What is the most popular DRM-free PC gaming platform?";
		questions [6] = "What is the newest Post-Apocalyptic game scheduled for release by Bethesda?";
		questions [7] = "What company made the Elder Scrolls series?";
		questions [8] = "What is the name of the last console that Sega releasing in North America?";
		questions [9] = "In Final Fantasy VII, what is Cloud's last name?";
		questions [10] = "What is the name of Mario's brother?";
		questions [11] = "What system of Nintendo's is least known and sold the worst?";
		questions [12] = "What does the J in JRPG stand for?";
		questions [13] = "True or False: there are Dungeons and Dragons games for the computer.";
		questions [14] = "Who is the main character in all of the Zelda games?";
		questions [15] = "What Atari game was dug up in the New Mexican desert?";
		questions [16] = "What sport is played in Final Fantasy X?";
		questions [17] = "How many female characters are playable in the first Mortal Kombat game (ex: 5)";
		questions [18] = "What console series is Ratchet and Clank exclusive to?";
		questions [19] = "What is the most popular subscription-based MMO?";
		
		answers [0] = "Natsume";
		answers [1] = "Roman numerals";
		answers [2] = "14";
		answers [3] = "Bungie";
		answers [4] = "Steam";
		answers [5] = "GOG Galaxy";
		answers [6] = "Fallout 4";
		answers [7] = "Bethesda";
		answers [8] = "Dreamcast";
		answers [9] = "Strife";
		answers [10] = "Luigi";
		answers [11] = "Virtual Boy";
		answers [12] = "Japanese";
		answers [13] = "True";
		answers [14] = "Link";
		answers [15] = "E.T.";
		answers [16] = "Blitzball";
		answers [17] = "1";
		answers [18] = "Playstation";
		answers [19] = "World of Warcraft";
		
		for (int i = 0; i < 20; i++)
		{
			index [i] = i;
		}
		
		shuffle ();
	}
	
	private void shuffle ()
	{
		newNum = new Random ();
		int position;
		int temp1;
		
		for (int i = 0; i < index.length; i++) //scramble array
		{
			position = newNum.nextInt(index.length);
			temp1 = index[i];
			index[i] = index[position];
			index[position] = temp1;
		}
	}
	
	public void setUserInput (String nameIn, String themeIn)
	{
		name = nameIn;
		theme = themeIn;
	}
	
	public String getQuestion ()
	{
		currentQ = questions[index[i]];
		currentA = answers[index[i]];
		i++;
		return currentQ;
	}
	
	public void setAnswer (String answerIn)
	{
		answer = answerIn;
		checkAnswer ();
	}
	
	private void checkAnswer ()
	{
		question ++;
		
		if (answer.equals(currentA))
		{
			result = "Correct!";
			rightAnswers ++;
		}
		else
		{			
			result = "Wrong, the real answer is:\n" + currentA;
			wrongAnswers ++;
		}
	}
	
	public String getResult ()
	{
		return result;
	}
	
	public String gradeQuiz ()
	{
		pGrade =((double)rightAnswers/question) * 100;
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
		
		grade = "Name: " + name + "\nTheme: " + theme + "\nTotal Questions: " + question + "\nCorrect Answers: " + rightAnswers 
						+ "\nTotal missed: " + wrongAnswers + "\nPercent grade: " + form.format(pGrade) + "\nLetter grade: " + lGrade;
		return grade;
	}
}