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
import com.archsystemsinc.ipms.sec.model.MeetingAgendaItem;
import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.persistence.dao.IMeetingAgendaItemJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.IMeetingAgendaItemService;
import com.archsystemsinc.ipms.sec.util.SearchSecUtil;
import com.google.common.collect.Lists;

@Service
@Transactional
public class MeetingAgendaItemServiceImpl extends AbstractService<MeetingAgendaItem>
implements IMeetingAgendaItemService {

	@Autowired
	public IMeetingAgendaItemJpaDAO dao;

	public MeetingAgendaItemServiceImpl() {
		super(MeetingAgendaItem.class);
	}

	// API

	// search
	@Override
	public List<MeetingAgendaItem> search(
			final ImmutableTriple<String, ClientOperation, String>... constraints) {
		final Specification<MeetingAgendaItem> firstSpec = SearchSecUtil
				.resolveConstraint(constraints[0], MeetingAgendaItem.class);
		Specifications<MeetingAgendaItem> specifications = Specifications
				.where(firstSpec);
		for( int i = 1; i < constraints.length; i++ ){
			specifications = specifications.and(SearchSecUtil
					.resolveConstraint(constraints[i], MeetingAgendaItem.class));
		}
		if( firstSpec == null ){
			return Lists.newArrayList();
		}

		return getDao().findAll( specifications );
	}

	// find
   

	@Override
	@Transactional( readOnly = true )
	public MeetingAgendaItem findByName(final String name) {
		return dao.findByName( name );
	}

	// Spring

	@Override
	protected final IMeetingAgendaItemJpaDAO getDao() {
		return dao;
	}

	@Override
	public Specification<MeetingAgendaItem> resolveConstraint(
			final ImmutableTriple<String, ClientOperation, String> constraint) {
		return SearchSecUtil.resolveConstraint(constraint, MeetingAgendaItem.class);
	}

	@Override
	protected JpaSpecificationExecutor<MeetingAgendaItem> getSpecificationExecutor() {
		return dao;
	}

	@Override
	public void add(MeetingAgendaItem meetingAgendaItem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MeetingAgendaItem findOne(MeetingAgendaItem topic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MeetingAgendaItem> findCurrentUserMeetingAgendaItem(
			Principal principal) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
