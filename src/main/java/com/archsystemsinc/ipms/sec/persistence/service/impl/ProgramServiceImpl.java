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
import com.archsystemsinc.ipms.persistence.search.ProgramSpecifications;
import com.archsystemsinc.ipms.persistence.service.AbstractService;
import com.archsystemsinc.ipms.sec.model.OrganizationGroup;
import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.model.Program;
import com.archsystemsinc.ipms.sec.persistence.dao.IProgramJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.IProgramService;
import com.archsystemsinc.ipms.sec.util.SearchSecUtil;
import com.google.common.collect.Lists;

@Service
@Transactional
public class ProgramServiceImpl extends AbstractService<Program> implements
IProgramService {

	@Autowired
	IProgramJpaDAO dao;

	public ProgramServiceImpl() {
		super(Program.class);
		
	}

	// API

	// search

	@Override
	public List<Program> search(
			final ImmutableTriple<String, ClientOperation, String>... constraints) {
		final Specification<Program> firstSpec = SearchSecUtil.resolveConstraint(constraints[0], Program.class);

		Specifications<Program> specifications = Specifications
				.where(firstSpec);
		for( int i = 1; i < constraints.length; i++ ){
			specifications = specifications.and(SearchSecUtil
					.resolveConstraint(constraints[i], Program.class));
		}

		if( firstSpec == null ){
			return Lists.newArrayList();
		}

		return getDao().findAll(specifications);
	}

	// find

	@Override
	@Transactional( readOnly = true )
	public Program findByName(final String name) {
		return dao.findByName( name );
	}

	// Spring

	@Override
	protected final IProgramJpaDAO getDao() {
		return dao;
	}

	@Override
	public Specification<Program> resolveConstraint(
			final ImmutableTriple<String, ClientOperation, String> constraint) {
		return SearchSecUtil.resolveConstraint(constraint, Program.class);
	}

	@Override
	protected JpaSpecificationExecutor<Program> getSpecificationExecutor() {
		return dao;
	}

	@Override
	public List<Program> findActiveUserPrograms(final Principal principal) {
		return dao.findAll(Specifications.where(
				ProgramSpecifications.isActivePrograms()).and(
						ProgramSpecifications.userPrograms(principal)));
	}

	@Override
	public List<Program> findActivePrograms() {
		return dao.findAll(Specifications.where(ProgramSpecifications
				.isActivePrograms()));
	}

	@Override
	public List<Program> findUserPrograms(final Principal currentUser) {
		return dao.findAll(Specifications.where(ProgramSpecifications
				.userPrograms(currentUser)));
	}

	@Override
	public List<Program> findProgramListByOrganizationGroup(OrganizationGroup organizationGroup) {
		return dao.findProgramListByOrganizationGroup(organizationGroup);
	}

}
