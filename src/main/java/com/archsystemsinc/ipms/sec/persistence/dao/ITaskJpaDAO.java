package com.archsystemsinc.ipms.sec.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.archsystemsinc.ipms.sec.model.Project;
import com.archsystemsinc.ipms.sec.model.Task;

public interface ITaskJpaDAO extends JpaRepository<Task, Long>,
JpaSpecificationExecutor<Task> {

	Task findByName(final String name);

	List<Task> findByNameAndProject(String name, Project project);
	
	List<Task> findByMsProjectParentTaskId(Integer Id);
	
	List<Task> findByMsProjectParentTaskIdIsNull();
}
