package aiss.model;

public class Tutorial {
	
	private String id;
	private String tittle;
	private String author;
	private String year;
	
	
	public Tutorial() {
	}
	public Tutorial(String tittle, String author, String year) {
		this.tittle=tittle;
		this.author=author;
		this.year=year;
	}
	
	public Tutorial(String id, String tittle, String author, String year) {
		this.id=id;
		this.tittle=tittle;
		this.author=author;
		this.year=year;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id=id;
	}
	
	public String getTittle() {
		return tittle;
	}
	public void setTittle(String titulo) {
		this.tittle=titulo;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String autor) {
		this.author=autor;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String newYear) {
		this.year=newYear;
	}
}
