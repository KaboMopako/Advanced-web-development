package com.redfox.redfox.controllers;

import com.redfox.redfox.DAO.MovieDao;
import com.redfox.redfox.DAO.UserDAO;
import com.redfox.redfox.beans.MovieBean;
import com.redfox.redfox.beans.UserBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

@WebServlet("/validation")
public class validationServlet extends HttpServlet {
	String url;
	String action;
	String message;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean cookiesAccepted = false;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("cookies_accepted") && cookie.getValue().equals("true")) {
					cookiesAccepted = true;
					break;
				}
			}
		}
		if (cookiesAccepted) {
			doPost(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		action = request.getParameter("action");
		HttpSession session;

		// perform action and set URL to appropriate page
		if (action.equals("login")) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			UserDAO userDAO = new UserDAO();
			UserBean user = userDAO.getUserByEmail(email);

			if (user != null && user.getPassword().equals(password)) {
				String prefGen = user.getPrefferedGenre();
				
				session = request.getSession();
				session.setAttribute("user", user);
				session.setAttribute("userId", user.getID());
				String rememberMe = request.getParameter("rememberMe");
				
				// If rememberMe is checked during login, create a cookie to remember the email
				if (rememberMe != null && rememberMe.equals("true")) {
					// Create a cookie to remember the email
					Cookie emailCookie = new Cookie("remembered_email", email);
					emailCookie.setMaxAge(7 * 24 * 60 * 60); // Cookie valid for 7 days
					response.addCookie(emailCookie);
				}

				int usertype = user.getUsertype();

				if (usertype == 2) {
					ArrayList<MovieBean> preferredGenre = MovieDao.getPreferredGenre(prefGen);
					session.setAttribute("preferGenre", preferredGenre);
					ArrayList<MovieBean> movies = MovieDao.getAllMovies();
					session.setAttribute("movies", movies);
					ArrayList<MovieBean> newMovies = MovieDao.newMovies();
					session.setAttribute("newMovies", newMovies);
					url = "/home.jsp";
				} else if (usertype == 1) {
					url = "/employeeSide/employeeHome.jsp";
				}
			} else {
				message = "The email or password provided does not match the credentials in our record.\nPlease try again";
				url = "/index.jsp";
			}
		} else if (action.equals("join")) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String Fname = request.getParameter("Fname");
			String Lname = request.getParameter("Lname");
			String Contact = request.getParameter("Contact");
			String gender = request.getParameter("gender");
			String prefGen = request.getParameter("prefGen");

			UserBean newUser = new UserBean();
			newUser.setEmail(email);
			newUser.setPassword(password);
			newUser.setFname(Fname);
			newUser.setLname(Lname);
			newUser.setContact(Contact);
			newUser.setGender(gender);
			newUser.setPrefferedGenre(prefGen);

			UserDAO userDAO = new UserDAO();

			boolean registrationSuccess = userDAO.registerUser(newUser);

			if (registrationSuccess == true) {
				// Create a session for the user
				session = request.getSession();
				session.setAttribute("user", newUser);

				String rememberMe = request.getParameter("rememberMe");

				if (rememberMe != null && rememberMe.equals("true")) {
					// If "Remember Me" is checked, create a cookie for the email
					Cookie emailCookie = new Cookie("remembered_email", email);
					emailCookie.setMaxAge(7 * 24 * 60 * 60); // Cookie valid for 7 days
					response.addCookie(emailCookie);
				}
				ArrayList<MovieBean> preferredGenre = MovieDao.getPreferredGenre(prefGen);
				session.setAttribute("preferGenre", preferredGenre);
				ArrayList<MovieBean> newMovies = MovieDao.newMovies();
				session.setAttribute("newMovies", newMovies);
				ArrayList<MovieBean> movies = MovieDao.getAllMovies();
				session.setAttribute("movies", movies);
				// Set the url
				url = "/home.jsp";
			} else {
				// setting url where it will be forwarded to
				message = "Registration process failed.Username already exist, Please try again";
				url = "/index.jsp";
			}

		} else if (action.equals("details")) {

			session = request.getSession();
			String movieTitle = request.getParameter("movieTitle");
			

			if (movieTitle != null) {
				MovieBean movie = MovieDao.getMovieByTitle(movieTitle);

				if (movie != null) {
					session.setAttribute("movie", movie);
					url = "/movieDetails.jsp";
				} else {
					message = "Invalid request for movie details.";
					url = "/home.jsp";
				}
			} else {
				// Handle the case when movieTitle is null
				message = "Invalid request for movie details.";
				url = "/home.jsp";
			}

		}else if (action.equals("latestMovies")) {
			session = request.getSession();
	        ArrayList<MovieBean> latestMovies = MovieDao.allNewMovies();
	        session.setAttribute("latestMovies", latestMovies);
	        url = "/latestMovies.jsp";
	    } else if (action.equals("logout")) {
			session = request.getSession(false); // Get the existing session without creating a new one
			if (session != null) {
				session.invalidate(); // Invalidate the user's session
			}
			// Redirect the user to the index page after logout
			url = "/index.jsp";
		}else if (action.equals("home")) {
		    url = "/home.jsp";
		}else if (action.equals("insert")) {
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
            		 url = "/employeeSide/addedMovie.jsp";
            	 }
            }
            
        }
		
		// Prevent caching of sensitive pages
	    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
	    response.setHeader("Pragma", "no-cache"); // HTTP 1.0
	    response.setHeader("Expires", "0"); // Proxies

		// forward request and response to the specified URl
		request.setAttribute("loginMessage", message);
		request.setAttribute("registrationMessage", message);
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}
}
