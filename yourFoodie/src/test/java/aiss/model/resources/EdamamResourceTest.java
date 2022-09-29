package aiss.model.resources;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import java.io.UnsupportedEncodingException;
import org.junit.Test;
import aiss.model.edamam.EdamamSearch;

public class EdamamResourceTest {

	@Test
	public void getRecipesTest() throws UnsupportedEncodingException {
		String keyword = "macarrones";
		EdamamResource recipe = new EdamamResource();
		EdamamSearch resultados = recipe.getRecipes(keyword);

		assertNotNull("The search returned null", resultados);
		assertFalse("The number of photos of " + keyword + " is zero", resultados.getHits().size() == 0);

		System.out.println(
				"The search for recipes of " + keyword + " returned " + resultados.getHits().size() + " recipes.");
	}

}
