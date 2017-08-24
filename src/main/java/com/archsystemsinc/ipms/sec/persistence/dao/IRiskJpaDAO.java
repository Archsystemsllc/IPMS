package com.archsystemsinc.ipms.sec.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.archsystemsinc.ipms.sec.model.Risk;

public interface IRiskJpaDAO extends JpaRepository<Risk, Long>,
		JpaSpecificationExecutor<Risk> {

	Risk findByName(final String name);
}
