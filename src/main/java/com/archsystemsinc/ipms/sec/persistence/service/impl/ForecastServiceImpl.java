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
import com.archsystemsinc.ipms.persistence.search.IssueSpecifications;
import com.archsystemsinc.ipms.persistence.service.AbstractService;
import com.archsystemsinc.ipms.sec.model.Forecast;
import com.archsystemsinc.ipms.sec.model.Issue;
import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.model.Project;
import com.archsystemsinc.ipms.sec.persistence.dao.IForecastJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.dao.IIssueJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.IForecastService;
import com.archsystemsinc.ipms.sec.persistence.service.IIssueService;
import com.archsystemsinc.ipms.sec.util.SearchSecUtil;
import com.google.common.collect.Lists;

@Service
@Transactional
public class ForecastServiceImpl extends AbstractService<Forecast> implements
IForecastService{
	@Autowired
	IForecastJpaDAO dao;

	public ForecastServiceImpl() {
		super(Forecast.class);
	}
	// API

	// search

	
	// find



	// Spring

	@Override
	protected final IForecastJpaDAO getDao() {
		return dao;
	}

	@Override
	public Specification<Forecast> resolveConstraint(
			final ImmutableTriple<String, ClientOperation, String> constraint) {
		return SearchSecUtil.resolveConstraint(constraint, Forecast.class);
	}

	@Override
	protected JpaSpecificationExecutor<Forecast> getSpecificationExecutor() {
		return dao;
	}

	@Override
	public List<Forecast> findForeCastByProjectId(final Project project) {
		return dao.findForeCastByProjectId(project);
	}

	

}
