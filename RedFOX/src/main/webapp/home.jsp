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
<title>RedFox Movies Rating Web Application</title>


<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet">
<style>
.main {
	width: 100%;
	background-color: #f5f5f5;
	padding-top: 60px;
	margin: 0;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.section {
	max-width: 100%;
	margin-bottom: 2px; /* Add space between sections */
}

.sectionDesc {
	text-align: center;
	margin-bottom: 10px; /* Optional: Add space below the title */
}

.footer {
	background-color: #333;
	color: white;
	padding: 20px;
	text-align: center;
}

.movie-list {
	display: flex;
	flex-wrap: wrap;
	gap: 10px;
	padding: 10px;
}

.movie-card {
	width: calc(20% - 25px); /* To display 5 cards per row */
	box-sizing: border-box;
	padding: -5px; /* Adjust for spacing between cards */
	height: 400px;
	border: 0.5px solid #000;
	border-radius: 8px;
}

.movie-card .movie-poster {
	width: 100%; /* Ensure image takes full width */
	height: 80%; /* Adjust the image height within the card */
	overflow: hidden; /* Hide overflow if necessary */
	border-radius: 8px; /* Rounded corners for images */
}

.movie-card .movie-poster img {
	width: 100%;
	height: 97%;
	object-fit: cover; /* Maintain aspect ratio and cover the container */
}

.movie-card:hover {
	transform: scale(1.05);
}

.movie-details {
	text-align: center;
	margin-bottom: 10px;
	margin: 0 auto; /* Center the container */
}

@media ( max-width : 768px) {
	.movie-card {
		width: 25%; /* To display 4 cards per row on smaller screens */
	}
}

/* Adjustments for even smaller screens */
@media ( max-width : 480px) {
	.movie-card {
		width: 50%; /* To display 2 cards per row on mobile screens */
	}
}
/* Style the modal */
</style>
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
			}else {

			       // Handle the case when the attribute is not found

			       response.sendRedirect("index.jsp");

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
		<div class="section newMovies-list" id="latestMovies">
			<h1 class="sectionDesc">RECOMMENDATION</h1>
			<div class="movie-list">
				<c:forEach var="movie" items="${sessionScope.newMovies}">
					<c:url var="movieDetail" value="validation">
						<c:param name="movieTitle" value="${movie.title}" />
						<c:param name="action" value="details" />
					</c:url>
					<a href="${movieDetail}" class="movie-card-link">
						<div class="movie-card">
							<a href="validation?action=details&movieTitle=${movie.title}">
								<div class="movie-poster">
									<img src="data:image/jpeg;base64,${movie.base64Image}"
										alt="${movie.title} Poster">
								</div>
							</a>
							<div class="movie-details">
								<h3>${movie.title}</h3>
							</div>
							<%-- <button onclick="rateMovie('${movie.title}')">Rate</button> --%>
						</div>
					</a>
				</c:forEach>

			</div>
		</div>

		<div class="section all-movie">
			<h1 class="sectionDesc">TOP 5 MOVIES IN YOUR PREFERRED GENRE</h1>
			<div class="movie-list" id="availableMovies">
				<c:forEach var="movie" items="${sessionScope.preferGenre}">
					<c:url var="movieDetail" value="validation">
						<c:param name="movieTitle" value="${movie.title}" />
						<c:param name="action" value="details" />
					</c:url>
					<a href="${movieDetail}" class="movie-card-link">
						<div class="movie-card">
							<a href="validation?action=details&movieTitle=${movie.title}">
								<div class="movie-poster">
									<img src="data:image/jpeg;base64,${movie.base64Image}"
										alt="${movie.title} Poster">
								</div>
							</a>
							<div class="movie-details">
								<h3>${movie.title}</h3>
							</div>
							<%-- <button onclick="rateMovie('${movie.title}')">Rate</button> --%>
						</div>
					</a>
				</c:forEach>
			</div>
		</div>
	</div>
	<div class="footer">
		<c:choose>
			<c:when test="${not empty user}">
				<div class="welcome-container">
					<h1>Welcome, ${user.getFname()}!</h1>
					</br>
					<hi>${user.getID()}!</h1>
					<p>Explore our movie collection and enjoy your time on RedFox
						Movies.</p>
					<a href="#">Browse Movies</a>
				</div>
			</c:when>
		</c:choose>
	</div>


</body>
</html>
