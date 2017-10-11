package projects;
public class MastermindController {
	public MastermindController(MastermindModel model){
	}
	//returns true if all characters in both the answer and guess match, false otherwise. 
	public boolean isCorrect (String guess, MastermindModel model){
		if (guess.charAt(0) == model.getColorAt(0) && guess.charAt(1) == model.getColorAt(1) && guess.charAt(2) == model.getColorAt(2) && guess.charAt(3) == model.getColorAt(3)){
			return true;
		}
		else{
			return false;
		}
	}
	//looks at each character and sees if it matches the character in the answer
	public int getRightColorRightPlace(String guess, MastermindModel model){
		int count = 0;
		for (int i=0; i < 4; i++){
			if (guess.charAt(i) == model.getColorAt(i)){
				count++;
			}
		}
		return count;
	}
	//takes in copy of guess and changes things that aren't right color in wrong place to non-color characters
	public int getRightColorWrongPlace(String guess, MastermindModel model){
		int count = 0;
		//reset  for each guess
		for (int i = 0; i < 4; i++){
			model.setModifiedGuess(i, model.getAnswer(i));
		}
		// turn guesses that are correct into x
		for (int i = 0; i < 4; i++){
			if (guess.charAt(i) == model.getModifiedGuess(i)){
				model.setModifiedGuess(i, 'x');
				model.setNumberOfX();
			}
		}
		//turn guesses that aren't even colors in the actual answer to z
		for (int i = 0; i < 4; i++){
			int count2 = 0;
			if (model.getModifiedGuess(i) != 'x'){
				for (int j = 0; j < 4; j++){
					if (guess.charAt(i) != model.getModifiedGuess(j)){
						count2++;
					}
				}
				if (count2 > (model.getNumberOfColorsGuessed())){
					model.setModifiedGuess(i, 'z');
				}
			}
		}
		//count number of guesses that are right colors in wrong place
		for (int i = 0; i < 4; i++){
			if (model.getModifiedGuess(i) != 'x' && model.getModifiedGuess(i) != 'z'){
				count++;
			}
		}
		
		return count;
	}
	// figuring out  the colors guessed
	public void determineColorsGuessed(String guess, MastermindModel model){
		String s = "roygbp";
		for (int i = 0; i < 6; i++){
			model.setColorsGuessed(i, 0);
		}
		for (int j = 0; j < 6; j++){
			for (int i = 0; i < 4; i++){
				if (s.charAt(j) == guess.charAt(i)){
					model.setColorsGuessed(j, 1);
				}
			}
		}
		model.setNumberOfColorsGuessed(0);
		for (int i = 0; i < 6; i++){
			if (model.getColorsGuessed(i) > 0){
				model.setNumberOfColorsGuessed(1);
			}
		}
	}
	// see if color at position in arrays has is not in answer and has been guessed
	public boolean test(int position, MastermindModel model){
		if (model.getColorCount(position) == 0 && model.getColorsGuessed(position) > 1){
			return true;
		}
		else{
			return false;
		}
	}
}