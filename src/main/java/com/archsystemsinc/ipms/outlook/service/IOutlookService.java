package com.archsystemsinc.ipms.outlook.service;

import java.util.Date;
import java.util.List;

import com.archsystemsinc.ipms.sec.model.Meeting;
import com.archsystemsinc.ipms.sec.model.Project;


public interface IOutlookService {

	public List<Meeting> findAppointments(Project project, Date startDate, Date endDate);
}
