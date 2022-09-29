package aiss.model.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import aiss.model.Recipe;
import aiss.model.Tutorial;

public class MapRecipeRepository implements RecipeRepository {
 
	Map<String,Recipe> recipeMap;
	Map<String, Tutorial> tutorialMap;
	private static MapRecipeRepository instance=null;
	private int index=0;
	
	public static MapRecipeRepository getInstance() {
		if(instance==null) {
			instance=new MapRecipeRepository();
			instance.init();
		}
		return instance;
	}
	
	public void init() {
		recipeMap = new HashMap<String, Recipe>();
		tutorialMap =new HashMap<String, Tutorial>();
		
		
		//create tutorials
		Tutorial tutorial1 = new Tutorial();
		tutorial1.setTittle("Macorrones con queso");
		tutorial1.setAuthor("JuanMartinez");
		tutorial1.setYear("2015");
		addTutorial(tutorial1);
		
		Tutorial tutorial2 = new Tutorial();
		tutorial2.setTittle("Macorrones con queso al estilo mexicano");
		tutorial2.setAuthor("AnaRecetasFaciles");
		tutorial2.setYear("2010");
		addTutorial(tutorial2);
		
		Tutorial tutorial3 = new Tutorial();
		tutorial3.setTittle("Pescado encebollado");
		tutorial3.setAuthor("enCasaContigo");
		tutorial3.setYear("2019");
		addTutorial(tutorial3);
		
		Tutorial tutorial4 = new Tutorial();
		tutorial4.setTittle("Pescado encebollado al estilo canario");
		tutorial4.setAuthor("cocina con alma canaria");
		tutorial4.setYear("2017");
		addTutorial(tutorial4);
		
		Tutorial tutorial5 = new Tutorial();
		tutorial5.setTittle("Mi receta favorita pescado a la portuguesa");
		tutorial5.setAuthor("La cocina de Yaiza");
		tutorial5.setYear("2019");
		addTutorial(tutorial5);
		
		//create Recipes
		Recipe recetaMacarrones = new Recipe();
		recetaMacarrones.setName("Receta de Macarrones con queso");
		recetaMacarrones.setDescription("estupenda receta de macarrones");
		addRecipe(recetaMacarrones);
		
		Recipe recetaPescado = new Recipe();
		recetaPescado.setName("Receta de Pescado Encebollado");
		recetaPescado.setDescription("estupenda receta de pescado");
		addRecipe(recetaPescado);
		
		
		//ad tutorials to recipes
		addTutorial(recetaMacarrones.getId(), tutorial1.getId());
		addTutorial(recetaMacarrones.getId(), tutorial2.getId());
		
		addTutorial(recetaPescado.getId(),tutorial3.getId());
		addTutorial(recetaPescado.getId(),tutorial4.getId());
		addTutorial(recetaPescado.getId(),tutorial5.getId());
	}
	
	
	//Recipe related operatios
	@Override
	public void addRecipe(Recipe r) {
		String id= "r" + index++;
		r.setId(id);
		recipeMap.put(id, r);
	}
	@Override
	public Collection<Recipe> getAllRecipes(){
		return recipeMap.values();
	}
	@Override
	public Recipe getRecipe(String id) {
		return recipeMap.get(id);
	}
	@Override
	public void updateRecipe(Recipe r) {
		recipeMap.put(r.getId(), r);
	}
	@Override
	public void deleteRecipe(String id) {
		recipeMap.remove(id);
	}
	@Override
	public void addTutorial(String recipeId, String tutorialId) {
		Recipe receta= getRecipe(recipeId);
		receta.addTutorial(tutorialMap.get(tutorialId));
	}
	@Override
	public Collection<Tutorial> getAll(String recetaID){
		return getRecipe(recetaID).getTutorials();
	}
	@Override
	public void removeTutorial(String recetaID, String tutorialID) {
		getRecipe(recetaID).deleteTutorial(tutorialID);
	}
	
	//tutorial related operations
	@Override
	public void addTutorial(Tutorial t) {
		String id="t"+ index++;
		t.setId(id);
		tutorialMap.put(id, t);
	}
	@Override
	public Collection<Tutorial> getAllTutorials(){
		return tutorialMap.values();
	}
	@Override
	public Tutorial getTutorial(String tutorialID) {
		return tutorialMap.get(tutorialID);
	}
	@Override
	public void updateTutorial(Tutorial t) {
		Tutorial tutorial= tutorialMap.get(t.getId());
		tutorial.setTittle(t.getTittle());
		tutorial.setAuthor(t.getAuthor());
		tutorial.setYear(t.getYear());
		
	}
	@Override
	public void deleteTutorial(String tutorialID) {
		tutorialMap.remove(tutorialID);
	}
	
}
