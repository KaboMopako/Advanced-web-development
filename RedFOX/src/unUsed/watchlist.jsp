<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>User Watch list</title>
</head>
<body>

    <h1>User Watch list</h1>

    <c:choose>
        <c:when test="${not empty watchlist}">
            <ul>
                <c:forEach var="movie" items="${watchlist}">
                    <li>${movie.title}</li>
                </c:forEach>
            </ul>
        </c:when>
        <c:otherwise>
            <p>No movies in your watch list.</p>
        </c:otherwise>
    </c:choose>

</body>
</html>
