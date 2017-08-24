package com.archsystemsinc.ipms.sec.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.archsystemsinc.ipms.sec.model.MeetingPrincipal;
import com.archsystemsinc.ipms.sec.model.RevisionHistory;

public interface IRevisionHistoryJpaDAO extends
		JpaRepository<RevisionHistory, Long>,
JpaSpecificationExecutor<RevisionHistory> {

	RevisionHistory findByName(final String name);
	
	@Query("select m from com.archsystemsinc.ipms.sec.model.RevisionHistory m where m.requirementId = :requirementId")
	List<RevisionHistory> findByRequirement(@Param("requirementId") Long requirementId);
	
	@Query("select m from com.archsystemsinc.ipms.sec.model.RevisionHistory m where m.issueId = :issueId")
	List<RevisionHistory> findByIssue(@Param("issueId") Long issueId);
	
	@Query("select m from com.archsystemsinc.ipms.sec.model.RevisionHistory m where m.actionItemId = :actionItemId")
	List<RevisionHistory> findByActionItem(@Param("actionItemId") Long actionItemId);
	
	@Query("select m from com.archsystemsinc.ipms.sec.model.RevisionHistory m where m.taskId = :taskId")
	List<RevisionHistory> findByTask(@Param("taskId") Long taskId);
	
	@Query("select m from com.archsystemsinc.ipms.sec.model.RevisionHistory m where m.riskId = :riskId")
	List<RevisionHistory> findByRisk(@Param("riskId") Long riskId);

	@Query("select m from com.archsystemsinc.ipms.sec.model.RevisionHistory m where m.lessonsLearnedId = :lessonsLearnedId")
	List<RevisionHistory> findByLessonsLearned(@Param("lessonsLearnedId") Long lessonsLearnedId);
}
