package com.socialnetwork.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.socialnetwork.spring.dao.PersonDAO;
import com.socialnetwork.spring.model.Person;

@Service
public class PersonServiceImpl implements PersonService {

	private PersonDAO personDAO;

	public void setPersonDAO(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	@Override
	@Transactional
	public Person addPerson(Person p) {
		Person savedPerson= this.personDAO.addPerson(p);
		
		return savedPerson;
	}

	@Override
	@Transactional
	public void updatePerson(Person p) {
		this.personDAO.updatePerson(p);
	}

	@Override
	@Transactional
	public List<Person> listPersons() {
		return this.personDAO.listPersons();
	}

	@Override
	@Transactional
	public Person getPersonById(int id) {
		return this.personDAO.getPersonById(id);
	}

	@Override
	@Transactional
	public void removePerson(int id) {
		this.personDAO.removePerson(id);
	}
	
	
	@Override
    public Person getPersonByEmail(Person p) {
        return this.personDAO.getPersonByEmail(p);
    }

	@Override
	public String getPersonByEmail2(Person p) {
		return this.personDAO.getPersonByEmail2(p);
		 
	}
	
	@Override
	public boolean addFriend(long id1, long id2) {

	return this.personDAO.addFriend(id1, id2);
	}

	@Override
	public boolean removeFriend(long id1, long id2) {
	return this.removeFriend(id1, id2);
	}

}
