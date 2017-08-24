package com.archsystemsinc.ipms.sec.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.archsystemsinc.ipms.sec.model.Project;
import com.archsystemsinc.ipms.sec.model.ProjectEvm;

public interface IProjectEvmJpaDAO extends JpaRepository<ProjectEvm, Long>,
		JpaSpecificationExecutor<ProjectEvm> {

	ProjectEvm findByName(final String name);

	List<ProjectEvm> findByProject(Project project);
	
	List<ProjectEvm> findByProjectOrderByDateAsc(Project project);
	
}
