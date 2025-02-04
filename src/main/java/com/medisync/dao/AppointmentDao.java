package com.medisync.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.medisync.models.Appointment;
import com.medisync.models.Doctor;
import com.medisync.models.Patient;
import com.medisync.util.DBConnection;

public class AppointmentDao implements IDao<Appointment> {
	private DBConnection dbConnection;

	public AppointmentDao() {
		super();
	}

	public AppointmentDao(DBConnection dbConnection) {
		super();
		this.dbConnection = dbConnection;
	}

	public Appointment create(Appointment appointment) throws Exception {
		try {
			Connection connection = dbConnection.getConnection();
			String sqlQuery = "INSERT INTO Appointment (patient_id,doctor_id,status,appointment_date,appointment_slot) VALUES(?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, appointment.getPatient().getPatientId());
			preparedStatement.setInt(2, appointment.getDoctor().getDoctorId());
			preparedStatement.setString(3, appointment.getStatus());
			preparedStatement.setDate(4, appointment.getAppointment_date());
			preparedStatement.setString(5, appointment.getAppointment_slot());

			if (preparedStatement.executeUpdate() < 0) {
				appointment = null;
			}
			preparedStatement.close();
		} catch (SQLException e) {
			appointment = null;
			e.printStackTrace();
		}

		return appointment;
	}

	public boolean update(int id, Appointment appointment) throws Exception {
		boolean result = false;
		try {
			System.out.println("In Update of appointment");
			Connection connection = dbConnection.getConnection();
			String sqlQuery = "UPDATE Appointment SET patient_id=?, doctor_id=?, appointment_date=?, appointment_slot=?, status=? WHERE appointment_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, appointment.getPatient().getPatientId());
			preparedStatement.setInt(2, appointment.getDoctor().getDoctorId());
			preparedStatement.setDate(3, appointment.getAppointment_date());
			preparedStatement.setString(4, appointment.getAppointment_slot());
			preparedStatement.setString(5, appointment.getStatus());
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
			System.out.println("In delete of appointment");
			Connection connection = dbConnection.getConnection();
			String sqlQuery = "DELETE FROM appointment WHERE appointment_id=?";
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

	public Appointment findOne(int id) throws Exception {
		System.out.println("In Find one of Appointment");
		Connection connection = dbConnection.getConnection();
		final String sqlQuery = "SELECT * FROM Appointment WHERE appointment_id=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		Appointment appointment = null;
		if (resultSet.next()) {

			Patient patient = new PatientDao(dbConnection).findOne(resultSet.getInt("patient_id"));
			Doctor doctor = new DoctorDao(dbConnection).findOne(resultSet.getInt("doctor_id"));

			appointment = new Appointment(resultSet.getInt("appointment_id"), patient, doctor,
					resultSet.getString("status"), resultSet.getDate("appointment_date"),
					resultSet.getString("appointment_slot"));

		}
		System.out.println(appointment);
		resultSet.close();
		preparedStatement.close();
		if (appointment.equals(null))
			return null;
		return appointment;
	}

	public List<Appointment> findAll() throws Exception {
		List<Appointment> appointments = new ArrayList<Appointment>();
		System.out.print("In Find All of appointment");
		Connection connection = dbConnection.getConnection();
		Statement selectStatement = connection.createStatement();

		final String sqlQuery = "SELECT * FROM Appointment ORDER BY appointment_id";

		ResultSet resultSet = selectStatement.executeQuery(sqlQuery);

		while (resultSet.next()) {
			Patient patient = new PatientDao(dbConnection).findOne(resultSet.getInt("patient_id"));
			Doctor doctor = new DoctorDao(dbConnection).findOne(resultSet.getInt("doctor_id"));

			Appointment appointment = new Appointment(resultSet.getInt("appointment_id"), patient, doctor,
					resultSet.getString("status"), resultSet.getDate("appointment_date"),
					resultSet.getString("appointment_slot"));
			appointments.add(appointment);

		}
		resultSet.close();
		selectStatement.close();
		System.out.print(appointments);
		if (appointments.isEmpty())
			return null;
		return appointments;
	}

	public List<Appointment> findAllByDoctorId(int doctorId) throws Exception {
		List<Appointment> appointments = new ArrayList<Appointment>();

		Connection connection = dbConnection.getConnection();

		final String sqlQuery = "SELECT * FROM appointment WHERE doctor_id=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setInt(1, doctorId);
		ResultSet resultSet = preparedStatement.executeQuery();

		System.out.println("\nList of appointments by doctor id are: " + resultSet + "\n");

		while (resultSet.next()) {
			Patient patient = new PatientDao(dbConnection).findOne(resultSet.getInt("patient_id"));
			Doctor doctor = new DoctorDao(dbConnection).findOne(resultSet.getInt("doctor_id"));

			Appointment appointment = new Appointment(resultSet.getInt("appointment_id"), patient, doctor,
					resultSet.getString("status"), resultSet.getDate("appointment_date"),
					resultSet.getString("appointment_slot"));
			appointments.add(appointment);
		}
		preparedStatement.close();
		resultSet.close();
		if (appointments.isEmpty()) {
			return null;
		}
		return appointments;

	}

	public Appointment findOne(String username) throws Exception {
		return null;
	}

}
