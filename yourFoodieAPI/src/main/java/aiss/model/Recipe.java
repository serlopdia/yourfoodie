package aiss.model;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
	
	private String id;
	private String name;
	private String description;
	private List<Tutorial> tutorials;
	
	public Recipe() {}
	
	public Recipe(String name) {
		this.name=name;
	}
	
	protected void setIngredients(List<Tutorial> tutoriales) {
		tutorials=tutoriales;
	}
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id=id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String nombre) {
		this.name=nombre;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description=description;
	}
	
	public List<Tutorial> getTutorials(){
		return tutorials;
	}
	public Tutorial getTutorial(String id) {
		if(tutorials==null) {
			return null;
		}
		
		Tutorial t=null;
		for(Tutorial ttl: tutorials) {
			if(ttl.getId().equals(id)) {
				t=ttl;
				break;
			}
		}
		return t;
	}
	public void addTutorial(Tutorial t) {
		if(tutorials==null)
			tutorials= new ArrayList<Tutorial>();
		tutorials.add(t);
	}
	public void deleteTutorial(Tutorial t) {
		tutorials.remove(t);
	}
	
	public void deleteTutorial(String id) {
		Tutorial t=getTutorial(id);
		if(t!=null)
			tutorials.remove(t);
	}
}
