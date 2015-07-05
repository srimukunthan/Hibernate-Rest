package com.mukunth.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Length;

@Entity
public class Organization {

	
	
	public Organization() {
		super();
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	int id;
	@Length(min=10)
	String companyName;
	String hrPerson;
	String contactNumber;
	public int getId() {
		return id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public String getHrPerson() {
		return hrPerson;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public void setHrPerson(String hrPerson) {
		this.hrPerson = hrPerson;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	
	
}
