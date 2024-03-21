package com.medisync.models;

import java.sql.Date;

public class LabTest {
	private int labTestId;
	private Patient patient;
	private Doctor doctor;
	private String labTestName;
	private Date testDate;
	private String testResult;
	
	public LabTest() {
		super();
	}

	public LabTest(int labTestId, Patient patient, Doctor doctor, String labTestName, Date testDate,
			String testResult) {
		super();
		this.labTestId = labTestId;
		this.patient = patient;
		this.doctor = doctor;
		this.labTestName = labTestName;
		this.testDate = testDate;
		this.testResult = testResult;
	}

	public int getLabTestId() {
		return labTestId;
	}

	public void setLabTestId(int labTestId) {
		this.labTestId = labTestId;
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

	public String getLabTestName() {
		return labTestName;
	}

	public void setLabTestName(String labTestName) {
		this.labTestName = labTestName;
	}

	public Date getTestDate() {
		return testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	public String getTestResult() {
		return testResult;
	}

	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + labTestId;
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
		LabTest other = (LabTest) obj;
		if (labTestId != other.labTestId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LabTest [labTestId=" + labTestId + ", patient=" + patient + ", doctor=" + doctor + ", labTestName="
				+ labTestName + ", testDate=" + testDate + ", testResult=" + testResult + "]";
	}
	
	
}
