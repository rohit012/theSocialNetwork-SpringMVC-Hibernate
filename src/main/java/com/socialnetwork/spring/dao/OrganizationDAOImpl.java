package com.socialnetwork.spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.socialnetwork.spring.model.Organization;
import com.socialnetwork.spring.model.Person;

/*
 * 
 */
@Repository
public class OrganizationDAOImpl implements OrganizationDao {
//	private static final Logger logger = LoggerFactory.getLogger(OrganizationDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	/*New organization object is added into the organization table in the database
	 * And Organization object is returned back
	 * @param - Organization Org
	 * @return - org object
	 * (non-Javadoc)
	 * @see com.socialnetwork.spring.dao.OrganizationDao#addOrg(com.socialnetwork.spring.model.Organization)
	 */
	@Override
	public Organization addOrg(Organization org) {
		System.out.println("org in dao is"+org);
		Session session = this.sessionFactory.openSession();
		session.save(org);
		System.out.println("Organization saved successfully, Organization Details="+org);
		return org;
		
	}

	
	/* organization object is updated into the organization table in the database
	 * And Organization object is returned back
	 * @param - Organization Org
	 * @return - org object
	 * (non-Javadoc)
	 * @see com.socialnetwork.spring.dao.OrganizationDao#addOrg(com.socialnetwork.spring.model.Organization)
	 */
	
	@Override
	public void updateOrg(Organization org) {
		Session session = this.sessionFactory.openSession();
		Organization currentOrg=(Organization)session.get(Organization.class,org.getId());
		currentOrg.setName(org.getName());
		session.update(org);
		System.out.println("Organization updated successfully, Organization Details="+org);
		
	}

	
	/*
	 * And Organization object is returned back
	 * @param - Organization Org
	 * @return - org object
	 * (non-Javadoc)
	 * @see com.socialnetwork.spring.dao.OrganizationDao#addOrg(com.socialnetwork.spring.model.Organization)
	 */
	
	@Override
	public Organization getOrgById(Long id) {
		Session session = this.sessionFactory.openSession();	
		Organization org = (Organization) session.load(Organization.class, id);
		System.out.println("Organization loaded successfully, Organization details="+org);
		return org;
	}

	/* organization object is removed from the organization table in the database
	 * And Organization object is returned back
	 * @param - Organization Org
	 * @return - org object
	 * (non-Javadoc)
	 * @see com.socialnetwork.spring.dao.OrganizationDao#addOrg(com.socialnetwork.spring.model.Organization)
	 */
	
	@Override
	public void removeOrg(Long id) {
		Session session = this.sessionFactory.openSession();
		Organization org = (Organization) session.load(Organization.class, new Long(id));
		if(null != org){
			session.delete(org);
		}
		System.out.println("Organization deleted successfully, organization details="+org);
	}

}
