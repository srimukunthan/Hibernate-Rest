package com.mukunth.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import com.mukunth.exceptions.ResourceException;
import com.mukunth.main.HibernateUtil;
import com.mukunth.model.Company;

public class CompanyDaoImpl implements CompanyDao {
	
	private static final CompanyDao companyDao = new CompanyDaoImpl();

	public static CompanyDao getInstance() {
		return companyDao;
	}
	
	private CompanyDaoImpl() {
		
	}
	
	@Override
	public List<Company> getCompany() {
		List<Company> companyList = new ArrayList<Company>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			 
			session.beginTransaction();
			
			Criteria cr = session.createCriteria(Company.class)
				    .setProjection(Projections.projectionList()
				      .add(Projections.property("id"), "id")
				      .add(Projections.property("companyName"), "companyName")
				      .add(Projections.property("hrPerson"), "hrPerson")
				      .add(Projections.property("contactNumber"), "contactNumber"))
				    .setResultTransformer(Transformers.aliasToBean(Company.class));
			
			companyList = cr.list();
			session.getTransaction().commit();
			}catch(Exception e){
				e.printStackTrace();
			}finally {
		         session.close(); 
		      }
		return companyList;
	}

	@Override
	public Company getCompanyByID(int id) throws ResourceException {
		
		Company company = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		session.beginTransaction();
		company = (Company) session.get(Company.class, id);
		System.out.println(company.toString());
		session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
	         session.close(); 
	      }
		return company;
	}

	@Override
	public void deleteCompanyByID(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			 
			session.beginTransaction();
			/*Company company = null;
			company = (Company) session.get(Company.class, id);
			session.delete(company);*/
			Query qry = session.createQuery("delete from Company c where c.id=:cmpny_id");
			qry.setParameter("cmpny_id",id);
			qry.executeUpdate();
			session.getTransaction().commit();
			}catch(Exception e){
				e.printStackTrace();
			}finally {
		         session.close(); 
		      }
	}

	@Override
	public void createCompanyByID(Company company) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			session.save(company);

			System.out.println(company.toString());
			session.getTransaction().commit();
			}catch(Exception e){
				e.printStackTrace();
			}finally {
		         session.close(); 
		      }
	}

	@Override
	public void updateCompanyByID(Company company) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			 
			session.beginTransaction();
			session.update(company);

			System.out.println(company.toString());
			session.getTransaction().commit();
			}catch(Exception e){
				e.printStackTrace();
			}finally {
		         session.close(); 
		      }
	}

}
