<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Resultado de búsqueda</title>
<link href="css/ResultadoStyle.css" rel="stylesheet">
</head>
<body>

	<c:forEach items="${requestScope.recipes}" var="recipes">
		<div class="receta">
			<div class="izq">
				<img src="<c:out value="${recipes.recipe.image}"/>"><br />

				<form id="videoYoutube" action="/SearchVideoController"
					method="post" accept-charset="UTF-8">
					<input id="recipe" name="recipe" type="hidden" value="${recipes}" />
					<input id="label" name="label" type="hidden"
						value="${recipes.recipe.label}" /> <input id="ingredients"
						name="ingredients" type="hidden"
						value="${recipes.recipe.ingredients}"> <input
						id="uriRecipe" name="uriRecipe" type="hidden"
						value="${recipes.recipe.uri}" /> <input id="query" name="query"
						type="hidden" value="${requestScope.query}" />
					<p>Ver tutorial:</p>
					<input type="submit" class="btn btn-default" name="buscarVideo"
						value="Tutorial">


				</form>


				<form id="searchMap" action="/SearchMapsController" method="post"
					accept-charset="UTF-8">
					<div id="divlocation">
						<p>Introduzca su localización:</p>
						<input id="location" type="text" class="form-control"
							name="location" placeholder="Dirección" required /> <input
							id="query" name="query" type="hidden"
							value="${requestScope.query}" />
					</div>
					<div id="divsuper">
						<input type="submit" class="btn btn-default" id="location"
							name="location" value="Buscar restaurantes">
					</div>
					<input id="label" name="label" type="hidden"
						value="${recipes.recipe.label}" />
				</form>

				<form id="guardarDrive" action="/NewRecipeController" method="post"
					accept-charset="UTF-8">
					<input id="searchQuery" name="searchQuery" type="hidden"
						value="${requestScope.query}" /> <input id="name" name="name"
						type="hidden" value="${recipes.recipe.label}" /> <input
						id="recipe" name="recipe" type="hidden" value="${recipes}" /> <input
						id="uriRecipe" name="uriRecipe" type="hidden"
						value="${recipes.recipe.uri}" /> <input type="submit"
						class="btn btn-default" id="nuevaReceta" name="nuevaReceta"
						value="Añadir receta a Google Drive">

				</form>
			</div>


			<div class="dcha">
				<h2>
					<c:out value="${recipes.recipe.label}" />
				</h2>
				<p>Ingredientes:</p>
				<div class="ingredientes">
					<c:forEach items="${recipes.recipe.ingredients}" var="ingredient">
						<p>
							<c:out value="${ingredient.text}" />
						</p>
					</c:forEach>
				</div>
			</div>
		</div>
	</c:forEach>

</body>
</html>