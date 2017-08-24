package com.archsystemsinc.ipms.sec.persistence.service;


import java.util.List;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.Meeting;
import com.archsystemsinc.ipms.sec.model.MeetingAgendaItem;
import com.archsystemsinc.ipms.sec.model.Project;

public interface IMeetingService extends IService<Meeting> {

	Meeting findByName(final String name);

	List<Meeting> findByInvited(String name);

	public void update(MeetingAgendaItem meetingAgendaItem);
	
}
