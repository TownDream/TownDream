package com.yidu.d257.service.base.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yidu.d257.dao.base.PickStandardDao;
import com.yidu.d257.domain.base.PickStandard;
import com.yidu.d257.service.base.PickStandardService;

@Service
public class PickStandardServiceImpl implements PickStandardService{

	@Autowired
	private PickStandardDao pickStandardDao;
	
	public PickStandardServiceImpl() {
		super();
	}

	public PickStandardServiceImpl(PickStandardDao pickStandardDao) {
		super();
		this.pickStandardDao = pickStandardDao;
	}

	public PickStandardDao getPickStandardDao() {
		return pickStandardDao;
	}

	public void setPickStandardDao(PickStandardDao pickStandardDao) {
		this.pickStandardDao = pickStandardDao;
	}

	public List<PickStandard> findAllPickStandardWithPage(int pageIndex,int pageSize) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("start", (pageIndex-1)*pageSize);
		paramMap.put("end", pageIndex*pageSize);
		return pickStandardDao.findAllPickStandardWithPage(paramMap);
	}

	public List<PickStandard> findAllPSNameAndPSIdWithUseable() {
		return pickStandardDao.findAllPSNameAndPSIdWithUseable();
	}
	
	public int countPickStandard(){
		return pickStandardDao.countPickStandard();
	}

}
