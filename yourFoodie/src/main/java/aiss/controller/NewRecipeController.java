package aiss.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import aiss.model.edamam.EdamamSearch;
import aiss.model.edamam.Hit;
import aiss.model.edamam.Ingredient;
import aiss.model.edamam.Recipe;
import aiss.model.resources.EdamamResource;



public class NewRecipeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(NewRecipeController.class.getName());
	
	public NewRecipeController() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		
		String query = request.getParameter("searchQuery");
		String uriReceta = request.getParameter("uriRecipe");
		
		EdamamResource receta = new EdamamResource();
		EdamamSearch resultados = receta.getRecipes(query);
		List<Recipe> lista = new ArrayList<>();
		
		for(Hit hit: resultados.getHits()) {
			lista.add(hit.getRecipe());
		}
		
		Map<String, Recipe> map2 = new HashMap<>();
		for(Recipe recipe2 : lista) {
			map2.put(recipe2.getUri(), recipe2);
		}
		
		HttpSession session = request.getSession();
		Recipe recipe2 = map2.get(uriReceta);
		List<Ingredient> ingredientes = recipe2.getIngredients();
		List<String> ingredientes2 = new ArrayList<String>();
		for(Ingredient ingrediente: ingredientes) {
			ingredientes2.add(ingrediente.getText());
		}
		
		session.setAttribute("recipe", recipe2.getLabel());
		session.setAttribute("ingredients", ingredientes2);
		
		rd = request.getRequestDispatcher("/GoogleDriveFileNewController");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
}
