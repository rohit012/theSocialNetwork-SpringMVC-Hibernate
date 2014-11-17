package com.socialnetwork.spring.dao;

import org.springframework.stereotype.Repository;

import com.socialnetwork.spring.model.Organization;

public interface OrganizationDao {

	public Organization addOrg(Organization p);

	public void updateOrg(Organization p);
	
	public Organization getOrgById(Long id);

	public void removeOrg(int id);
}
