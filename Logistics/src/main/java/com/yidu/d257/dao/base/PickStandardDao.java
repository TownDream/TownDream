package com.yidu.d257.dao.base;

import java.util.List;
import java.util.Map;

import com.yidu.d257.domain.base.PickStandard;

public interface PickStandardDao {

	public List<PickStandard> findAllPickStandardWithPage(Map<String, Object> paramMap);
	
	public List<PickStandard> findAllPSNameAndPSIdWithUseable();
	
	public int countPickStandard();
}
