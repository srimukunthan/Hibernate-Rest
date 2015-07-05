package com.mukunth.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Length;

@Entity
public class Company {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	int id;
	@Length(min=10)
	String companyName;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "Company")
	String hrPerson;
	String contactNumber;
	
	public Company(int id, String companyName, String hrPerson, String contactNumber) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.hrPerson = hrPerson;
		this.contactNumber = contactNumber;
	}
	
	public Company() {
		super();
	}
	public int getId() {
		return id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public String gethrPerson() {
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
	public void sethrPerson(String hrPerson) {
		this.hrPerson = hrPerson;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", companyName=" + companyName
				+ ", hrPerson=" + hrPerson + ", contactNumber=" + contactNumber
				+ "]";
	}
	
}
