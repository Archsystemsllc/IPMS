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
import com.archsystemsinc.ipms.sec.persistence.dao.IActionItemJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.IActionItemService;
import com.archsystemsinc.ipms.sec.util.SearchSecUtil;
import com.google.common.collect.Lists;

@Service
@Transactional
public class ActionItemServiceImpl extends AbstractService<ActionItem>
implements IActionItemService {

	@Autowired
	IActionItemJpaDAO dao;

	public ActionItemServiceImpl() {
		super(ActionItem.class);
	}

	// API

	// search

	@Override
	public List<ActionItem> search(
			final ImmutableTriple<String, ClientOperation, String>... constraints) {
		final Specification<ActionItem> firstSpec = SearchSecUtil
				.resolveConstraint(constraints[0], ActionItem.class);

		Specifications<ActionItem> specifications = Specifications
				.where(firstSpec);
		for( int i = 1; i < constraints.length; i++ ){
			specifications = specifications.and(SearchSecUtil
					.resolveConstraint(constraints[i], ActionItem.class));
		}

		if( firstSpec == null ){
			return Lists.newArrayList();
		}

		return getDao().findAll(specifications);
	}

	// find

	@Override
	@Transactional( readOnly = true )
	public ActionItem findByName(final String name) {
		return dao.findByName( name );
	}

	// Spring

	@Override
	protected final IActionItemJpaDAO getDao() {
		return dao;
	}

	@Override
	public Specification<ActionItem> resolveConstraint(
			final ImmutableTriple<String, ClientOperation, String> constraint) {
		return SearchSecUtil
				.resolveConstraint(constraint, ActionItem.class);
	}

	@Override
	protected JpaSpecificationExecutor<ActionItem> getSpecificationExecutor() {
		return dao;
	}

	@Override
	public List<ActionItem> findCurrentUserActionItems(final Principal principal) {
		return dao.findAll(Specifications
				.where(ActionItemSpecifications.notClosedActionItems())
				.and(ActionItemSpecifications.notCompletedActionItems())
				.and(ActionItemSpecifications.userActionItems(principal)));
	}

	@Override
	public List<ActionItem> findCurrentActionItems() {
		return dao.findAll(Specifications.where(
				ActionItemSpecifications.notClosedActionItems()).and(
						ActionItemSpecifications.notCompletedActionItems()));
	}

}
