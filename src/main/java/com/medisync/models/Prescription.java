package com.medisync.models;

import java.sql.Date;

public class Prescription {
	private int prescriptionId;
	private Patient patient;
	private Doctor doctor;
	private String medicineName;
	private Date datePrescribed;
	private int duration;
	
	public Prescription() {
		super();
	}

	public Prescription(int prescriptionId, Patient patient, Doctor doctor, String medicineName, Date datePrescribed,
			int duration) {
		super();
		this.prescriptionId = prescriptionId;
		this.patient = patient;
		this.doctor = doctor;
		this.medicineName = medicineName;
		this.datePrescribed = datePrescribed;
		this.duration = duration;
	}

	public int getPrescriptionId() {
		return prescriptionId;
	}

	public void setPrescriptionId(int prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public Date getDatePrescribed() {
		return datePrescribed;
	}

	public void setDatePrescribed(Date datePrescribed) {
		this.datePrescribed = datePrescribed;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + prescriptionId;
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
		Prescription other = (Prescription) obj;
		if (prescriptionId != other.prescriptionId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Prescription [prescriptionId=" + prescriptionId + ", patient=" + patient + ", doctor=" + doctor
				+ ", medicineName=" + medicineName + ", datePrescribed=" + datePrescribed + ", duration=" + duration
				+ "]";
	}
	
}
