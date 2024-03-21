package com.medisync.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.medisync.models.Patient;
import com.medisync.models.User;
import com.medisync.util.DBConnection;

public class PatientDao implements IDao<Patient> {

	private DBConnection dbConnection;
	
	public PatientDao() {
		super();
	}

	public PatientDao(DBConnection dbConnection) {
		super();
		this.dbConnection = dbConnection;
	}

	public Patient create(Patient patient) throws Exception {
		try {
			Connection connection=dbConnection.getConnection();
			String sqlQuery="INSERT INTO Patient (name, dob, gender, contact_no, email) VALUES(?,?,?,?,?)";
			PreparedStatement preparedStatement=connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1, patient.getName());
			preparedStatement.setDate(2, patient.getDateOfBirth());
			preparedStatement.setString(3, patient.getGender());
			preparedStatement.setString(4, patient.getContactNo());
			preparedStatement.setString(5, patient.getEmail());
			
			if(preparedStatement.executeUpdate()<0) {
				patient=null;
			}
		} catch (SQLException e) {
			patient = null;
			e.printStackTrace();
		}
		
		return patient;
	}

	public boolean update(int id, Patient patient) throws Exception {
		boolean result=false;
		try {
			System.out.println("In Update Method");
			Connection connection=dbConnection.getConnection();
			String sqlQuery="UPDATE Patient SET name=?, dob=?, gender=?, contact_no=?, email=? WHERE PATIENT_ID=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1, patient.getName());
			preparedStatement.setDate(2, patient.getDateOfBirth());
			preparedStatement.setString(3, patient.getGender());
			preparedStatement.setString(4, patient.getContactNo());
			preparedStatement.setString(5, patient.getEmail());
			preparedStatement.setInt(6, id);
			if(preparedStatement.executeUpdate()>0) {
				System.out.println("In Update if");
				result=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public boolean delete(int id) throws Exception {
		boolean result=false;
		try {
			Connection connection=dbConnection.getConnection();
			String sqlQuery="DELETE FROM patient WHERE patient_id=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, id);
			if(preparedStatement.executeUpdate()>0) {
				result=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Patient findOne(String username) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Patient findOne(int id) throws Exception {
		Connection connection = dbConnection.getConnection();
		final String sqlQuery = "SELECT * FROM Patient WHERE Patient_id=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		Patient patient = null;
		if (resultSet.next()) {
			patient = new Patient(resultSet.getInt("patient_id"), resultSet.getString("name"), resultSet.getString("contact_no"), resultSet.getString("email"), resultSet.getDate("dob"), resultSet.getString("gender"), resultSet.getDate("registration_date"));
		}
		return patient;
	}

	public List<Patient> findAll() throws Exception {
		List<Patient> patients = new ArrayList<Patient>();
		System.out.print("In Find All");
		Connection connection = dbConnection.getConnection();
		Statement selectStatement = connection.createStatement();
		
		final String sqlQuery = "SELECT * FROM Patient";
		
		ResultSet resultSet = selectStatement.executeQuery(sqlQuery);

		while (resultSet.next()) {
			Patient patient = new Patient(resultSet.getInt("patient_id"), resultSet.getString("name"), resultSet.getString("contact_no"), resultSet.getString("email"), resultSet.getDate("dob"), resultSet.getString("gender"), resultSet.getDate("registration_date"));
			patients.add(patient);

		}
		System.out.print(patients);
		if (patients.isEmpty())
			return null;
		return patients;
	}
	
}
