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
import com.archsystemsinc.ipms.sec.model.LessonsLearned;
import com.archsystemsinc.ipms.sec.persistence.dao.ILessonsLearnedJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.ILessonsLearnedService;
import com.archsystemsinc.ipms.sec.util.SearchSecUtil;
import com.google.common.collect.Lists;

@Service
@Transactional
public class LessonsLearnedServiceImpl extends AbstractService<LessonsLearned>
		implements ILessonsLearnedService {

	@Autowired
	ILessonsLearnedJpaDAO dao;

	public LessonsLearnedServiceImpl() {
		super(LessonsLearned.class);
	}

	// API

	// search

	@Override
	public List<LessonsLearned> search(
			final ImmutableTriple<String, ClientOperation, String>... constraints) {
		final Specification<LessonsLearned> firstSpec = SearchSecUtil
				.resolveConstraint(constraints[0], LessonsLearned.class);

		Specifications<LessonsLearned> specifications = Specifications
				.where(firstSpec);
		for( int i = 1; i < constraints.length; i++ ){
			specifications = specifications.and(SearchSecUtil
					.resolveConstraint(constraints[i], LessonsLearned.class));
		}

		if( firstSpec == null ){
			return Lists.newArrayList();
		}

		return getDao().findAll(specifications);
	}

	// find

	@Override
	@Transactional( readOnly = true )
	public LessonsLearned findByName(final String name) {
		return dao.findByName( name );
	}

	// Spring

	@Override
	protected final ILessonsLearnedJpaDAO getDao() {
		return dao;
	}

	@Override
	public Specification<LessonsLearned> resolveConstraint(
			final ImmutableTriple<String, ClientOperation, String> constraint) {
		return SearchSecUtil
				.resolveConstraint(constraint, LessonsLearned.class);
	}

	@Override
	protected JpaSpecificationExecutor<LessonsLearned> getSpecificationExecutor() {
		return dao;
	}

	@Override
	public LessonsLearned findById(Long id) {			
		return getDao().findById(id);
	}

}
