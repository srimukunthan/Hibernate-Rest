package com.mukunth.service;

import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;
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

import com.mukunth.dao.CompanyDaoImpl;
import com.mukunth.exceptions.ResourceException;
import com.mukunth.model.Company;

@Path("/companies")
@Produces(MediaType.APPLICATION_JSON)
public class CompanyService {
 
	@GET
	public Response getCompanyList() {
		List<Company> company = CompanyDaoImpl.getInstance().getCompany();
		return Response.status(200).entity(company).build();
	}
 
	@GET
	@Path("{id}")
	public Response getCompany(@PathParam("id") int id) throws ResourceException {
		Company company = CompanyDaoImpl.getInstance().getCompanyByID(id);
	return Response.status(200).entity(company).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteCompany(@PathParam("id") int id) throws ResourceException {
		getCompany(id);
		CompanyDaoImpl.getInstance().deleteCompanyByID(id);
	return Response.status(204).build();
	}	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCompany(@Valid Company company) throws SQLException {
		CompanyDaoImpl.getInstance().createCompanyByID(company);
			List<Company> companies = CompanyDaoImpl.getInstance().getCompany();
			return Response.status(201).entity(companies).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response updateCompany(Company company, @PathParam("id") int id) throws ResourceException {
		getCompany(id);
		company.setId(id);
		CompanyDaoImpl.getInstance().updateCompanyByID(company);
			return Response.status(204).build();
	}
	
	@Path("{companyId}/employee")
	public EmployeeService employee(@PathParam("companyId") int companyId) throws ResourceException {
		getCompany(companyId);
		return new EmployeeService(companyId);
	}
	
} 