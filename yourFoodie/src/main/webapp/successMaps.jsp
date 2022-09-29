<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/css/mapsStyle.css">
<meta name="viewport" content="initial-scale=1.0">
<meta charset="utf-8">

<title>Maps</title>
</head>
<body>

	<h3>
		<c:out value="${requestScope.label}"></c:out>
	</h3>
	<div id="map">
		<script>
			function initMap() {
				var map<c:out value="${requestScope.myLocation.id}"/> = {
					lat : <c:out value="${requestScope.myLocation.geometry.location.lat}"/>,
					lng : <c:out value="${requestScope.myLocation.geometry.location.lng}"/>
				};
				var map = new google.maps.Map(
						document.getElementById('map'),
						{
							center : map<c:out value="${requestScope.myLocation.id}" />,
							zoom : 17
						});
				<c:forEach items="${requestScope.results}" var="result">
				var map<c:out value="${result.id}"/> = {
					lat : <c:out value="${result.geometry.location.lat}"/>,
					lng : <c:out value="${result.geometry.location.lng}"/>
				};

				// Creacion de marcadores.
				var marker<c:out value="${result.id}"/> = new google.maps.Marker(
						{
							map : map,
							position : map<c:out value="${result.id}"/>,
							title : '<c:out value="${result.name}"/>'
						});
				</c:forEach>
			}
		</script>
	</div>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBzaDDZFz3r7Px4rxYjtxG5IpFPRwomfUI&callback=initMap"
		async defer></script>
	<div id="botones">
	<form id="returnRecetas" action="/SearchController" method="post" accept-charset="UTF-8">
		<input id="searchQuery" name="searchQuery" type="hidden" value="${requestScope.query}" /> 
		<input type="submit" class="btn btn-default" id="buscarRecetas" name="buscarRecetas"
			value="Recetas">
		</form>
	</div>
</body>
</html>
