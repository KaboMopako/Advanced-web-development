<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.redfox.redfox.beans.UserBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="finalstyle.css">
<style>
.main {
	display: flex;
	justify-content: center;
	align-items: flex-start;
	width: 100%;
	background-color: #f5f5f5;
	padding-top: 60px;
	margin: 0;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.movie-image {
            padding-left: 20px; /* Add space between the left wall and image */
        }
.movie-image img {
	width: 300px; /* Adjust width as needed */
	height: auto;
	display: block;
	border-radius: 8px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}
/* Style for movie description */
.movie-description {
	margin-left: 2px; /* Adjusted margin */
	max-width: calc(100% - 340px); /* Adjusted width */
}
/* Additional styles for details */
.movie-description h1 {
	margin-bottom: 10px;
}

.movie-description p {
	margin-bottom: 8px;
}

.movie-description strong {
	margin-right: 5px;
}

.movie-description ul {
	margin-top: 10px;
}
</style>
<title>Movie Details</title>
</head>
<body>
	<div class="header">
		<a href="validation?action=home">
			<div class="logo">RED FOX AGENCY</div>
		</a>
		<div class="navigation">

			<a href="validation?action=latestMovies">LATEST MOVIES</a>

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
		<div class="movie-image">
			<img src="data:image/jpeg;base64,${movie.base64Image}"
				alt="${movie.title} Poster">

		</div>
		<div class="movie-description">
			<h1>${movie.title}</h1>
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
	</div>
	<div class="footer">
	<c:choose>
			<c:when test="${not empty user}">
				<div class="welcome-container">
					<h1>Dear ${user.getFname()}!</h1>
					</br>
					<p>We hope you are enjoying your browsing experience</p></br>
					<span>REDFOX AGENCY CopyRights Reserved @2023</span>
				</div>
			</c:when>
		</c:choose>
	</div>
	<script>
		
	</script>
</body>
</html>
