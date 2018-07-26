package com.yidu.d257.dao.base;

import java.util.List;
import java.util.Map;

import com.yidu.d257.domain.base.Replace;
import com.yidu.d257.domain.base.ReplaceOV;

public interface ReplaceDao {

	public List<Replace> findAllReplaceWithPage(Map<String,Object> paramMap);
	
	public int addReplace(Replace replace);
	
	public int countReplace();

	public List<Replace> findReplaceByNameWithPage(Map<String,Object> paramMap);
	
	public int countFindReplaceByNameWithPage(String name);
	
	public int freezeOrActivateReplace(Map<String,String> paramMap);
	
	public int deleteReplace(String replaceId);
	
	public List<Replace> findReplaceByCriteriaWithPage(ReplaceOV replaceOV);
	
	public int countReplaceByCriteriaWithPage(ReplaceOV replaceOV);
}
