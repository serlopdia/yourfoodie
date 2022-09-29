package aiss.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.edamam.EdamamSearch;
import aiss.model.resources.EdamamResource;

/**
 * Servlet implementation class SearchController
 */
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(SearchController.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String query = request.getParameter("searchQuery");
		RequestDispatcher rd = null;
		EdamamSearch resultados =null;

		log.log(Level.FINE, "Searching for Edamam recipes that contain " + query);
		

		if (query != null) {
			log.log(Level.FINE, "Searching for Edamam recipes that contain " + query);
			 EdamamResource edamam = new EdamamResource();
			 resultados = edamam.getRecipes(query);
			if (resultados.getHits().size() == 0) {
				request.setAttribute("message", "No existen recetas que coincidan con sus parámetros de búsqueda");
				rd = request.getRequestDispatcher("/index.html");
			} else {
				request.setAttribute("query", query);
				request.setAttribute("recipes", resultados.getHits());
				rd = request.getRequestDispatcher("/success.jsp");
			}

		} else {
			
			log.log(Level.SEVERE, "Edamam object: " + resultados);

			rd = request.getRequestDispatcher("/error.jsp");
		}

		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
