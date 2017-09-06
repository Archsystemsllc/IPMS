package com.archsystemsinc.ipms.sec.persistence.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.archsystemsinc.ipms.common.ClientOperation;
import com.archsystemsinc.ipms.persistence.search.TaskSpecifications;
import com.archsystemsinc.ipms.persistence.service.AbstractService;
import com.archsystemsinc.ipms.sec.model.FinancialExpenses;
import com.archsystemsinc.ipms.sec.model.FinancialHeader;
import com.archsystemsinc.ipms.sec.model.FinancialHours;
import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.model.Project;
import com.archsystemsinc.ipms.sec.model.Task;
import com.archsystemsinc.ipms.sec.persistence.dao.IFinancialExpensesJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.dao.IFinancialHoursJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.dao.ITaskJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.IFinancialExpensesService;
import com.archsystemsinc.ipms.sec.persistence.service.IFinancialHoursService;
import com.archsystemsinc.ipms.sec.persistence.service.ITaskService;
import com.archsystemsinc.ipms.sec.util.SearchSecUtil;
import com.google.common.collect.Lists;

@Service
@Transactional
public class FinancialHoursServiceImpl extends AbstractService<FinancialHours> implements
IFinancialHoursService {

	public FinancialHoursServiceImpl() {
		super(FinancialHours.class);
	}

	@Autowired
	IFinancialHoursJpaDAO dao;

	@Override
	public List<FinancialHours> findByProjectAndPeriods(Long projectId, Date startDate, Date endDate) {
		return dao.findByProjectAndPeriods(projectId, new java.sql.Date(startDate.getTime()), new java.sql.Date(endDate.getTime()));
	}

	@Override
	protected PagingAndSortingRepository<FinancialHours, Long> getDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected JpaSpecificationExecutor<FinancialHours> getSpecificationExecutor() {
		// TODO Auto-generated method stub
		return null;
	}



}
