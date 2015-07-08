package com.mukunth.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.mukunth.exceptions.ResourceException;
import com.mukunth.general.ConnectionManager;
import com.mukunth.main.HibernateUtil;
import com.mukunth.model.Company;
import com.mukunth.model.Employee;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class EmployeeDaoImpl implements EmployeeDao {
	
	private static final EmployeeDao employeeDao = new EmployeeDaoImpl();

	public static EmployeeDao getInstance() {
		return employeeDao;
	}
	
	private EmployeeDaoImpl() {
		
	}
	
	@Override
	public List<Employee> getEmployee(int companyID) {
		/*Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Employee> employeeList = new ArrayList<Employee>();
		con = (Connection) ConnectionManager.getConnection();
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from employee_master where companyID="+companyID);
			while(rs.next()) {
				Employee employee = new Employee(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4));
				employeeList.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(con);
		}
		return employeeList;*/
		
		List<Employee> employeeList = new ArrayList<Employee>();
		try{
			Session session = HibernateUtil.getSessionFactory().openSession();
			 
			session.beginTransaction();
			
			employeeList = session.createCriteria(Employee.class).list();
			session.getTransaction().commit();
			}catch(Exception e){
				e.printStackTrace();
			}
		return employeeList;
	}

	@Override
	public Employee getEmployeeByID(int id) throws ResourceException {
		/*Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		con = (Connection) ConnectionManager.getConnection();
		Employee employee = null;
		try {
			pst = (PreparedStatement) con.prepareStatement("select * from employee_master where employee_id=? and companyId=?");
			pst.setInt(1, id);
			pst.setInt(2, companyId);
			rs = pst.executeQuery();
			while(rs.next()) {
				employee = new Employee(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4));
			}
			if(employee == null) {
				throw new ResourceException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(con);
		}
		*/
		
		Employee employee = null;
		try{
		Session session = HibernateUtil.getSessionFactory().openSession();
		 
		session.beginTransaction();
		
		employee = (Employee) session.get(Employee.class, id);
		System.out.println(employee.toString());
		session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}
		return employee;
	}

	@Override
	public void deleteEmployeeByID(int id) {
		/*Connection con = null;
		PreparedStatement pst = null;
		con = (Connection) ConnectionManager.getConnection();
		try {
			pst = (PreparedStatement) con.prepareStatement("delete from employee_master where employee_id=? and companyId=?");
			pst.setInt(1, id);
			pst.setInt(2, companyId);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(con);
		}*/
		try{
			Session session = HibernateUtil.getSessionFactory().openSession();
			 
			session.beginTransaction();
			Employee employee = null;
			employee = (Employee) session.get(Employee.class, id);
			session.delete(employee);
			session.getTransaction().commit();
			}catch(Exception e){
				e.printStackTrace();
			}
	}

	@Override
	public void createEmployeeByID(Employee employee, int id) {
		/*Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		con = (Connection) ConnectionManager.getConnection();
		try {
			pst = (PreparedStatement) con.prepareStatement("select * from employee_master where name=?");
			pst.setString(1, employee.getName());
			rs = pst.executeQuery();
			if(rs.next()) {
			}
			pst = (PreparedStatement) con.prepareStatement("insert into employee_master (name,department,companyId) values (?,?,?)");
			pst.setString(1, employee.getName());
			pst.setString(2, employee.getDepartment());
			pst.setInt(3, employee.getCompanyId());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(con);
		}*/
		try{
			Session session = HibernateUtil.getSessionFactory().openSession();
			 
			session.beginTransaction();
			System.out.println(employee.toString()+"----------");
			//employee.setCompany((Company) session.get(Company.class, id));
			session.saveOrUpdate(employee);
			session.getTransaction().commit();
			}catch(Exception e){
				e.printStackTrace();
			}
	}

	@Override
	public void updateEmployeeByID(Employee employee, int id) {
		/*Connection con = null;
		PreparedStatement pst = null;
		con = (Connection) ConnectionManager.getConnection();
		try {
			pst = (PreparedStatement) con.prepareStatement("update employee_master set name=?,department=? where employee_id=?  and companyId=?");
			pst.setString(1, employee.getName());
			pst.setString(2, employee.getDepartment());
			pst.setInt(3, employee.getId());
			pst.setInt(4, employee.getCompanyId());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(con);
		}*/
		try{
			Session session = HibernateUtil.getSessionFactory().openSession();
			 
			session.beginTransaction();
		//	employee.setCompany((Company) session.get(Company.class, id));
			session.saveOrUpdate(employee);

			System.out.println(employee.toString());
			session.getTransaction().commit();
			}catch(Exception e){
				e.printStackTrace();
			}
	}

}
