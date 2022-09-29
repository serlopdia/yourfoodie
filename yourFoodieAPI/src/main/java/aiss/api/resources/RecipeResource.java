package aiss.api.resources;

import aiss.model.Recipe;
import aiss.model.Tutorial;

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
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import aiss.model.repository.MapRecipeRepository;
import aiss.model.repository.RecipeRepository;

@Path("/recipes")
public class RecipeResource {
	
	/* Singleton */
	private static RecipeResource _instance=null;
	RecipeRepository repository;
	
	private RecipeResource() {
		repository= MapRecipeRepository.getInstance();
	}
	
	public static RecipeResource getInstance() {
		if(_instance ==null)
			_instance = new RecipeResource();
		return _instance;
			
	}
	
	
	@GET
	@Produces("application/json")
	public Collection<Recipe> getAll(){
		return repository.getAllRecipes();
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Recipe get(@PathParam("id") String id) {
		Recipe receta = repository.getRecipe(id);
				if(receta==null)
					throw new NotFoundException("The recipe with id=" + id +"was not found");
			return receta;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addRecipe(@Context UriInfo uriInfo, Recipe recipe) {
		if(recipe.getName()==null || "".equals(recipe.getName()))
			throw new BadRequestException("The name of the recipe must not be null");
		
		if(recipe.getTutorials()!=null)
			throw new BadRequestException("The tutorials property is not editable.");
		
		repository.addRecipe(recipe);
		
		//Builds the respose. Return the recipe the has just been added.
		UriBuilder ub= uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri= ub.build(recipe.getId());
		ResponseBuilder resp= Response.created(uri);
		resp.entity(recipe);
		return resp.build();
		
	}
	
	@PUT
	@Consumes("application/json")
	public Response updateRecipe(Recipe recipe) {
		Recipe oldRecipe= repository.getRecipe(recipe.getId());
		if(oldRecipe==null)
			throw new NotFoundException("The recipe with id=" + recipe.getId()+ "was not found");
		
		if(recipe.getTutorials()!= null) 
			throw new BadRequestException("The tutorials property is not editable.");
				
		if(recipe.getName()!= null)
			oldRecipe.setName(recipe.getName());
		
		if(recipe.getDescription()!= null)
		oldRecipe.setDescription(recipe.getDescription());
		
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response removeRecipe(@PathParam("id") String id) {
		Recipe toberemoved =repository.getRecipe(id);
		if(toberemoved==null)
			throw new NotFoundException("The playlist with id=" + id + "was not found.");
		else
			repository.deleteRecipe(id);
		
		return Response.noContent().build();
	}
	
	@POST
	@Path("/{recipeId}/{tutorialId}")
	@Consumes("text/plain")
	@Produces("application/json")
	public Response addTutorial(@Context UriInfo uriInfo,@PathParam("recipeId") String recipeId, @PathParam("tutorialId") String tutorialId) {
		
		Recipe receta= repository.getRecipe(recipeId);
		Tutorial tutorial= repository.getTutorial(tutorialId);
		
		if(receta ==null)
			throw new NotFoundException("The recipe with id="+ recipeId+ "was not found");
		
		if(tutorial==null)
			throw new NotFoundException("The tutorial with id="+ tutorialId+ "was not found.");
		
		if(receta.getTutorial(tutorialId)!= null) {
			throw new BadRequestException("The tutorial is already included in the recipe.");
		}
		
		repository.addTutorial(recipeId, tutorialId);
		
		//Build the response
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(recipeId);
		ResponseBuilder resp=Response.created(uri);
		resp.entity(receta);
		return resp.build();
		
		
	}
	
	@DELETE
	@Path("/{recipeId}/{tutorialId}")
	public Response removeTutorial(@PathParam("recipeId") String recipeId,@PathParam("tutorialId") String tutorialId) {
		Recipe receta = repository.getRecipe(recipeId);
		Tutorial tutorial= repository.getTutorial(tutorialId);
		
		if(receta==null)
			throw new NotFoundException("The recipe with id="+ recipeId + "was not found");
		
		if(tutorial==null)
			throw new NotFoundException("The tutorial with id="+tutorialId + "was not found");
		
		repository.removeTutorial(recipeId, tutorialId);
		return Response.noContent().build();
	}
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	
}
