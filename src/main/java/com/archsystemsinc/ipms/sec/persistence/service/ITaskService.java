package com.archsystemsinc.ipms.sec.persistence.service;


import java.util.Date;
import java.util.List;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.model.Project;
import com.archsystemsinc.ipms.sec.model.ProjectEvm;
import com.archsystemsinc.ipms.sec.model.Task;

public interface ITaskService extends IService<Task> {

	Task findByName(final String name);
	
	//List<Task> findByName(final String name);

	List<Task> findCurrentUserTasks(Principal principal);

	List<Task> findCurrentTasks();

	List<Task> findByNameAndProject(String name, Project project);
	
	List<Task> findByMsProjectParentTaskIdIsNull();
	
	List<Task> findByMsProjectParentTaskId(Integer Id);

	List<Task> findResolvedTasksBetweenForProject(Date startDate, Date endDate, Project project);
	}
