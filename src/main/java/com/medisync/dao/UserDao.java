package com.medisync.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.medisync.models.User;
import com.medisync.util.DBConnection;

public class UserDao implements IDao<User> {

	private DBConnection dbConnection;
	
	public UserDao() {
		super();
	}

	public UserDao(DBConnection dbConnection) {
		super();
		this.dbConnection = dbConnection;
	}
	
	public User create(User t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean update(int id, User t) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean delete(int id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public User findOne(String username) throws Exception {
		Connection connection = dbConnection.getConnection();
		final String sqlQuery = "SELECT User_Id, UserName, Password, Role FROM Users WHERE UserName=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setString(1, username);
		ResultSet resultSet = preparedStatement.executeQuery();
		User user = null;
		if (resultSet.next()) {
			user = new User(resultSet.getInt("User_Id"), resultSet.getString("UserName"), resultSet.getString("Password"), resultSet.getString("Role"));
		}
		resultSet.close();
		return user;
	}

	public List<User> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
