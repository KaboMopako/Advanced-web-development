/*
 * package com.redfox.redfox.DAO;
 * 
 * import java.sql.Connection; import java.sql.PreparedStatement; import
 * java.sql.SQLException;
 * 
 * import com.redfox.redfox.beans.RatingBean;
 * 
 * public class ratingDAO { public boolean saveRating(RatingBean rating) {
 * try(Connection conn= ConnectionManager.getConnection(); PreparedStatement
 * psmt = conn.
 * prepareStatement("Insert into rating(movieID, userID, rating, date) values (?, ?, ?, ?)"
 * )){
 * 
 * psmt.setInt(1, rating.getMovieID()); psmt.setInt(2, rating.getUserID());
 * psmt.setDouble(3, rating.getRating()); psmt.setDate(4, rating.getDate());
 * 
 * 
 * int rowsAffected = psmt.executeUpdate();
 * 
 * return rowsAffected > 0;
 * 
 * 
 * }catch(SQLException e) { throw new RuntimeException(e); } } }
 */