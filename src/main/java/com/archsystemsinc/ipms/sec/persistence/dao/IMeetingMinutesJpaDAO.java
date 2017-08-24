package com.archsystemsinc.ipms.sec.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.archsystemsinc.ipms.sec.model.MeetingAgendaItem;
import com.archsystemsinc.ipms.sec.model.MeetingMinutes;

public interface IMeetingMinutesJpaDAO extends
		JpaRepository<MeetingMinutes, Long>,
JpaSpecificationExecutor<MeetingMinutes> {

	MeetingMinutes findByName(final String name);

}
