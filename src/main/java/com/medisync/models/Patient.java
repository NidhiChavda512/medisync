package com.medisync.models;

import java.sql.Date;

public class Patient {
	private int patientId;
	private String name;
	private Date dateOfBirth;
	private String gender;
	private String contactNo;
	private String email;
	private Date registrationDate;
	
	public Patient() {
		super();
	}

	public Patient(int patientId, String name, String contactNo, String email, Date dateOfBirth, 
			String gender, Date registrationDate) {
		super();
		this.patientId = patientId;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.contactNo = contactNo;
		this.email = email;
		this.registrationDate = registrationDate;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + patientId;
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
		Patient other = (Patient) obj;
		if (patientId != other.patientId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", name=" + name + ", dateOfBirth=" + dateOfBirth + ", gender="
				+ gender + ", contact_no=" + contactNo + ", email=" + email + ", registrationDate=" + registrationDate
				+ "]";
	}
	
}
