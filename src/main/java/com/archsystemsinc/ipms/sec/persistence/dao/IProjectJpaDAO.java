package com.archsystemsinc.ipms.sec.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.model.Program;
import com.archsystemsinc.ipms.sec.model.Project;

public interface IProjectJpaDAO extends JpaRepository<Project, Long>,
JpaSpecificationExecutor<Project> {

	Project findByName(final String name);
	
	List<Project> findByManager(Principal manager);
	
	List<Project> findByProgram(Program program);
}
