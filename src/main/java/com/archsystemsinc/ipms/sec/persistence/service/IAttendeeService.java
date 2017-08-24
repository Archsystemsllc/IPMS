package com.archsystemsinc.ipms.sec.persistence.service;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.Attendee;

public interface IAttendeeService extends IService<Attendee> {

	Attendee findByName(final String name);

}
