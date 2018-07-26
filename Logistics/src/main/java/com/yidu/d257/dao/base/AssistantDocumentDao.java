package com.yidu.d257.dao.base;

import java.util.List;
import java.util.Map;

import com.yidu.d257.domain.base.AssistantDocument;
import com.yidu.d257.domain.base.Replace;
import com.yidu.d257.domain.base.ReplaceOV;

public interface AssistantDocumentDao {

 	public List<AssistantDocument> findAllBusTypeAndIdWithUseable();
 	
 	public List<AssistantDocument> findAllDispatcherTypeAndIdWithUseable();
 	
 	public String findShortNameById(String assistantDocumentId);
}
