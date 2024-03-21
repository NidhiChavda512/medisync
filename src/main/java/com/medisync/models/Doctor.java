package com.medisync.models;

import java.sql.Date;

public class Doctor {
	private int doctorId;
	private User user;
	private Department department;
	private String name;
	private String contactNo;
	private String email;
	
	public Doctor() {
		super();
	}

	public Doctor(int doctorId, User user, Department department, String name, String contactNo, String email) {
		super();
		this.doctorId = doctorId;
		this.user = user;
		this.department = department;
		this.name = name;
		this.contactNo = contactNo;
		this.email = email;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + doctorId;
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
		Doctor other = (Doctor) obj;
		if (doctorId != other.doctorId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Doctor [doctorId=" + doctorId + ", user=" + user + ", department=" + department + ", name=" + name
				+ ", contactNo=" + contactNo + ", email=" + email + "]";
	}
	
}
