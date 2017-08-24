package com.archsystemsinc.ipms.sec.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.archsystemsinc.ipms.sec.model.LessonsLearned;

public interface ILessonsLearnedJpaDAO extends
		JpaRepository<LessonsLearned, Long>,
		JpaSpecificationExecutor<LessonsLearned> {

	LessonsLearned findByName(final String name);
	LessonsLearned findById(final Long id);
}
