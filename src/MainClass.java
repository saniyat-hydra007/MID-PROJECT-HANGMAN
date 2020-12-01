import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class file1
{
	fileio f1;

	public static void writeNewWord(String wordToAdd)
	{
		fileio f1 = new fileio();
		f1.writeToFile(wordToAdd);
	}

	public static ArrayList<String> getRandomWordArrList()
	{
		fileio f1 = new fileio();
		f1.openFile();
		ArrayList<String> arrayListToReturn = f1.readFile();
		f1.closeFile();

		return arrayListToReturn;
	}

	public static void deleteSomeWord(int indexOfWordToDelete)
	{
		fileio f1 = new fileio();
		f1.openFile();
		ArrayList<String> cur = f1.readFile();
		cur.remove(indexOfWordToDelete);
		f1.writeCleanToFile(cur);
		f1.closeFile();
	}
}

class fileio
{
	private Scanner scn;

	public void openFile()
	{
		try
		{
			scn = new Scanner(new File("WordBank.txt"));
		}
		catch(Exception e)
		{
			System.out.println("Something went wrong!");
		}
	}

	public ArrayList<String> readFile()
	{

		ArrayList<String> list1 = new ArrayList<String>();
		while (scn.hasNext())
		{
			list1.add(scn.nextLine());
		}

		return list1;
	}

	public void closeFile()
	{
		scn.close();
	}

	public void writeToFile(String newWord)
	{
		try
		{
			BufferedWriter bw = new BufferedWriter(new FileWriter("WordBank.txt", true));
			bw.append("\n" + newWord);
			bw.close();
		}
		catch(Exception e)
		{
			System.out.println("Something went wrong!");
		}
	}

	public void writeCleanToFile(ArrayList<String> updatedFile)
	{
		try
		{
			BufferedWriter bw2 = new BufferedWriter(new FileWriter("WordBank.txt", false));

			boolean run = true;
			for (String x: updatedFile)
			{
				if(run == true)
				{
					bw2.write(x);
					run = false;
				}
				else
				{
					bw2.write("\n" + x);
				}
			}
			bw2.close();
		}
		catch(Exception e)
		{
			System.out.println("Something went wrong!");
		}
	}
}

class GameLogic1
{
	static ArrayList<String> wordBank = new ArrayList<String>();

	public static String getRandomWord()
	{
		wordBank = file1.getRandomWordArrList();

		Random randomNumGen = new Random();
		int randomWordIndex = randomNumGen.nextInt(wordBank.size()) + 0;

		return wordBank.get(randomWordIndex);

	}

	public static boolean[] checkArrayForMatches(String currentWord, char charToTest)
	{
		String upperCaseCurretWord = currentWord.toUpperCase();
		char[] currentWordArray = upperCaseCurretWord.toCharArray();
		boolean[] returnArray = new boolean[currentWordArray.length];

		for(int x = 0; x < returnArray.length; x++)
		{
			char characterInArray = currentWordArray[x];
			if(characterInArray == charToTest)
			{
				returnArray[x] = true;
			}
			else
			{
				returnArray[x] = false;
			}
		}
		return returnArray;
	}

	public static boolean doesArrayContainATrue(boolean[] someBoolArray)
	{
		for (boolean x : someBoolArray)
		{
			if(x == true)
			{
				return true;
			}
		}
		return false;
	}

	public static boolean doesArrayContainUnderscores(char[] someCharArray)
	{
		for(char x : someCharArray)
		{
			if(x == '_')
			{
				return true;
			}
		}
		return false;
	}
}

class GameOver1 extends JFrame
{
	private static final long serialVersionUID = 1L;
	JButton bt1;
	GridBagConstraints gbc = new GridBagConstraints();


	public GameOver1(int x, int y, String st)
	{
		this.setSize(600, 400);
		this.setResizable(false);
		this.setVisible(true);
		this.setLocation(x - 150, y + 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("HANGMAN");
		this.validate();

		JPanel jp1 = new JPanel();
		JLabel jl1 = new JLabel();
		bt1 = new JButton("Play Again");

		Font gameOverFont = new Font("Helvetica", Font.PLAIN, 30);

		jp1.setLayout(new GridBagLayout());
		jl1.setText(st);
		jl1.setFont(gameOverFont);

		GameOver1.ListenForButton someButtonListener = new GameOver1.ListenForButton();
		bt1.addActionListener(someButtonListener);

		gbc.gridx = 0;
		gbc.gridy = 0;
		jp1.add(jl1, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		jp1.add(bt1, gbc);

		this.add(jp1);
	}

	private class ListenForButton implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			int x1 = GameOver1.super.getX();
			int y2 = GameOver1.super.getY();
			new menu(x1 + 75, y2 - 100);
			GameOver1.super.dispose();
		}
	}
}

class GamePlay1 extends JFrame
{
	private static final long serialVersionUID = 1L;

	char[] cur1;
	char[] cue2;
	int count = 1;

	String curWord = GameLogic1.getRandomWord();
	String img = "/ImageAssets/hangman" + count + ".png";

	JPanel jp3;
	JLabel jl3;
	JLabel jl4;
	JButton jb1, jb2, jb3, jb4, jb5, jb6, jb7, jb8, jb9, jb10, jb11, jb12, jb13, jb14, jb15, jb16, jb17, jb18, jb19, jb20, jb21, jb22, jb23, jb24, jb25, jb26;


	public GamePlay1(int xLocation, int yLocation)
	{
		jb1 = new JButton("A");
		jb2 = new JButton("B");
		jb3 = new JButton("C");
		jb4 = new JButton("D");
		jb5 = new JButton("E");
		jb6 = new JButton("F");
		jb7 = new JButton("G");
		jb8 = new JButton("H");
		jb9 = new JButton("I");
		jb10 = new JButton("J");
		jb11 = new JButton("K");
		jb12 = new JButton("L");
		jb13 = new JButton("M");
		jb14 = new JButton("N");
		jb15 = new JButton("O");
		jb16 = new JButton("P");
		jb17 = new JButton("Q");
		jb18 = new JButton("R");
		jb19 = new JButton("S");
		jb20 = new JButton("T");
		jb21 = new JButton("U");
		jb22 = new JButton("V");
		jb23 = new JButton("W");
		jb24 = new JButton("X");
		jb25 = new JButton("Y");
		jb26 = new JButton("Z");

		this.setVisible(true);
		this.setSize(800,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(xLocation + 75, yLocation - 100);
		this.setResizable(false);
		this.setTitle("HANGMAN");

		Font fontForGuess = new Font("Algerian", Font.PLAIN, 30);
		String currentWord = this.curWord;


		jp3 = new JPanel();
		JPanel jp4 = new JPanel();
		JPanel jp5 = new JPanel();
		JPanel jp6  = new JPanel();
		JPanel jp7 = new JPanel();
		JPanel jp8 = new JPanel();


		jl4 = new JLabel();
		jl3 = new JLabel();

		jl4.setIcon//draws the first image in the set
				(
						new ImageIcon(getClass().getResource("/images/hangman1.png"))
				);


		jp5.setLayout(new GridLayout(3, 0, 3, 3));
		jp6.setLayout(new FlowLayout());
		jp7.setLayout(new FlowLayout());
		jp8.setLayout(new FlowLayout());
		jp3.setLayout(new FlowLayout());


		cue2 = currentWord.toCharArray();
		for(int x = 0; x < cue2.length; x++)
		{
			if(cue2[x] != ' ')
			{
				cue2[x] = '_';
			}
		}

		String wordToDisplay = new String(cue2);

		jl3.setText(wordToDisplay);
		jl3.setFont(fontForGuess);

		//sets up keyboard
		jp6.add(jb1);
		jp6.add(jb2);
		jp6.add(jb3);
		jp6.add(jb4);
		jp6.add(jb5);
		jp6.add(jb6);
		jp6.add(jb7);
		jp6.add(jb8);
		jp6.add(jb9);
		jp6.add(jb10);
		jp7.add(jb11);
		jp7.add(jb12);
		jp7.add(jb13);
		jp7.add(jb14);
		jp7.add(jb15);
		jp7.add(jb16);
		jp7.add(jb17);
		jp7.add(jb18);
		jp7.add(jb19);
		jp8.add(jb20);
		jp8.add(jb21);
		jp8.add(jb22);
		jp8.add(jb23);
		jp8.add(jb24);
		jp8.add(jb25);
		jp8.add(jb26);

		jp5.add(jp6);
		jp5.add(jp7);
		jp5.add(jp8);
		jp4.add(jp5);
		jp3.add(jl4);
		jp3.add(jl3);
		jp3.add(jp4);

		this.add(jp3);


		GamePlay1.ListenForKeyboard keyboardListener = new GamePlay1.ListenForKeyboard();


		jb1.addActionListener(keyboardListener);
		jb2.addActionListener(keyboardListener);
		jb3.addActionListener(keyboardListener);
		jb4.addActionListener(keyboardListener);
		jb5.addActionListener(keyboardListener);
		jb6.addActionListener(keyboardListener);
		jb7.addActionListener(keyboardListener);
		jb8.addActionListener(keyboardListener);
		jb9.addActionListener(keyboardListener);
		jb10.addActionListener(keyboardListener);
		jb11.addActionListener(keyboardListener);
		jb12.addActionListener(keyboardListener);
		jb13.addActionListener(keyboardListener);
		jb14.addActionListener(keyboardListener);
		jb15.addActionListener(keyboardListener);
		jb16.addActionListener(keyboardListener);
		jb17.addActionListener(keyboardListener);
		jb18.addActionListener(keyboardListener);
		jb19.addActionListener(keyboardListener);
		jb20.addActionListener(keyboardListener);
		jb21.addActionListener(keyboardListener);
		jb22.addActionListener(keyboardListener);
		jb23.addActionListener(keyboardListener);
		jb24.addActionListener(keyboardListener);
		jb25.addActionListener(keyboardListener);
		jb26.addActionListener(keyboardListener);
	}

	private class ListenForKeyboard implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			String st2 = ((JButton) e.getSource()).getText();
			char ch1 = st2.charAt(0);

			boolean[] b1 = GameLogic1.checkArrayForMatches(curWord, ch1);

			String upperCaseCurretWord = curWord.toUpperCase();
			cur1 = upperCaseCurretWord.toCharArray();

			for(int x = 0; x < b1.length; x++)
			{
				if(b1[x] == true)
				{
					cue2[x] = cur1[x];
				}
			}

			String wordToShow = new String(cue2);
			jl3.setText(wordToShow);

			if((GameLogic1.doesArrayContainATrue(b1)) == true)
			{
				if((GameLogic1.doesArrayContainUnderscores(cue2)) == false)
				{
					String winPhrase = "You Won! The word was \'" + curWord + "\'.";
					int xLocation = GamePlay1.super.getX();
					int yLocation = GamePlay1.super.getY();
					new GameOver1(xLocation, yLocation, winPhrase);

					GamePlay1.super.dispose();
				}
			}
			else
			{
				count++;
				img = "/images/hangman" + count + ".png";
				if(count == 8)
				{
					String losePhrase = "You Lost! The word was \'" + curWord + "\'.";
					int xLocation = GamePlay1.super.getX();
					int yLocation = GamePlay1.super.getY();
					new GameOver1(xLocation, yLocation, losePhrase);
					GamePlay1.super.dispose();
				}

				jl4.setIcon(new ImageIcon(getClass().getResource(img)));
			}

			String holderForButton = ((JButton) e.getSource()).getText();
			String buttonCalled = "but" + holderForButton;
			switch(buttonCalled)
			{
				case "butA":
					jb1.setEnabled(false);
					break;
				case "butB":
					jb2.setEnabled(false);
					break;
				case "butC":
					jb3.setEnabled(false);
					break;
				case "butD":
					jb4.setEnabled(false);
					break;
				case "butE":
					jb5.setEnabled(false);
					break;
				case "butF":
					jb6.setEnabled(false);
					break;
				case "butG":
					jb7.setEnabled(false);
					break;
				case "butH":
					jb8.setEnabled(false);
					break;
				case "butI":
					jb9.setEnabled(false);
					break;
				case "butJ":
					jb10.setEnabled(false);
					break;
				case "butK":
					jb11.setEnabled(false);
					break;
				case "butL":
					jb12.setEnabled(false);
					break;
				case "butM":
					jb13.setEnabled(false);
					break;
				case "butN":
					jb14.setEnabled(false);
					break;
				case "butO":
					jb15.setEnabled(false);
					break;
				case "butP":
					jb16.setEnabled(false);
					break;
				case "butQ":
					jb17.setEnabled(false);
					break;
				case "butR":
					jb18.setEnabled(false);
					break;
				case "butS":
					jb19.setEnabled(false);
					break;
				case "butT":
					jb20.setEnabled(false);
					break;
				case "butU":
					jb21.setEnabled(false);
					break;
				case "butV":
					jb22.setEnabled(false);
					break;
				case "butW":
					jb23.setEnabled(false);
					break;
				case "butX":
					jb24.setEnabled(false);
					break;
				case "butY":
					jb25.setEnabled(false);
					break;
				case "butZ":
					jb26.setEnabled(false);
					break;
			}
		}
	}
}

 class menu extends JFrame {
	private static final long serialVersionUID = 1L;

	JButton jb30;
	private JLabel jl11;

	public menu(int X, int Y)
	{
		this.setSize(400,100);
		this.setLocation(X, Y);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Hangman");
		this.setResizable(false);

		jb30 = new JButton("Start Game");
		//optionsButton = new JButton("Options");
		jl11 = new JLabel();

		jl11.setIcon
				(
						new ImageIcon(getClass().getResource("/images/hangman8.png"))
				);

		JPanel jp20 = new JPanel();
		jp20.setLayout(new BorderLayout());
		jp20.add(jb30, BorderLayout.WEST);
		jp20.add(jl11, BorderLayout.CENTER);

		this.add(jp20);
		this.pack();
		this.validate();

		menu.ListenForButton someButtonListener = new menu.ListenForButton();

		jb30.addActionListener(someButtonListener);
	}

	private class ListenForButton implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == jb30)
			{
				int xLocation = menu.super.getX();
				int yLocation = menu.super.getY();
				new GamePlay1(xLocation, yLocation);
				menu.super.dispose();

			}
		}
	}

}

public class MainClass 
{
	public static void main(String[] args) 
	{
		menu m1 = new menu(0, 0);
		m1.setLocationRelativeTo(null);
	}
}
