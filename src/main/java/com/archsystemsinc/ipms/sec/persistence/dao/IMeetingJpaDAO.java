package com.archsystemsinc.ipms.sec.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.archsystemsinc.ipms.sec.model.Meeting;

public interface IMeetingJpaDAO extends JpaRepository<Meeting, Long>,
JpaSpecificationExecutor<Meeting> {

	Meeting findByName(final String name);

	// discouraged...please use specifications and predicates - lekan
	@Query("select m from com.archsystemsinc.ipms.sec.model.Meeting m, com.archsystemsinc.ipms.sec.model.Principal p, com.archsystemsinc.ipms.sec.model.MeetingPrincipal mp where m.id=mp.meetingId and mp.principalId=p.id and p.name = :name")
	List<Meeting> findByInvited(@Param("name") String name);
}
