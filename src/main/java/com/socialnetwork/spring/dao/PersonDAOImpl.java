package com.socialnetwork.spring.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.socialnetwork.spring.model.Person;

@Repository
public class PersonDAOImpl implements PersonDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public Person addPerson(Person p) {
		Session session = this.sessionFactory.getCurrentSession();
		System.out.println("Person saved successfully, Person Details="+p);
		 
		session.save(p);
		System.out.println(p.getId());
		System.out.println(p);
		session.close();
		return p;	
	}

	@Override
	public void updatePerson(Person p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
		session.close();
		logger.info("Person updated successfully, Person Details="+p);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> listPersons() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Person> personsList = session.createQuery("from Person").list();
		for(Person p : personsList){
			logger.info("Person List::"+p);
		}
		session.close();
		return personsList;
	}

	@Override
	public Person getPersonById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Person p = (Person) session.load(Person.class, new Integer(id));
		logger.info("Person loaded successfully, Person details="+p);
		session.close();
		return p;
	}

	@Override
	public void removePerson(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Person p = (Person) session.load(Person.class, new Integer(id));
		if(null != p){
			session.delete(p);
		}
		session.close();
		logger.info("Person deleted successfully, person details="+p);
	}

	
	@Override
    public Person getPersonByEmail(Person p) {
        Session session = this.sessionFactory.getCurrentSession();        
        Person p1 = (Person) session.get(Person.class, p.getEmail() );
        if(p1 != null){
            logger.info("Person already exist");
            return p1;
        }
        else
            return null;
        
    }

	@Override
	public String getPersonByEmail2(Person p) {
  //      Session session = this.sessionFactory.getCurrentSession();     
        Session session = this.sessionFactory.openSession();        


		String hql="SELECT E.email from Person E where E.email=:email";
		Query query= session.createQuery(hql);
		query.setParameter("email",p.getEmail() );
		
		List list=query.list();
		
		String emailId=null;
		
		for(Object obj:list){
			if(obj!=null){
				emailId=(String)obj;
				break;
			}
		}
		
		System.out.println("values returned"+emailId);
		
		
		return emailId; 
		
		
	}
	
	@Override
	public boolean addFriend(long id1, long id2) {
	// TODO Auto-generated method stub
	Session session=this.sessionFactory.openSession();
	int i;
	String hql="select E.Friend_ID from PERSON_FRIEND E where E.Person_ID=id1";
	Query query=session.createQuery(hql);
	List list=query.list();

	for(i=0;i<list.size();i++){
	if (id2==((Long)list.get(i)).longValue()){
	return false;
	}
	}

	Person p1,p2;
	Query q=session.createQuery("from Person as E where E.id=id1");
	List l1=q.list();

	Query q2=session.createQuery("from Person as E where E.id=id2");
	List l2=q2.list();

	p1=(Person)(l1.get(0));
	p2=(Person)(l2.get(0));

	p1.getFriends().add(p2);

	session.save(p1);
	return true;
	}

	@Override
	public boolean removeFriend(long id1, long id2) {
	// TODO Auto-generated method stub
	Session session=this.sessionFactory.openSession();
	int i;
	String hql="select E.Friend_ID from PERSON_FRIEND E where E.Person_ID=id1";
	Query query=session.createQuery(hql);
	List list=query.list();

	int flag=0;
	for(i=0;i<list.size();i++){
	if (id2==((Long)list.get(i)).longValue()){
	flag=1;
	}
	}

	if (flag!=1){
	return false;
	}
	else
	{
	Person p1,p2;
	Query q=session.createQuery("from Person as E where E.id=id1");
	List l1=q.list();

	Query q2=session.createQuery("from Person as E where E.id=id2");
	List l2=q2.list();

	p1=(Person)(l1.get(0));
	p2=(Person)(l2.get(0));

	p1.getFriends().remove(p2);

	session.save(p1);
	return true;
	}

	}
	
	
}
