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
import com.archsystemsinc.ipms.sec.model.Comment;
import com.archsystemsinc.ipms.sec.persistence.dao.ICommentJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.ICommentService;
import com.archsystemsinc.ipms.sec.util.SearchSecUtil;
import com.google.common.collect.Lists;

@Service
@Transactional
public class CommentServiceImpl extends AbstractService<Comment> implements
		ICommentService {

	@Autowired
	public ICommentJpaDAO dao;

	public CommentServiceImpl() {
		super(Comment.class);
	}

	// API

	// search
	@Override
	public List<Comment> search(
			final ImmutableTriple<String, ClientOperation, String>... constraints) {
		final Specification<Comment> firstSpec = SearchSecUtil
				.resolveConstraint(constraints[0], Comment.class);
		Specifications<Comment> specifications = Specifications
				.where(firstSpec);
		for( int i = 1; i < constraints.length; i++ ){
			specifications = specifications.and(SearchSecUtil
					.resolveConstraint(constraints[i], Comment.class));
		}
		if( firstSpec == null ){
			return Lists.newArrayList();
		}

		return getDao().findAll( specifications );
	}

	// find

	@Override
	@Transactional( readOnly = true )
	public Comment findByName(final String name) {
		return dao.findByName( name );
	}

	// Spring

	@Override
	protected final ICommentJpaDAO getDao() {
		return dao;
	}

	@Override
	public Specification<Comment> resolveConstraint(
			final ImmutableTriple<String, ClientOperation, String> constraint) {
		return SearchSecUtil.resolveConstraint(constraint, Comment.class);
	}

	@Override
	protected JpaSpecificationExecutor<Comment> getSpecificationExecutor() {
		return dao;
	}

}
