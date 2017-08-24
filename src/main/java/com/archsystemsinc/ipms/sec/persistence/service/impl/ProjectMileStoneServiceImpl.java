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
import com.archsystemsinc.ipms.sec.model.Project;
import com.archsystemsinc.ipms.sec.model.ProjectMileStone;
import com.archsystemsinc.ipms.sec.persistence.dao.IProjectMileStoneJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.IProjectMileStoneService;
import com.archsystemsinc.ipms.sec.util.SearchSecUtil;
import com.google.common.collect.Lists;

@Service
@Transactional
public class ProjectMileStoneServiceImpl extends AbstractService<ProjectMileStone>
implements IProjectMileStoneService {

	@Autowired
	IProjectMileStoneJpaDAO dao;

	public ProjectMileStoneServiceImpl() {
		super(ProjectMileStone.class);
	}

	// API

	// search

	@Override
	public List<ProjectMileStone> search(
			final ImmutableTriple<String, ClientOperation, String>... constraints) {
		final Specification<ProjectMileStone> firstSpec = SearchSecUtil
				.resolveConstraint(constraints[0], ProjectMileStone.class);

		Specifications<ProjectMileStone> specifications = Specifications
				.where(firstSpec);
		for( int i = 1; i < constraints.length; i++ ){
			specifications = specifications.and(SearchSecUtil
					.resolveConstraint(constraints[i], ProjectMileStone.class));
		}

		if( firstSpec == null ){
			return Lists.newArrayList();
		}

		return getDao().findAll(specifications);
	}

	// find

	@Override
	@Transactional( readOnly = true )
	public ProjectMileStone findByName(final String name) {
		return dao.findByName( name );
	}

	// Spring

	@Override
	protected final IProjectMileStoneJpaDAO getDao() {
		return dao;
	}

	@Override
	public Specification<ProjectMileStone> resolveConstraint(
			final ImmutableTriple<String, ClientOperation, String> constraint) {
		return SearchSecUtil
				.resolveConstraint(constraint, ProjectMileStone.class);
	}

	@Override
	protected JpaSpecificationExecutor<ProjectMileStone> getSpecificationExecutor() {
		return dao;
	}

	@Override
	public List<ProjectMileStone> findByProject(Project project) {
		return dao.findByProject(project);
	}

}
