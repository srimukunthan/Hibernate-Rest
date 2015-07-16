package com.mukunth.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import com.mukunth.exceptions.ResourceException;
import com.mukunth.main.HibernateUtil;
import com.mukunth.model.Company;
import com.mukunth.model.Employee;

public class EmployeeDaoImpl implements EmployeeDao {
	
	private static final EmployeeDao employeeDao = new EmployeeDaoImpl();

	public static EmployeeDao getInstance() {
		return employeeDao;
	}
	
	private EmployeeDaoImpl() {
		
	}
	
	@Override
	public List<Employee> getEmployee(int companyID) {
		
		List<Employee> employeeList = new ArrayList<Employee>();
		
			Session session = HibernateUtil.getSessionFactory().openSession();
			try{
			session.beginTransaction();
			
			employeeList = session.createCriteria(Employee.class).list();
			session.getTransaction().commit();
			}catch(Exception e){
				e.printStackTrace();
			}finally {
		         session.close(); 
		      }
		return employeeList;
	}

	@Override
	public Employee getEmployeeByID(int id) throws ResourceException {
		
		Employee employee = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		session.beginTransaction();
		
		employee = (Employee) session.get(Employee.class, id);
		System.out.println(employee.toString());
		session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
	         session.close(); 
	      }
		return employee;
	}

	@Override
	public void deleteEmployeeByID(int id) {
		
			Session session = HibernateUtil.getSessionFactory().openSession();
			try{
			session.beginTransaction();
			/*Employee employee = null;
			employee = (Employee) session.get(Employee.class, id);
			session.delete(employee);*/
			Query qry = session.createQuery("delete from Employee e where e.id=:emp_id");
			qry.setParameter("emp_id",id);
			qry.executeUpdate();
			session.getTransaction().commit();
			}catch(Exception e){
				e.printStackTrace();
			}finally {
		         session.close(); 
		      }
	}

	@Override
	public void createEmployeeByID(Employee employee, int id) {
		
			Session session = HibernateUtil.getSessionFactory().openSession();
			try{
			session.beginTransaction();
			employee.setCompany((Company) session.get(Company.class, id));

			session.save(employee);
			session.getTransaction().commit();
			}catch(Exception e){
				e.printStackTrace();
			}finally {
		         session.close(); 
		      }
	}

	@Override
	public void updateEmployeeByID(Employee employee, int id) {
		
			Session session = HibernateUtil.getSessionFactory().openSession();
			try{ 
			session.beginTransaction();
			employee.setCompany((Company) session.get(Company.class, id));
			session.update(employee);

			session.getTransaction().commit();
			}catch(Exception e){
				e.printStackTrace();
			}finally {
		         session.close(); 
		      }
	}

}
