package com.yidu.d257.service.base.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yidu.d257.dao.base.AssistantDocumentDao;
import com.yidu.d257.dao.base.DispatcherDao;
import com.yidu.d257.dao.sys.OrganizationDao;
import com.yidu.d257.domain.base.Dispatcher;
import com.yidu.d257.domain.base.DispatcherOV;
import com.yidu.d257.service.base.DispatcherService;

@Service
public class DispatcherServiceImpl implements DispatcherService {

	@Autowired
	private DispatcherDao dispatcherDao;
	@Autowired
	private OrganizationDao organizationDao;
	@Autowired
	private AssistantDocumentDao assistantDocumentDao;
	
	
	public DispatcherServiceImpl() {
		super();
	}

	public DispatcherServiceImpl(DispatcherDao dispatcherDao, OrganizationDao organizationDao,
			AssistantDocumentDao assistantDocumentDao) {
		super();
		this.dispatcherDao = dispatcherDao;
		this.organizationDao = organizationDao;
		this.assistantDocumentDao = assistantDocumentDao;
	}

	public void setDispatcherDao(DispatcherDao dispatcherDao) {
		this.dispatcherDao = dispatcherDao;
	}

	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

	public void setAssistantDocumentDao(AssistantDocumentDao assistantDocumentDao) {
		this.assistantDocumentDao = assistantDocumentDao;
	}

	public List<Dispatcher> findAllDispatcher() {
		return dispatcherDao.findAllDispatcher();
	}

	public List<Dispatcher> pageQueryDispatcher(int pageIndex,int pageSize) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("start", (pageIndex-1)*pageSize);
		paramMap.put("end", pageIndex*pageSize);
		return dispatcherDao.pageQueryDispatcher(paramMap);
	}

	public int countDispatcher() {
		return dispatcherDao.countDispatcher();
	}

	public List<Dispatcher> findDispatcherByCriteriaWithPage(DispatcherOV dispatcherOV) {
		return dispatcherDao.findDispatcherByCriteriaWithPage(dispatcherOV);
	}

	public int countDispatcherByCriteriaWithPage(DispatcherOV dispatcherOV) {
		return dispatcherDao.countDispatcherByCriteriaWithPage(dispatcherOV);
	}

	public boolean addDispatcher(Dispatcher dispatcher) {
		return dispatcherDao.addDispatcher(dispatcher)!=0 ;
	}

	public boolean deleteDispatcher(String dispatcherId) {
		return dispatcherDao.deleteDispatcher(dispatcherId)!=0;
	}
	
	public boolean updateDispatcher(Dispatcher dispatcher) {
		return dispatcherDao.deleteDispatcher(dispatcher.getDispatcherId())!=0 && dispatcherDao.addDispatcher(dispatcher)!=0;
	}
	
	public int freezeOrActivateDispatcher(Map<String,String> paramMap){
		return dispatcherDao.freezeOrActivateDispatcher(paramMap);
	}
	
	public boolean existDispatcherByDispatcherNo(String dispatcherNo){
		return dispatcherDao.existDispatcherByDispatcherNo(dispatcherNo)>0;
	}
	
	public boolean existDispatcherByPhoneNumber(String phoneNumber){
		return dispatcherDao.existDispatcherByPhoneNumber(phoneNumber)>0;
	}
	
	public boolean existDispatcherByBusNumber(String busNumber){
		return dispatcherDao.existDispatcherByBusNumber(busNumber)>0;
	}

	public Dispatcher findDispatcherByDispatcherNo(String dispatcherNo) {
		return dispatcherDao.findDispatcherByDispatcherNo(dispatcherNo);
	}

	public String findOrganizationNameByDispatcherNo(String dispatcherNo) {
		return dispatcherDao.findOrganizationNameByDispatcherNo(dispatcherNo);
	}

	public Dispatcher findDispatcherByDispathcerId(String dispatcherId) {
		return dispatcherDao.findDispatcherByDispathcerId(dispatcherId);
	}

	public Map<String,Object> madeParamMap(int pageIndex,int pageSize){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("start", (pageIndex-1)*pageSize);
		paramMap.put("end", pageIndex*pageSize);
		return paramMap;
	}

	/**
	 * 生成工号
	 */
	public String generateDispatcherNo(Dispatcher dispatcher) {
		String connectPhone = organizationDao.findConnectPhoneByOrganizationId(dispatcher.getOrganizationId());
		String shortName = assistantDocumentDao.findShortNameById(dispatcher.getDispatcherTypeId());
		shortName=shortName.equals("SJ")?"LSJ":shortName;
		Integer count = dispatcherDao.countIsOrganizationById(dispatcher.getOrganizationId())+1;
		String sCount = "";
		if(count<10){
			sCount = "0" + "0" + "0" +count.toString();
		}else if(count<100){
			sCount = "0" +"0" +count.toString();
		}else if(count<1000){
			sCount = "0" + count.toString();
		}
		return connectPhone+shortName+sCount;
	}
	
	/**
	 * 修改工号
	 */
	public String updateDispatcherNo(Dispatcher dispatcher){
		String connectPhone = organizationDao.findConnectPhoneByOrganizationId(dispatcher.getOrganizationId());
		if(connectPhone.equals(dispatcher.getDispatcherNo().substring(0,5))){
			String shortName = assistantDocumentDao.findShortNameById(dispatcher.getDispatcherTypeId());
			shortName=shortName.equals("SJ")?"LSJ":shortName;
			String dispathcerNo = dispatcher.getDispatcherNo();
			String count = dispathcerNo.substring(dispathcerNo.length()-4,dispathcerNo.length());
			return connectPhone+shortName+count;
		}else{
			return generateDispatcherNo(dispatcher);
		}
	}
}
