package com.yidu.d257.service.base.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yidu.d257.dao.base.AssistantDocumentDao;
import com.yidu.d257.domain.base.AssistantDocument;
import com.yidu.d257.service.base.AssistantDocumentService;

@Service
public class AssistantDocumentServiceImpl implements AssistantDocumentService{

	@Autowired
	private AssistantDocumentDao assistantDocumentDao;
	
	public AssistantDocumentServiceImpl() {
		super();
	}

	public AssistantDocumentServiceImpl(AssistantDocumentDao assistantDocumentDao) {
		super();
		this.assistantDocumentDao = assistantDocumentDao;
	}

	public AssistantDocumentDao getAssistantDocumentDao() {
		return assistantDocumentDao;
	}

	public void setAssistantDocumentDao(AssistantDocumentDao assistantDocumentDao) {
		this.assistantDocumentDao = assistantDocumentDao;
	}

	public List<AssistantDocument> findAllBusTypeAndIdWithUseable() {
		return assistantDocumentDao.findAllBusTypeAndIdWithUseable();
	}

	public List<AssistantDocument> findAllDispatcherTypeAndIdWithUseable() {
		return assistantDocumentDao.findAllDispatcherTypeAndIdWithUseable();
	}
}
