<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.redfox.redfox.beans.UserBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body, h1, h2, h3, p, ul, li {
	margin: 0;
	padding: 0;
}

/* Header styles */
.header {
	background-color: #222;
	color: #fff;
	padding: 10px 20px;
	display: flex;
	justify-content: space-between;
	align-items: center;
	position: fixed;
	width: 100%;
	z-index: 1000;
}

.logo {
	font-size: 24px;
	font-weight: bold;
}

.user-section {
	display: flex;
	align-items: center;
}

.user-section span {
	margin-right: 10px;
}
/* Remove margin and padding for the main content */
.main {
	padding-top: 60px;
	margin: 0;
	width: 100%;
	background-color: #f5f5f5;
}

.main .insertion {
	text-align: center;
}

.form-submit {
	width: calc(100% - 22px); /* Adjusted button width with padding */
	padding: 12px 20px;
	border: none;
	border-radius: 3px;
	background-color: #3f51b5; /* New color for submit button */
	color: #fff;
	font-size: 16px;
	cursor: pointer;
	transition: background-color 0.3s ease;
	margin-top: 20px; /* Added margin for spacing */
}

.form-submit:hover {
	background-color: #606fb4; /* Lighter shade when hovered */
}

.form-input {
	width: 100%;
	padding: 10px;
	margin-bottom: 15px;
	border: 1px solid #ccc;
	border-radius: 3px;
	font-size: 16px;
	color: #444;
}

/* Style for textareas */
.form-textarea {
	width: 100%;
	padding: 10px;
	margin-bottom: 15px;
	border: 1px solid #ccc;
	border-radius: 3px;
	font-size: 16px;
}

/* Style for the form container */
.form-container {
	padding: 30px;
	margin: 30px auto 50px;
	border-radius: 8px;
	background-color: #f1f1f1;
	max-width: 400px;
	box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
	border-left: 5px solid #e50914;
}

/* Style for form labels */
label {
	display: block;
	margin-bottom: 8px;
	font-weight: bold;
}

.form-submit:hover {
	background-color: #27ae60; /* Lighter shade on hover */
}

.logout-btn {
	padding: 8px 20px;
	border: none;
	border-radius: 3px;
	background-color: #f44336; /* Red color for logout button */
	color: #fff;
	font-size: 16px;
	cursor: pointer;
	transition: background-color 0.3s ease;
	margin-right: 10px; /* Added margin for button */
}

.logout-btn:hover {
	background-color: #d32f2f; /* Lighter shade when hovered */
}
</style>
</head>
<body>

	<div class="header">
		<div class="logo">RED FOX AGENCY</div>
		<div class="navigation">
			<!-- Your navigation links -->
			<a href="#">Home</a>
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
		String errorMessage = (String) session.getAttribute("errorMessage");
		if (errorMessage != null && !errorMessage.isEmpty()) {
		%>
		<p style="color: red;"><%=errorMessage%></p>
		<%
		}
		%>
		<h1 class="insertion">Insert New Movie</h1>
		<form action="validation" method="post" class="form-container"
			enctype="multipart/form-data">

			<label for="title">Title:</label> <input type="text" name="title"
				class="form-input" required> <label for="director">Director:</label>
			<input type="text" name="director" class="form-input" required>
			<label for="synopsis">Synopsis:</label>
			<textarea name="synopsis" class="form-textarea" required></textarea>
			<label for="mainCharacters">Main Characters:</label>
			<textarea name="mainCharacters" class="form-textarea" required></textarea>
			<label for="Genre">Genre:</label> <select id="genre" name="genre">
				<option value="action">Action</option>
				<option value="comedy">Comedy</option>
				<option value="drama">Drama</option>
				<!-- Add more genre options as needed -->
			</select> <label for="yearOfProduction">Year of Production:</label> <input
				type="date" name="yearOfProduction" class="form-input" required>
			<label for="AverageRating">AverageRating</label> <input type="text"
				name="average" class="form-input" required> <label
				for="image">Image:</label> <!-- <input type="file" name="image"
				class="form-input" required> --> <input type="hidden"
				name="action" value="insert"> <button type="submit">Login</button></button>
		</form>
	</div>
	<div class="footer"></div>
</body>
</html>