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
import com.archsystemsinc.ipms.sec.model.MeetingMinutes;
import com.archsystemsinc.ipms.sec.model.ParkingLot;
import com.archsystemsinc.ipms.sec.persistence.dao.IMeetingMinutesJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.dao.IParkingLotJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.IMeetingMinutesService;
import com.archsystemsinc.ipms.sec.persistence.service.IParkingLotService;
import com.archsystemsinc.ipms.sec.util.SearchSecUtil;
import com.google.common.collect.Lists;



@Service
@Transactional
public class ParkingLotServiceImpl extends AbstractService<ParkingLot>
implements IParkingLotService {

	@Autowired
	IParkingLotJpaDAO dao;

	public ParkingLotServiceImpl() {
		super(ParkingLot.class);
	}

	// API

	// search

	@Override
	public List<ParkingLot> search(
			final ImmutableTriple<String, ClientOperation, String>... constraints) {
		final Specification<ParkingLot> firstSpec = SearchSecUtil
				.resolveConstraint(constraints[0], ParkingLot.class);

		Specifications<ParkingLot> specifications = Specifications
				.where(firstSpec);
		for( int i = 1; i < constraints.length; i++ ){
			specifications = specifications.and(SearchSecUtil
					.resolveConstraint(constraints[i], ParkingLot.class));
		}

		if( firstSpec == null ){
			return Lists.newArrayList();
		}

		return getDao().findAll(specifications);
	}

	// find


	// Spring

	@Override
	protected final IParkingLotJpaDAO getDao() {
		return dao;
	}

	@Override
	public Specification<ParkingLot> resolveConstraint(
			final ImmutableTriple<String, ClientOperation, String> constraint) {
		return SearchSecUtil
				.resolveConstraint(constraint, ParkingLot.class);
	}

	@Override
	protected JpaSpecificationExecutor<ParkingLot> getSpecificationExecutor() {
		return dao;
	}

	@Override
	public void add(ParkingLot parkinglot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ParkingLot findOne(ParkingLot topic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ParkingLot findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
