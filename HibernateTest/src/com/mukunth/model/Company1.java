package com.mukunth.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;




public class Company1 {
	String companyName;
	String hrPerson;
	@Min(10)
	int contactNumber;
	
	
	public Company1() {
		super();
	}



	public Company1(String companyName, String hrPerson, int contactNumber) {
		super();
		this.companyName = companyName;
		this.hrPerson = hrPerson;
		this.contactNumber = contactNumber;
	}



	public String getCompanyName() {
		return companyName;
	}



	public String getHrPerson() {
		return hrPerson;
	}



	public int getContactNumber() {
		return contactNumber;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}



	public void setHrPerson(String hrPerson) {
		this.hrPerson = hrPerson;
	}



	public void setContactNumber(int contactNumber) {
		this.contactNumber = contactNumber;
	}



	@Override
	public String toString() {
		return "Company [companyName=" + companyName + ", hrPerson=" + hrPerson
				+ ", contactNumber=" + contactNumber + "]";
	}

	
}
