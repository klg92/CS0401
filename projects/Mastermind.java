package projects;
public class Mastermind {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Welcome to Mastermind by Kevin Good!");
		MastermindModel model = new MastermindModel();
		MastermindController controller = new MastermindController(model);
		int count = 1;
		while (true){
			java.util.Scanner input = new java.util.Scanner(System.in);
			System.out.println("Would you like to play?");
			String prompt = input.nextLine();
			if (prompt.equals("no")){
				break;
			}
			while (true){
				System.out.println("Enter guess number " + count + " : [r/o/y/g/b/p]");
				String guess = input.nextLine();
				if (controller.isCorrect(guess, model) == true){
					System.out.println("You got it right! you win!");
					break;
				}
				else{
					if (count >= 10){
						System.out.println("you lose.");
						break;
					}
					count++;
					controller.determineColorsGuessed(guess, model);
					System.out.println("colors in the correct place: " + controller.getRightColorRightPlace(guess, model));
					System.out.println("colors guessed correctly but not in the correct place: " + controller.getRightColorWrongPlace(guess, model));
				}
			}
			break;
		}
	}

}
