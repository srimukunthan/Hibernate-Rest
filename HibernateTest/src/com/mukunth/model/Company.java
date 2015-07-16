package com.mukunth.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Company {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	int id;
	@Length(min=10)
	String companyName;
	String hrPerson;
	String contactNumber;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="company")
	/*@JoinTable(name = "company_emp_map", joinColumns = @JoinColumn(name = "cmpny_id"), 
	inverseJoinColumns = @JoinColumn(name = "emp_id"))*/
	@JsonManagedReference
	private Collection<Employee> employee = new HashSet<Employee>();
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

	public Collection<Employee> getEmployee() {
		return employee;
	}
	public void setEmployee(Collection<Employee> employee) {
		this.employee = employee;
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
	
	public void addEmployee(Employee emp) {
		if (employee==null) {
			employee = new ArrayList<Employee>();
		}
		if (!employee.contains(emp)) {
			employee.add(emp);
		}
	}
	
	
}
