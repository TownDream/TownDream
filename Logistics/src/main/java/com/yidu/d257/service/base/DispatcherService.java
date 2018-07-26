package com.yidu.d257.service.base;

import java.util.List;
import java.util.Map;

import com.yidu.d257.domain.base.Dispatcher;
import com.yidu.d257.domain.base.DispatcherOV;



public interface DispatcherService {

	public List<Dispatcher> findAllDispatcher();
	
	public List<Dispatcher> pageQueryDispatcher(int pageIndex,int pageSize);
	
	public List<Dispatcher> findDispatcherByCriteriaWithPage(DispatcherOV dispatcherOV);
	
	public int countDispatcherByCriteriaWithPage(DispatcherOV dispatcherOV);
	
	public boolean addDispatcher(Dispatcher dispatcher);
	
	public boolean deleteDispatcher(String dispatcherId);
	
	public boolean updateDispatcher(Dispatcher dispatcher);
	
	public int freezeOrActivateDispatcher(Map<String,String> paramMap);
	
	public boolean existDispatcherByDispatcherNo(String dispatcherNo);
	
	public boolean existDispatcherByPhoneNumber(String phoneNumber);
	
	public boolean existDispatcherByBusNumber(String busNumber);
	
	public Dispatcher findDispatcherByDispatcherNo(String dispatcherNo);

	public String findOrganizationNameByDispatcherNo(String dispatcherNo);
	
	public Dispatcher findDispatcherByDispathcerId(String dispatcherId);
	
	public int countDispatcher();
	
	public String generateDispatcherNo(Dispatcher dispatcher);
	
	public String updateDispatcherNo(Dispatcher dispatcher);
}
