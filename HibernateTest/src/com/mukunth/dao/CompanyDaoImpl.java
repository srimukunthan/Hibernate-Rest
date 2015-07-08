package com.mukunth.dao;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.server.Authentication.User;
import org.hibernate.Criteria;
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
		/*Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Company> companyList = new ArrayList<Company>();
		con = (Connection) ConnectionManager.getConnection();
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from company_master");
			while(rs.next()) {
			Company company = new Company(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4));
			companyList.add(company);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(con);
		}
		return companyList;*/
		List<Company> companyList = new ArrayList<Company>();
		try{
			Session session = HibernateUtil.getSessionFactory().openSession();
			 
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
			}
		return companyList;
	}

	@Override
	public Company getCompanyByID(int id) throws ResourceException {
		/*Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		con = (Connection) ConnectionManager.getConnection();
		Company company = null;
		try {
			pst = (PreparedStatement) con.prepareStatement("select * from company_master where id=?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				company = new Company(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4));
			}
			if(company == null) {
				throw new ResourceException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(con);
		}*/
		Company company = null;
		try{
		Session session = HibernateUtil.getSessionFactory().openSession();
		 
		session.beginTransaction();
		
		company = (Company) session.get(Company.class, id);
		System.out.println(company.toString());
		session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}
		return company;
	}

	@Override
	public void deleteCompanyByID(int id) {
		/*Connection con = null;
		PreparedStatement pst = null;
		con = (Connection) ConnectionManager.getConnection();
		try {
			pst = (PreparedStatement) con.prepareStatement("delete from company_master where id=?");
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(con);
		}*/
		try{
			Session session = HibernateUtil.getSessionFactory().openSession();
			 
			session.beginTransaction();
			Company company = null;
			company = (Company) session.get(Company.class, id);
			session.delete(company);
			session.getTransaction().commit();
			}catch(Exception e){
				e.printStackTrace();
			}
	}

	@Override
	public void createCompanyByID(Company company) {
		/*Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		con = (Connection) ConnectionManager.getConnection();
		try {
			pst = (PreparedStatement) con.prepareStatement("select * from company_master where name=?");
			pst.setString(1, company.getCompanyName());
			rs = pst.executeQuery();
			if(rs.next()) {
				//throw new ConflictException();
			}
			pst = (PreparedStatement) con.prepareStatement("insert into company_master (name,hr,phone) values (?,?,?)");
			pst.setString(1, company.getCompanyName());
			pst.setString(2, company.gethrPerson());
			pst.setString(3, company.getContactNumber());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(con);
		}*/
		
		try{
			Session session = HibernateUtil.getSessionFactory().openSession();
			 
			session.beginTransaction();
			session.save(company);

			System.out.println(company.toString());
			session.getTransaction().commit();
			}catch(Exception e){
				e.printStackTrace();
			}
	}

	@Override
	public void updateCompanyByID(Company company) {
		/*Connection con = null;
		PreparedStatement pst = null;
		con = (Connection) ConnectionManager.getConnection();
		try {
			pst = (PreparedStatement) con.prepareStatement("update company_master set name=?,hr=?,phone=? where id=? ");
			pst.setString(1, company.getCompanyName());
			pst.setString(2, company.gethrPerson());
			pst.setString(3, company.getContactNumber());
			pst.setInt(4, company.getId());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(con);
		}*/
		try{
			Session session = HibernateUtil.getSessionFactory().openSession();
			 
			session.beginTransaction();
			session.update(company);

			System.out.println(company.toString());
			session.getTransaction().commit();
			}catch(Exception e){
				e.printStackTrace();
			}
	}

}
