package com.socialnetwork.spring.service;

import org.springframework.stereotype.Service;

import com.socialnetwork.spring.model.Organization;

@Service
public interface OrganizationService {

	public Organization addOrg(Organization p);

	public void updateOrg(Organization p);
	
	public Organization getOrgById(Long id);

	public void removeOrg(int id);
}
