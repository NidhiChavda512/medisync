package com.medisync.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.medisync.models.Department;
import com.medisync.util.DBConnection;

public class DepartmentDao implements IDao<Department> {
	private DBConnection dbConnection;

	public DepartmentDao(DBConnection dbConnection) {
		super();
		this.dbConnection = dbConnection;
	}

	public Department create(Department t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean update(int id, Department t) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean delete(int id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public Department findOne(int deptId) throws Exception {
		System.out.println("In find one of Department");
		Connection connection = dbConnection.getConnection();
		final String sqlQuery = "SELECT dept_id, name FROM Department WHERE dept_id=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setInt(1, deptId);

		ResultSet resultSet = preparedStatement.executeQuery();
		Department department = null;
		if (resultSet.next()) {
			department = new Department(resultSet.getInt("dept_id"), resultSet.getString("name"));
			System.out.println("Department : " + department);
		}
		resultSet.close();
		preparedStatement.close();
		System.out.print(department);
		if (department.equals(null))
			return null;
		return department;
	}

	public List<Department> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Department findOne(String username) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
