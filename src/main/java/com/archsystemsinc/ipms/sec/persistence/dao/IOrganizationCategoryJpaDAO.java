package com.archsystemsinc.ipms.sec.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.archsystemsinc.ipms.sec.model.OrganizationCategory;
import com.archsystemsinc.ipms.sec.model.Project;

public interface IOrganizationCategoryJpaDAO extends JpaRepository<OrganizationCategory,Long>, 
JpaSpecificationExecutor<OrganizationCategory> {
	
//	List<OrganizationCategory> findOrganizationCategoryByProjectId(final Project project);
	OrganizationCategory findById(final Long id);

}
