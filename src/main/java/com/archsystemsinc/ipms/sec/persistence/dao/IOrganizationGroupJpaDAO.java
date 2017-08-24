package com.archsystemsinc.ipms.sec.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.archsystemsinc.ipms.sec.model.OrganizationGroup;

public interface IOrganizationGroupJpaDAO extends JpaRepository<OrganizationGroup, Long>,
JpaSpecificationExecutor<OrganizationGroup> {

	OrganizationGroup findByName(final String name);

}
