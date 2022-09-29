package aiss.model.resources;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.restlet.resource.ClientResource;

import aiss.model.googleMaps.GoogleMapsSearch;

public class GoogleMapsResource {
	private static final String GOOGLEMAPS_API_KEY = "AIzaSyBzaDDZFz3r7Px4rxYjtxG5IpFPRwomfUI";

	private static final Logger log = Logger.getLogger(EdamamResource.class.getName());

	public GoogleMapsSearch getPlaces(String query) throws UnsupportedEncodingException {
		query = "restaurante " + query;
		String keyword = URLEncoder.encode(query, "UTF-8");
		String uri = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=" + keyword + "&key="
				+ GOOGLEMAPS_API_KEY;
		log.log(Level.FINE, "URL:" + uri);
		ClientResource cr = new ClientResource(uri);
		GoogleMapsSearch gMS = cr.get(GoogleMapsSearch.class);

		return gMS;
	}

	public GoogleMapsSearch getMyLocation(String query) throws UnsupportedEncodingException {
		String keyword = URLEncoder.encode(query, "UTF-8");
		String uri = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=" + keyword + "&key="
				+ GOOGLEMAPS_API_KEY;
		log.log(Level.FINE, "URL:" + uri);
		ClientResource cr = new ClientResource(uri);
		GoogleMapsSearch gMS = cr.get(GoogleMapsSearch.class);

		return gMS;
	}

}
