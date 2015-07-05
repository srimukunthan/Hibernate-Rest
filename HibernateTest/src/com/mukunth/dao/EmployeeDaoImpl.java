package com.mukunth.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mukunth.exceptions.ResourceException;
import com.mukunth.general.ConnectionManager;
import com.mukunth.model.Company;
import com.mukunth.model.Employee;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class EmployeeDaoImpl implements EmployeeDao {
	
	private static final EmployeeDao employeeDao = new EmployeeDaoImpl();

	public static EmployeeDao getInstance() {
		return employeeDao;
	}
	
	private EmployeeDaoImpl() {
		
	}
	
	@Override
	public List<Employee> getEmployee(int companyID) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Employee> employeeList = new ArrayList<Employee>();
		con = (Connection) ConnectionManager.getConnection();
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from employee_master where companyID="+companyID);
			while(rs.next()) {
				Employee employee = new Employee(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4));
				employeeList.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(con);
		}
		return employeeList;
	}

	@Override
	public Employee getEmployeeByID(int id,int companyId) throws ResourceException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		con = (Connection) ConnectionManager.getConnection();
		Employee employee = null;
		try {
			pst = (PreparedStatement) con.prepareStatement("select * from employee_master where employee_id=? and companyId=?");
			pst.setInt(1, id);
			pst.setInt(2, companyId);
			rs = pst.executeQuery();
			while(rs.next()) {
				employee = new Employee(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4));
			}
			if(employee == null) {
				throw new ResourceException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(con);
		}
		
		return employee;
	}

	@Override
	public void deleteEmployeeByID(int id, int companyId) {
		Connection con = null;
		PreparedStatement pst = null;
		con = (Connection) ConnectionManager.getConnection();
		try {
			pst = (PreparedStatement) con.prepareStatement("delete from employee_master where employee_id=? and companyId=?");
			pst.setInt(1, id);
			pst.setInt(2, companyId);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(con);
		}
	}

	@Override
	public void createEmployeeByID(Employee employee) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		con = (Connection) ConnectionManager.getConnection();
		try {
			pst = (PreparedStatement) con.prepareStatement("select * from employee_master where name=?");
			pst.setString(1, employee.getName());
			rs = pst.executeQuery();
			if(rs.next()) {
			}
			pst = (PreparedStatement) con.prepareStatement("insert into employee_master (name,department,companyId) values (?,?,?)");
			pst.setString(1, employee.getName());
			pst.setString(2, employee.getDepartment());
			pst.setInt(3, employee.getCompanyId());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(con);
		}
	}

	@Override
	public void updateEmployeeByID(Employee employee, int companyId) {
		Connection con = null;
		PreparedStatement pst = null;
		con = (Connection) ConnectionManager.getConnection();
		try {
			pst = (PreparedStatement) con.prepareStatement("update employee_master set name=?,department=? where employee_id=?  and companyId=?");
			pst.setString(1, employee.getName());
			pst.setString(2, employee.getDepartment());
			pst.setInt(3, employee.getId());
			pst.setInt(4, employee.getCompanyId());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(con);
		}
	}

}
