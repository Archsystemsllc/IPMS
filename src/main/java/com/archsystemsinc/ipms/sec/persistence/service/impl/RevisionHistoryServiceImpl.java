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
import com.archsystemsinc.ipms.sec.model.RevisionHistory;
import com.archsystemsinc.ipms.sec.persistence.dao.IRevisionHistoryJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.IRevisionHistoryService;
import com.archsystemsinc.ipms.sec.util.SearchSecUtil;
import com.google.common.collect.Lists;

@Service
@Transactional
public class RevisionHistoryServiceImpl extends
		AbstractService<RevisionHistory> implements IRevisionHistoryService {

	@Autowired
	public IRevisionHistoryJpaDAO dao;

	public RevisionHistoryServiceImpl() {
		super(RevisionHistory.class);
	}

	// API

	// search

	@Override
	public List<RevisionHistory> search(
			final ImmutableTriple<String, ClientOperation, String>... constraints) {
		final Specification<RevisionHistory> firstSpec = SearchSecUtil
				.resolveConstraint(constraints[0], RevisionHistory.class);
		Specifications<RevisionHistory> specifications = Specifications
				.where(firstSpec);
		for( int i = 1; i < constraints.length; i++ ){
			specifications = specifications.and(SearchSecUtil
					.resolveConstraint(constraints[i], RevisionHistory.class));
		}
		if( firstSpec == null ){
			return Lists.newArrayList();
		}

		return getDao().findAll( specifications );
	}

	// find

	@Override
	@Transactional( readOnly = true )
	public RevisionHistory findByName(final String name) {
		return dao.findByName( name );
	}

	// Spring

	@Override
	protected final IRevisionHistoryJpaDAO getDao() {
		return dao;
	}

	@Override
	public Specification<RevisionHistory> resolveConstraint(
			final ImmutableTriple<String, ClientOperation, String> constraint) {
		return SearchSecUtil.resolveConstraint(constraint,
				RevisionHistory.class);
	}

	@Override
	protected JpaSpecificationExecutor<RevisionHistory> getSpecificationExecutor() {
		return dao;
	}
	
	@Override
	public List<RevisionHistory> findByRequirement(final Long requirementId) {
		return dao.findByRequirement(requirementId);
	}

	@Override
	public void bulkCreate(Iterable<RevisionHistory> itms) {
		dao.save(itms);
		
	}

	@Override
	public List<RevisionHistory> findByIssue(Long issueId) {
		return dao.findByIssue(issueId);
	}
	
	@Override
	public List<RevisionHistory> findByTask(Long taskId) {
		return dao.findByTask(taskId);
	}
	
	@Override
	public List<RevisionHistory> findByActionItem(Long actionItemId) {
		return dao.findByActionItem(actionItemId);
	}

	@Override
	public List<RevisionHistory> findByRisk(Long riskId) {
		// TODO Auto-generated method stub
		return dao.findByRisk(riskId);
	}
	
	@Override
	public List<RevisionHistory> findByLessonsLearned(Long lessonsLearnedId) {
		// TODO Auto-generated method stub
		return dao.findByLessonsLearned(lessonsLearnedId);
		
	}



}
