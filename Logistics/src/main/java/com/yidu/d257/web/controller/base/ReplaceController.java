package com.yidu.d257.web.controller.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yidu.d257.domain.base.Replace;
import com.yidu.d257.domain.base.ReplaceOV;
import com.yidu.d257.service.base.ReplaceService;

@Controller
@RequestMapping("/base/replace")
public class ReplaceController {

	@Autowired
	private ReplaceService replaceService;

	public ReplaceController() {
		super();
	}

	public ReplaceController(ReplaceService replaceService) {
		super();
		this.replaceService = replaceService;
	}

	public ReplaceService getreplaceService() {
		return replaceService;
	}

	public void setreplaceService(ReplaceService replaceService) {
		this.replaceService = replaceService;
	}
	
	@ResponseBody
	@RequestMapping(value="/findAllReplaceWithPage.action",method={RequestMethod.POST})
	private Map<String,Object> doFindAllReplaceWithPage(int pageIndex,int pageSize){
		List<Replace> replaceList = replaceService.findAllReplaceWithPage(pageIndex, pageSize);
		return formartterConditions(replaceList, pageIndex, pageSize);
	}
	
	@ResponseBody
	@RequestMapping(value="/addReplace.action",method={RequestMethod.POST})
	private String doAddReplace(ReplaceOV replaceOV){
		return replaceService.addReplace(replaceOV)>0?"Y":"N";
	}
	
	@ResponseBody
	@RequestMapping(value="/freezeReplace.action",method={RequestMethod.POST})
	private Integer doFreezeReplace(@RequestParam("replaceIds[]")List<String> replaceIds){
		Integer count =0;
		for(String replaceId:replaceIds){
			Map<String,String> paramMap = new HashMap<String,String>();
			paramMap.put("replaceId", replaceId);
			paramMap.put("useable", "N");
			count+=replaceService.freezeOrActivateReplace(paramMap);
		}
		return count;
	}
	
	@ResponseBody
	@RequestMapping(value="/activateReplace.action",method={RequestMethod.POST})
	private Integer doActivateReplace(@RequestParam("replaceIds[]")List<String> replaceIds){
		Integer count =0;
		for(String replaceId:replaceIds){
			Map<String,String> paramMap = new HashMap<String,String>();
			paramMap.put("replaceId", replaceId);
			paramMap.put("useable", "Y");
			count+=replaceService.freezeOrActivateReplace(paramMap);
		}
		return count;
	}
	
	@ResponseBody
	@RequestMapping(value="/updateReplace.action",method={RequestMethod.POST})
	private String doUpdateReplace(ReplaceOV replaceOV,String replaceId){
		return replaceService.updateReplace(replaceOV,replaceId)?"Y":"N";
	}

	@ResponseBody
	@RequestMapping(value="/findReplaceByNameWithPage.action",method={RequestMethod.POST})
	private Map<String,Object> doFindReplaceByNameWithPage(String condition,int pageIndex,int pageSize){
		return replaceService.findReplaceByNameWithPage(condition, pageIndex, pageSize);
	}
	
	@ResponseBody
	@RequestMapping(value="/findReplaceByCriteriaWithPage.action",method={RequestMethod.POST})
	private Map<String,Object> doFindDispatcherByCriteriaWithPage(ReplaceOV replaceOV){
		return replaceService.findReplaceByCriteriaWithPage(replaceOV);
	}
	
	/**
	 * 数据格式化
	 * @param replaceList 替班集合
	 * @param pageIndex	页码
	 * @param pageSize	页面大小
	 * @return	结果Map集
	 */
	public Map<String,Object> formartterConditions(List<Replace> replaceList,int pageIndex,int pageSize){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("total", replaceService.countReplace());
		resultMap.put("rows", replaceList);
		return resultMap;
	}
}
