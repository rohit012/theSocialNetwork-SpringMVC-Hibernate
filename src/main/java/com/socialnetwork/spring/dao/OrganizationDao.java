package com.socialnetwork.spring.dao;

import java.util.List;

import com.socialnetwork.spring.model.Organization;
import com.socialnetwork.spring.model.Person;

public interface OrganizationDao {

	public Organization addOrg(Organization p);

	public void updateOrg(Organization p);
	
	public Organization getOrgById(int id);

	public void removeOrg(int id);
}
