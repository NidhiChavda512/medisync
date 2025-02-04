package com.medisync.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.medisync.models.Patient;
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
			Connection connection = dbConnection.getConnection();
			String sqlQuery = "INSERT INTO Patient (name, dob, gender, contact_no, email) VALUES(?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1, patient.getName());
			preparedStatement.setDate(2, patient.getDateOfBirth());
			preparedStatement.setString(3, patient.getGender());
			preparedStatement.setString(4, patient.getContactNo());
			preparedStatement.setString(5, patient.getEmail());

			if (preparedStatement.executeUpdate() < 0) {
				patient = null;
			}
		} catch (SQLException e) {
			patient = null;
			e.printStackTrace();
		}

		return patient;
	}

	public boolean update(int id, Patient patient) throws Exception {
		boolean result = false;
		try {
			System.out.println("In Update Method");
			Connection connection = dbConnection.getConnection();
			String sqlQuery = "UPDATE Patient SET name=?, dob=?, gender=?, contact_no=?, email=? WHERE PATIENT_ID=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1, patient.getName());
			preparedStatement.setDate(2, patient.getDateOfBirth());
			preparedStatement.setString(3, patient.getGender());
			preparedStatement.setString(4, patient.getContactNo());
			preparedStatement.setString(5, patient.getEmail());
			preparedStatement.setInt(6, id);
			if (preparedStatement.executeUpdate() > 0) {
				System.out.println("In Update if");
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean delete(int id) throws Exception {
		boolean result = false;
		try {
			Connection connection = dbConnection.getConnection();
			String sqlQuery = "DELETE FROM patient WHERE patient_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, id);
			if (preparedStatement.executeUpdate() > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Patient findOne(String username) throws Exception {
		
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
			patient = new Patient(resultSet.getInt("patient_id"), resultSet.getString("name"),
					resultSet.getString("contact_no"), resultSet.getString("email"), resultSet.getDate("dob"),
					resultSet.getString("gender"), resultSet.getDate("registration_date"));
		}
		return patient;
	}

	public Map<Integer, String> findAllNames() throws SQLException {
		Map<Integer, String> names = new HashMap<Integer, String>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = dbConnection.getConnection();
			String query = "SELECT patient_id, name FROM Patient";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				names.put(resultSet.getInt("patient_id"), resultSet.getString("name"));
			}
		} catch (SQLException e) {
			// Handle SQLException
			e.printStackTrace();
		}
		return names;
	}

	public List<Patient> findAll() throws Exception {
		List<Patient> patients = new ArrayList<Patient>();
		System.out.print("In Find All");
		Connection connection = dbConnection.getConnection();
		Statement selectStatement = connection.createStatement();

		final String sqlQuery = "SELECT * FROM Patient ORDER BY patient_id";

		ResultSet resultSet = selectStatement.executeQuery(sqlQuery);

		while (resultSet.next()) {
			Patient patient = new Patient(resultSet.getInt("patient_id"), resultSet.getString("name"),
					resultSet.getString("contact_no"), resultSet.getString("email"), resultSet.getDate("dob"),
					resultSet.getString("gender"), resultSet.getDate("registration_date"));
			patients.add(patient);

		}
		resultSet.close();
		System.out.print(patients);
		if (patients.isEmpty())
			return null;
		return patients;
	}

	public List<Patient> findAllByDoctor(int doctorId) throws Exception {
		List<Patient> patients = new ArrayList<Patient>();

		int patientId = -1;
		Connection connection = dbConnection.getConnection();
		// Statement selectStatement = connection.createStatement();

		final String sqlQuery = "SELECT patient_id FROM appointment where doctor_id=?";

		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setInt(1, doctorId);
		ResultSet resultSet = preparedStatement.executeQuery();

		System.out.println("\nList of patients are: " + resultSet);

		while (resultSet.next()) {
			patientId = resultSet.getInt("patient_id");
			final String fetchQuery = "SELECT * FROM Patient WHERE Patient_id=?";
			preparedStatement = connection.prepareStatement(fetchQuery);
			preparedStatement.setInt(1, patientId);
			ResultSet resultSetFetchPatient = preparedStatement.executeQuery();
			
			while (resultSetFetchPatient.next()) {
				Patient patient = new Patient(resultSetFetchPatient.getInt("patient_id"),
						resultSetFetchPatient.getString("name"), resultSetFetchPatient.getString("contact_no"),
						resultSetFetchPatient.getString("email"), resultSetFetchPatient.getDate("dob"),
						resultSetFetchPatient.getString("gender"), resultSetFetchPatient.getDate("registration_date"));
				patients.add(patient);

			}
			resultSetFetchPatient.close();
		}
		// return patientId ;
		// Statement selectStatement = connection.createStatement();
//		final String fetchQuery = "SELECT * FROM Patient WHERE Patient_id=?";
//		preparedStatement = connection.prepareStatement(fetchQuery);
//		preparedStatement.setInt(1, patientId);
//		resultSet = preparedStatement.executeQuery();
//		while (resultSet.next()) {
//			Patient patient = new Patient(resultSet.getInt("patient_id"), resultSet.getString("name"), resultSet.getString("contact_no"), resultSet.getString("email"), resultSet.getDate("dob"), resultSet.getString("gender"), resultSet.getDate("registration_date"));
//			patients.add(patient);
//
//		}
		resultSet.close();
		System.out.print(patients);
		if (patients.isEmpty())
			return null;
		return patients;
	}

	public List<Patient> searchByName(String patientName) throws Exception {
		System.out.println("In Search Patient Name : " + patientName);
		List<Patient> patients = new ArrayList<Patient>();

		Connection connection = dbConnection.getConnection();
		Statement selectStatement = connection.createStatement();

		final String sqlQuery = "SELECT * FROM Patient WHERE name LIKE '%" + patientName + "%' OR email LIKE '%"
				+ patientName + "%'";

		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		// preparedStatement.setString(1, patientName);
		ResultSet resultSet = preparedStatement.executeQuery();
		Patient patient = null;
		if (resultSet.next()) {
			patient = new Patient(resultSet.getInt("patient_id"), resultSet.getString("name"),
					resultSet.getString("contact_no"), resultSet.getString("email"), resultSet.getDate("dob"),
					resultSet.getString("gender"), resultSet.getDate("registration_date"));
			patients.add(patient);
		}
		System.out.println("Patient Searched : " + patients);
		return patients;
	}
	
	public List<Patient> searchByNameAndDoctor(String patientName, int doctorId) throws Exception {
		System.out.println("In Search Patient Name : " + patientName);
		List<Patient> patients = new ArrayList<Patient>();

		Connection connection = dbConnection.getConnection();
		final String sqlQuery = "SELECT patient_id FROM appointment where doctor_id=?";

		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setInt(1, doctorId);
		ResultSet resultSet = preparedStatement.executeQuery();

		System.out.println("\nList of patients are: " + resultSet);

		while (resultSet.next()) {
			int patientId = resultSet.getInt("patient_id");
			final String fetchQuery = "SELECT * FROM Patient WHERE patient_id="+patientId+" AND name LIKE '%" + patientName + "%'";
			preparedStatement = connection.prepareStatement(fetchQuery);
			ResultSet resultSetFetchPatient = preparedStatement.executeQuery();
			
			while (resultSetFetchPatient.next()) {
				Patient patient = new Patient(resultSetFetchPatient.getInt("patient_id"),
						resultSetFetchPatient.getString("name"), resultSetFetchPatient.getString("contact_no"),
						resultSetFetchPatient.getString("email"), resultSetFetchPatient.getDate("dob"),
						resultSetFetchPatient.getString("gender"), resultSetFetchPatient.getDate("registration_date"));
				patients.add(patient);

			}
		}
		// return patientId ;
		// Statement selectStatement = connection.createStatement();
//		final String fetchQuery = "SELECT * FROM Patient WHERE Patient_id=?";
//		preparedStatement = connection.prepareStatement(fetchQuery);
//		preparedStatement.setInt(1, patientId);
//		resultSet = preparedStatement.executeQuery();
//		while (resultSet.next()) {
//			Patient patient = new Patient(resultSet.getInt("patient_id"), resultSet.getString("name"), resultSet.getString("contact_no"), resultSet.getString("email"), resultSet.getDate("dob"), resultSet.getString("gender"), resultSet.getDate("registration_date"));
//			patients.add(patient);
//
//		}
		System.out.print(patients);
		if (patients.isEmpty())
			return null;
		return patients;
	}

}
