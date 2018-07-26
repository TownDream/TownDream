package com.yidu.d257.service.base.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yidu.d257.dao.base.DispatcherDao;
import com.yidu.d257.dao.base.ReplaceDao;
import com.yidu.d257.domain.base.Dispatcher;
import com.yidu.d257.domain.base.Replace;
import com.yidu.d257.domain.base.ReplaceOV;
import com.yidu.d257.service.base.ReplaceService;
import com.yidu.d257.util.MyUtil;

@Service
public class ReplaceServiceImpl implements ReplaceService {

	@Autowired
	private ReplaceDao replaceDao;
	
	@Autowired
	private DispatcherDao dispatcherDao;
	
	public ReplaceServiceImpl() {
		super();
	}

	public ReplaceServiceImpl(ReplaceDao replaceDao) {
		super();
		this.replaceDao = replaceDao;
	}
	
	public ReplaceDao getReplaceDao() {
		return replaceDao;
	}

	public void setReplaceDao(ReplaceDao replaceDao) {
		this.replaceDao = replaceDao;
	}

	public List<Replace> findAllReplaceWithPage(int pageIndex,int pageSize) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("start", (pageIndex-1)*pageSize);
		paramMap.put("end", pageIndex*pageSize);
		return replaceDao.findAllReplaceWithPage(paramMap);
	}

	public int addReplace(ReplaceOV replaceOV) {
		return replaceDao.addReplace(replcaceOVToReplace(replaceOV));
	}

	public int countReplace() {
		return replaceDao.countReplace();
	}

	public int freezeOrActivateReplace(Map<String, String> paramMap) {
		return replaceDao.freezeOrActivateReplace(paramMap);
	}

	public boolean updateReplace(ReplaceOV replaceOV,String replaceId) {
		Replace replace = replcaceOVToReplace(replaceOV);
		replace.setReplaceId(replaceId);
		return replaceDao.deleteReplace(replaceId)!=0 && replaceDao.addReplace(replace)!=0 ;
	}
	
	public Map<String,Object> findReplaceByNameWithPage(String name,int pageIndex,int pageSize ){
		Map<String, Object> paramMap = madeParamMap(pageIndex, pageSize);
		paramMap.put("name", name);
		List<Replace> replaceList = replaceDao.findReplaceByNameWithPage(paramMap);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("total", replaceDao.countFindReplaceByNameWithPage(name));
		resultMap.put("rows", replaceList);
		return resultMap;
	}
	
	public Map<String,Object> findReplaceByCriteriaWithPage(ReplaceOV replaceOV){
		List<Replace> replaceList = replaceDao.findReplaceByCriteriaWithPage(replaceOV);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("total", replaceDao.countReplaceByCriteriaWithPage(replaceOV));
		resultMap.put("rows", replaceList);
		return resultMap;
	}
	
	public Map<String,Object> madeParamMap(int pageIndex,int pageSize){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("start", (pageIndex-1)*pageSize);
		paramMap.put("end", pageIndex*pageSize);
		return paramMap;
	}
	
	public Replace replcaceOVToReplace(ReplaceOV replaceOV){
		Replace replace = new Replace();
		replace.setReplaceId(MyUtil.getUUID());
		replace.setReplaceManId(dispatcherDao.findDispatcherByDispatcherNo(replaceOV.getrDispatcherNo()).getDispatcherId());
		replace.setReplaceName(replaceOV.getReplaceName());
		replace.setReplacedId(dispatcherDao.findDispatcherByDispatcherNo(replaceOV.getRdDispatcherNo()).getDispatcherId());
		replace.setReplacedName(replaceOV.getReplacedName());
		replace.setStartTime(replaceOV.getStartTime());
		replace.setEndTime(replaceOV.getEndTime());
		replace.setOrganizationId(replaceOV.getOrganizationId());
		replace.setOrganizationName(replaceOV.getOrganizationName());
		replace.setUseable(replaceOV.getUseable());
		replace.setDescription(replaceOV.getDescription());
		return replace;
	}
}
