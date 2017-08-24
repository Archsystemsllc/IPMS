package com.archsystemsinc.ipms.sec.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.archsystemsinc.ipms.sec.model.MeetingAgendaItem;

public interface IMeetingAgendaItemJpaDAO extends
		JpaRepository<MeetingAgendaItem, Long>,
JpaSpecificationExecutor<MeetingAgendaItem> {

	MeetingAgendaItem findByName(final String name);

}
