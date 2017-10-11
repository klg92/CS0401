package projects;
public class MastermindModel {
	//array of answers
	private char[] answer = new char[4];
	private int firstColor = (int)(Math.random() * 6);
	private int secondColor = (int)(Math.random() * 6);
	private int thirdColor = (int)(Math.random() * 6);
	private int fourthColor = (int)(Math.random() * 6);
	//variables for RightColorWrongPlace
	private int numberOfColors = 0;
	private int numberOfColorsGuessed = 0;
	private int[] colorCount = {0, 0, 0, 0, 0, 0};
	private int[] colorsGuessed = {0, 0, 0, 0, 0, 0};
	private char[] colors = {'r', 'o', 'y', 'g', 'b', 'p'};
	private char[] modifiedGuess = new char[4];
	private int numberOfX = 0;
	//creates randomly generated numbers and converts it into chars for answer and puts it into answer array
	public MastermindModel(){
		String q = "roygbp";
		answer[0] = q.charAt(firstColor);
		answer[1] = q.charAt(secondColor);
		answer[2] = q.charAt(thirdColor);
		answer[3] = q.charAt(fourthColor);
		for(int i = 0; i < colorCount.length; i++){
			int count = 0;
			for (int j = 0; j < 4; j++){
				if(getColorAt(j) == colors[i]){
					count++;
				}
				colorCount[i] = count;
			}
		}
		for (int i: colorCount){
			if (i > 0){
				numberOfColors++;
			}
		}
		// this array will initially be the answer, but will be modified to take right guesses and colors that aren't in the answer out. 
		for (int i = 0; i < 4; i++){
			modifiedGuess[i] = answer[i];
		}
	}
	//gets color at specific index
	public char getColorAt (int index){
		return answer[index];
	}
	//accessors and mutators for RightColorWrongPlace variables
	public int getColorCount(int index){
		return colorCount[index];
	}
	public void setColorCount(int index, int value){
		colorCount[index] += value;
	}
	public int getColorsGuessed(int index){
		return colorsGuessed[index];
	}
	public void setColorsGuessed(int index, int value){
		if (value == 0){
			colorsGuessed[index] = value;
		}
		else {
			colorsGuessed[index] += value;
		}
	}
	public int getNumberOfColors(){
		return numberOfColors;
	}
	public int getNumberOfColorsGuessed(){
		return numberOfColorsGuessed;
	}
	public void setNumberOfColorsGuessed(int value){
		if (value == 0){
			numberOfColorsGuessed = 0;
		}
		else{
		numberOfColorsGuessed += value;
		}
	}
	public void setModifiedGuess (int position, char modifier){
		if (modifier == 'x'){
			modifiedGuess[position] = modifier;
		}else if (modifier == 'z'){
			modifiedGuess[position] = modifier;
		}else {
			modifiedGuess[position] = modifier;
		}
	}
	public char getModifiedGuess (int index){
		return modifiedGuess[index];
	}
	public char getAnswer(int index){
		return answer[index];
	}
	public int getNumberOfX(){
		return numberOfX;
	}
	public void setNumberOfX(){
		numberOfX++;
	}
}