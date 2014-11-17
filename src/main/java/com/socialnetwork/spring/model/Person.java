package com.socialnetwork.spring.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 * @author rohit
 *
 */
@Entity
@Table(name="PERSON")
public class Person implements Serializable{

	@Id
	@Column(name="Person_Id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public Set<Person> getFriends() {
		return friends;
	}

	public void setFriends(Set<Person> friends) {
		this.friends = friends;
	}

	private String name;
	
	private String country;
	
	@Column(name = "FirstName")
	private String firstname;
	
	@Column(name = "LastName")
    private String lastname;
	
	 @Column(name = "Email")
    private String email;
	 
	@Column(name = "Description" )
    private String description;
	
	@Embedded
    private Address address;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="Org_Id")
    private Organization org;
	
	/*@OneToMany
    @JoinTable(name="Person_Friendship", joinColumns = @JoinColumn(name="Person_ID"), inverseJoinColumns = @JoinColumn(name="Friend_ID"))
    private List<Person> friends;*/

	   @ManyToMany(cascade={CascadeType.ALL})
	    @JoinTable(name="PERSON_FRIEND",
	        joinColumns={@JoinColumn(name="Person_Id")},
	        inverseJoinColumns={@JoinColumn(name="Friend_Id")})
	    private Set<Person> friends = new HashSet<Person>();
/*
	    @ManyToMany(mappedBy="Friends")
	    private Set<Person> teammates = new HashSet<Person>();
	*/

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	@Override
	public String toString(){
		return "id="+id+", name="+name+", country="+country;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
