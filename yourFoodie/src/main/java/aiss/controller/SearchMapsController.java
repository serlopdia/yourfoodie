package aiss.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.googleMaps.GoogleMapsSearch;
import aiss.model.googleMaps.Location;
import aiss.model.googleMaps.Result;
import aiss.model.resources.GoogleMapsResource;

public class SearchMapsController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(SearchMapsController.class.getName());

	public SearchMapsController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String query = request.getParameter("location");
		String receta = request.getParameter("query");
		String name = request.getParameter("label");

		RequestDispatcher rd = null;
		GoogleMapsResource map = new GoogleMapsResource();
		GoogleMapsSearch gMResults = map.getPlaces(query);
		GoogleMapsSearch gMyLocation = map.getMyLocation(query);
		List<Result> lista = gMResults.getResults();
		List<Result> myLocation = gMyLocation.getResults();
		List<Location> locations = new ArrayList<>();
		for (Result result : lista) {
			locations.add(result.getGeometry().getLocation());
		}

		if (query != null) {
			request.setAttribute("results", lista);
			request.setAttribute("label", name);
			request.setAttribute("query", receta);
			if (myLocation.size() >= 1) {
				request.setAttribute("myLocation", myLocation.get(0));
			} else {
				request.setAttribute("myLocation", lista.get(0));
			}
			rd = request.getRequestDispatcher("/successMaps.jsp");

		} else {
			log.log(Level.SEVERE, "Name: " + query);
			rd = request.getRequestDispatcher("/error.jsp");
		}

		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
