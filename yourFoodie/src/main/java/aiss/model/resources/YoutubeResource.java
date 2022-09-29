package aiss.model.resources;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import aiss.model.youtube.VideoSearch;

public class YoutubeResource {

	
	
	private static final String YOUTUBE_API_KEY = "AIzaSyDxCF-Wtoxa_eTcbce6wUIzznoSTxujw_w";
	private static final Logger log = Logger.getLogger(YoutubeResource.class.getName());
	
	public VideoSearch getVideos(String param) throws UnsupportedEncodingException {
		
		String query = URLEncoder.encode(param, "UTF-8");
		String uri = "https://www.googleapis.com/youtube/v3/search?part=snippet&q=" +
					query + "+music&key=" + YOUTUBE_API_KEY + "&maxResults=1";
		ClientResource cr = null;
		VideoSearch youtubeSearch = null;
		
		try {
		cr = new ClientResource(uri);
		youtubeSearch = cr.get(VideoSearch.class);
			log.log(Level.FINE, "Búsqueda de " + query + " realizada correctamente.");
		
		} catch (ResourceException e) {
			log.log(Level.WARNING, "Error al obtener vídeos de: " + cr.getResponse().getStatus());
			throw e;
		}
		return youtubeSearch;
	}
}
