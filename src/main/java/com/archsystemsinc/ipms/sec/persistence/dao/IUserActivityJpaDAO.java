package com.archsystemsinc.ipms.sec.persistence.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.archsystemsinc.ipms.sec.model.UserActivity;

public interface IUserActivityJpaDAO extends JpaRepository<UserActivity, Long>,
JpaSpecificationExecutor<UserActivity> {

	List<UserActivity> findByEntityType(String entityType);

//	@Query("select u from UserActivity u where u.entityType = 'Program' or u.entityType = 'Project' or u.entityType = 'Task' or u.entityType = 'Issue' or u.entityType = 'ActionItem' or u.entityType = 'LessonsLearned'")
//	List<UserActivity> findByArtifacts();

	List<UserActivity> findByEntityTypeInOrderByDateCreatedDesc(List<String> entityType, Pageable pageable);
}
