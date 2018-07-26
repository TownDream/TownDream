package com.yidu.d257.web.controller.sys;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yidu.d257.domain.sys.Organization;
import com.yidu.d257.service.sys.OrganizationService;

@Controller
@RequestMapping("/sys/organization")
public class OrganizationController {

	@Autowired
	private OrganizationService organizationService;
	
	public OrganizationController() {
		super();
	}

	public OrganizationController(OrganizationService organizationService) {
		super();
		this.organizationService = organizationService;
	}

	public OrganizationService getOrganizationService() {
		return organizationService;
	}

	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}

	@ResponseBody
	@RequestMapping(value="/findAllOrganizationNameWithUseable.action",method={RequestMethod.POST})
	private List<Organization> doFindAllOrganizationNameWithUseable(){
		List<Organization> oList = organizationService.findAllOrganizationNameWithUseable();
		return organizationService.findAllOrganizationNameWithUseable();
	}
	
	@ResponseBody
	@RequestMapping(value="/findOrganizationIdByOrganizationName.action",method={RequestMethod.POST})
	private List<String> doFindOrganizationIdByOrganizationName(String organizationName){
		List<String> resultList = new ArrayList<String>();
		resultList.add(organizationService.findOrganizationIdByOrganizationName(organizationName));
		return resultList;
	}
}
