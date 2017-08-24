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
import com.archsystemsinc.ipms.sec.model.Meeting;
import com.archsystemsinc.ipms.sec.model.MeetingAgendaItem;
import com.archsystemsinc.ipms.sec.persistence.dao.IMeetingJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.IMeetingService;
import com.archsystemsinc.ipms.sec.util.SearchSecUtil;
import com.google.common.collect.Lists;

@Service
@Transactional
public class MeetingServiceImpl extends AbstractService<Meeting> implements
IMeetingService {

	@Autowired
	IMeetingJpaDAO dao;

	public MeetingServiceImpl() {
		super(Meeting.class);
	}

	// API

	// search

	@Override
	public List<Meeting> search(
			final ImmutableTriple<String, ClientOperation, String>... constraints) {

		final Specification<Meeting> firstSpec = SearchSecUtil
				.resolveConstraint(constraints[0], Meeting.class);

		Specifications<Meeting> specifications = Specifications
				.where(firstSpec);

		for( int i = 1; i < constraints.length; i++ ){
			specifications = specifications.and(SearchSecUtil
					.resolveConstraint(constraints[i], Meeting.class));
		}

		if( firstSpec == null ){
			return Lists.newArrayList();
		}

		return getDao().findAll(specifications);
	}

	// find

	@Override
	@Transactional( readOnly = true )
	public Meeting findByName(final String name) {
		return dao.findByName( name );
	}

	// Spring

	@Override
	protected final IMeetingJpaDAO getDao() {
		return dao;
	}

	@Override
	public Specification<Meeting> resolveConstraint(
			final ImmutableTriple<String, ClientOperation, String> constraint) {
		return SearchSecUtil.resolveConstraint(constraint, Meeting.class);
	}

	@Override
	protected JpaSpecificationExecutor<Meeting> getSpecificationExecutor() {
		return dao;
	}

	@Override
	public List<Meeting> findByInvited(final String name) {
		return dao.findByInvited(name);
	}

	@Override
	public void update(MeetingAgendaItem meetingAgendaItem) {
		// TODO Auto-generated method stub
		
	}


}
