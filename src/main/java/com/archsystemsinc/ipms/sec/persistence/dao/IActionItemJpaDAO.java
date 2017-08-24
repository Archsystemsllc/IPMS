package com.archsystemsinc.ipms.sec.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.archsystemsinc.ipms.sec.model.ActionItem;

public interface IActionItemJpaDAO extends JpaRepository<ActionItem, Long>,
JpaSpecificationExecutor<ActionItem> {

	ActionItem findByName(final String name);
}
