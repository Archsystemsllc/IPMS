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
import com.archsystemsinc.ipms.persistence.search.ModelSpecifications;
import com.archsystemsinc.ipms.persistence.search.ProgramSpecifications;
import com.archsystemsinc.ipms.persistence.service.AbstractService;
import com.archsystemsinc.ipms.sec.model.ModelIPMS;
import com.archsystemsinc.ipms.sec.model.OrganizationGroup;
import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.model.Program;
import com.archsystemsinc.ipms.sec.persistence.dao.IModelJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.dao.IProgramJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.IModelService;
import com.archsystemsinc.ipms.sec.persistence.service.IProgramService;
import com.archsystemsinc.ipms.sec.util.SearchSecUtil;
import com.google.common.collect.Lists;

@Service
@Transactional
public class ModelServiceImpl extends AbstractService<ModelIPMS> implements
IModelService{
	@Autowired
	IModelJpaDAO dao;

	public ModelServiceImpl() {
		super(ModelIPMS.class);
		
	}
	
	// API

		// search

		@Override
		public List<ModelIPMS> search(
				final ImmutableTriple<String, ClientOperation, String>... constraints) {
			final Specification<ModelIPMS> firstSpec = SearchSecUtil.resolveConstraint(constraints[0], ModelIPMS.class);

			Specifications<ModelIPMS> specifications = Specifications
					.where(firstSpec);
			for( int i = 1; i < constraints.length; i++ ){
				specifications = specifications.and(SearchSecUtil
						.resolveConstraint(constraints[i], ModelIPMS.class));
			}

			if( firstSpec == null ){
				return Lists.newArrayList();
			}

			return getDao().findAll(specifications);
		}
		// find

		@Override
		@Transactional( readOnly = true )
		public ModelIPMS findByName(final String name) {
			return dao.findByName( name );
		}

		// Spring

		@Override
		protected final IModelJpaDAO getDao() {
			return dao;
		}

		@Override
		public Specification<ModelIPMS> resolveConstraint(
				final ImmutableTriple<String, ClientOperation, String> constraint) {
			return SearchSecUtil.resolveConstraint(constraint, ModelIPMS.class);
		}

		@Override
		protected JpaSpecificationExecutor<ModelIPMS> getSpecificationExecutor() {
			return dao;
		}

		@Override
		public List<ModelIPMS> findActiveUserModels(final Principal principal) {
			return dao.findAll(Specifications.where(
					ModelSpecifications.isActiveModels()).and(
							ModelSpecifications.userModels(principal)));
		}

		@Override
		public List<ModelIPMS> findActiveModels() {
			return dao.findAll(Specifications.where(ModelSpecifications
					.isActiveModels()));
		}

		@Override
		public List<ModelIPMS> findUserModels(final Principal currentUser) {
			return dao.findAll(Specifications.where(ModelSpecifications
					.userModels(currentUser)));
		}

		@Override
		public List<ModelIPMS> findModelListByProgram(Program program) {
			return dao.findModelListByProgram(program);
		}


}
