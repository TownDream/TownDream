package com.yidu.d257.service.sys.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yidu.d257.dao.sys.OrganizationDao;
import com.yidu.d257.domain.sys.Organization;
import com.yidu.d257.service.sys.OrganizationService;

@Service
public class OrganizationServiceImpl implements OrganizationService{

	@Autowired
	private OrganizationDao organizationDao;
	
	public OrganizationServiceImpl() {
		super();
	}
	
	public OrganizationServiceImpl(OrganizationDao organizationDao) {
		super();
		this.organizationDao = organizationDao;
	}

	public OrganizationDao getOrganizationDao() {
		return organizationDao;
	}

	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

	public List<Organization> findAllOrganizationNameWithUseable() {
		return organizationDao.findAllOrganizationNameWithUseable();
	}

	public String findOrganizationIdByOrganizationName(String organizationName) {
		return organizationDao.findOrganizationIdByOrganizationName(organizationName);
	}

}
