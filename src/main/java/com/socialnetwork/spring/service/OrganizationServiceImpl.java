package com.socialnetwork.spring.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialnetwork.spring.dao.OrganizationDao;
import com.socialnetwork.spring.model.Organization;


@Service
public class OrganizationServiceImpl implements OrganizationService{

	
private OrganizationDao orgDAO;
	
	
	public void setOrgDAO(OrganizationDao orgDAO) {
		this.orgDAO = orgDAO;
	}

	@Override
	public Organization addOrg(Organization org) {
		System.out.println("org in service"+org);
		return this.orgDAO.addOrg(org);
		
	}

	@Override
	public void updateOrg(Organization org) {
		this.orgDAO.updateOrg(org);
		
	}

	@Override
	public Organization getOrgById(Long id) {
		
		return this.orgDAO.getOrgById(id);
	}

	@Override
	public void removeOrg(Long id) {
		this.orgDAO.removeOrg(id);
		
	}
	
}
