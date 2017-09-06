package com.archsystemsinc.ipms.sec.persistence.service;

import java.util.List;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.Task;
import com.archsystemsinc.ipms.sec.model.WBSBudget;

public interface IWBSBudgetService extends IService<WBSBudget>{

	WBSBudget findById(Long id);
	
	//List<WBSBudget> findWbsBudgetByTasakId (final Task task);
	//WBSBudget findById(final Long id);
	//List<WBSBudget> findWbsBudgetByTaskId(Task task);


}
