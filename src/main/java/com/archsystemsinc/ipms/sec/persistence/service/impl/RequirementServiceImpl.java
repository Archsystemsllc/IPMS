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
import com.archsystemsinc.ipms.persistence.search.ActionItemSpecifications;
import com.archsystemsinc.ipms.persistence.service.AbstractService;
import com.archsystemsinc.ipms.sec.model.ActionItem;
import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.model.Requirement;
import com.archsystemsinc.ipms.sec.persistence.dao.IRequirementJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.IRequirementService;
import com.archsystemsinc.ipms.sec.util.SearchSecUtil;
import com.google.common.collect.Lists;

@Service
@Transactional
public class RequirementServiceImpl extends AbstractService<Requirement>
implements IRequirementService {

	@Autowired
	public IRequirementJpaDAO dao;

	public RequirementServiceImpl() {
		super(Requirement.class);
	}

	// API

	// search

	@Override
	public List<Requirement> search(
			final ImmutableTriple<String, ClientOperation, String>... constraints) {
		final Specification<Requirement> firstSpec = SearchSecUtil
				.resolveConstraint(constraints[0], Requirement.class);
		Specifications<Requirement> specifications = Specifications
				.where(firstSpec);
		for( int i = 1; i < constraints.length; i++ ){
			specifications = specifications.and(SearchSecUtil
					.resolveConstraint(constraints[i], Requirement.class));
		}
		if( firstSpec == null ){
			return Lists.newArrayList();
		}

		return getDao().findAll( specifications );
	}

	// find

	@Override
	@Transactional( readOnly = true )
	public Requirement findByName(final String name) {
		return dao.findByName( name );
	}

	// Spring

	@Override
	protected final IRequirementJpaDAO getDao() {
		return dao;
	}

	@Override
	public Specification<Requirement> resolveConstraint(
			final ImmutableTriple<String, ClientOperation, String> constraint) {
		return SearchSecUtil.resolveConstraint(constraint, Requirement.class);
	}

	@Override
	protected JpaSpecificationExecutor<Requirement> getSpecificationExecutor() {
		return dao;
	}
	
	
	@Override
	public void save(final Requirement requirement) {
		dao.save(requirement);
	}

	@Override
	public List<Requirement> findCurrentUserRequirements(Principal principal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Requirement> findCurrentRequirements() {
		// TODO Auto-generated method stub
		return null;
	}

}
