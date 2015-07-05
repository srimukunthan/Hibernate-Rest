package com.mukunth.dao;

import java.sql.SQLException;
import java.util.List;

import com.mukunth.exceptions.ResourceException;
import com.mukunth.model.Company;

public interface CompanyDao {
	
	List<Company> getCompany();
	
	Company getCompanyByID(int id) throws ResourceException;
	
	void deleteCompanyByID(int id);

	void createCompanyByID(Company company) throws SQLException;

	void updateCompanyByID(Company company);

}
 