package com.archsystemsinc.ipms.sec.persistence.service;

import java.util.List;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.ActionItem;
import com.archsystemsinc.ipms.sec.model.MeetingAgendaItem;
import com.archsystemsinc.ipms.sec.model.Principal;

public interface IMeetingAgendaItemService extends IService<MeetingAgendaItem> {

	MeetingAgendaItem findByName(final String name);

	void add(MeetingAgendaItem meetingAgendaItem);
	
	MeetingAgendaItem findOne(final MeetingAgendaItem topic);
	
	List<MeetingAgendaItem> findCurrentUserMeetingAgendaItem(Principal principal);

}
