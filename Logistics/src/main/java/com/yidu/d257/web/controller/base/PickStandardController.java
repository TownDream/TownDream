package com.yidu.d257.web.controller.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yidu.d257.domain.base.Dispatcher;
import com.yidu.d257.domain.base.PickStandard;
import com.yidu.d257.service.base.PickStandardService;

@Controller
@RequestMapping("/base/pickStandard")
public class PickStandardController {

	@Autowired
	private PickStandardService pickStandardService;
	
	public PickStandardController() {
		super();
	}

	public PickStandardController(PickStandardService pickStandardService) {
		super();
		this.pickStandardService = pickStandardService;
	}

	public PickStandardService getPickStandardService() {
		return pickStandardService;
	}

	public void setPickStandardService(PickStandardService pickStandardService) {
		this.pickStandardService = pickStandardService;
	}
	
	@ResponseBody
	@RequestMapping(value="/findAllPickStandardWithPage.action",method={RequestMethod.POST})
	private Map<String,Object> doFindAllPickStandardWithPage(int pageIndex,int pageSize){
		List<PickStandard> pickStandardList = pickStandardService.findAllPickStandardWithPage(pageIndex,pageSize);
		return formartterConditions(pickStandardList, pageIndex, pageSize);
	}
	
	@ResponseBody
	@RequestMapping(value="/findAllPSNameAndPSIdWithUseable.action",method={RequestMethod.POST})
	private List<PickStandard> doFindAllPSNameAndPSIdWithUseable(){
		return pickStandardService.findAllPSNameAndPSIdWithUseable();
	}
	
	/**
	 * 数据格式化
	 * @param dispatcherList 职员集合
	 * @param pageIndex	页码
	 * @param pageSize	页面大小
	 * @return	结果Map集
	 */
	public Map<String,Object> formartterConditions(List<PickStandard> pickStandardList,int pageIndex,int pageSize){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("total", pickStandardService.countPickStandard());
		resultMap.put("rows", pickStandardList);
		return resultMap;
	}
}
