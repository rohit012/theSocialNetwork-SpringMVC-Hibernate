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


}
