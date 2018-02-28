package edu.nyu.cs9053.homework5;

public class RecipeReady implements RecipeReadyCallback { 
	
	private final Recipe recipe;

	private final Oven oven;

	public RecipeReady(Oven oven, Recipe recipe){
		this.recipe = recipe;
		this.oven = oven;
	}

	@Override public void recipeReadyToCook(Recipe recipe) {
		if (recipe == null) {
            throw new IllegalArgumentException("recipe cannot be null");
        }
		Timer recipeTimer = new Timer() {
			@Override public void update(Time unit, int value, int ovenTemperature) {
				if (unit == null) {
            		throw new IllegalArgumentException("unit cannot be null");
        		}
				recipe.adjust(unit, value, ovenTemperature);
			}
		};
		this.oven.cook(recipe, recipeTimer, true);
		while (!recipe.isRecipeDone()) {
			this.oven.cook(recipe, recipeTimer, false);
		}
		this.oven.takeOut(recipe);
	}
}