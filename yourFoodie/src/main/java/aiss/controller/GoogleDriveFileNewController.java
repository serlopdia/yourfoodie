package aiss.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.googleDrive.FileItem;
import aiss.model.resources.GoogleDriveResource;

public class GoogleDriveFileNewController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(GoogleDriveFileNewController.class.getName());
	
	public GoogleDriveFileNewController() {
		super();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accessToken = (String) request.getSession().getAttribute("GoogleDrive-token");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String recipe = request.getParameter("recipe");
		
		if(accessToken != null && !"".equals(accessToken)) {
			if(title != null && !"".equals(title)) {
				GoogleDriveResource gd = new GoogleDriveResource(accessToken);
				FileItem file = new FileItem();
				file.setTitle(title);
				file.setMimeType("text/plain");
				gd.insertFile(file, content);
				request.setAttribute("message", "¡Archivo " + title +" añadido a tu Drive!");
				request.getRequestDispatcher("GoogleDriveFileNew.jsp").forward(request, response);
			} else{
				request.setAttribute("message", "No puede haber campos vacíos.");
				request.setAttribute("content", content);
				request.setAttribute("recipe", recipe);
				request.getRequestDispatcher("GoogleDriveFileNew.jsp").forward(request, response);
			}
		} else {
			log.info("Trying to access Google Drive without an access token, redirecting to OAuth servlet");
			request.getRequestDispatcher("/AuthController/GoogleDrive").forward(request, response);
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	
}
