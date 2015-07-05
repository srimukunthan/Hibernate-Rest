package com.mukunth.dao;

import java.util.List;

import com.mukunth.exceptions.ResourceException;
import com.mukunth.model.Employee;

public interface EmployeeDao {
	
	List<Employee> getEmployee(int companyId);
	
	void deleteEmployeeByID(int employeeId, int companyId);

	void createEmployeeByID(Employee Employee);

	void updateEmployeeByID(Employee Employee, int companyId);

	Employee getEmployeeByID(int employeeId, int companyId) throws ResourceException;

}
 