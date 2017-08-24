package com.archsystemsinc.ipms.sec.persistence.service;


import java.util.List;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.MeetingPrincipal;

public interface IMeetingPrincipalService extends IService<MeetingPrincipal> {

	List<MeetingPrincipal> findByMeeting(Long meetingId);
}
