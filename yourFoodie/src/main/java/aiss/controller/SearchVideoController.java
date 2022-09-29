package aiss.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.resources.YoutubeResource;
import aiss.model.youtube.VideoSearch;
import aiss.model.edamam.EdamamSearch;
import aiss.model.edamam.Hit;
import aiss.model.edamam.Recipe;
import aiss.model.resources.EdamamResource;

/**
 * Servlet implementation class SearchVideoController
 */
public class SearchVideoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(SearchController.class.getName());

	public SearchVideoController() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("label");
		String receta = request.getParameter("query");
		String uriReceta = request.getParameter("uriRecipe");
		EdamamResource edamam = new EdamamResource();
		EdamamSearch resultados = edamam.getRecipes(receta);
		RequestDispatcher rd = null;
		
		List<Recipe> lista = new ArrayList<>();
		for (Hit hit : resultados.getHits()) {
			lista.add(hit.getRecipe());

		}
		
		Map<String, Recipe> map = new HashMap<>();
		for (Recipe recipe2 : lista) {
			map.put(recipe2.getUri(), recipe2);
		}
		
		Recipe mapaReceta = map.get(uriReceta);
		
		// Search for videos in YouTube
		log.log(Level.FINE, "Searching for Youtube videos that contain " + receta);
		YoutubeResource youtube = new YoutubeResource();
		VideoSearch youtubeResults = youtube.getVideos(name);
		
		if (youtubeResults!=null) {
			request.setAttribute("label", name);
			request.setAttribute("videos", youtubeResults.getItems());
			request.setAttribute("mapRecipe", mapaReceta);
			request.setAttribute("uriReceta", uriReceta);
			request.setAttribute("query", receta);
			request.setAttribute("ingredientes", map);
			rd = request.getRequestDispatcher("/successVideo.jsp");
		} else {
			log.log(Level.SEVERE, "YouTube object: " + youtubeResults);
			rd = request.getRequestDispatcher("/error.jsp");
		}
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doGet(request, response);
	}
}
