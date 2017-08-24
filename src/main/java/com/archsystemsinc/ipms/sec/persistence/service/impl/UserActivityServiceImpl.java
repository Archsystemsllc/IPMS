package com.archsystemsinc.ipms.sec.persistence.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.archsystemsinc.ipms.persistence.service.AbstractService;
import com.archsystemsinc.ipms.sec.model.UserActivity;
import com.archsystemsinc.ipms.sec.persistence.dao.IUserActivityJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.IUserActivityService;

@Service
@Transactional
public class UserActivityServiceImpl extends AbstractService<UserActivity>
implements IUserActivityService {

	@Autowired
	IUserActivityJpaDAO dao;

	public UserActivityServiceImpl() {
		super(UserActivity.class);
	}

	@Override
	protected final IUserActivityJpaDAO getDao() {
		return dao;
	}

	@Override
	protected JpaSpecificationExecutor<UserActivity> getSpecificationExecutor() {
		return dao;
	}
	
	@Override
	public List<UserActivity> findByEntityType(String type) {
		return dao.findByEntityType(type);
	}
	
	@Override
	public List<UserActivity> findByEntityTypeInOrderByDateCreatedDesc(List<String> entityTypes) {
		return dao.findByEntityTypeInOrderByDateCreatedDesc(entityTypes, new PageRequest(0, 20));
	}

}
