package com.archsystemsinc.ipms.sec.persistence.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.archsystemsinc.ipms.common.ClientOperation;
import com.archsystemsinc.ipms.persistence.search.ProgramSpecifications;
import com.archsystemsinc.ipms.persistence.search.ProjectEvmSpecifications;
import com.archsystemsinc.ipms.persistence.service.AbstractService;
import com.archsystemsinc.ipms.sec.model.Project;
import com.archsystemsinc.ipms.sec.model.ProjectEvm;
import com.archsystemsinc.ipms.sec.persistence.dao.IProjectEvmJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.IProjectEvmService;
import com.archsystemsinc.ipms.sec.util.SearchSecUtil;
import com.google.common.collect.Lists;

@Service
@Transactional
public class ProjectEvmServiceImpl extends AbstractService<ProjectEvm> implements
		IProjectEvmService {

	@Autowired
	public IProjectEvmJpaDAO dao;

	public ProjectEvmServiceImpl() {
		super(ProjectEvm.class);
	}

	// API

	// search
	@Override
	public List<ProjectEvm> search(
			final ImmutableTriple<String, ClientOperation, String>... constraints) {
		final Specification<ProjectEvm> firstSpec = SearchSecUtil
				.resolveConstraint(constraints[0], ProjectEvm.class);
		Specifications<ProjectEvm> specifications = Specifications
				.where(firstSpec);
		for( int i = 1; i < constraints.length; i++ ){
			specifications = specifications.and(SearchSecUtil
					.resolveConstraint(constraints[i], ProjectEvm.class));
		}
		if( firstSpec == null ){
			return Lists.newArrayList();
		}

		return getDao().findAll( specifications );
	}

	// find

	@Override
	@Transactional( readOnly = true )
	public ProjectEvm findByName(final String name) {
		return dao.findByName( name );
	}

	// Spring

	@Override
	protected final IProjectEvmJpaDAO getDao() {
		return dao;
	}

	@Override
	public Specification<ProjectEvm> resolveConstraint(
			final ImmutableTriple<String, ClientOperation, String> constraint) {
		return SearchSecUtil.resolveConstraint(constraint, ProjectEvm.class);
	}

	@Override
	protected JpaSpecificationExecutor<ProjectEvm> getSpecificationExecutor() {
		return dao;
	}

	@Override
	public List<ProjectEvm> findByProject(Project project) {
		return dao.findByProject(project);
	}

	@Override
	public List<ProjectEvm> findProjectEvmBetween(Date startDate, Date endDate, Project project) {
		return dao.findAll(Specifications.where
				(ProjectEvmSpecifications.greaterThan(startDate)).and
				(ProjectEvmSpecifications.lessThan(endDate)).and
				(ProjectEvmSpecifications.projectEvm(project)));
	}
	
	@Override
	public List<ProjectEvm> findProjectEvm(Date date, Project project) {
		return dao.findAll(Specifications.where
				(ProjectEvmSpecifications.forDate(date)).and
				(ProjectEvmSpecifications.projectEvm(project)));
	}

	@Override
	public List<ProjectEvm> findByProjectOrderByDateAsc(Project project) {
		return dao.findByProjectOrderByDateAsc(project);
	}
}
