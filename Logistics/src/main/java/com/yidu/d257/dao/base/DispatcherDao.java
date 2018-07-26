package com.yidu.d257.dao.base;


import java.util.List;
import java.util.Map;

import com.yidu.d257.domain.base.Dispatcher;
import com.yidu.d257.domain.base.DispatcherOV;

public interface DispatcherDao {

	/**
	 * 查询所有职员
	 * @return 职员集合
	 */
	public List<Dispatcher> findAllDispatcher();
	
	/**
	 * 分页查询职员
	 * @param paramMap 条件Map集合
	 * @return 职员集合
	 */
	public List<Dispatcher> pageQueryDispatcher(Map<String,Object> paramMap);
	
	/**
	 * 根据条件组合分页查询职员
	 * @param paramMap 条件Map集合
	 * @return 职员集合
	 */
	public List<Dispatcher> findDispatcherByCriteriaWithPage(DispatcherOV dispatcherOV);
	
	/**
	 * 查询条件查询总数目
	 * @return dispatcher总数目
	 */
	public int countDispatcherByCriteriaWithPage(DispatcherOV dispatcherOV);
	
	public int addDispatcher(Dispatcher dispatcher);
	
	public int deleteDispatcher(String dispatcherId);
	
	public int freezeOrActivateDispatcher(Map<String,String> paramMap);
	
	public int existDispatcherByDispatcherNo(String dispatcherNo);
	
	public int existDispatcherByPhoneNumber(String phoneNumber);
	
	public int existDispatcherByBusNumber(String busNumber);
	
	public String findDispatcherNameByDispatcherNo(String dispatcherNo);
	
	public Dispatcher findDispatcherByDispatcherNo(String dispatcherNo);
	
	public String findOrganizationNameByDispatcherNo(String dispatcherNo);
	
	public Dispatcher findDispatcherByDispathcerId(String dispatcherId);
	
	public int countIsOrganizationById(String organizationId);
	
	/**
	 * 查询dispatcher总数目
	 * @return dispatcher总数目
	 */
	public int countDispatcher();
}
