/* File name: Recipe.java
 * Author: Ha Nhu Y Tran, 041165059
 * Course: CST8284 – OOP
 * Assignment: Assignment 3
 * Date: Nov 29, 2024
 * Professor: Dr. Moshiur Rahman
 * Purpose: The Recipe class represents a recipe with ingredients and quantities,
 * and allows for getting and setting properties such as the recipe name, 
 * ingredient quantities, and the ordered quantity.
 * Class list: Recipe.java, RecipeManager.java, RecipeManagerTest.java
 */

package assn3;

/**
 * The Recipe class models a recipe with multiple ingredients and their corresponding quantities.
 * It provides methods to retrieve and modify the recipe's name, ingredients, and ordered quantity.
 * @author Ha Nhu Y Tran
 * @see assn3
 * @version 1.01
 * @since 17.0.11
 */
public class Recipe {

	/** The name of the recipe */	
	private String recipeName;

	/** The quantity of eggs required for the recipe*/
	private float eggs;

	/** The quantity of yeast (in grams) required for the recipe */
	private float yeast;

	/** The quantity of flour (in grams) required for the recipe */
	private float flour;

	/** The quantity of sugar (in grams) required for the recipe */
	private float sugar;

	/** The quantity of butter (in grams) required for the recipe */
	private float butter;

	/** The ordered quantity of the recipe */
	private int orderedQuantity;

	/**
	 * Constructs a new Recipe object with the identified recipe name and ingredients.
	 * @param recipeName the name of the recipe
	 * @param eggs the number of eggs required for the recipe
	 * @param yeast the amount of yeast (in grams) required for the recipe
	 * @param flour the amount of flour (in grams) required for the recipe 
	 * @param sugar the amount of sugar (in grams) required for the recipe 
	 * @param butter the amount of butter (in grams) required for the recipe 
	 */
	public Recipe(String recipeName, float eggs, float yeast, float flour, float sugar, float butter) {
		this.recipeName = recipeName; 
		this.eggs = eggs;
		this.yeast = yeast;
		this.flour = flour;
		this.sugar = sugar;
		this.butter = butter;
		this.orderedQuantity = 0;
	}

	/**
	 * Gets the name of the recipe.
	 * @return the name of the recipe
	 */
	public String getRecipeName() {
		return recipeName;
	}

	/**
	 * Sets the name of the recipe.
	 * @param recipeName the name to set for the recipe
	 */
	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	/**
	 * Gets the number of eggs required for the recipe.
	 * @return the number of eggs required
	 */
	public float getEggs() {
		return eggs;
	}

	/**
	 * Sets the number of eggs required for the recipe.
	 * @param eggs the number of eggs to set
	 */
	public void setEggs(float eggs) {
		this.eggs = eggs;
	}

	/**
	 * Gets the amount of yeast required for the recipe.
	 * @return the amount of yeast (in grams) required 
	 */
	public float getYeast() {
		return yeast;
	}

	/**
	 * Sets the amount of yeast required for the recipe.
	 * @param yeast the amount of yeast (in grams) to set 
	 */
	public void setYeast(float yeast) {
		this.yeast = yeast;
	}

	/**
	 * Gets the amount of flour required for the recipe.
	 * @return the amount of flour (in grams) required 
	 */
	public float getFlour() {
		return flour;
	}

	/**
	 * Sets the amount of flour required for the recipe.
	 * @param flour the amount of flour (in grams) to set 
	 */
	public void setFlour(float flour) {
		this.flour = flour;
	}

	/**
	 * Gets the amount of sugar required for the recipe.
	 * @return the amount of sugar (in grams) required 
	 */
	public float getSugar() {
		return sugar;
	}

	/**
	 * Sets the amount of sugar required for the recipe.
	 * @param sugar the amount of sugar (in grams) to set 
	 */
	public void setSugar(float sugar) {
		this.sugar = sugar;
	}

	/**
	 * Gets the amount of butter required for the recipe.
	 * @return the amount of butter (in grams) required 
	 */	
	public float getButter() {
		return butter;
	}

	/**
	 * Sets the amount of butter required for the recipe.
	 * @param butter the amount of butter(in grams) to set 
	 */
	public void setButter(float butter) {
		this.butter = butter;
	}

	/**
	 * Gets the ordered quantity of the recipe.
	 * @return the ordered quantity of the recipe
	 */
	public int getOrderedQuantity() {
		return orderedQuantity;
	}

	/**
	 * Sets the ordered quantity of the recipe.
	 * @param quantity the ordered quantity of recipe to set
	 */
	public void setOrderedQuantity(int quantity) {
		this.orderedQuantity = quantity;
	}
}