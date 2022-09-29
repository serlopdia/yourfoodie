package aiss.model.repository;

import java.util.Collection;

import aiss.model.Recipe;
import aiss.model.Tutorial;

public interface RecipeRepository {
	
	//tutorials
	public void addTutorial(Tutorial t);
	public Collection<Tutorial> getAllTutorials();
	public Tutorial getTutorial(String tutorialID);
	public void updateTutorial(Tutorial t);
	public void deleteTutorial(String tutorialID);
	
	
	//Recipes
	public void addRecipe(Recipe r);
	public Collection<Recipe> getAllRecipes();
	public Recipe getRecipe(String id);
	public void updateRecipe(Recipe r);
	public void deleteRecipe(String id);
	public Collection<Tutorial> getAll(String recetaID);
	public void addTutorial(String recipeId, String tutorialId);
	public void removeTutorial(String recetaID, String tutorialID);

}
