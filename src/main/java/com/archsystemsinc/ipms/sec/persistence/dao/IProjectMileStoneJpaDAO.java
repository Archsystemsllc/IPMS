package com.archsystemsinc.ipms.sec.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.archsystemsinc.ipms.sec.model.ActionItem;
import com.archsystemsinc.ipms.sec.model.Project;
import com.archsystemsinc.ipms.sec.model.ProjectMileStone;

public interface IProjectMileStoneJpaDAO extends JpaRepository<ProjectMileStone, Long>,
JpaSpecificationExecutor<ProjectMileStone> {

	ProjectMileStone findByName(final String name);
	
	List<ProjectMileStone> findByProject(Project project);
}
