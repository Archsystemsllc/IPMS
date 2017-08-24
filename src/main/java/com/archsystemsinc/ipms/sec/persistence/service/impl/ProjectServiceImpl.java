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
import com.archsystemsinc.ipms.persistence.search.ProjectSpecifications;
import com.archsystemsinc.ipms.persistence.service.AbstractService;
import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.model.Program;
import com.archsystemsinc.ipms.sec.model.Project;
import com.archsystemsinc.ipms.sec.persistence.dao.IProjectJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.IProjectService;
import com.archsystemsinc.ipms.sec.util.SearchSecUtil;
import com.google.common.collect.Lists;

@Service
@Transactional
public class ProjectServiceImpl extends AbstractService<Project> implements
IProjectService {

	@Autowired
	IProjectJpaDAO dao;

	public ProjectServiceImpl() {
		super(Project.class);
	}

	// API

	// search

	@Override
	public List<Project> search(
			final ImmutableTriple<String, ClientOperation, String>... constraints) {
		final Specification<Project> firstSpec = SearchSecUtil
				.resolveConstraint(constraints[0], Project.class);
		Specifications<Project> specifications = Specifications
				.where(firstSpec);
		for( int i = 1; i < constraints.length; i++ ){
			specifications = specifications.and(SearchSecUtil
					.resolveConstraint(constraints[i], Project.class));
		}
		if( firstSpec == null ){
			return Lists.newArrayList();
		}

		return getDao().findAll( specifications );
	}

	// find

	@Override
	@Transactional( readOnly = true )
	public Project findByName(final String name) {
		return dao.findByName( name );
	}

	// Spring

	@Override
	protected final IProjectJpaDAO getDao() {
		return dao;
	}

	@Override
	public Specification<Project> resolveConstraint(
			final ImmutableTriple<String, ClientOperation, String> constraint) {
		return SearchSecUtil.resolveConstraint(constraint, Project.class);
	}

	@Override
	protected JpaSpecificationExecutor<Project> getSpecificationExecutor() {
		return dao;
	}

	@Override
	public List<Project> findActiveUserProjects(final Principal principal) {
		return dao.findAll(Specifications.where(
				ProjectSpecifications.isActiveProjects()).and(
						ProjectSpecifications.userProjects(principal)));
	}

	@Override
	public List<Project> findActiveProjects() {
		return dao.findAll(Specifications.where(ProjectSpecifications
				.isActiveProjects()));
	}

	@Override
	public List<Project> findUserProjects(final Principal currentUser) {
		return dao.findAll(Specifications.where(ProjectSpecifications
				.userProjects(currentUser)));
	}

	@Override
	public List<Project> findByManager(Principal manager) {
		return dao.findByManager(manager);
	}

	@Override
	public List<Project> findByProgram(Program program) {
		return dao.findByProgram(program);
	}

}
