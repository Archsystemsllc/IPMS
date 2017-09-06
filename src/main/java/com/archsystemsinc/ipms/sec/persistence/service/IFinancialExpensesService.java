package com.archsystemsinc.ipms.sec.persistence.service;


import java.util.Date;
import java.util.List;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.FinancialExpenses;

public interface IFinancialExpensesService extends IService<FinancialExpenses> {
	
	public List<FinancialExpenses> findByProjectAndPeriods(Long projectId, Date startDate, Date endDate);
	
}
