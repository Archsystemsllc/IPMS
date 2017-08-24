package com.archsystemsinc.ipms.sec.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.archsystemsinc.ipms.sec.model.ActionItem;
import com.archsystemsinc.ipms.sec.model.ProjectStatusReport;

public interface IProjectStatusReportJpaDAO extends JpaRepository<ProjectStatusReport, Long>,
JpaSpecificationExecutor<ProjectStatusReport> {

	ProjectStatusReport findByName(final String name);
}
