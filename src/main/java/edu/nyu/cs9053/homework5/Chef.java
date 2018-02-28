package edu.nyu.cs9053.homework5;

/**
 * User: blangel
 */
public class Chef {

    public static void main(String[] args) {
        Oven oven = new Oven(300);
        SousChef sousChef = new SousChef(oven);
        Chef chef = new Chef(oven);
        chef.cookRecipe(sousChef, new PotRoast(oven));
        chef.cookRecipe(sousChef, new Baguette(oven));
        chef.cookRecipe(sousChef, new RoastedSweetPotato(oven));
        chef.cookRecipe(sousChef, new Baguette(oven));
    }

    private final Oven oven;

    public Chef(Oven oven) { 
        this.oven = oven;
    }

	public void cookRecipe(SousChef sousChef, Recipe recipe) {
		if ((recipe == null) || (sousChef == null)) {
			throw new IllegalArgumentException("Recipe or sousChef cannot be null");
        }
        recipe.initializeFromOven(this.oven);
        sousChef.prepare(recipe, new RecipeReady(this.oven, recipe));
	}
}