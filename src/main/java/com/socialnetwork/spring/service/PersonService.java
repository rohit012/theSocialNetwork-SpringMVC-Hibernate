package com.socialnetwork.spring.service;

import java.util.List;

import com.socialnetwork.spring.model.Person;

public interface PersonService {

	public Person addPerson(Person p);

	public void updatePerson(Person p);

	public List<Person> listPersons();

	public Person getPersonById(int id);

	public void removePerson(int id);
	
	public Person getPersonByEmail(Person p);
	
	public String getPersonByEmail2(Person p);

	public boolean addFriend(long id1,long id2);
	public boolean removeFriend(long id1,long id2);

}
