package com.medisync.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.medisync.models.Department;
import com.medisync.models.Doctor;
import com.medisync.models.User;
import com.medisync.util.DBConnection;

public class DoctorDao implements IDao<Doctor>{
	private DBConnection dbConnection;
	
	public DoctorDao() {
		super();
	}

	public DoctorDao(DBConnection dbConnection) {
		super();
		this.dbConnection = dbConnection;
	}

	public Doctor create(Doctor t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean update(int id, Doctor t) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean delete(int id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public Doctor findOne(int id) throws Exception {
		Connection connection = dbConnection.getConnection();
		final String sqlQuery = "SELECT * FROM Doctor WHERE DOCTOR_ID=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		Doctor doctor = null;
		//System.out.println("Doctor"+doctor);
		if (resultSet.next()) {
			System.out.println(resultSet.getInt("doctor_id"));
			
			Department department = new DoctorDao().findOneDepartment(resultSet.getInt("dept_id"), connection);
			User user = new DoctorDao().findOneUser(resultSet.getInt("user_id"), connection);
			System.out.println("Department : "+department);
			doctor = new Doctor(resultSet.getInt("doctor_id"), user, department,
					resultSet.getString("name"), resultSet.getString("contact_no"), resultSet.getString("email"));
			System.out.println("Doctor"+doctor);
		}
		preparedStatement.close();
		resultSet.close();
		return doctor;
	}

	public List<Doctor> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	public Doctor findByName(String name) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Doctor doctor = null;
        try {
            connection = dbConnection.getConnection();
            String sqlQuery = "SELECT * FROM Doctor WHERE Name=?";
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                doctor = new Doctor();
                doctor.setDoctorId(resultSet.getInt("Doctor_id"));
                doctor.setName(resultSet.getString("Name"));
                // Set other doctor properties as needed
            }
        } catch (SQLException e) {
            // Handle SQLException
            e.printStackTrace();
        
        }
        preparedStatement.close();
        resultSet.close();
        return doctor;
    }
	 public Map<Integer, String> findAllNames() throws SQLException {
		 	Map<Integer, String> names = new HashMap<Integer, String>();
			Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	        try {
	            connection = dbConnection.getConnection();
	            String query = "SELECT doctor_id, name FROM Doctor";
	            preparedStatement = connection.prepareStatement(query);
	            resultSet = preparedStatement.executeQuery();
	            while (resultSet.next()) {
					names.put(resultSet.getInt("doctor_id"), resultSet.getString("name"));
				}
	        }
	         catch (SQLException e) {
	            // Handle SQLException
	            e.printStackTrace();
	        }
	        preparedStatement.close();
			resultSet.close();
			return names;
	    }

	public Doctor findOne(String username) throws Exception {
		Connection connection = dbConnection.getConnection();
		final String sqlQuery = "SELECT * FROM Doctor WHERE email=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setString(1, username);
		ResultSet resultSet = preparedStatement.executeQuery();
		Doctor doctor = null;
		//System.out.println("Doctor"+doctor);
		if (resultSet.next()) {
			System.out.println(resultSet.getInt("doctor_id"));
			
			Department department = new DoctorDao().findOneDepartment(resultSet.getInt("dept_id"), connection);
			User user = new DoctorDao().findOneUser(resultSet.getInt("user_id"), connection);
			System.out.println("Department : "+department);
			doctor = new Doctor(resultSet.getInt("doctor_id"), user, department,
					resultSet.getString("name"), resultSet.getString("contact_no"), resultSet.getString("email"));
			System.out.println("Doctor"+doctor);
		}
		preparedStatement.close();
		resultSet.close();
		return doctor;
	}
	
	public Department findOneDepartment(int deptId ,Connection connection) throws Exception {
		
		final String sqlQuery = "SELECT dept_id, name FROM Department WHERE dept_id=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setInt(1, deptId);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		Department department = null;
		if (resultSet.next()) {
			department = new Department(resultSet.getInt("dept_id"), resultSet.getString("name"));
			System.out.println("Department : "+department);
		}
		preparedStatement.close();
		resultSet.close();
		return department;
	}
	
	public User findOneUser(int userId ,Connection connection) throws Exception {
		
		final String sqlQuery = "SELECT * FROM Users WHERE user_id=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setInt(1, userId);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		User user = null;
		if (resultSet.next()) {
			user = new User(resultSet.getInt("user_id"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("role"));
			System.out.println("Department : "+user);
		}
		preparedStatement.close();
		resultSet.close();
		return user;
	}


}
