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
import com.archsystemsinc.ipms.sec.model.DiscussionAgenda;
import com.archsystemsinc.ipms.sec.model.ParkingLot;
import com.archsystemsinc.ipms.sec.persistence.dao.IDiscussionAgendaJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.dao.IParkingLotJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.IDiscussionAgendaService;
import com.archsystemsinc.ipms.sec.persistence.service.IParkingLotService;
import com.archsystemsinc.ipms.sec.util.SearchSecUtil;
import com.google.common.collect.Lists;


@Service
@Transactional
public class DiscussionAgendaServiceImpl extends AbstractService<DiscussionAgenda>
implements IDiscussionAgendaService {

	@Autowired
	IDiscussionAgendaJpaDAO dao;

	public DiscussionAgendaServiceImpl() {
		super(DiscussionAgenda.class);
	}

	// API

	// search

	@Override
	public List<DiscussionAgenda> search(
			final ImmutableTriple<String, ClientOperation, String>... constraints) {
		final Specification<DiscussionAgenda> firstSpec = SearchSecUtil
				.resolveConstraint(constraints[0], DiscussionAgenda.class);

		Specifications<DiscussionAgenda> specifications = Specifications
				.where(firstSpec);
		for( int i = 1; i < constraints.length; i++ ){
			specifications = specifications.and(SearchSecUtil
					.resolveConstraint(constraints[i], DiscussionAgenda.class));
		}

		if( firstSpec == null ){
			return Lists.newArrayList();
		}

		return getDao().findAll(specifications);
	}



	// Spring

	@Override
	protected final IDiscussionAgendaJpaDAO getDao() {
		return dao;
	}

	@Override
	public Specification<DiscussionAgenda> resolveConstraint(
			final ImmutableTriple<String, ClientOperation, String> constraint) {
		return SearchSecUtil
				.resolveConstraint(constraint, DiscussionAgenda.class);
	}

	@Override
	protected JpaSpecificationExecutor<DiscussionAgenda> getSpecificationExecutor() {
		return dao;
	}

	@Override
	public void add(DiscussionAgenda discussionagenda) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DiscussionAgenda findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
