package com.medisync.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.medisync.models.Doctor;
import com.medisync.models.LabTest;
import com.medisync.models.Patient;
import com.medisync.util.DBConnection;

public class LabTestDao implements IDao<LabTest> {

	private DBConnection dbConnection;

	public LabTestDao() {
		super();
	}

	public LabTestDao(DBConnection dbConnection) {
		super();
		this.dbConnection = dbConnection;
	}

	public LabTest create(LabTest labTest) throws Exception {
		try {
			Connection connection = dbConnection.getConnection();

			String sqlQuery = "INSERT INTO Lab_test (Patient_id, Doctor_id, Name) VALUES (?, ? , ?)";
			PreparedStatement preparedstatement = connection.prepareStatement(sqlQuery);
			preparedstatement = connection.prepareStatement(sqlQuery);
			preparedstatement.setInt(1, labTest.getPatient().getPatientId());
			preparedstatement.setInt(2, labTest.getDoctor().getDoctorId());
			preparedstatement.setString(3, labTest.getLabTestName());

			if (preparedstatement.executeUpdate() < 0) {
				labTest = null;
			}
			preparedstatement.close();
		}

		catch (SQLException e) {
			labTest = null;
			e.printStackTrace();
		}

		return labTest;
	}

	public boolean update(int id, LabTest labtest) throws Exception {
		boolean result = false;
		try {
			System.out.println("In Update Method of labtest");
			Connection connection = dbConnection.getConnection();
			String sqlQuery = "UPDATE lab_test SET patient_id=?, doctor_id=?, name=?, test_date=?, test_result=? WHERE lab_test_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

			preparedStatement.setInt(1, labtest.getPatient().getPatientId());
			preparedStatement.setInt(2, labtest.getDoctor().getDoctorId());
			preparedStatement.setString(3, labtest.getLabTestName());
			preparedStatement.setDate(4, labtest.getTestDate());
			preparedStatement.setString(5, labtest.getTestResult());

			preparedStatement.setInt(6, id);
			if (preparedStatement.executeUpdate() > 0) {
				System.out.println("In Update if");
				result = true;
			}
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;

	}

	public boolean delete(int id) throws Exception {
		boolean result = false;
		try {
			Connection connection = dbConnection.getConnection();
			String sqlQuery = "DELETE FROM lab_test WHERE lab_test_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, id);
			if (preparedStatement.executeUpdate() > 0) {
				result = true;
			}
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}

	public LabTest findOne(String username) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public LabTest findOne(int id) throws Exception {
		System.out.println("In Find one of labtest");
		Connection connection = dbConnection.getConnection();
		System.out.println("In Find one of labtest");
		final String sqlQuery = "SELECT * FROM Lab_test WHERE lab_test_id=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		LabTest labtest = null;
		if (resultSet.next()) {

			Patient patient = new PatientDao(dbConnection).findOne(resultSet.getInt("patient_id"));
			Doctor doctor = new DoctorDao(dbConnection).findOne(resultSet.getInt("doctor_id"));

			labtest = new LabTest(resultSet.getInt("lab_test_id"), patient, doctor, resultSet.getString("name"),
					resultSet.getDate("test_date"), resultSet.getString("test_result"));
			System.out.println(labtest);

		}
		preparedStatement.close();
		resultSet.close();
		System.out.print(labtest);
		if (labtest==null)
			return null;
		return labtest;
	}

	public List<LabTest> findAll() throws Exception {
		System.out.println("In Find all of labtest");
		List<LabTest> labTests = new ArrayList<LabTest>();
		Connection connection = null;
		Statement selectStatement = null;
		ResultSet resultSet = null;

		connection = dbConnection.getConnection();
		selectStatement = connection.createStatement();

		final String sqlQuery = "SELECT * FROM lab_test";
		resultSet = selectStatement.executeQuery(sqlQuery);

		while (resultSet.next()) {
			Patient patient = new PatientDao(dbConnection).findOne(resultSet.getInt("Patient_id"));
			Doctor doctor = new DoctorDao(dbConnection).findOne(resultSet.getInt("Doctor_id"));

			LabTest labTest = new LabTest(resultSet.getInt("Lab_test_id"), patient, doctor, resultSet.getString("Name"),
					resultSet.getDate("Test_date"), resultSet.getString("Test_result"));
			labTests.add(labTest);
			System.out.println(labTests);
		}
		System.out.println(labTests);
		selectStatement.close();
		resultSet.close();
		if (labTests.isEmpty())
			return null;
		return labTests;

	}
	
	
	public LabTest getLatestLabTestId() throws Exception{
		
		Connection connection = dbConnection.getConnection();
		final String sqlQuery = "SELECT * FROM Lab_test WHERE lab_test_id=(SELECT MAX(lab_test_id) FROM Lab_test)";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		LabTest labTest = null;
		if (resultSet.next()) {
			Patient patient = new PatientDao(dbConnection).findOne(resultSet.getInt("patient_id"));
			Doctor doctor = new DoctorDao(dbConnection).findOne(resultSet.getInt("doctor_id"));
			labTest = new LabTest(resultSet.getInt("lab_test_id"), patient, doctor, resultSet.getString("name"), resultSet.getDate("test_date"), resultSet.getString("test_result"));
		}
		
		System.out.println("LabTest is: " + labTest);
		preparedStatement.close();
		resultSet.close();
		return labTest;
	}

//	public List<LabTest> findAll() throws Exception {
//		List<LabTest> labtests = new ArrayList<LabTest>();
//	
//		Connection connection = dbConnection.getConnection();
//		System.out.print("In Find All labtest");
//		Statement selectStatement = connection.createStatement();
//
//		final String sqlQuery = "SELECT * FROM lab_test";
//
//		ResultSet resultSet = selectStatement.executeQuery(sqlQuery);
//		System.out.println(resultSet);
//
//		while (resultSet.next()) {
//			
//			Patient patient = new PatientDao(dbConnection).findOne(resultSet.getInt("patient_id"));
//			Doctor doctor = new DoctorDao(dbConnection).findOne(resultSet.getInt("doctor_id"));
//
//			LabTest labtest = new LabTest(resultSet.getInt("lab_test_id"), patient, doctor, resultSet.getString("name"),
//					resultSet.getDate("test_date"), resultSet.getString("test_result"));
//			labtests.add(labtest);
//		
//
//		}
//
//		System.out.print(labtests);
//		if (labtests.isEmpty()) {
//			System.out.println("it is null");
//			return null;
//			
//		} else {
//			return labtests;
//		}
//
//	}
//
}
