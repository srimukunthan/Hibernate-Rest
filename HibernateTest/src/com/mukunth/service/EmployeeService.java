package com.mukunth.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.mukunth.dao.EmployeeDaoImpl;
import com.mukunth.exceptions.ResourceException;
import com.mukunth.model.Employee;

@Produces(MediaType.APPLICATION_JSON)
public class EmployeeService {
	
	private int companyId;
	
	public EmployeeService(int companyId) {
		this.companyId = companyId;
	}

	@GET
	public Response getEmployeeList() throws NumberFormatException {
		List<Employee> employee = EmployeeDaoImpl.getInstance().getEmployee(companyId);
		return Response.status(200).entity(employee).build();
	}
	
	@GET
	@Path("{employeeId}")
	public Response getEmployee(@PathParam("employeeId") int employeeId) throws ResourceException {
		Employee employee = EmployeeDaoImpl.getInstance().getEmployeeByID(employeeId, companyId);
	return Response.status(200).entity(employee).build();
	}
	
	@DELETE
	@Path("{employeeId}")
	public Response deleteEmployee(@PathParam("employeeId") int employeeId) throws ResourceException {
		getEmployee(employeeId);
		EmployeeDaoImpl.getInstance().deleteEmployeeByID(employeeId, companyId);
			return Response.status(204).build();
	}	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createEmployee(Employee Employee) {
		Employee.setCompanyId(companyId);
		EmployeeDaoImpl.getInstance().createEmployeeByID(Employee);
			return Response.status(201).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{employeeId}")
	public Response updateEmployee(Employee employee, @PathParam("employeeId") int employeeId) throws ResourceException {
		getEmployee(employeeId);
		employee.setId(employeeId);
		employee.setCompanyId(companyId);
		EmployeeDaoImpl.getInstance().updateEmployeeByID(employee, companyId);
			return Response.status(204).build();
	}

}
