package com.yidu.d257.service.base;

import java.util.List;
import java.util.Map;

import com.yidu.d257.domain.base.Replace;
import com.yidu.d257.domain.base.ReplaceOV;

public interface ReplaceService {

	public List<Replace> findAllReplaceWithPage(int pageIndex,int pageSize);
	
	public int countReplace();
	
	public int addReplace(ReplaceOV replaceOV);
	
	public Replace replcaceOVToReplace(ReplaceOV replaceOV);
	
	public int freezeOrActivateReplace(Map<String,String> paramMap);
	
	public boolean updateReplace(ReplaceOV replaceOV,String replaceId);
	
	public Map<String, Object> findReplaceByNameWithPage(String name,int pageIndex,int pageSize );
	
	public Map<String, Object> findReplaceByCriteriaWithPage(ReplaceOV replaceOV);
}
