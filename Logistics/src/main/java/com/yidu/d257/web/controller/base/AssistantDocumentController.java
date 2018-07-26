package com.yidu.d257.web.controller.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yidu.d257.domain.base.AssistantDocument;
import com.yidu.d257.service.base.AssistantDocumentService;

@Controller
@RequestMapping("/base/assistantDocument")
public class AssistantDocumentController {

	@Autowired
	private AssistantDocumentService assistantDocumentService;

	public AssistantDocumentController() {
		super();
	}

	public AssistantDocumentController(AssistantDocumentService assistantDocumentService) {
		super();
		this.assistantDocumentService = assistantDocumentService;
	}

	public AssistantDocumentService getAssistantDocumentService() {
		return assistantDocumentService;
	}

	public void setAssistantDocumentService(AssistantDocumentService assistantDocumentService) {
		this.assistantDocumentService = assistantDocumentService;
	}
	
	@ResponseBody
	@RequestMapping(value="/findAllBusTypeAndIdWithUseable.action",method={RequestMethod.POST})
	public List<AssistantDocument> doFindAllBusTypeAndIdWithUseable(){
		return assistantDocumentService.findAllBusTypeAndIdWithUseable();
	}
	
	@ResponseBody
	@RequestMapping(value="/findAllDispatcherTypeAndIdWithUseable.action",method={RequestMethod.POST})
	public List<AssistantDocument> doFindAllDispatcherTypeAndIdWithUseable(){
		return assistantDocumentService.findAllDispatcherTypeAndIdWithUseable();
	}
}
