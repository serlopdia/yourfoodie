package aiss.model.resources;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import aiss.model.youtube.VideoSearch;

public class YoutubeResourceTest {

	@Test
	public void getVideosTest() throws UnsupportedEncodingException {
		String title = "macarrones con tomate";
		YoutubeResource youtube = new YoutubeResource();
		VideoSearch youtubeResults = youtube.getVideos(title);
		
		assertNotNull("The search returned null", youtubeResults);
		assertNotNull("The search returned null", youtubeResults.getItems());
		assertFalse("The number of videos of " + title + " is zero", youtubeResults.getItems().size()==0);
		
		System.out.println("The search for " + title + " returned " + youtubeResults.getItems().size() + " videos.");
	}
}
