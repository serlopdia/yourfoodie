package aiss.model.resources;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import aiss.model.googleMaps.GoogleMapsSearch;

public class GoogleMapsResourceTest {
	@Test
	public void getLocationsTest() throws UnsupportedEncodingException {
		String title = "Huerta del rey,Sevilla";
		title= title.replaceAll(",", " ");
		GoogleMapsResource map = new GoogleMapsResource();
		GoogleMapsSearch gMResults = map.getPlaces(title);

		assertNotNull("The search returned null", gMResults);
		assertNotNull("The search returned null", gMResults.getResults());
		assertFalse("The number of results of " + title + " is zero", gMResults.getResults().size() == 0);

		System.out.println(
				"The search for " + title + "'s results returned " + gMResults.getResults().get(0).getTypes()+ " results.");

	}

	@Test
	public void getMyLocationTest() throws UnsupportedEncodingException {
		String title = "Huerta del rey,Sevilla";
		title = title.replaceAll(",", " ");
		GoogleMapsResource map = new GoogleMapsResource();
		GoogleMapsSearch gMResults = map.getMyLocation(title);

		assertNotNull("The search returned null", gMResults);
		assertNotNull("The search returned null", gMResults.getResults());
		assertFalse("The number of results of " + title + " is zero", gMResults.getResults().size() == 0);

		System.out.println("The search for " + title + "'s results returned "
				+ gMResults.getResults().get(0).getFormattedAddress() + " results.");

	}

}
