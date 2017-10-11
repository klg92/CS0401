package projects;
import java.util.Scanner;
public class JavaShoppe {
	public static void main (String[] args){
		System.out.println("Welcome to the Java Shoppe!");
		// stores quantities of things bought and discounts, will be changed in loop
		int smallCoffeeTotal = 0;
		int mediumCoffeeTotal = 0;
		int mondoCoffeeTotal = 0; 
		int muffinTotal = 0;
		int teaTotal = 0;
		int hotCocoTotal = 0;
		int waterTotal = 0;
		int organicWaterTotal = 0;
		int muffinDiscountNumber = 0;
		boolean organicWaterSpecial = false;
		float subTotal = 0;
		int size2 = 0;
		while (true) {
			// prints menu and reads customer choice
			System.out.println("The Java Shoppe Menu: ");
			System.out.println("\t1.) Brewed Coffee");
			System.out.println("\t2.) Chocolate Chip Muffin");
			System.out.println("\t3.) Pot of Tea");
			System.out.println("\t4.) Hot Chocolate");
			System.out.println("\t5.) Water");
			System.out.println("\t6.) Organic Water");
			System.out.println("\t7.) Check out/Print Receipt");
			System.out.print("Which item is being purchased?(use numbers) ");
			java.util.Scanner input = new java.util.Scanner(System.in);
			int choice = input.nextInt();
			// coffee sub-menu
			if (choice == 1){
				System.out.println("Coffee sizes: ");
				System.out.println("\t1.) Small");
				System.out.println("\t2.) Medium");
				System.out.println("\t3.) Mondo");
				System.out.print("what size coffee would you like?");
				java.util.Scanner input2 = new java.util.Scanner(System.in);
				int size = input2.nextInt();
				size2 = size;
			}
			// enter checkout
			else if (choice == 7){
				break;	
			}
			// error message
			else if (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5 && choice != 6 && choice!=7){
				System.out.println("INVALID ENTRY. ");
				continue; 
			}
			
			// quantity of items
			System.out.print("How many are you purchasing? ");
			java.util.Scanner input3 = new java.util.Scanner(System.in);
			int quantity = input.nextInt();
			// updates quantities and subTotal
			if (choice == 1) {
				if (size2 == 1){
					smallCoffeeTotal += quantity;
					subTotal += (1.25 * quantity);
				}else if (size2 == 2){
					mediumCoffeeTotal += quantity;
					subTotal += (1.50 * quantity);
				}else if (size2 == 3){
					mondoCoffeeTotal += quantity;
					subTotal += (2.00 * quantity);
				}
			}else if (choice == 2){
				muffinTotal += quantity;
				subTotal += (2.75 * quantity);
			}else if (choice == 3){
				teaTotal += quantity;
				subTotal += (2.00 * quantity);
			}else if(choice == 4){
				hotCocoTotal += quantity;
				subTotal += (1.50 * quantity);
			}else if (choice == 5){
				waterTotal += quantity;
				subTotal += (2.00 * quantity);
			}else if(choice == 6){
				organicWaterTotal += quantity;
				subTotal += (4.00 * quantity);
			}
			// tracks discount and special
			if (mondoCoffeeTotal > 0 && muffinTotal > 0){
				if (mondoCoffeeTotal - muffinTotal >= 0){
					muffinDiscountNumber = muffinTotal;
				}else if (mondoCoffeeTotal - muffinTotal <= 0){
					muffinDiscountNumber = mondoCoffeeTotal;
				}
			}else if (organicWaterTotal > 0){
				organicWaterSpecial = true;
			}
		}
		// calculate prices and tax/discounts
		if (muffinDiscountNumber > 0){
			subTotal -= (1.75 * muffinDiscountNumber);
		}
		float javaTax = (float) 0.08;
		float taxTotal = (subTotal * javaTax);
		float totalPrice = taxTotal + subTotal;
		// printing receipt
		System.out.println("Your total is " + totalPrice);
		System.out.println("Customer Paid? $");
		java.util.Scanner input3 = new java.util.Scanner(System.in);
		float pay = input3.nextFloat();
		float change = pay - totalPrice;
		System.out.println("Your receipt: ");
		if (smallCoffeeTotal > 0){
			System.out.println("\t" +smallCoffeeTotal + " Small Coffee $" + (smallCoffeeTotal * 1.25));
		}
		if (mediumCoffeeTotal > 0){
			System.out.println("\t" + mediumCoffeeTotal + " Medium Coffee $" + (mediumCoffeeTotal * 1.50) + "0");
		}
		if (mondoCoffeeTotal > 0){
			System.out.println("\t" + mondoCoffeeTotal + " Mondo Coffee $" + (mondoCoffeeTotal * 2.00) + "0");
		}
		if (muffinTotal > 0){
			System.out.println("\t" + muffinTotal + " Chocolate Chip Muffin $" + (muffinTotal * 2.75));
		}
		if (teaTotal > 0){
			System.out.println("\t" + teaTotal + " Pot of Tea $" + (teaTotal * 2.00) + "0");
		}
		if (hotCocoTotal > 0) {
			System.out.println("\t" + hotCocoTotal + " Hot Chocolate $" + (hotCocoTotal * 1.50) + "0");
		}
		if (waterTotal > 0){
			System.out.println("\t" + waterTotal + " Water $" + (waterTotal * 2.00) + "0");
		}
		if (organicWaterTotal > 0){
			System.out.println("\t" + organicWaterTotal + " Organic Water $" + (organicWaterTotal * 4.00) + "0");
		}
		System.out.println("----------------------------------------");
		if (muffinDiscountNumber > 0){
			System.out.println("\tMondo Muffin Discount $-" + (muffinDiscountNumber * 1.75));
		}
		if (organicWaterSpecial = true){
			System.out.println("\tFree chemistry textbook $0.00");
		}
		System.out.println("-----------------------------------------");
		System.out.print("\tSubtotal: ");
		System.out.printf("$%,.2f", subTotal);
		System.out.print("\n\t8% Java Tax: ");
		System.out.printf("$%,.2f", taxTotal);
		System.out.println("\n----------------------------------------");
		System.out.println("\tTotal: $" + totalPrice);
		System.out.print("\tYour change is " );
		System.out.printf("$%,.2f", change);
		
	}
}
