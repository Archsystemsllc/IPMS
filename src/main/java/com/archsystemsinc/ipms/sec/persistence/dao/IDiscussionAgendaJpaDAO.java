package com.archsystemsinc.ipms.sec.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.archsystemsinc.ipms.sec.model.DiscussionAgenda;
import com.archsystemsinc.ipms.sec.model.MeetingAgendaItem;


public interface IDiscussionAgendaJpaDAO extends
JpaRepository<DiscussionAgenda, Long>,
JpaSpecificationExecutor<DiscussionAgenda> {



}

