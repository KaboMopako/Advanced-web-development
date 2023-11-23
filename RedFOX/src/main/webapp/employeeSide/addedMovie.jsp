<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.redfox.redfox.beans.MovieBean"%>
<%@ page import="com.redfox.redfox.beans.UserBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="finalstyle.css">
<title>Added Movie</title>
</head>
<body>
	<div class="header">
		<div class="logo">RED FOX AGENCY</div>
		<div class="navigation">
			<!-- Your navigation links -->
			<a href="#">Home</a> <a href="#">Movies</a> <a href="#">TV Shows</a>
			<!-- Add more links as needed -->
		</div>
		<div class="user-section">
			<%
			UserBean user = (UserBean) session.getAttribute("user");
			String userName = "";
			if (user != null) {
				userName = user.getFname() + " " + user.getLname();
			}
			%>
			<span>Welcome, <%=userName%>!
			</span>
		</div>
		<form action="validation" method="post">
			<input type="hidden" name="action" value="logout">
			<button type="submit" class="logout-btn">Logout</button>
		</form>
	</div>
	<div class="main">
		<%
		MovieBean movie = (MovieBean) session.getAttribute("confMovie");

		if (movie != null) {
		%>

		<div class="movie-description">
			<div class="movie-image">
				<img src >
			</div>
			
			<h1><strong>Title</strong><%= %></h1>
			<p>
				<strong>Director:</strong> ${movie.director}
			</p>
			<p>
				<strong>Genre:</strong> ${movie.genre}
			</p>
			<p>
				<strong>Year of Production:</strong> ${movie.yearOfProduction}
			</p>
			<p>
				<strong>Average Rating:</strong> ${movie.averageRating}
			</p>
			<h2>Synopsis</h2>
			<p>${movie.synopsis}</p>
			<h2>Main Characters</h2>
			<ul>
				<c:forEach var="character" items="${movie.mainCharacters}">
					<li>${character}</li>
				</c:forEach>
			</ul>
		</div>
		} else { %>
		<p>No movie details available.</p>
		<%
		}
		%>
		<a href="employeeHome.jsp">Add Another Movie</a>

	</div>
	<div class="footer"></div>
</body>
</html>