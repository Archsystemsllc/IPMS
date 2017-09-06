package com.archsystemsinc.ipms.sec.persistence.service.impl;

import java.util.List;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.archsystemsinc.ipms.common.ClientOperation;
import com.archsystemsinc.ipms.persistence.search.TaskSpecifications;
import com.archsystemsinc.ipms.persistence.service.AbstractService;
import com.archsystemsinc.ipms.sec.model.Forecast;
import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.model.Project;
import com.archsystemsinc.ipms.sec.model.Task;
import com.archsystemsinc.ipms.sec.model.WBSBudget;
import com.archsystemsinc.ipms.sec.persistence.dao.IForecastJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.dao.IWBSBudgetJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.IForecastService;
import com.archsystemsinc.ipms.sec.persistence.service.IWBSBudgetService;
import com.archsystemsinc.ipms.sec.util.SearchSecUtil;

@Service
@Transactional
public class WBSBudgetServiceImpl extends AbstractService<WBSBudget> implements IWBSBudgetService{
	@Autowired
	IWBSBudgetJpaDAO dao;
	public WBSBudgetServiceImpl() {
		super(WBSBudget.class);
	}
	// API

	// search

	
	// find



	// Spring

	@Override
	protected final IWBSBudgetJpaDAO getDao() {
		return dao;
	}

	@Override
	public Specification<WBSBudget> resolveConstraint(
			final ImmutableTriple<String, ClientOperation, String> constraint) {
		return SearchSecUtil.resolveConstraint(constraint, WBSBudget.class);
	}

	@Override
	protected JpaSpecificationExecutor<WBSBudget> getSpecificationExecutor() {
		return dao;
	}
	
	
	/*@Override
	public List<WBSBudget> findWbsBudgetByTaskId (final Task task) {
		return dao.findWbsBudgetByTaskId(task);
		}*/
	
	/*@Override
	public 	List<WBSBudget> findWbsBudgetsByTaskId(final Task task) {
		return dao.findWbsBudgetsByTaskId(task);
	}*/

	@Override
	public 	WBSBudget findById(final Long id){
		return getDao().findById(id);
	}
}
