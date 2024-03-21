package com.medisync.models;

public class MedicalRecord {
	private int recordId;
	private Patient patient;
	private Prescription prescription;
	private LabTest labTest;
	private String symptoms;
	private String diagnosis;
	
	public MedicalRecord() {
		super();
	}

	public MedicalRecord(int recordId, Patient patient, Prescription prescription, LabTest labTest, String symptoms,
			String diagnosis) {
		super();
		this.recordId = recordId;
		this.patient = patient;
		this.prescription = prescription;
		this.labTest = labTest;
		this.symptoms = symptoms;
		this.diagnosis = diagnosis;
	}

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Prescription getPrescription() {
		return prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}

	public LabTest getLabTest() {
		return labTest;
	}

	public void setLabTest(LabTest labTest) {
		this.labTest = labTest;
	}

	public String getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + recordId;
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
		MedicalRecord other = (MedicalRecord) obj;
		if (recordId != other.recordId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MedicalRecord [recordId=" + recordId + ", patient=" + patient + ", prescription=" + prescription
				+ ", labTest=" + labTest + ", symptoms=" + symptoms + ", diagnosis=" + diagnosis + "]";
	}
}
