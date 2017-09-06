package com.archsystemsinc.ipms.sec.persistence.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.archsystemsinc.ipms.persistence.service.AbstractService;
import com.archsystemsinc.ipms.sec.model.FinancialExpenses;
import com.archsystemsinc.ipms.sec.persistence.dao.IFinancialExpensesJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.IFinancialExpensesService;

@Service
@Transactional
public class FinancialExpensesServiceImpl extends AbstractService<FinancialExpenses> implements
IFinancialExpensesService {

	public FinancialExpensesServiceImpl() {
		super(FinancialExpenses.class);
	}

	@Autowired
	IFinancialExpensesJpaDAO expnsDao;
	
	@Override
	public List<FinancialExpenses> findByProjectAndPeriods(Long projectId, Date startDate, Date endDate) {
		return expnsDao.findByProjectAndPeriods(projectId, new java.sql.Date(startDate.getTime()), new java.sql.Date(endDate.getTime()));
	}

	
	@Override
	protected PagingAndSortingRepository<FinancialExpenses, Long> getDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected JpaSpecificationExecutor<FinancialExpenses> getSpecificationExecutor() {
		// TODO Auto-generated method stub
		return null;
	}



}
