package com.archsystemsinc.ipms.outlook.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.archsystemsinc.ipms.outlook.service.IOutlookService;
import com.archsystemsinc.ipms.sec.model.Meeting;
import com.archsystemsinc.ipms.sec.model.Project;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.service.folder.CalendarFolder;
import microsoft.exchange.webservices.data.core.service.item.Appointment;
import microsoft.exchange.webservices.data.property.complex.FolderId;
import microsoft.exchange.webservices.data.property.complex.Mailbox;
import microsoft.exchange.webservices.data.search.CalendarView;
import microsoft.exchange.webservices.data.search.FindItemsResults;

@Service
public class OutlookServiceImpl implements IOutlookService {


	protected final Logger logger = LoggerFactory.getLogger(OutlookServiceImpl.class);
	
	@Autowired
	ExchangeService exchangeService;

	@Autowired 
	OutlookMeetingConverter outlookMeetingConverter;
	
	@Override
	public List<Meeting> findAppointments(Project project, Date startDate, Date endDate)  {
		List<Meeting> lst = new ArrayList<>();
		try {
			
			FolderId calendar = new FolderId(WellKnownFolderName.Calendar, new Mailbox(project.getEmail()));
			CalendarFolder cf = CalendarFolder.bind(exchangeService, calendar);
			FindItemsResults<Appointment> results = cf.findAppointments(new CalendarView(startDate, endDate));
			for (Appointment appt : results.getItems()) {		
				Meeting meeting = outlookMeetingConverter.convert(appt);
				if(meeting != null) {
					meeting.setProject(project);
					lst.add(meeting);
				}
			}
			return lst;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

}
