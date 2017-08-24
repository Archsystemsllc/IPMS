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
import com.archsystemsinc.ipms.sec.model.Attendee;
import com.archsystemsinc.ipms.sec.persistence.dao.IAttendeeJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.IAttendeeService;
import com.archsystemsinc.ipms.sec.util.SearchSecUtil;
import com.google.common.collect.Lists;

@Service
@Transactional
public class AttendeeServiceImpl extends AbstractService<Attendee> implements
IAttendeeService {

	@Autowired
	public IAttendeeJpaDAO dao;

	public AttendeeServiceImpl() {
		super(Attendee.class);
	}

	// API

	// search
	@Override
	public List<Attendee> search(
			final ImmutableTriple<String, ClientOperation, String>... constraints) {
		final Specification<Attendee> firstSpec = SearchSecUtil
				.resolveConstraint(constraints[0], Attendee.class);
		Specifications<Attendee> specifications = Specifications
				.where(firstSpec);
		for( int i = 1; i < constraints.length; i++ ){
			specifications = specifications.and(SearchSecUtil
					.resolveConstraint(constraints[i], Attendee.class));
		}
		if( firstSpec == null ){
			return Lists.newArrayList();
		}

		return getDao().findAll( specifications );
	}

	// find

	@Override
	@Transactional( readOnly = true )
	public Attendee findByName(final String name) {
		return dao.findByName( name );
	}

	// Spring

	@Override
	protected final IAttendeeJpaDAO getDao() {
		return dao;
	}

	@Override
	public Specification<Attendee> resolveConstraint(
			final ImmutableTriple<String, ClientOperation, String> constraint) {
		return SearchSecUtil.resolveConstraint(constraint, Attendee.class);
	}

	@Override
	protected JpaSpecificationExecutor<Attendee> getSpecificationExecutor() {
		return dao;
	}

}
