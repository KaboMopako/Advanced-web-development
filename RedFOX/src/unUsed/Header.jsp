<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>RedFOX</title>
<link rel="stylesheet" type="text/css" href="style2.css">
<!-- Link to your CSS file -->
<Style>
body {
	margin: 0;
	font-family: Arial, sans-serif;
}
</style>
</head>
<body>
	<div class="header">
	
	</div>
	<c:choose>
		<c:when test="${not empty user}">
			<div class="welcome-container">
				<h1>Welcome, ${user.getFname()}!</h1>
		</c:when>

	</c:choose>
</body>
</html>