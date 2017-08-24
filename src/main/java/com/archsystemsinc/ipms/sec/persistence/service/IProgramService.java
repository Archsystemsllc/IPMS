package com.archsystemsinc.ipms.sec.persistence.service;


import java.util.List;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.OrganizationGroup;
import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.model.Program;

public interface IProgramService extends IService<Program> {

	Program findByName(final String name);

	List<Program> findActiveUserPrograms(Principal principal);

	List<Program> findActivePrograms();

	List<Program> findUserPrograms(Principal currentUser);
	
	List<Program> findProgramListByOrganizationGroup(OrganizationGroup organizationGroup);
}
