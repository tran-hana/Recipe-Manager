/* File name: RecipeManager.java 
 * Author: Ha Nhu Y Tran, 041165059
 * Course: CST8284 – OOP
 * Assignment: Assignment 3
 * Date: Nov 29, 2024
 * Professor: Dr. Moshiur Rahman
 * Purpose: This class manages a list of recipes. It provides functionality to update the ordered quantity for a specific recipe,
 * get shopping list text of required ingredients based on user's orders, and load recipes from a file.
 * Class list: Recipe.java, RecipeManager.java, RecipeManagerTest.java
 */

package assn3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class is used to manage a list of recipes. It provides functionality to update the ordered quantity for a specific recipe,
 * get shopping list text of required ingredients based on user's orders, and load recipes from a file.
 * @see assn3
 * @see java.io.File
 * @see java.io.IOException
 * @see java.util.ArrayList
 * @see java.util.List
 * @see java.util.Scanner
 * @author Ha Nhu Y Tran
 * @version 1.01
 * @since 17.0.11
 */ 
public class RecipeManager {

    /** The list of recipes managed by RecipeManager. 
     * Each recipe includes information of its name, ingredients and ordered quantities
     */
    private List<Recipe> recipes;

    /**
     * Create no-arg constructor
     */
    public RecipeManager() {
        this.recipes = new ArrayList<>();
    }
    
    /**
     * Returns the list of recipes managed by this RecipeManager class.
     * @return a list of recipes
     */
    public List<Recipe> getRecipes() {
    	return this.recipes;
    }
    
    /**
     * Updates the ordered quantity for a specific recipe.
     * @param breadIndex the index of the recipe in the list
     * @param breadQuantity the quantity to add to the ordered quantity
     * @throws IllegalStateException if the total ordered quantity is negative
     */
	public void order(int breadIndex, int breadQuantity) throws IllegalStateException {
		int newOrderedQuantity = this.recipes.get(breadIndex).getOrderedQuantity() + breadQuantity;
		if (newOrderedQuantity < 0) {
			throw new IllegalStateException(
					"Invalid new ordered quantity. The total ordered quantity of a bread type cannot be negative."
				);
		}
		this.recipes.get(breadIndex).setOrderedQuantity(newOrderedQuantity);
	}

    /**
     * Create a shopping list of required ingredients for the current orders.
     * @return a string containing details of the shopping list
     */
	public String getShoppingListText() {
		String result = "";
		float totalEggs = 0.0f;
	    float totalYeast = 0.0f;
	    float totalFlour = 0.0f;
	    float totalSugar = 0.0f;
	    float totalButter = 0.0f;
	    
	    // Iterating through the list of recipes to calculate and build the shopping list text.
		for (int i = 0; i < this.getRecipes().size(); i++) {
		    // Retrieve the current recipe from the list.
			Recipe currentRecipe = this.getRecipes().get(i);
			
		    // Get the quantity of this recipe that was ordered.
    		int orderedQuantity = currentRecipe.getOrderedQuantity();
    		
    		// If the ordered quantity is positive, process following
    		if (orderedQuantity > 0) {
    			
    	        // Append the ordered quantity and recipe name to the shopping list text.
    			result += orderedQuantity + " " + 
    						currentRecipe.getRecipeName() +
    						" loaf/loaves.\n";
    			// Update the totals for each ingredient
    			totalEggs += (currentRecipe.getEggs() * orderedQuantity);
    			totalYeast += (currentRecipe.getYeast() * orderedQuantity);
    			totalFlour += (currentRecipe.getFlour() * orderedQuantity);
    			totalSugar += (currentRecipe.getSugar() * orderedQuantity);
    			totalButter += (currentRecipe.getButter() * orderedQuantity);
    		}
		}
    	result += "\nYou will need a total of:\n";
    	
    	// If the total quantity of each ingredient is positive, it is added to shopping list text
		if (totalEggs > 0) {
			result += (totalEggs + " egg(s)\n");
		}
		if (totalYeast > 0) {
			result += (totalYeast + " grams of yeast\n");
		}
		if (totalFlour > 0) {
			result += (totalFlour + " grams of flour\n");
		}
		if (totalSugar > 0) {
			result += (totalSugar + " grams of sugar\n");
		}
		if (totalButter > 0) {
			result += (totalButter + " grams of butter\n");
		}
		return result;
	}
	
    /**
     * This is used to load recipes from a file and adds them to the recipes list.
     * Each recipe includes a name and five ingredients: sugar, eggs, flour, yeast, and butter.
     * If a recipe name is missing, an error message is displayed and the recipe is skipped.
     * @param filePath the path to the file containing recipe data
     */
    public void loadRecipes(String filePath) {
        try {
        	Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.startsWith("Recipe")) {
                    String recipeName = "";
                    try {
                    	recipeName = line.substring("Recipe".length()).trim(); // Extract recipe name
                    	if (recipeName.length() == 0) {
                    		throw new IllegalArgumentException();
                    	}
                    }
                	catch (IllegalArgumentException e) {
                        System.out.println("Error: Missing recipe name after 'Recipe' in file.");
                        continue; // Skip this recipe and move to the next line
                    }
                    
                    Float sugar = 0.0f;
                    Float eggs = 0.0f;
                    Float flour = 0.0f;
                    Float yeast = 0.0f;
                    Float butter = 0.0f;

                    // Read the next 5 lines for ingredients
                    for (int i = 0; i < 5 && scanner.hasNextLine(); i++) {
                        line = scanner.nextLine().trim();
                        if (line.startsWith("sugar")) {
                            sugar = Float.parseFloat(line.split(" ")[1]);
                        } else if (line.startsWith("eggs")) {
                            eggs = Float.parseFloat(line.split(" ")[1]);
                        } else if (line.startsWith("flour")) {
                            flour = Float.parseFloat(line.split(" ")[1]);
                        } else if (line.startsWith("yeast")) {
                            yeast = Float.parseFloat(line.split(" ")[1]);
                        } else if (line.startsWith("butter")) {
                            butter = Float.parseFloat(line.split(" ")[1]);
                        }
                    }

                    // Create Recipe object and add to the list
                    recipes.add(new Recipe(recipeName, eggs, yeast, flour, sugar, butter));
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Writes the shopping list text to a specified file.
     * This method retrieves the shopping list text and writes it to the given file.
     * If the file already exists, its content will be overwritten.     
     * @param fileName the name of the file including the file's path to write the shopping list text to.  
     */
	public void printToFile(String fileName) {
		String shoppingListText = this.getShoppingListText();
		
		// Open a FileWriter to write the shopping list text to the file
		FileWriter writer;
		try {
			writer = new FileWriter(fileName);
		
			// Write the shopping list text to the file
			writer.write(shoppingListText);
			
			// Close the FileWriter to save the changes
			writer.close();
		} catch (IOException e) {
			System.out.println("Error when writing to the file: " + e.getMessage());
		}
		
	}
}