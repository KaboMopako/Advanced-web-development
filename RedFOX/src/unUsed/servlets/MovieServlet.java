package com.redfox.redfox.servlets;

import com.redfox.redfox.DAO.MovieDao;
import com.redfox.redfox.beans.MovieBean;

import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/movieServlet")
public class MovieServlet extends HttpServlet {
	String url;
	String action;
	String message;
	
	public void init() throws ServletException {
		url="/employeeSide/employeeHome.jsp";
	}
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session;
        
        if (action != null && action.equals("insert")) {
            // Retrieve form data
            String title = request.getParameter("title");
            String director = request.getParameter("director");
            String synopsis = request.getParameter("synopsis");
            String mainCharacters = request.getParameter("mainCharacters");
            String genre = request.getParameter("genre");
            String yearOfProductionStr = request.getParameter("yearOfProduction");
            String averageRating = request.getParameter("average");
            Part imagePart = request.getPart("image");
            
            
            //convert string to double
            double aveRat = Double.parseDouble(averageRating);
            // Convert yearOfProduction to SQL Date in the format yyyy-MM-dd
            SimpleDateFormat formatFromForm = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat formatForDB = new SimpleDateFormat("yyyy-MM-dd");
            
            Date yearOfProduction;
            try {
            	 yearOfProduction = (Date) formatForDB.parse(formatForDB.format(formatFromForm.parse(yearOfProductionStr)));
            } catch (ParseException e) {
                e.printStackTrace();
                return;
            }

            // Prepare MovieBean object
            MovieBean movie = new MovieBean();
            movie.setTitle(title);
            movie.setDirector(director);
            movie.setSynopsis(synopsis);
            movie.setMainCharacters(new ArrayList<>(Arrays.asList(mainCharacters.split(",")))); // Assuming main characters are comma-separated
            movie.setGenre(genre);
            movie.setYearOfProduction(yearOfProduction);
            movie.setAverageRating(aveRat);

            // Read and set image data
            byte[] imageData = imagePart.getInputStream().readAllBytes();
            String base64Image = Base64.getEncoder().encodeToString(imageData);
            movie.setBase64Image(base64Image);

            if(movie != null) {
            	session = request.getSession();
                session.setAttribute("newMovie", movie);
                url = "confirmationPage.jsp";
            }
            
        }else if(action.equals("insertConfirmed")) {
        	session = request.getSession();
            MovieBean newMovie = (MovieBean) session.getAttribute("newMovie");
            
            if (newMovie != null) {
            	MovieDao movieDao = new MovieDao();
            	
            	 boolean insertionStatus = movieDao.insertMovie(newMovie);
            	 
            	 if (insertionStatus) {
            		 session.setAttribute("confMovie", newMovie);
            	 }
            }
            
        }
        request.setAttribute("errorMessage", message);
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
    
}
