package com.archsystemsinc.ipms.sec.persistence.service.impl;

import java.util.List;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.archsystemsinc.ipms.common.ClientOperation;
import com.archsystemsinc.ipms.persistence.search.TaskSpecifications;
import com.archsystemsinc.ipms.persistence.service.AbstractService;
import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.model.Project;
import com.archsystemsinc.ipms.sec.model.Task;
import com.archsystemsinc.ipms.sec.persistence.dao.ITaskJpaDAO;
import com.archsystemsinc.ipms.sec.persistence.service.ITaskService;
import com.archsystemsinc.ipms.sec.util.SearchSecUtil;
import com.google.common.collect.Lists;

@Service
@Transactional
public class TaskServiceImpl extends AbstractService<Task> implements
ITaskService {

	@Autowired
	ITaskJpaDAO dao;

	public TaskServiceImpl() {
		super(Task.class);
	}

	// API

	// search

	@Override
	public List<Task> search(
			final ImmutableTriple<String, ClientOperation, String>... constraints) {
		final Specification<Task> firstSpec = SearchSecUtil.resolveConstraint(
				constraints[0], Task.class);

		Specifications<Task> specifications = Specifications.where(firstSpec);
		for (int i = 1; i < constraints.length; i++) {
			specifications = specifications.and(SearchSecUtil
					.resolveConstraint(constraints[i], Task.class));
		}

		if (firstSpec == null) {
			return Lists.newArrayList();
		}

		return getDao().findAll(specifications);
	}

	// find

	@Override
	@Transactional(readOnly = true)
	public Task findByName(final String name) {
		return dao.findByName(name);
	}

	// Spring

	@Override
	protected final ITaskJpaDAO getDao() {
		return dao;
	}

	@Override
	public Specification<Task> resolveConstraint(
			final ImmutableTriple<String, ClientOperation, String> constraint) {
		return SearchSecUtil.resolveConstraint(constraint, Task.class);
	}

	@Override
	protected JpaSpecificationExecutor<Task> getSpecificationExecutor() {
		return dao;
	}

	@Override
	public List<Task> findCurrentTasks() {
		return dao.findAll(Specifications.where(
				TaskSpecifications.notClosedTasks()).and(
						TaskSpecifications.notResolvedTasks()));
	}

	@Override
	public List<Task> findCurrentUserTasks(final Principal principal) {
		return dao.findAll(Specifications
				.where(TaskSpecifications.notClosedTasks())
				.and(TaskSpecifications.notResolvedTasks())
				.and(TaskSpecifications.userTasks(principal)));
	}

	@Override
	public List<Task> findByNameAndProject(String name, Project project) {
		return dao.findByNameAndProject(name, project);
	}

	@Override
	public List<Task> findByMsProjectParentTaskIdIsNull() {
		return dao.findByMsProjectParentTaskIdIsNull();
	}

	@Override
	public List<Task> findByMsProjectParentTaskId(Integer id) {
		return dao.findByMsProjectParentTaskId(id);
	}
}
