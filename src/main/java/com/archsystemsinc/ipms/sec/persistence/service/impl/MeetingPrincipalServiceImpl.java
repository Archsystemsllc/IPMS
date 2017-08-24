package com.archsystemsinc.ipms.sec.persistence.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.archsystemsinc.ipms.persistence.service.AbstractService;
import com.archsystemsinc.ipms.sec.model.MeetingPrincipal;
import com.archsystemsinc.ipms.sec.persistence.dao.IMeetingPrincipalJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.IMeetingPrincipalService;

@Service
@Transactional
public class MeetingPrincipalServiceImpl extends
AbstractService<MeetingPrincipal>
implements IMeetingPrincipalService {

	@Autowired
	IMeetingPrincipalJpaDAO dao;

	public MeetingPrincipalServiceImpl() {
		super(MeetingPrincipal.class);
	}

	// API

	// search



	// find



	// Spring
	@Override
	protected final IMeetingPrincipalJpaDAO getDao() {
		return dao;
	}



	@Override
	protected JpaSpecificationExecutor<MeetingPrincipal> getSpecificationExecutor() {
		return dao;
	}

	@Override
	public List<MeetingPrincipal> findByMeeting(final Long meetingId) {
		return dao.findByMeeting(meetingId);
	}

}
