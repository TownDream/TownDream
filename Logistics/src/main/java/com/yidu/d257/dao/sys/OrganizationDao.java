package com.yidu.d257.dao.sys;

import java.util.List;
import java.util.Map;

import com.yidu.d257.domain.base.PickStandard;
import com.yidu.d257.domain.sys.Organization;

public interface OrganizationDao {
	
	public List<Organization> findAllOrganizationNameWithUseable();
	
	public String findOrganizationIdByOrganizationName(String organizationName);
	
	public String findConnectPhoneByOrganizationId(String organizationId);
}
