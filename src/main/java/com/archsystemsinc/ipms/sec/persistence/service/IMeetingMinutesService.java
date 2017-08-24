package com.archsystemsinc.ipms.sec.persistence.service;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.MeetingMinutes;

public interface IMeetingMinutesService extends IService<MeetingMinutes> {
	MeetingMinutes findByName(final String name);

	
}
