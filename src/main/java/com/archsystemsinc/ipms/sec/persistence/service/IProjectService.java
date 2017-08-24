package com.archsystemsinc.ipms.sec.persistence.service;


import java.util.List;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.model.Program;
import com.archsystemsinc.ipms.sec.model.Project;

public interface IProjectService extends IService<Project> {

	Project findByName(final String name);

	List<Project> findActiveUserProjects(Principal principal);

	List<Project> findActiveProjects();

	List<Project> findUserProjects(Principal currentUser);
	
	List<Project> findByManager(Principal manager);
	
	List<Project> findByProgram(Program program);

}
