package com.archsystemsinc.ipms.sec.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.archsystemsinc.ipms.sec.model.MeetingPrincipal;

public interface IMeetingPrincipalJpaDAO extends
JpaRepository<MeetingPrincipal, Long>,
JpaSpecificationExecutor<MeetingPrincipal> {

	@Query("select m from com.archsystemsinc.ipms.sec.model.MeetingPrincipal m where m.meetingId = :meetingId")
	List<MeetingPrincipal> findByMeeting(@Param("meetingId") Long meetingId);

}
