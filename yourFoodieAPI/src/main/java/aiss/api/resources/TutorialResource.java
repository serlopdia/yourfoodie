package aiss.api.resources;

import java.net.URI;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import aiss.model.Tutorial;
import aiss.model.repository.MapRecipeRepository;
import aiss.model.repository.RecipeRepository;

@Path("/tutorials")
public class TutorialResource {
		public static TutorialResource _instance=null;
		RecipeRepository repository;
		
		public TutorialResource() {
			repository=MapRecipeRepository.getInstance();
		}
		
		public static TutorialResource getInstance() {
			if(_instance==null)
				_instance= new TutorialResource();
			return _instance;
		}
		
		@GET
		@Produces("application/json")
		public Collection<Tutorial> getAll(){
			return repository.getAllTutorials();
		}
		
		@GET
		@Path("/{id}")
		@Produces("application/json")
		public Tutorial get(@PathParam("id") String tutorialId) {
			Tutorial tutorial= repository.getTutorial(tutorialId);
			if(tutorial==null) {
				throw new NotFoundException("The tutorial with id="+ tutorialId+ "was not found.");
			}
			return tutorial;
			}
		
		@POST
		@Consumes("application/json")
		@Produces("application/json")
		public Response addTutorial(@Context UriInfo uriInfo, Tutorial t) {
			if(t.getTittle()==null || "".equals(t.getTittle())) {
				throw new BadRequestException("The tittle of tutorial must not be null");
			}
			if(t.getAuthor()==null || "".equals(t.getAuthor())) {
				throw new BadRequestException("The author of tutorial must not be null");
			}
			if(t.getYear()==null || "".equals(t.getYear())) {
				throw new BadRequestException("The year of tutorial must not be null");
			}
			repository.addTutorial(t);
			// creamos la respuesta
			UriBuilder ub= uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
			URI uri= ub.build(t.getId());
			ResponseBuilder resp= Response.created(uri);
			resp.entity(t);
			return resp.build();
		}
		
		@PUT
		@Consumes("application/json")
		public Response updateTutorial(Tutorial t) {
			Tutorial oldTutorial= repository.getTutorial(t.getId());
			if(oldTutorial==null)
				throw new NotFoundException("The tutorial with id="+ t.getId()+ "was not found");
			
			if(t.getTittle()!=null) {
				oldTutorial.setTittle(t.getTittle());
			}
			if(t.getAuthor()!=null) {
				oldTutorial.setAuthor(t.getAuthor());
			}
			if(t.getYear()!=null) {
				oldTutorial.setYear(t.getYear());
			}
			return Response.noContent().build();
		}
		
		@DELETE
		@Path("/{id}")
		public Response removeTutorial(@PathParam("id") String tutorialId) {
			Tutorial tutorialRemoved = repository.getTutorial(tutorialId);
			if(tutorialRemoved== null)
				throw new NotFoundException("The tutorial with id="+ tutorialId+"was not found.");
			else
				repository.deleteTutorial(tutorialId);
					
			
			return Response.noContent().build();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
