package com.mukunth.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Length;

@Entity
public class Company {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	int id;
	@Length(min=10)
	String companyName;
	String hrPerson;
	String contactNumber;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "company_emp_map", joinColumns = @JoinColumn(name = "cmpny_id"), 
	inverseJoinColumns = @JoinColumn(name = "emp_id"))
	private Set<Employee> employee = new HashSet<Employee>();
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
	public Set<Employee> getEmployee() {
		return employee;
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
	public void setEmployee(Set<Employee> employee) {
		this.employee = employee;
	}
	@Override
	public String toString() {
		return "Company [id=" + id + ", companyName=" + companyName
				+ ", hrPerson=" + hrPerson + ", contactNumber=" + contactNumber
				+ ", employee=" + employee + "]";
	}
	public Company(int id, String companyName, String hrPerson,
			String contactNumber) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.hrPerson = hrPerson;
		this.contactNumber = contactNumber;
	}
	public Company() {
		super();
	}
	
	
}
