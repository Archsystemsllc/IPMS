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
import com.archsystemsinc.ipms.sec.model.OrganizationGroup;
import com.archsystemsinc.ipms.sec.persistence.dao.IOrganizationGroupJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.IOrganizationGroupService;
import com.archsystemsinc.ipms.sec.util.SearchSecUtil;
import com.google.common.collect.Lists;

@Service
@Transactional
public class OrganizationGroupServiceImpl extends AbstractService<OrganizationGroup> implements
IOrganizationGroupService {

	@Autowired
	IOrganizationGroupJpaDAO dao;

	public OrganizationGroupServiceImpl() {
		super(OrganizationGroup.class);
		
	}

	// API

	// search

	@Override
	public List<OrganizationGroup> search(
			final ImmutableTriple<String, ClientOperation, String>... constraints) {
		final Specification<OrganizationGroup> firstSpec = SearchSecUtil.resolveConstraint(constraints[0], OrganizationGroup.class);

		Specifications<OrganizationGroup> specifications = Specifications
				.where(firstSpec);
		for( int i = 1; i < constraints.length; i++ ){
			specifications = specifications.and(SearchSecUtil
					.resolveConstraint(constraints[i], OrganizationGroup.class));
		}

		if( firstSpec == null ){
			return Lists.newArrayList();
		}

		return getDao().findAll(specifications);
	}

	// find

	@Override
	@Transactional( readOnly = true )
	public OrganizationGroup findByName(final String name) {
		return dao.findByName( name );
	}

	// Spring

	@Override
	protected final IOrganizationGroupJpaDAO getDao() {
		return dao;
	}

	@Override
	public Specification<OrganizationGroup> resolveConstraint(
			final ImmutableTriple<String, ClientOperation, String> constraint) {
		return SearchSecUtil.resolveConstraint(constraint, OrganizationGroup.class);
	}

	@Override
	protected JpaSpecificationExecutor<OrganizationGroup> getSpecificationExecutor() {
		return dao;
	}
}
