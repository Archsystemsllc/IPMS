package com.archsystemsinc.ipms.sec.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.archsystemsinc.ipms.sec.model.OrganizationGroup;
import com.archsystemsinc.ipms.sec.model.Program;

public interface IProgramJpaDAO extends JpaRepository<Program, Long>,
JpaSpecificationExecutor<Program> {

	Program findByName(final String name);
	
	List<Program> findProgramListByOrganizationGroup(final OrganizationGroup organizationGroup);

}
