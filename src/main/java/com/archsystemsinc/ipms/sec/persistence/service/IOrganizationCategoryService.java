package com.archsystemsinc.ipms.sec.persistence.service;

import java.util.List;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.OrganizationCategory;
import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.model.Project;

public interface IOrganizationCategoryService extends IService<OrganizationCategory>{

	//List<OrganizationCategory> findOrganizationCategoryByProjectId(Project project);
	//OrganizationCategory findById(final long id);

	OrganizationCategory findById(Long id);
	
	List<OrganizationCategory> findByResourceProject(Project project, Principal resourceId);
}

