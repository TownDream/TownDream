package com.yidu.d257.service.sys;

import java.util.List;

import com.yidu.d257.domain.sys.Organization;

public interface OrganizationService {

	public List<Organization> findAllOrganizationNameWithUseable();
	
	public String findOrganizationIdByOrganizationName(String organizationName);
}
