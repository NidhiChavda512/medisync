package com.medisync.models;

import java.sql.Date;

public class Appointment {
	private int appointmentId;
	private Patient patient;
	private Doctor doctor;
	private String status;
	private Date appointment_date;
	private String appointment_slot;
	
	public Appointment() {
		super();
	}

	public Appointment(int appointmentId, Patient patient, Doctor doctor, String status, Date appointment_date,
			String appointment_slot) {
		super();
		this.appointmentId = appointmentId;
		this.patient = patient;
		this.doctor = doctor;
		this.status = status;
		this.appointment_date = appointment_date;
		this.appointment_slot = appointment_slot;
	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Patient getPatientId() {
		return patient;
	}

	public void setPatientId(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctorId() {
		return doctor;
	}

	public void setDoctorId(Doctor doctor) {
		this.doctor = doctor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getAppointment_date() {
		return appointment_date;
	}

	public void setAppointment_date(Date appointment_date) {
		this.appointment_date = appointment_date;
	}

	public String getAppointment_slot() {
		return appointment_slot;
	}

	public void setAppointment_slot(String appointment_slot) {
		this.appointment_slot = appointment_slot;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + appointmentId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appointment other = (Appointment) obj;
		if (appointmentId != other.appointmentId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Appointment [appointmentId=" + appointmentId + ", patientId=" + patient + ", doctorId=" + doctor
				+ ", status=" + status + ", appointment_date=" + appointment_date + ", appointment_slot="
				+ appointment_slot + "]";
	}
	
}
