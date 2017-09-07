package com.archsystemsinc.ipms.sec.persistence.service.impl;

import java.util.List;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.hibernate.dialect.function.TrimFunctionTemplate.Specification;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.archsystemsinc.ipms.common.ClientOperation;
import com.archsystemsinc.ipms.persistence.search.OrganizationCategorySpecifications;
import com.archsystemsinc.ipms.persistence.service.AbstractService;
import com.archsystemsinc.ipms.sec.model.LessonsLearned;
import com.archsystemsinc.ipms.sec.model.OrganizationCategory;
import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.model.Project;
import com.archsystemsinc.ipms.sec.persistence.dao.IOrganizationCategoryJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.IOrganizationCategoryService;
import com.archsystemsinc.ipms.sec.util.SearchSecUtil;

@Service
@Transactional
public class OrganizationCategoryServiceImpl extends AbstractService<OrganizationCategory> implements IOrganizationCategoryService {
	
	@Autowired
	IOrganizationCategoryJpaDAO dao;
	
	public OrganizationCategoryServiceImpl(){
		super(OrganizationCategory.class);
	}
	
	@Override
	protected final IOrganizationCategoryJpaDAO getDao(){
		return dao;
	}
	
	@Override
	public org.springframework.data.jpa.domain.Specification<OrganizationCategory> resolveConstraint
	(final ImmutableTriple<String, ClientOperation, String> constraint) {
		return SearchSecUtil.resolveConstraint(constraint, OrganizationCategory.class);
	}
	
	@Override
	protected JpaSpecificationExecutor<OrganizationCategory> getSpecificationExecutor(){
		return dao;
	}
	
	@Override
	public OrganizationCategory findById(Long id){
		return getDao().findById(id);
	}
	
	@Override
	public List<OrganizationCategory> findByResourceProject(Project project, Principal resourceId) {
		
		return dao.findAll(Specifications.where
				(OrganizationCategorySpecifications.organizationCategorysForProject(project)).and
				(OrganizationCategorySpecifications.organizationCategorysForAssigned(resourceId)));
	}
}

