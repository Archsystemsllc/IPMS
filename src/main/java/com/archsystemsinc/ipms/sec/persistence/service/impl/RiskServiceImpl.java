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
import com.archsystemsinc.ipms.persistence.service.AbstractService;
import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.model.Risk;
import com.archsystemsinc.ipms.sec.persistence.dao.IRiskJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.IRiskService;
import com.archsystemsinc.ipms.sec.util.SearchSecUtil;
import com.google.common.collect.Lists;

@Service
@Transactional
public class RiskServiceImpl extends AbstractService<Risk> implements
IRiskService {

	@Autowired
	IRiskJpaDAO dao;

	public RiskServiceImpl() {
		super(Risk.class);
	}

	// API

	// search

	@Override
	public List<Risk> search(
			final ImmutableTriple<String, ClientOperation, String>... constraints) {
		final Specification<Risk> firstSpec = SearchSecUtil.resolveConstraint(
				constraints[0], Risk.class);

		Specifications<Risk> specifications = Specifications
				.where(firstSpec);
		for( int i = 1; i < constraints.length; i++ ){
			specifications = specifications.and(SearchSecUtil
					.resolveConstraint(constraints[i], Risk.class));
		}

		if( firstSpec == null ){
			return Lists.newArrayList();
		}

		return getDao().findAll(specifications);
	}

	// find

	@Override
	@Transactional( readOnly = true )
	public Risk findByName(final String name) {
		return dao.findByName( name );
	}

	// Spring

	@Override
	protected final IRiskJpaDAO getDao() {
		return dao;
	}

	@Override
	public Specification<Risk> resolveConstraint(
			final ImmutableTriple<String, ClientOperation, String> constraint) {
		return SearchSecUtil
.resolveConstraint(constraint, Risk.class);
	}

	@Override
	protected JpaSpecificationExecutor<Risk> getSpecificationExecutor() {
		return dao;
	}

	@Override
	public List<Risk> findCurrentUserRisks(Principal principal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Risk> findCurrentRisks() {
		// TODO Auto-generated method stub
		return null;
	}

}
