package com.medisync.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.medisync.models.LabTest;
import com.medisync.models.MedicalRecord;
import com.medisync.models.Patient;
import com.medisync.models.Prescription;
import com.medisync.util.DBConnection;

public class MedicalRecordDao implements IDao<MedicalRecord> {
	private DBConnection dbConnection;
	
	public MedicalRecordDao() {
		super();
	}

	public MedicalRecordDao(DBConnection dbConnection) {
		super();
		this.dbConnection = dbConnection;
	}

	public MedicalRecord create(MedicalRecord medicalRecord) throws Exception {
		try {
			Connection connection = dbConnection.getConnection();
			PreparedStatement preparedstatement = null;
			if(medicalRecord.getLabTest()!=null)
			{
				String sqlQuery = "INSERT INTO Medical_records (Patient_id, Prescription_id, Lab_test_id , Symptoms , Diagnosis) VALUES (?, ? , ? , ? , ?)";
				preparedstatement = connection.prepareStatement(sqlQuery);
				//preparedstatement = connection.prepareStatement(sqlQuery);
				preparedstatement.setInt(1 , medicalRecord.getPatient().getPatientId());
				preparedstatement.setInt(2 ,medicalRecord.getPrescription().getPrescriptionId());
				preparedstatement.setInt(3 ,medicalRecord.getLabTest().getLabTestId());
				preparedstatement.setString(4 , medicalRecord.getSymptoms());
				preparedstatement.setString(5 , medicalRecord.getDiagnosis());
			} else {
				String sqlQuery = "INSERT INTO Medical_records (Patient_id, Prescription_id, Symptoms , Diagnosis) VALUES (?, ? , ? , ?)";
				preparedstatement = connection.prepareStatement(sqlQuery);
				//preparedstatement = connection.prepareStatement(sqlQuery);
				preparedstatement.setInt(1 , medicalRecord.getPatient().getPatientId());
				preparedstatement.setInt(2 ,medicalRecord.getPrescription().getPrescriptionId());
				preparedstatement.setString(3 , medicalRecord.getSymptoms());
				preparedstatement.setString(4 , medicalRecord.getDiagnosis());
			}
			if(preparedstatement.executeUpdate()<0) {
				medicalRecord=null;
			}
			
			preparedstatement.close();
			
		} 
		
		catch (SQLException e) {
			medicalRecord = null;
			e.printStackTrace();
		}
		return medicalRecord;
	}

	public boolean update(int id, MedicalRecord medicalRecord) throws Exception {
		boolean result = false;
		try {
			System.out.println("In Update of Medical Record");
			Connection connection = dbConnection.getConnection();
			String sqlQuery = "UPDATE Medical_records SET Symptoms=? ,Diagnosis=? WHERE prescription_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setString(1, medicalRecord.getSymptoms());
			preparedStatement.setString(2, medicalRecord.getDiagnosis());
//			preparedStatement.setDate(3, appointment.getAppointment_date());
//			preparedStatement.setString(4, appointment.getAppointment_slot());
//			preparedStatement.setString(5, appointment.getStatus());
			preparedStatement.setInt(3, id);
			if (preparedStatement.executeUpdate() > 0) {
				System.out.println("In Update if of medical records");
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

	public MedicalRecord findOne(String username) throws Exception {
		return null;
	}

	public List<MedicalRecord> findAll() throws Exception {
		return null;
	}
	
	public List<MedicalRecord> findAllByPatientId(int patientId) throws Exception {
		List<MedicalRecord> medicalRecords = new ArrayList<MedicalRecord>();
		System.out.print("In Find All");
		Connection connection = dbConnection.getConnection();
		Statement selectStatement = connection.createStatement();
		
		final String sqlQuery = "SELECT * FROM medical_records WHERE patient_id="+patientId+"ORDER BY patient_id";
		
		ResultSet resultSet = selectStatement.executeQuery(sqlQuery);

		while (resultSet.next()) {
			
			//int recordId, Patient patient, Prescription prescription, LabTest labTest, String symptoms,
			//String diagnosis
			Patient patient = new PatientDao(dbConnection).findOne(resultSet.getInt("patient_id"));
			Prescription prescription = new PrescriptionDao(dbConnection).findOne(resultSet.getInt("prescription_id"));
			LabTest labTest = new LabTestDao(dbConnection).findOne(resultSet.getInt("lab_test_id"));
			MedicalRecord medicalRecord = new MedicalRecord(resultSet.getInt("record_id"), patient, prescription, labTest, resultSet.getString("symptoms"), resultSet.getString("diagnosis"));
			medicalRecords.add(medicalRecord);
		}
		selectStatement.close();
		resultSet.close();
		System.out.print(medicalRecords);
		if (medicalRecords.isEmpty())
			return null;
		return medicalRecords;
	}
	
	public List<MedicalRecord> findAllByDate(Date startDate, Date endDate, int patientId) throws Exception {
		List<MedicalRecord> medicalRecords = new ArrayList<MedicalRecord>();
		System.out.print("In Find All");
		Connection connection = dbConnection.getConnection();
		Statement selectStatement = connection.createStatement();
		
		final String sqlQuery = "SELECT * FROM medical_records WHERE patient_id="+patientId+"ORDER BY patient_id";
		
		ResultSet resultSet = selectStatement.executeQuery(sqlQuery);

		while (resultSet.next()) {
			
			//int recordId, Patient patient, Prescription prescription, LabTest labTest, String symptoms,
			//String diagnosis
			Patient patient = new PatientDao(dbConnection).findOne(resultSet.getInt("patient_id"));
			Prescription prescription = new PrescriptionDao(dbConnection).findOne(resultSet.getInt("prescription_id"));
			if(prescription.getDatePrescribed().after(startDate) && prescription.getDatePrescribed().before(endDate)) {
				LabTest labTest = new LabTestDao(dbConnection).findOne(resultSet.getInt("lab_test_id"));
				MedicalRecord medicalRecord = new MedicalRecord(resultSet.getInt("record_id"), patient, prescription, labTest, resultSet.getString("symptoms"), resultSet.getString("diagnosis"));
				medicalRecords.add(medicalRecord);
			}
		}
		selectStatement.close();
		resultSet.close();
		System.out.print(medicalRecords);
		if (medicalRecords.isEmpty())
			return null;
		return medicalRecords;
	}
	
	public Map<Integer, Integer> countAllMedicalRecords() throws Exception {
		Map<Integer, Integer> countMedicalRecords = new HashMap<Integer, Integer>();
		try {
			Connection connection=dbConnection.getConnection();
			String sqlQuery="SELECT patient_id, COUNT(*) AS total FROM medical_records GROUP BY patient_id";
			Statement statement=connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQuery);
			
			while(resultSet.next()){
			    countMedicalRecords.put(resultSet.getInt("patient_id"), resultSet.getInt("total"));
			}
			statement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return countMedicalRecords;
	}
	
}
