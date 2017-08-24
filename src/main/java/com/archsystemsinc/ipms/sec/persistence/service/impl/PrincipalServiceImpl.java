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
import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.persistence.dao.IPrincipalJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.IPrincipalService;
import com.archsystemsinc.ipms.sec.util.SearchSecUtil;
import com.google.common.collect.Lists;

@Service
@Transactional
public class PrincipalServiceImpl extends AbstractService< Principal > implements IPrincipalService{

	@Autowired
	public IPrincipalJpaDAO dao;

	public PrincipalServiceImpl(){
		super( Principal.class );
	}

	// API

	// search

	@Override
	public List< Principal > search( final ImmutableTriple< String, ClientOperation, String >... constraints ){
		final Specification< Principal > firstSpec = SearchSecUtil.resolveConstraint( constraints[0], Principal.class );
		Specifications< Principal > specifications = Specifications.where( firstSpec );
		for( int i = 1; i < constraints.length; i++ ){
			specifications = specifications.and( SearchSecUtil.resolveConstraint( constraints[i], Principal.class ) );
		}
		if( firstSpec == null ){
			return Lists.newArrayList();
		}

		return getDao().findAll( specifications );
	}

	// find

	@Override
	@Transactional( readOnly = true )
	public Principal findByName( final String name ){
		return dao.findByName( name );
	}

	// Spring

	@Override
	protected final IPrincipalJpaDAO getDao(){
		return dao;
	}

	@Override
	public Specification< Principal > resolveConstraint( final ImmutableTriple< String, ClientOperation, String > constraint ){
		return SearchSecUtil.resolveConstraint( constraint, Principal.class );
	}

	@Override
	protected JpaSpecificationExecutor< Principal > getSpecificationExecutor(){
		return dao;
	}
}
