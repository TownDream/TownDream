package com.yidu.d257.web.controller.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yidu.d257.domain.base.Dispatcher;
import com.yidu.d257.domain.base.DispatcherOV;
import com.yidu.d257.service.base.DispatcherService;
import com.yidu.d257.util.MyUtil;


@Controller
@RequestMapping("/base/dispatcher")
public class DispatcherController {

	@Autowired
	private DispatcherService dispatcherService;

	public DispatcherController() {
		super();
	}

	public DispatcherController(DispatcherService dispatcherService) {
		super();
		this.dispatcherService = dispatcherService;
	}

	public void setDispatcherService(DispatcherService dispatcherService) {
		this.dispatcherService = dispatcherService;
	}
	
	@ResponseBody
	@RequestMapping(value="/findAllDispatcher.action",method={RequestMethod.POST})
	public Map<String,Object> doFindAllDispatcher(int pageIndex,int pageSize){
		System.out.println(pageIndex+" "+pageSize);
		List<Dispatcher> dispatcherList = dispatcherService.findAllDispatcher();
		return formartterConditions(dispatcherList);
	}
	
	@ResponseBody
	@RequestMapping(value="/pageQueryDispatcher.action",method={RequestMethod.POST})
	public Map<String,Object> doPageQueryDispatcher(int pageIndex,int pageSize){
		List<Dispatcher> dispatcherList = dispatcherService.pageQueryDispatcher(pageIndex, pageSize);
		return formartterConditions(dispatcherList);
	}
	
	@ResponseBody
	@RequestMapping(value="/findDispatcherByCriteriaWithPage.action",method={RequestMethod.POST})
	public Map<String,Object> findDispatcherByCriteriaWithPage(DispatcherOV dispatcherOV){
		Integer pageIndex = dispatcherOV.getPageIndex();
		Integer pageSize = dispatcherOV.getPageSize();
		dispatcherOV.setStart((pageIndex-1)*pageSize);
		dispatcherOV.setEnd(pageIndex*pageSize);
		List<Dispatcher> dispatcherList = dispatcherService.findDispatcherByCriteriaWithPage(dispatcherOV);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("total", dispatcherService.countDispatcherByCriteriaWithPage(dispatcherOV));
		resultMap.put("rows", dispatcherList);
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/addDispatcher.action",method={RequestMethod.POST})
	public String doAddDispatcher(Dispatcher dispatcher){
		dispatcher.setDispatcherId(MyUtil.getUUID());
		return dispatcherService.addDispatcher(dispatcher) ? "Y" : "N";
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteDispatcher.action",method={RequestMethod.POST})
	public String doDeleteDispatcher(String dispatcherId){
		return dispatcherService.deleteDispatcher(dispatcherId) ? "Y" : "N";
	}
	
	@ResponseBody
	@RequestMapping(value="/updateDispatcher.action",method={RequestMethod.POST})
	public String doUpdateDispatcher(Dispatcher dispatcher){
		return dispatcherService.updateDispatcher(dispatcher) ? "Y" : "N";
	}
	
	@ResponseBody
	@RequestMapping(value="/freezeDispatcher.action",method={RequestMethod.POST})
	public Integer doFreezeDispatcher(@RequestParam("dispatcherIds[]")List<String> dispatcherIds){
		Integer count =0;
		System.out.println(dispatcherIds.size());
		for(String disapatcherId:dispatcherIds){
			Map<String,String> paramMap = new HashMap<String,String>();
			paramMap.put("dispatcherId", disapatcherId);
			paramMap.put("useable", "N");
			count+=dispatcherService.freezeOrActivateDispatcher(paramMap);
		}
		return count;
	}
	
	@ResponseBody
	@RequestMapping(value="/activateDispatcher.action",method={RequestMethod.POST})
	public Integer doActivateDispatcher(@RequestParam("dispatcherIds[]")List<String> dispatcherIds){
		Integer count =0;
		System.out.println(dispatcherIds.size());
		for(String disapatcherId:dispatcherIds){
			Map<String,String> paramMap = new HashMap<String,String>();
			paramMap.put("dispatcherId", disapatcherId);
			paramMap.put("useable", "Y");
			count+=dispatcherService.freezeOrActivateDispatcher(paramMap);
		}
		return count;
	}
	
	//-------------------------------校验方法-----------------------------
	@ResponseBody
	@RequestMapping(value="/existDispatcherByDispatcherNo.action",method={RequestMethod.POST})
	public Map<String, Boolean> doExistDispatcherByDispatcherNo(String dispatcherNo){
		Map<String, Boolean> resultMap = new HashMap<String, Boolean>();
		resultMap.put("valid", dispatcherService.existDispatcherByDispatcherNo(dispatcherNo)?false:true);
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/existDispatcherByDispatcherNoInReplace.action",method={RequestMethod.POST})
	public Map<String, Boolean> doExistDispatcherByDispatcherNoInReplace(String rDispatcherNo){
		Map<String, Boolean> resultMap = new HashMap<String, Boolean>();
		resultMap.put("valid", dispatcherService.existDispatcherByDispatcherNo(rDispatcherNo));
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/existDispatcherByDispatcherNoInReplaced.action",method={RequestMethod.POST})
	public Map<String, Boolean> doExistDispatcherByDispatcherNoInReplaced(String rdDispatcherNo){
		Map<String, Boolean> resultMap = new HashMap<String, Boolean>();
		resultMap.put("valid", dispatcherService.existDispatcherByDispatcherNo(rdDispatcherNo));
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/existDispatcherByPhoneNumber.action",method={RequestMethod.POST})
	public Map<String, Boolean> doExistDispatcherByPhoneNumber(String phoneNumber){
		Map<String, Boolean> resultMap = new HashMap<String, Boolean>();
		resultMap.put("valid", dispatcherService.existDispatcherByPhoneNumber(phoneNumber)?false:true);
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/existDispatcherByBusNumber.action",method={RequestMethod.POST})
	public Map<String, Boolean> doExistDispatcherByBusNumber(String busNumber){
		Map<String, Boolean> resultMap = new HashMap<String, Boolean>();
		resultMap.put("valid", dispatcherService.existDispatcherByBusNumber(busNumber)?false:true);
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/findNameByDispatcherNo.action",method={RequestMethod.POST})
	public List<String> doFindDispatcherNameByDispatcherNo(String dispatcherNo){
		Dispatcher dispatcher = dispatcherService.findDispatcherByDispatcherNo(dispatcherNo);
		List<String> resultList = new ArrayList<String>();
		if(dispatcher!=null){
			resultList.add(dispatcher.getDispatcherName());
			resultList.add(dispatcher.getOrganizationId());
			resultList.add(dispatcher.getOrganizationName());
		}
		return resultList;
	}
	
	@ResponseBody
	@RequestMapping(value="/findOrganizationNameByDispatcherNo.action",method={RequestMethod.POST})
	public List<String> doFindOrganizationNameByDispatcherNo(String dispatcherNo){
		String organizationName = dispatcherService.findOrganizationNameByDispatcherNo(dispatcherNo);
		List<String> resultList = new ArrayList<String>();
		resultList.add(organizationName);
		return resultList;
	}
	
	@ResponseBody
	@RequestMapping(value="/findDispatcherNoByDispathcerId.action",method={RequestMethod.POST})
	public List<String> doFindDispatcherNoByDispathcerId(String dispatcherId){
		Dispatcher dispatcher = dispatcherService.findDispatcherByDispathcerId(dispatcherId);
		List<String> resultList = new ArrayList<String>();
		resultList.add(dispatcher.getDispatcherNo());
		return resultList;
	}
	
	@ResponseBody
	@RequestMapping(value="/generateDispatcherNo.action",method={RequestMethod.POST})
	public String doGenerateDispatcherNo(Dispatcher dispatcher){
		if(dispatcher.getDispatcherNo()==""){
			return dispatcherService.generateDispatcherNo(dispatcher);
		}else{
			return dispatcherService.updateDispatcherNo(dispatcher);
		}
	}
	
	
	/**
	 * 数据格式化
	 * @param dispatcherList 职员集合
	 * @return	结果Map集
	 */
	public Map<String,Object> formartterConditions(List<Dispatcher> dispatcherList){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("total", dispatcherService.countDispatcher());
		resultMap.put("rows", dispatcherList);
		return resultMap;
	}
}
