/*
 * package com.redfox.redfox.controllers;
 * 
 * import com.redfox.redfox.DAO.ratingDAO; import
 * com.redfox.redfox.beans.RatingBean;
 * 
 * import javax.servlet.ServletException; import
 * javax.servlet.annotation.WebServlet; import javax.servlet.http.HttpServlet;
 * import javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse; import java.io.IOException; import
 * java.net.http.HttpRequest; import java.net.http.HttpResponse;
 * 
 * 
 * @WebServlet("/rateMovie") public class ratingServlet extends HttpServlet {
 * protected void doPost(HttpRequest request, HttpResponse response) throws
 * ServletException, IOException{
 * 
 * int movieId = Integer.parseInt(request.getParameter("movieId")); int userId =
 * Integer.parseInt(request.getParameter("userId")); double userRating =
 * Double.parseDouble(request.getParameter("userRating"));
 * 
 * // Get current date java.util.Date utilDate = new java.util.Date();
 * java.sql.Date ratingDate = new java.sql.Date(utilDate.getTime());
 * 
 * RatingBean rating = new RatingBean(); rating.setMovieID(movieId);
 * rating.setUserID(userId); rating.setRating(userRating);
 * rating.setDate(ratingDate);
 * 
 * ratingDAO ratingDAO = new ratingDAO(); ratingDAO.saveRating(rating);
 * 
 * } }
 */