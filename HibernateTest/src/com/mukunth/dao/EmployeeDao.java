package com.mukunth.dao;

import java.util.List;

import com.mukunth.exceptions.ResourceException;
import com.mukunth.model.Employee;

public interface EmployeeDao {
	
	List<Employee> getEmployee(int companyId);
	
	void deleteEmployeeByID(int employeeId);

	void createEmployeeByID(Employee Employee, int companyId);

	void updateEmployeeByID(Employee Employee, int companyId);

	Employee getEmployeeByID(int employeeId) throws ResourceException;

}
 