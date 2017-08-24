package com.archsystemsinc.ipms.sec.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.archsystemsinc.ipms.sec.model.Attendee;

public interface IAttendeeJpaDAO extends JpaRepository<Attendee, Long>,
JpaSpecificationExecutor<Attendee> {

	Attendee findByName(final String name);

}
