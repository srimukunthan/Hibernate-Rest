package com.mukunth.main;

import org.hibernate.Session;

import com.mukunth.model.Organization;

public class Test {

	
	public static void main(String[] args) {
		 
        System.out.println("Hibernate one to many (Annotation)");
	Session session = HibernateUtil.getSessionFactory().openSession();
 
	session.beginTransaction();
	
	Organization org = new Organization();
	org.setCompanyName("ddddddddddddddddddddddddddddd");
	org.setContactNumber("43");
	org.setHrPerson("fdfdsfdf");
	session.getTransaction().commit();
	System.out.println("Done");
	}
}
