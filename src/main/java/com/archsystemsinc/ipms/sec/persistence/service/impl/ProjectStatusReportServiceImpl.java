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
import com.archsystemsinc.ipms.sec.model.ProjectStatusReport;
import com.archsystemsinc.ipms.sec.persistence.dao.IProjectStatusReportJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.IProjectStatusReportService;
import com.archsystemsinc.ipms.sec.util.SearchSecUtil;
import com.google.common.collect.Lists;

@Service
@Transactional
public class ProjectStatusReportServiceImpl extends AbstractService<ProjectStatusReport>
implements IProjectStatusReportService {

	@Autowired
	IProjectStatusReportJpaDAO dao;

	public ProjectStatusReportServiceImpl() {
		super(ProjectStatusReport.class);
	}

	// API

	// search

	@Override
	public List<ProjectStatusReport> search(
			final ImmutableTriple<String, ClientOperation, String>... constraints) {
		final Specification<ProjectStatusReport> firstSpec = SearchSecUtil
				.resolveConstraint(constraints[0], ProjectStatusReport.class);

		Specifications<ProjectStatusReport> specifications = Specifications
				.where(firstSpec);
		for( int i = 1; i < constraints.length; i++ ){
			specifications = specifications.and(SearchSecUtil
					.resolveConstraint(constraints[i], ProjectStatusReport.class));
		}

		if( firstSpec == null ){
			return Lists.newArrayList();
		}

		return getDao().findAll(specifications);
	}

	// find

	@Override
	@Transactional( readOnly = true )
	public ProjectStatusReport findByName(final String name) {
		return dao.findByName( name );
	}

	// Spring

	@Override
	protected final IProjectStatusReportJpaDAO getDao() {
		return dao;
	}

	@Override
	public Specification<ProjectStatusReport> resolveConstraint(
			final ImmutableTriple<String, ClientOperation, String> constraint) {
		return SearchSecUtil
				.resolveConstraint(constraint, ProjectStatusReport.class);
	}

	@Override
	protected JpaSpecificationExecutor<ProjectStatusReport> getSpecificationExecutor() {
		return dao;
	}

}
