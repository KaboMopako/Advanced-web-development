package com.redfox.redfox.DAO;

import com.redfox.redfox.beans.UserBean;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	//checking if the user email exists
	private boolean checkUser(String email) {
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn
						.prepareStatement("SELECT COUNT(*) AS count FROM user WHERE Email_Address = ?")) {
			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				int count = rs.getInt("count");
				return count > 0;
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return false;
	}

	public boolean registerUser(UserBean user) {

		if (checkUser(user.getEmail()) == true) {
			return false;
		}
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement pst = connection.prepareStatement(
						"INSERT INTO user (Email_Address, Password, First_Name, Last_Name, Contact, Gender, prefferedGenre) VALUES (?, ?, ?, ?, ?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS)) {

			pst.setString(1, user.getEmail());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getFname());
			pst.setString(4, user.getLname());
			pst.setString(5, user.getContact());
			pst.setString(6, user.getGender());
			pst.setString(7, user.getPrefferedGenre());

			int rowsAffected = pst.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet generatedKeys = pst.getGeneratedKeys();
				if (generatedKeys.next()) {
					int userID = generatedKeys.getInt(1);
					user.setID(userID);
				}
			}

			return rowsAffected > 0;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Get a user by their email
	public UserBean getUserByEmail(String email) {
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("SELECT * FROM user WHERE Email_Address = ?")) {

			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				UserBean user = new UserBean();
				user.setID(resultSet.getInt("UserID"));
				user.setEmail(resultSet.getString("Email_Address"));
				user.setPassword(resultSet.getString("Password"));
				user.setFname(resultSet.getString("First_Name"));
				user.setLname(resultSet.getString("Last_Name"));
				user.setContact(resultSet.getString("Contact"));
				user.setGender(resultSet.getString("Gender"));
				user.setUsertype(resultSet.getInt("User_Type"));
				user.setPrefferedGenre(resultSet.getString("prefferedGenre"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
