<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.redfox.redfox.beans.MovieBean"%>
<%@ page import="com.redfox.redfox.beans.UserBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
</head>
<body>
	<div class="header">
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
			<span>Welcome, <%=userName%>!</span>
			<form action="validation" method="post">
			<input type="hidden" name="action" value="logout">
			<button type="submit" class="logout-btn">Logout</button>
		</form>
				
	</div>
	<div class="main">
		<%
		MovieBean newMovie = (MovieBean) session.getAttribute("newMovie");
		if (newMovie != null) {
		%>
		<p>
			Title:
			<%=newMovie.getTitle()%></p>
		<p>
			Director:
			<%=newMovie.getDirector()%></p>
		<p>
			Synopsis:
			<%=newMovie.getSynopsis()%>
		<p>
			Main Characters:
			<%=newMovie.getMainCharacters()%>
		<p>
			Genre:
			<%=newMovie.getGenre()%></p>
		<p>
			Date:
			<%=newMovie.getYearOfProduction()%></p>
		<p>
			Average Rating:
			<%=newMovie.getAverageRating()%>
		<p>Image:</p>
		<img src="data:image/jpeg;base64, <%=newMovie.getBase64Image()%>"
			alt="Movie Image">
		<form action="validation" method="post">
			<input type="hidden" name="action" value="insertConfirmed"> <input
				type="submit" value="Confirm">
		</form>
		<%
		} else {
		%>
		<p>Error: Movie details not found.</p>
		<%
		}
		%>
	</div>
	<div class="footer"></div>
</body>
</html>