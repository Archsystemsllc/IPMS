package com.archsystemsinc.ipms.sec.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.archsystemsinc.ipms.sec.model.Requirement;

public interface IRequirementJpaDAO extends JpaRepository<Requirement, Long>,
		JpaSpecificationExecutor<Requirement> {

	Requirement findByName(final String name);

}
