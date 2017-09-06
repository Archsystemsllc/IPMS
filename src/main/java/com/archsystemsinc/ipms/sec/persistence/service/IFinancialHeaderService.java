package com.archsystemsinc.ipms.sec.persistence.service;


import java.util.Date;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.FinancialHeader;

public interface IFinancialHeaderService extends IService<FinancialHeader> {
	
	public Date findInceptionStartDate(Long projectId);
	
	public Date findInceptionEndtDate(Long projectId);
	
}
