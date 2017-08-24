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
import com.archsystemsinc.ipms.sec.model.MeetingMinutes;
import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.persistence.dao.IActionItemJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.dao.IMeetingMinutesJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.IActionItemService;
import com.archsystemsinc.ipms.sec.persistence.service.IMeetingMinutesService;
import com.archsystemsinc.ipms.sec.util.SearchSecUtil;
import com.google.common.collect.Lists;

@Service
@Transactional
public class MeetingMinutesServiceImpl extends AbstractService<MeetingMinutes>
implements IMeetingMinutesService {

	@Autowired
	IMeetingMinutesJpaDAO dao;

	public MeetingMinutesServiceImpl() {
		super(MeetingMinutes.class);
	}

	// API

	// search

	@Override
	public List<MeetingMinutes> search(
			final ImmutableTriple<String, ClientOperation, String>... constraints) {
		final Specification<MeetingMinutes> firstSpec = SearchSecUtil
				.resolveConstraint(constraints[0], MeetingMinutes.class);

		Specifications<MeetingMinutes> specifications = Specifications
				.where(firstSpec);
		for( int i = 1; i < constraints.length; i++ ){
			specifications = specifications.and(SearchSecUtil
					.resolveConstraint(constraints[i], MeetingMinutes.class));
		}

		if( firstSpec == null ){
			return Lists.newArrayList();
		}

		return getDao().findAll(specifications);
	}

	// find

	@Override
	@Transactional( readOnly = true )
	public MeetingMinutes findByName(final String name) {
		return dao.findByName( name );
	}

	// Spring

	@Override
	protected final IMeetingMinutesJpaDAO getDao() {
		return dao;
	}

	@Override
	public Specification<MeetingMinutes> resolveConstraint(
			final ImmutableTriple<String, ClientOperation, String> constraint) {
		return SearchSecUtil
				.resolveConstraint(constraint, MeetingMinutes.class);
	}

	@Override
	protected JpaSpecificationExecutor<MeetingMinutes> getSpecificationExecutor() {
		return dao;
	}

	
	
}
