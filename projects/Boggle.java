import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
package projects;
class Boggle{
	//ArrayList of legal words
	private static ArrayList <String> words = new ArrayList<String>();
	//ArrayList of guessed words
	private static ArrayList <String> guesses = new ArrayList<String>();
	//total score earned by player 
	private static int totalScore = 0;
	
	static int[] scores = new int[10];
	
	//array of characters to represent board
	private static char[] board = {generateLetter(),generateLetter(),generateLetter(),generateLetter(),
	generateLetter(),generateLetter(),generateLetter(),generateLetter(),
	generateLetter(),generateLetter(),generateLetter(),generateLetter(),
	generateLetter(),generateLetter(),generateLetter(),generateLetter(), };
	
	public static void main(String[] args){
		try{
			//scanners for file and player input
			File f = new File("dict.txt");
			Scanner s = new Scanner(f);
			Scanner input = new Scanner(System.in);
			
			//put items in words ArrayList
			while (s.hasNextLine()){
				words.add(s.nextLine());
			}
			//print board
			System.out.println("Welcome to Boggle by Kevin Good!");
			System.out.println(board[0] + " " +board[1] + " " + board[2] + " " + board[3]);
			System.out.println(board[4] + " " +board[5] + " " + board[6] + " " + board[7]);
			System.out.println(board[8] + " " +board[9] + " " + board[10] + " " + board[11]);
			System.out.println(board[12] + " " +board[13] + " " + board[14] + " " + board[15]);
			//play game
			while (true){
				System.out.println("input: ");
				String word = input.next();
				if (word.equals("q")){
					break;
				}
				if (isLegalWord(word)){
					totalScore += getScore(word);
					guesses.add(word);
					System.out.println(word + " scored " + getScore(word) + " points. Total Points: " + totalScore);
				}
				
			}
			try{
				File g = new File("scores.txt");
				Scanner t = new Scanner(g);
				Scanner input2 = new Scanner(System.in);
				PrintWriter pw = new PrintWriter(g);
				while (t.hasNextLine()){
					for (int i = 0; i < 10; i++){
						scores[i] = t.nextInt();
						
					}
				}
				int count = 0;
				for (int i = 0; i < 10; i++){
					if (totalScore > scores[i] && count == 0){
						System.out.println("Your score is qualified to be in the top ten scores!");
						scores[i] = totalScore;
						count++;
						System.out.println("Enter your initials: ");
						String initials = input2.next();
					}
				}
				pw.print("");
				for (int i: scores){
					pw.println(i);
				}
				pw.close();
			}catch(FileNotFoundException fnfe){
			fnfe.printStackTrace();
			System.out.println("File not found! use command line argument for file");
			System.exit(0);
			}
			
			
		}catch(FileNotFoundException fnfe){
			fnfe.printStackTrace();
			System.out.println("File not found! use command line argument for file");
			System.exit(0);
		}
	}
	//make the board
	public static char generateLetter(){
		int generate = (int)(Math.random() * 26) + 1;
		if (generate == 1){
			return 'a';
		}else if (generate == 2){
			return 'b';
		}else if (generate == 3){
			return 'c';
		}else if (generate == 4){
			return 'd';
		}else if (generate == 5){
			return 'e';
		}else if (generate == 6){
			return 'f';
		}else if (generate == 7){
			return 'g';
		}else if (generate == 8){
			return 'h';
		}else if (generate == 9){
			return 'i';
		}else if (generate == 10){
			return 'j';
		}else if (generate == 11){
			return 'k';
		}else if (generate == 12){
			return 'l';
		}else if (generate == 13){
			return 'm';
		}else if (generate == 14){
			return 'n';
		}else if (generate == 15){
			return 'o';
		}else if (generate == 16){
			return 'p';
		}else if (generate == 17){
			return 'q';
		}else if (generate == 18){
			return 'r';
		}else if (generate == 19){
			return 's';
		}else if (generate == 20){
			return 't';
		}else if (generate == 21){
			return 'u';
		}else if (generate == 22){
			return 'v';
		}else if (generate == 23){
			return 'w';
		}else if (generate == 24){
			return 'x';
		}else if (generate == 25){
			return 'y';
		}else {
			return 'z';
		}
	}
	//calculate score for word
	public static int getScore(String word){
		int score = 0;
		for (int i = 0; i < word.length(); i++){
			char j = word.charAt(i);
			if (j == 'z' || j == 'q'){
				score += 10;
			}else if (j == 'x' || j == 'j'){
				score += 8;
			}else if (j == 'k'){
				score += 5;
			}else if (j == 'f' || j == 'h' || j == 'v' || j == 'w' || j == 'y'){
				score += 4;
			}else if (j == 'b' || j == 'c' || j == 'p' || j == 'm'){
				score += 3;
			}else if (j == 'd' || j == 'g'){
				score += 2;
			}else{
				score++;
			}
		}
		return score;
	}
	//determine if letters in user input are on the board
	public static boolean areLettersInBoard(String word){
		for (int i = 0; i < word.length(); i++){
			int count = 0;
			char wordChar = word.charAt(i);
			for (int j = 0; j < 15; j++){
				if (wordChar == board[j]){
					count++;
				}
			}
			if (count == 0){
				return false;
			}
		}
		return true;
	}
	//determine if user input is an actual word as defined by array of possible words
	public static boolean isAWord(String word){
		for (String s: words){
			if (word.equals(s)){
				return true;
			}
		}
		return false;
	}
	// see if characters are connected p1
	public static boolean isConnected(String word){
		if (!areLettersInBoard(word) || !isAWord(word)){
			return false;
		}
		int[] positions1 = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
		int[] altPositions = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
		int[] altPositions2 = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
		for (int i = 0; i < word.length(); i++){
			char c = word.charAt(i);
			for (int j = 0; j < 15; j++){
				if (c == board[j] && positions1[i] == -1){
					positions1[i] = j;
				}else if (c == board[j] && positions1[i] != -1 && altPositions[i] == -1){
					altPositions[i] = j;
				}else if (c == board[j] && positions1[i] != -1 && altPositions[i] != -1){
					altPositions2[i] = j;
				}
			}
		}
		for (int i = 0; i < word.length(); i++){
			if (isActuallyConnected(positions1, word)){
				return true;
			}else if (isActuallyConnected(altPositions, word)){
				return true;
			}else if (isActuallyConnected(altPositions2, word)){
				return true;
			}
		}
		return false;
	}
	//see if characters are connected p2
	public static boolean isActuallyConnected(int[] positions, String word){
		int count = 0;
		for (int i = 0; i < word.length(); i++){
				if (positions[i] == 0){
					if (positions[i + 1] == 1 || positions[i + 1] == 4 || positions[i + 1] == 5){
						count++;
					}
				}else if (positions[i] == 1){
					if (positions[i + 1] == 0 || positions[i + 1] == 2 || positions[i + 1] == 4 || positions[i + 1] == 5 || positions[i + 1] == 6 ){
						count++;
					}
				}else if (positions[i] == 2){
					if (positions[i + 1] == 1 || positions[i + 1] == 3 || positions[i + 1] == 5 || positions[i + 1] == 6 || positions[i + 1] == 7 ){
						count++;
					}
				}else if (positions[i] == 3){
					if (positions[i + 1] == 2 || positions[i + 1] == 6 || positions[i + 1] == 7 ){
						count++;
					}
				}else if (positions[i] == 4){
					if (positions[i + 1] == 0 || positions[i + 1] == 1 || positions[i + 1] == 5 || positions[i + 1] == 8 || positions[i + 1] == 9){
						count++;
					}
				}else if (positions[i] == 5){
					if (positions[i + 1] == 0 || positions[i + 1] == 1 || positions[i + 1] == 2 || positions[i + 1] == 4 || positions[i + 1] == 6 || positions[i + 1] == 8 || positions[i + 1] == 9 || positions[i + 1] == 10){
						count++;
					}
				}else if (positions[i] == 6){
					if (positions[i + 1] == 1 || positions[i + 1] == 2 || positions[i + 1] == 3 || positions[i + 1] == 5 || positions[i + 1] == 7 || positions[i + 1] == 9 || positions[i + 1] == 10 || positions[i + 1] == 11){
						count++;
					}
				}else if (positions[i] == 7){
					if (positions[i + 1] == 2 || positions[i + 1] == 3 || positions[i + 1] == 6 ||  positions[i + 1] == 10 || positions[i+1] == 11){
						count++;
					}
				}else if (positions[i] == 8){
					if (positions[i + 1] == 4 || positions[i + 1] == 5 || positions[i + 1] == 9 || positions[i + 1] == 12 || positions[i +1] == 13){
						count++;
					}
				}else if (positions[i] == 9){
					if (positions[i + 1] == 4 || positions[i + 1] == 5 || positions[i + 1] == 6 || positions[i + 1] == 8 || positions[i + 1] == 10 || positions[i + 1] == 12 || positions[i + 1] == 13 || positions[i + 1] == 14){
						count++;
					}
				}else if (positions[i] == 10){
					if (positions[i + 1] == 5 || positions[i + 1] == 6 || positions[i + 1] == 7 || positions[i + 1] == 9 || positions[i + 1] == 11 || positions[i + 1] == 13 || positions[i + 1] == 14 || positions[i + 1] == 15){
						count++;
					}
				}else if (positions[i] == 11){
					if (positions[i + 1] == 6 || positions[i + 1] == 7 || positions[i + 1] == 10 || positions[i + 1] == 14 || positions[i + 1] == 15){
						count++;
					}
				}else if (positions[i] == 12){
					if (positions[i + 1] == 8 || positions[i + 1] == 9 || positions[i + 1] == 13){
						count++;
					}
				}else if (positions[i] == 13){
					if (positions[i + 1] == 8 || positions[i + 1] == 9 || positions[i + 1] == 10 || positions[i + 1] == 14 || positions[i + 1] == 15){
						count++;
					}
				}else if (positions[i] == 14){
					if (positions[i + 1] != 9 || positions[i + 1] == 10 || positions[i + 1] == 11 || positions[i + 1] == 14 || positions[i + 1] == 15){
						count++;
					}
				}else if (positions[i] == 15){
					if (positions[i + 1] == 10 || positions[i + 1] == 11 || positions[i + 1] == 14){
						count++;
					}
				}
		}if (count >= (word.length() - 1)){
			return true;
		}
		return false;
	}
	public static boolean isAlreadyUsed(String word){
		for (String guess: guesses){
			if (word.equals(guess)){
				return true;
			}
		}
		return false;
	}
	//determine if the user input is legal according to rules of boggle
	public static boolean isLegalWord(String word){
		if (word.length() < 3 || !areLettersInBoard(word) || !isAWord(word) || !isConnected(word) || isAlreadyUsed(word)){
			System.out.println("illegal input");
			return false;
		}
		return true;
	}
}