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
import com.archsystemsinc.ipms.sec.model.Issue;
import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.persistence.dao.IIssueJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.IIssueService;
import com.archsystemsinc.ipms.sec.util.SearchSecUtil;
import com.google.common.collect.Lists;

@Service
@Transactional
public class IssueServiceImpl extends AbstractService<Issue> implements
IIssueService {

	@Autowired
	IIssueJpaDAO dao;

	public IssueServiceImpl() {
		super(Issue.class);
	}

	// API

	// search

	@Override
	public List<Issue> search(
			final ImmutableTriple<String, ClientOperation, String>... constraints) {
		final Specification<Issue> firstSpec = SearchSecUtil.resolveConstraint(
				constraints[0], Issue.class);

		Specifications<Issue> specifications = Specifications
				.where(firstSpec);
		for( int i = 1; i < constraints.length; i++ ){
			specifications = specifications.and(SearchSecUtil
					.resolveConstraint(constraints[i], Issue.class));
		}

		if( firstSpec == null ){
			return Lists.newArrayList();
		}

		return getDao().findAll(specifications);
	}

	// find

	@Override
	@Transactional( readOnly = true )
	public Issue findByName(final String name) {
		return dao.findByName( name );
	}

	// Spring

	@Override
	protected final IIssueJpaDAO getDao() {
		return dao;
	}

	@Override
	public Specification<Issue> resolveConstraint(
			final ImmutableTriple<String, ClientOperation, String> constraint) {
		return SearchSecUtil.resolveConstraint(constraint, Issue.class);
	}

	@Override
	protected JpaSpecificationExecutor<Issue> getSpecificationExecutor() {
		return dao;
	}

	@Override
	public List<Issue> findCurrentUserIssues(final Principal principal) {
		return dao.findAll(Specifications
				.where(IssueSpecifications.notClosedIssues())
				.and(IssueSpecifications.notResolvedIssues())
				.and(IssueSpecifications.userIssues(principal)));
	}

	@Override
	public List<Issue> findCurrentIssues() {
		return dao.findAll(Specifications.where(
				IssueSpecifications.notClosedIssues()).and(
				IssueSpecifications.notResolvedIssues()));
	}

}
