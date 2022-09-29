<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="aiss.model.edamam.Recipe"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="aiss.model.edamam.Recipe"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="aiss.model.edamam.Ingredient"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tutorial de la receta</title>
<link href="css/VideoStyle.css" rel="stylesheet">
</head>

<body>
	<c:choose>
		<c:when test="${not empty param.label}">
			<c:forEach items="${requestScope.videos}" var="item">
			<div class="tutorialframe">
				<iframe id="video" width="800" height="450" src="https://www.youtube.com/embed/<c:out value="${item.id.videoId}"/>" frameborder="0" allowfullscreen></iframe>
			<div class="receta">
				<h2>
					<c:out value="${requestScope.label}" />
				</h2>
				<p>Ingredientes:</p>
				<div id="ingredientes">
					<c:forEach items="${requestScope.mapRecipe.ingredients}" var="ingredient">
						<span id="ingrediente">
							<c:out value="${ingredient.text}"/><br> 
						</span>
					</c:forEach>
				</div>				
			</div>
			</div>
			</c:forEach>
			<div id="botones">
				<form id="returnRecetas" action="/SearchController" method="post" accept-charset="UTF-8">
					<input id="searchQuery" name="searchQuery" type="hidden" value="${requestScope.query}" /> 
					<input type="submit" class="volver" id="buscarRecetas" name="buscarRecetas"
						value="Recetas">
				</form>
			</div>
		</c:when>
		<c:otherwise>
			<span>No hay resultados.</span>
		</c:otherwise>
	</c:choose>
	
</body>

</html>