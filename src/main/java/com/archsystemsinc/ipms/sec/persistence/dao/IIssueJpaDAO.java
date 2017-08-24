package com.archsystemsinc.ipms.sec.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.archsystemsinc.ipms.sec.model.Issue;

public interface IIssueJpaDAO extends JpaRepository<Issue, Long>,
JpaSpecificationExecutor<Issue> {

	Issue findByName(final String name);

}
