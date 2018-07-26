package com.yidu.d257.service.base;

import java.util.List;

import com.yidu.d257.domain.base.AssistantDocument;

public interface AssistantDocumentService {

	public List<AssistantDocument> findAllBusTypeAndIdWithUseable();
	
	public List<AssistantDocument> findAllDispatcherTypeAndIdWithUseable();
}
