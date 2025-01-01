/* File name: RecipeManagerTest.java
 * Author: Ha Nhu Y Tran, 041165059
 * Course: CST8284 – OOP
 * Assignment: Assignment 3
 * Date: Nov 29, 2024
 * Professor: Dr. Moshiur Rahman
 * Purpose: This class is the main entry point for the Recipe Manager program. It allows the user to interact with the recipe manager 
 * to make bread order. The user can view recipes, order selected bread recipe of their choice with a specified quantity,
 * User also has the option to get the program to generate and print a shopping list for the ingredients required
 * to make the ordered breads. Furthermore, the program can save the shopping list to a file if the user requests it by choosing appropriate option.
 * Class list: Recipe.java, RecipeManager.java, RecipeManagerTest.java
 */
package assn3;

import java.util.Scanner;

/**
 * This class is the main entry point for the Recipe Manager application.
 * It allows the user to interact with the recipe manager to make bread order.
 * The user can view available recipes, order selected bread recipe of their choice with a specified quantity,
 * After user selects their options, the program generates and prints a shopping list for the ingredients required to make the ordered breads
 * as per their requests. Furthermore, the program can save the shopping list to a file for later use.
 * @see assn3
 * @see java.util.Scanner
 * @author Ha Nhu Y Tran
 * @version 1.01
 * @since 17.0.11
 */
public class RecipeManagerTest {

	/**
	 * The RecipeManager instance that manages recipes.
	 * This includes loading recipes, ordering bread, and managing the shopping list.
	 */
	private static RecipeManager manager;

	/**
	 * The Scanner object used for reading input from the user.
	 */
	private static Scanner scanner;

	/**
	 * The method printMenu() is used to print the main option menu of the application.
	 */
	private static void printMenu() {
		System.out.println("Please select one of the following options:");
		System.out.println("1. Show available recipes.");
		System.out.println("2. Create Shopping List.");
		System.out.println("3. Print Shopping List.");
		System.out.println("4. Quit Program.");
		System.out.println("0. Reprint this menu.");
	}

	/**
	 * The method displayAllBreadRecipes() is used to display the names of all available bread recipes,
	 * showing their corresponding number from 1 to n where n is the total number of recipes
	 */
	private static void displayAllBreadRecipes() {
		System.out.println("Available Recipes:");
		for (int i = 0; i < manager.getRecipes().size(); i++) {
			System.out.println((i+1) + ". " + manager.getRecipes().get(i).getRecipeName());
		}
	}

	/**
	 * This method is used to order a specified quantity of a selected bread recipe.
	 * The user is prompted to choose a bread type and quantity of selected bread type.
	 * @param breadType The index (1-based) of the selected bread recipe based on the recipe list.
	 * @param breadQuantity The quantity of selected bread.
	 */
	private static void orderBreadRecipe(int breadType, int breadQuantity) {
		manager.order(breadType-1, breadQuantity);
	}

	/**
	 * This method is used to handle user input of selecting a bread recipe and its quantity to order.
	 * If the input is invalid, the user is prompted again to provide correct values.
	 * The method uses exception handling to ensure valid inputs for both bread type and quantity.
	 */
	private static void displayAndOrderBreadRecipes() {
		int breadType = 0;
		int breadQuantity = 0;
		boolean isBreadOrdered = false;
		while (!isBreadOrdered) {
			System.out.print("Which bread would you like? ");
			try {
				breadType = Integer.parseInt(scanner.nextLine());
				if (breadType < 1 || breadType > manager.getRecipes().size()) {
					throw new IllegalArgumentException("Valid input are digits from 1 to " + manager.getRecipes().size());
				}
				isBreadOrdered = true; // Exit the loop if user enters valid input
			} catch (NumberFormatException e) {
				System.out.println("Please only type digits. \nValid inputs are digits from 1 to " + manager.getRecipes().size());
				continue;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				continue;
			}
		}
		isBreadOrdered = false;
		while (!isBreadOrdered) {
			System.out.print("How much of this bread would you like? ");
			try {
				breadQuantity = Integer.parseInt(scanner.nextLine());
				orderBreadRecipe(breadType, breadQuantity);
				isBreadOrdered = true; // Exit the loop after a successful order

			} catch (NumberFormatException e) {
				System.out.println("Please only type digits.");
				continue;
			} catch (IllegalStateException e) {
				System.out.println("Error when ordering the new bread: " + e.getMessage());
				continue;
			}
		}
	}

	/**
	 * This method is used to print/ generate shopping list and asks the user if they want to save it to a file.
	 * If the user agrees, the shopping list is written to "shoplist.txt" file.
	 */
	private static void printShoppingList() {
		String shoppingListText = manager.getShoppingListText();
		System.out.print(shoppingListText);
		System.out.print("Do you want to save this list (Y/n)? ");
		String choice = "";
		
		// Loop until the user provides a valid input (Y or N)
		while (!choice.equals("Y") && !choice.equals("N")) {
			try {
				choice = scanner.nextLine().toUpperCase();
				if (!choice.equals("Y") && !choice.equals("N")) {
					throw new IllegalArgumentException("Invalid input. Please enter 'Y' or 'N'.");
				}
				if (choice.equals("Y")) {
					manager.printToFile("./shoplist.txt");
				}
			} catch (IllegalArgumentException e) {
				// Handle invalid input (anything other than 'Y' or 'N')
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * Main method of the program. This allows the user  order bread recipes, 
	 * and generate and save a shopping list.
	 * @param args Command-line arguments (not used).
	 */
	public static void main(String[] args) {
		//Creates a new instance of the RecipeManager class, 
		manager = new RecipeManager();
		
		//Loads the list of recipes from the file located at "./recipelist.txt".
		manager.loadRecipes("./recipelist.txt");

		scanner = new Scanner(System.in);

		System.out.println("Welcome to Ha Nhu Y Recipe Manager.");
		printMenu();

		String errorMessage1 = "Please only type digits. \nValid input are digits from 0 to 4.";
		String errorMessage2 = "Valid input are digits from 0 to 4.";
		boolean running = true;
		
        // Using loop to process user's input
		while (running) {
			System.out.print("Please enter your choice: ");
			int choice = 0;
			try {
				choice = Integer.parseInt(scanner.nextLine());
				switch (choice) {
				case 1:
					displayAllBreadRecipes();
					break;
				case 2:
					displayAndOrderBreadRecipes();
					printMenu();// Reprint menu after ordering
					break;
				case 3:
					printShoppingList();
					break;
				case 4:
					running = false;
					break;
				case 0:
					printMenu();
					break;
				default:
					System.out.println(errorMessage2);
					continue;
				}
			}
			catch (NumberFormatException e) {
				System.out.println(errorMessage1);
				continue;
			}
		}
		scanner.close();
	}
}