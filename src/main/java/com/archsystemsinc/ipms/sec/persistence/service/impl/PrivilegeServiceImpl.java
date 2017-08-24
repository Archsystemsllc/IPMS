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
import com.archsystemsinc.ipms.sec.model.Privilege;
import com.archsystemsinc.ipms.sec.persistence.dao.IPrivilegeJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.IPrivilegeService;
import com.archsystemsinc.ipms.sec.util.SearchSecUtil;
import com.google.common.collect.Lists;

@Service
@Transactional
public class PrivilegeServiceImpl extends AbstractService< Privilege > implements IPrivilegeService{

	@Autowired
	public IPrivilegeJpaDAO dao;

	public PrivilegeServiceImpl(){
		super( Privilege.class );
	}

	// API

	// search

	@Override
	public List< Privilege > search( final ImmutableTriple< String, ClientOperation, String >... constraints ){
		final Specification< Privilege > firstSpec = SearchSecUtil.resolveConstraint( constraints[0], Privilege.class );
		Specifications< Privilege > specifications = Specifications.where( firstSpec );
		for( int i = 1; i < constraints.length; i++ ){
			specifications = specifications.and( SearchSecUtil.resolveConstraint( constraints[i], Privilege.class ) );
		}
		if( firstSpec == null ){
			return Lists.newArrayList();
		}

		return getDao().findAll( specifications );
	}

	// find

	@Override
	public Privilege findByName( final String name ){
		return getDao().findByName( name );
	}

	// Spring

	@Override
	protected final IPrivilegeJpaDAO getDao(){
		return dao;
	}

	@Override
	public Specification< Privilege > resolveConstraint( final ImmutableTriple< String, ClientOperation, String > constraint ){
		return SearchSecUtil.resolveConstraint( constraint, Privilege.class );
	}

	@Override
	protected JpaSpecificationExecutor< Privilege > getSpecificationExecutor(){
		return dao;
	}

}
