package com.archsystemsinc.ipms.sec.persistence.service;

import java.util.Date;
import java.util.List;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.Project;
import com.archsystemsinc.ipms.sec.model.ProjectEvm;

public interface IProjectEvmService extends IService<ProjectEvm> {

	ProjectEvm findByName(final String name);

	List<ProjectEvm> findByProject(Project project);
	
	List<ProjectEvm> findByProjectOrderByDateAsc(Project project);

	List<ProjectEvm> findProjectEvmBetween(Date startDate, Date endDate,Project Project);

	List<ProjectEvm> findProjectEvm(Date date, Project project);
}
