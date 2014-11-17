package com.socialnetwork.spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.socialnetwork.spring.model.Organization;
import com.socialnetwork.spring.model.Person;


@Repository
public class OrganizationDAOImpl implements OrganizationDao {
//	private static final Logger logger = LoggerFactory.getLogger(OrganizationDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public Organization addOrg(Organization org) {
		System.out.println("org in dao is"+org);
		Session session = this.sessionFactory.openSession();
		session.save(org);
		System.out.println("Organization saved successfully, Organization Details="+org);
		session.close();
		return org;
		
	}

	@Override
	public void updateOrg(Organization org) {
		Session session = this.sessionFactory.openSession();
		session.update(org);
		session.close();
		System.out.println("Organization updated successfully, Organization Details="+org);
		
	}

	@Override
	public Organization getOrgById(Long id) {
		Session session = this.sessionFactory.openSession();	
		Organization org = (Organization) session.load(Organization.class, id);
		System.out.println("Organization loaded successfully, Organization details="+org);
		session.close();
		return org;
	}

	@Override
	public void removeOrg(int id) {
		Session session = this.sessionFactory.openSession();
		Organization org = (Organization) session.load(Person.class, new Integer(id));
		if(null != org){
			session.delete(org);
		}
		System.out.println("Organization deleted successfully, organization details="+org);
	}

}
