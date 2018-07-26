package com.yidu.d257.service.base;

import java.util.List;
import java.util.Map;

import com.yidu.d257.domain.base.PickStandard;

public interface PickStandardService {

	public List<PickStandard> findAllPickStandardWithPage(int pageIndex,int pageSize);
	
	public List<PickStandard> findAllPSNameAndPSIdWithUseable();
	
	public int countPickStandard();
}
