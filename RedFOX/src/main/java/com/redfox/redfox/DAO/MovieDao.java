package com.redfox.redfox.DAO;

import com.redfox.redfox.beans.MovieBean;
import com.redfox.redfox.beans.UserBean;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

public class MovieDao {
	


	
	public static ArrayList<MovieBean> getPreferredGenre(String genre){
		ArrayList<MovieBean> topmovies = new ArrayList();
		try(Connection conn = ConnectionManager.getConnection();
				PreparedStatement psmt = conn.prepareStatement("Select * FROM movie WHERE Genre = ? ORDER BY Average_Rate DESC LIMIT 5")){
				psmt.setString(1, genre);
				
				ResultSet rs = psmt.executeQuery();
				while (rs.next()) {
					MovieBean movie = new MovieBean();
					movie.setId(rs.getInt("MovieID"));
					movie.setTitle(rs.getString("Title"));
					movie.setDirector(rs.getString("Director"));
					movie.setSynopsis(rs.getString("Synopsis"));
					String mainCharacters = rs.getString("Main_Characters");
					String[] mainCharactersArray = mainCharacters.split("\\|");
					ArrayList<String> mainCharactersList = new ArrayList<>(Arrays.asList(mainCharactersArray));
					movie.setMainCharacters(mainCharactersList);
					movie.setGenre(rs.getString("Genre"));
					movie.setYearOfProduction(rs.getDate("Year_of_production"));
					movie.setAverageRating(rs.getDouble("Average_Rate"));
					 byte[] imageData = rs.getBytes("image_path");
	                    
	                    if (imageData != null && imageData.length > 0) {
	                        // Encode the image data to Base64
	                        String base64Image = Base64.getEncoder().encodeToString(imageData);
	                        movie.setBase64Image(base64Image);
	                    }

					topmovies.add(movie);
				}
				
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return topmovies;
	}
	
	public static ArrayList<MovieBean> getAllMovies() {
		ArrayList<MovieBean> movies = new ArrayList<>();

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM movie")) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					MovieBean movie = new MovieBean();
					movie.setId(rs.getInt("MovieID"));
					movie.setTitle(rs.getString("Title"));
					movie.setDirector(rs.getString("Director"));
					movie.setSynopsis(rs.getString("Synopsis"));
					String mainCharacters = rs.getString("Main_Characters");
					String[] mainCharactersArray = mainCharacters.split("\\|");
					ArrayList<String> mainCharactersList = new ArrayList<>(Arrays.asList(mainCharactersArray));
					movie.setMainCharacters(mainCharactersList);
					movie.setGenre(rs.getString("Genre"));
					movie.setYearOfProduction(rs.getDate("Year_of_production"));
					movie.setAverageRating(rs.getDouble("Average_Rate"));
					 byte[] imageData = rs.getBytes("image_path");
	                    
	                    if (imageData != null && imageData.length > 0) {
	                        // Encode the image data to Base64
	                        String base64Image = Base64.getEncoder().encodeToString(imageData);
	                        movie.setBase64Image(base64Image);
	                    }

					movies.add(movie);
				}
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}

		return movies;
	}
	
	public static ArrayList<MovieBean> allNewMovies() {
		ArrayList<MovieBean> recentMovies = new ArrayList<>();
		LocalDate currentDate = LocalDate.now();
		LocalDate sixMonthsAgo = currentDate.minusMonths(6);
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement psmt = conn.prepareStatement("SELECT * FROM movie WHERE Year_of_production >= ? ORDER BY Year_of_production DESC")) {
			psmt.setDate(1, Date.valueOf(sixMonthsAgo));
			try (ResultSet rs = psmt.executeQuery()) {

				while (rs.next()) {
					MovieBean movie = new MovieBean();
					movie.setId(rs.getInt("MovieID"));
					movie.setTitle(rs.getString("Title"));
					movie.setDirector(rs.getString("Director"));
					movie.setSynopsis(rs.getString("Synopsis"));
					String mainCharacters = rs.getString("Main_Characters");
					String[] mainCharactersArray = mainCharacters.split("\\|");
					ArrayList<String> mainCharactersList = new ArrayList<>(Arrays.asList(mainCharactersArray));
					movie.setMainCharacters(mainCharactersList);
					movie.setGenre(rs.getString("Genre"));
					movie.setYearOfProduction(rs.getDate("Year_of_production"));
					movie.setAverageRating(rs.getDouble("Average_Rate"));
					byte[] imageData = rs.getBytes("image_path");

					if (imageData != null && imageData.length > 0) {
						// Encode the image data to Base64
						String base64Image = Base64.getEncoder().encodeToString(imageData);
						movie.setBase64Image(base64Image);
					}

					recentMovies.add(movie);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return recentMovies;
	}

	public static ArrayList<MovieBean> newMovies() {
		ArrayList<MovieBean> recentMovies = new ArrayList<>();
		LocalDate currentDate = LocalDate.now();
		LocalDate sixMonthsAgo = currentDate.minusMonths(6);
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement psmt = conn.prepareStatement("SELECT * FROM movie WHERE Year_of_production >= ? ORDER BY Year_of_production AND Average_Rate DESC LIMIT 5")) {
			psmt.setDate(1, Date.valueOf(sixMonthsAgo));
			try (ResultSet rs = psmt.executeQuery()) {

				while (rs.next()) {
					MovieBean movie = new MovieBean();
					movie.setId(rs.getInt("MovieID"));
					movie.setTitle(rs.getString("Title"));
					movie.setDirector(rs.getString("Director"));
					movie.setSynopsis(rs.getString("Synopsis"));
					String mainCharacters = rs.getString("Main_Characters");
					String[] mainCharactersArray = mainCharacters.split("\\|");
					ArrayList<String> mainCharactersList = new ArrayList<>(Arrays.asList(mainCharactersArray));
					movie.setMainCharacters(mainCharactersList);
					movie.setGenre(rs.getString("Genre"));
					movie.setYearOfProduction(rs.getDate("Year_of_production"));
					movie.setAverageRating(rs.getDouble("Average_Rate"));
					byte[] imageData = rs.getBytes("image_path");

					if (imageData != null && imageData.length > 0) {
						// Encode the image data to Base64
						String base64Image = Base64.getEncoder().encodeToString(imageData);
						movie.setBase64Image(base64Image);
					}

					recentMovies.add(movie);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return recentMovies;
	}

	public static MovieBean getMovieByTitle(String Title) {

		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("SELECT * FROM movie where Title = ?")) {

			preparedStatement.setString(1, Title);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				MovieBean movie = new MovieBean();
				movie.setId(rs.getInt("MovieID"));
				movie.setTitle(rs.getString("Title"));
				movie.setDirector(rs.getString("Director"));
				movie.setSynopsis(rs.getString("Synopsis"));
				String mainCharacters = rs.getString("Main_Characters");
				String[] mainCharactersArray = mainCharacters.split("\\|");
				ArrayList<String> mainCharactersList = new ArrayList<>(Arrays.asList(mainCharactersArray));
				movie.setMainCharacters(mainCharactersList);
				movie.setGenre(rs.getString("Genre"));
				movie.setYearOfProduction(rs.getDate("Year_of_production"));
				movie.setAverageRating(rs.getDouble("Average_Rate"));
				byte[] imageData = rs.getBytes("image_path");

				if (imageData != null && imageData.length > 0) {
					// Encode the image data to Base64
					String base64Image = Base64.getEncoder().encodeToString(imageData);
					movie.setBase64Image(base64Image);
				}

				return movie;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static boolean insertMovie(MovieBean movie) {
		
		try(Connection conn = ConnectionManager.getConnection();
				PreparedStatement psmt = conn.prepareStatement(
	                     "INSERT INTO movie (Title, Director, Synopsis, Main_Characters, Genre, Year_of_production, Average_Rate, image_path) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
				psmt.setString(1, movie.getTitle());
				psmt.setString(2, movie.getDirector());
				psmt.setString(3, movie.getSynopsis());
				psmt.setString(4, String.join("|", movie.getMainCharacters()));
				psmt.setString(5, movie.getGenre());
                psmt.setDate(6, movie.getYearOfProduction());
                psmt.setDouble(7, movie.getAverageRating());

                // Decode Base64 string back to byte array
                byte[] imageData = Base64.getDecoder().decode(movie.getBase64Image());
                psmt.setBytes(8, imageData);
                
                int rowsAffected = psmt.executeUpdate();
                return rowsAffected >0;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
