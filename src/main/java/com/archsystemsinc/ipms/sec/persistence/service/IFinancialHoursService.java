package com.archsystemsinc.ipms.sec.persistence.service;


import java.util.Date;
import java.util.List;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.FinancialHours;

public interface IFinancialHoursService extends IService<FinancialHours> {
	
	public List<FinancialHours> findByProjectAndPeriods(Long projectId, Date startDate, Date endDate);
}
