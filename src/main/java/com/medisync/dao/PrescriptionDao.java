package com.medisync.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.medisync.models.Appointment;
import com.medisync.models.Doctor;
import com.medisync.models.Patient;
import com.medisync.models.Prescription;
import com.medisync.util.DBConnection;

public class PrescriptionDao implements IDao<Prescription> {
	private DBConnection dbConnection;
	

	public PrescriptionDao() {
		super();
	}

	public PrescriptionDao(DBConnection dbConnection) {
		super();
		this.dbConnection = dbConnection;
	}

	public Prescription create(Prescription prescription) throws Exception {
		try {
			Connection connection = dbConnection.getConnection();
//			String sqlQuery = "SELECT patient_id FROM Patient WHERE name=?";
//			PreparedStatement preparedstatement = connection.prepareStatement(sqlQuery);
			String sqlQuery = "Insert into Prescription (patient_id , doctor_id , medicine_name , date_prescribed , duration) values (?,?,?,?,?)";
			PreparedStatement preparedstatement = connection.prepareStatement(sqlQuery);
			preparedstatement.setInt(1, prescription.getPatient().getPatientId());
			preparedstatement.setInt(2, prescription.getDoctor().getDoctorId());
			preparedstatement.setString(3, prescription.getMedicineName());
			preparedstatement.setDate(4, prescription.getDatePrescribed());
			preparedstatement.setInt(5, prescription.getDuration());

			System.out.println("inside PrescriptionDao");

			if (preparedstatement.executeUpdate() < 0) {
				prescription = null;
			}
			preparedstatement.close();
		}

		catch (SQLException e) {
			prescription = null;
			e.printStackTrace();
		}
		return prescription;
	}


	public boolean update(int id, Prescription prescription) throws Exception {
		
		boolean result = false;
		try {
			System.out.println("In Update of Prescription");
			Connection connection = dbConnection.getConnection();
			String sqlQuery = "UPDATE Prescription SET Medicine_name=? ,Duration=? WHERE prescription_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1, prescription.getMedicineName());
			preparedStatement.setInt(2, prescription.getDuration());
//			preparedStatement.setDate(3, appointment.getAppointment_date());
//			preparedStatement.setString(4, appointment.getAppointment_slot());
//			preparedStatement.setString(5, appointment.getStatus());
			preparedStatement.setInt(3, id);
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
		return false;
	}

	public Prescription findOne(String username) throws Exception {
		return null;
	}
	
	public Prescription findOne(int prescriptionId) throws Exception {
		Connection connection = dbConnection.getConnection();
		final String sqlQuery = "SELECT * FROM prescription WHERE prescription_id=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setInt(1, prescriptionId);
		ResultSet resultSet = preparedStatement.executeQuery();
		Prescription prescription = null;
		if (resultSet.next()) {
			//int prescriptionId, Patient patient, Doctor doctor, String medicineName, Date datePrescribed,
			//int duration
			Patient patient = new PatientDao(dbConnection).findOne(resultSet.getInt("patient_id"));
			Doctor doctor = new DoctorDao(dbConnection).findOne(resultSet.getInt("doctor_id"));
			prescription = new Prescription(resultSet.getInt("prescription_id"), patient, doctor, resultSet.getString("medicine_name"), resultSet.getDate("date_prescribed"), resultSet.getInt("duration"));
		}
		resultSet.close();
		preparedStatement.close();
		return prescription;
	}

	public List<Prescription> findAll() throws Exception {
		return null;
	}
	
	public List<Prescription> findAllByDoctorId(int doctorId) throws Exception {
		List<Prescription> prescriptions = new ArrayList<Prescription>();

		Connection connection = dbConnection.getConnection();

		final String sqlQuery = "SELECT * FROM Prescription WHERE doctor_id=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setInt(1, doctorId);
		ResultSet resultSet = preparedStatement.executeQuery();

		System.out.println("\n\nList of prescriptions by doctor id are: " + resultSet+"\n");
		
		while (resultSet.next()) {
			Patient patient = new PatientDao(dbConnection).findOne(resultSet.getInt("patient_id"));
			Doctor doctor = new DoctorDao(dbConnection).findOne(resultSet.getInt("doctor_id"));

			Prescription prescription = new Prescription(resultSet.getInt("prescription_id"), patient, doctor,
					resultSet.getString("medicine_name"), resultSet.getDate("date_prescribed"),
					resultSet.getInt("duration"));
			prescriptions.add(prescription);
		}
		resultSet.close();
		preparedStatement.close();
		if (prescriptions.isEmpty()) {
			return null;
		}
		return prescriptions;
	
	}
	
	public Prescription getLatestPrescriptionId() throws Exception {

		Connection connection = dbConnection.getConnection();
		final String sqlQuery = "SELECT * FROM Prescription WHERE Prescription_id=(SELECT MAX(Prescription_id) FROM Prescription)";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		Prescription prescription = null;
		if (resultSet.next()) {
			Patient patient = new PatientDao(dbConnection).findOne(resultSet.getInt("patient_id"));
			Doctor doctor = new DoctorDao(dbConnection).findOne(resultSet.getInt("doctor_id"));
			prescription = new Prescription(resultSet.getInt("Prescription_id"), patient, doctor,resultSet.getString("medicine_name"), resultSet.getDate("date_prescribed"), resultSet.getInt("duration"));
		}
		resultSet.close();
		preparedStatement.close();
//		(int prescriptionId, Patient patient, Doctor doctor, String medicineName, Date datePrescribed,
//				int duration)

		return prescription;
	}

}
