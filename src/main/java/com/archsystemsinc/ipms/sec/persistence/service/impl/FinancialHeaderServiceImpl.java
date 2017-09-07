package com.archsystemsinc.ipms.sec.persistence.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.archsystemsinc.ipms.persistence.service.AbstractService;
import com.archsystemsinc.ipms.sec.model.FinancialHeader;
import com.archsystemsinc.ipms.sec.persistence.dao.IFinancialHeaderJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.IFinancialHeaderService;

@Service
@Transactional
public class FinancialHeaderServiceImpl extends AbstractService<FinancialHeader> implements
IFinancialHeaderService {

	public FinancialHeaderServiceImpl() {
		super(FinancialHeader.class);
	}
	

	@Autowired
	IFinancialHeaderJpaDAO headerDao;

	@Override
	public Date findInceptionEndtDate(Long projectId) {
		List<FinancialHeader> header = headerDao.findRecentHeader(projectId);
		if(header != null)
			return header.get(0).getEndDate();
		
		return null;
	}



	@Override
	public Date findInceptionStartDate(Long projectId) {
		List<FinancialHeader> header = headerDao.findOldestHeader(projectId);
		if(header != null && header.size()>0)
			return header.get(0).getStartDate();
		
		return null;
	
	}



	@Override
	protected PagingAndSortingRepository<FinancialHeader, Long> getDao() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	protected JpaSpecificationExecutor<FinancialHeader> getSpecificationExecutor() {
		// TODO Auto-generated method stub
		return null;
	}


}
