package com.archsystemsinc.ipms.sec.persistence.service;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.DiscussionAgenda;
import com.archsystemsinc.ipms.sec.model.ParkingLot;


public interface IDiscussionAgendaService extends IService<DiscussionAgenda> {

	DiscussionAgenda findByName(final String name);

	void add(DiscussionAgenda discussionagenda);
	
	
}