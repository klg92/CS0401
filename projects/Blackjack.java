package projects;
import java.util.Scanner;
public class Blackjack {
	static int total;
	static int cpuTotal;
	public static int deal(){
		int card = (int)(Math.random() * 52) + 1;
		if (card <= 4){
			card = 11;
		}else if (card > 4 && card <= 8){
			card = 2;
		}else if (card > 8 && card <= 12){
			card = 3;
		}else if (card > 12 && card <= 16){
			card = 4;
		}else if (card > 16 && card <= 20){
			card = 5;
		}else if (card > 20 && card <= 24){
			card = 6;
		}else if (card > 24 && card <= 28){
			card = 7;
		}else if (card > 28 && card <= 32){
			card = 8;
		}else if (card > 32 && card <= 36){
			card = 9;
		}else if (card > 36){
			card = 10;
		}
		return card;
	}
	public static int player(){
		java.util.Scanner input = new java.util.Scanner(System.in);
		int card1 = deal();
		int card2 = deal();
		total = card1 + card2;
		int currentCard;
		int lastTotal;
		boolean ace = false;
		System.out.println("\nYou: \n" + card1 + " + " + card2 + " = " + total + "\n");
		if (card1 == 11 || card2 == 11){
			ace = true;
		}
		while (total < 21 || ace == true){
			System.out.print("Would you like to 'hit' or 'stand?'");
			String decision = input.next();
			System.out.println(decision);
			if (decision.equals("hit")){
				currentCard = deal();
				lastTotal = total;
				total += currentCard;
				if (currentCard == 11){
					ace = true;
					if (total > 21){
						currentCard = 1;
						total -= 10;
					}
				}
				System.out.println("You: " + lastTotal + " + " + currentCard + " = " + total);
			}else if (decision.equals("stand")){
				break;
			}
		}return total;
	}
	public static int dealer(int card2, int playerTotal){
		cpuTotal += card2;
		int currentCard;
		int lastTotal;
		if(playerTotal > 21){
			System.out.println("Player BUSTED! Dealer wins.");
			return cpuTotal;
		}
		while (cpuTotal < 17){
			System.out.println("\nDealer Hits.");
			currentCard = deal();
			lastTotal = cpuTotal;
			cpuTotal += currentCard;
			System.out.println("Dealer: \n" + lastTotal + " + " + currentCard + " = " + cpuTotal);
		}
		if (cpuTotal <= 21 && cpuTotal > 17){
			System.out.println("Dealer stands at " + cpuTotal);
		}
		return cpuTotal;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Welcome to The JavaShoppe Casino!\nWritten by Kevin Good");
		int card2 = deal();
		System.out.println("\nThe Dealer:\n? + " + card2);
		player();
		dealer(card2, total);
		if (total <= 21 && cpuTotal <= 21){
			if (cpuTotal > total){
				System.out.println("Dealer Wins " + cpuTotal + " to player's " + total );
			}else if (cpuTotal < total){
				System.out.println("Player Wins " + total + " to Dealer's " + cpuTotal);
			}else if (cpuTotal == total){
				System.out.println("It is a tie.");
			}
		}else if (cpuTotal > 21){
			System.out.println("Dealer BUSTED! Player wins.");
		}
	}

}
