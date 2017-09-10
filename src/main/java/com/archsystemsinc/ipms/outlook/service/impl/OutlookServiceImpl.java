/**
* Copyright (c) 2017, Archsystems Inc and/or its affiliates. All rights reserved.
*/
package com.archsystemsinc.ipms.outlook.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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

/**
* The Service implementation for outlook services
* @author Martin
* @version 0.2.1
*/
@Service
public class OutlookServiceImpl implements IOutlookService {

	/**
	 * The logger
	 */
	protected final Logger logger = LoggerFactory.getLogger(OutlookServiceImpl.class);
	
	/**
	 * The Autowired ExchangeService that connects to outlook
	 */
	@Autowired
	ExchangeService exchangeService;

	/**
	 * The Autowired Meeting Converter
	 */
	@Autowired 
	OutlookMeetingConverter outlookMeetingConverter;
	
	/**
	 * 
	 * @param project	The project calendar
	 * @param searchStr The optional subject to search
	 * @param startDate The Start Date of calendar to find
	 * @param endDate The End Date of the calendar to find
	 * @return The List of <code>Meeting</code> found on the project calendar given date range
	 */
	@Override
	public List<Meeting> findAppointments(Project project, String searchStr,  Date startDate, Date endDate)  {
		List<Meeting> lst = new ArrayList<>();
		try {
			
			FolderId calendar = new FolderId(WellKnownFolderName.Calendar, new Mailbox(project.getEmail()));
			CalendarFolder cf = CalendarFolder.bind(exchangeService, calendar);
			FindItemsResults<Appointment> results = cf.findAppointments(new CalendarView(startDate, endDate));
			for (Appointment appt : results.getItems()) {	
				if(StringUtils.isNotEmpty(searchStr) && appt.getSubject().indexOf(searchStr) ==-1)
					continue;
				
				Meeting meeting = outlookMeetingConverter.convert(appt);
				if(meeting != null) {
					meeting.setProject(project);
					lst.add(meeting);
				}
			}
		} catch (Exception e) {
			logger.debug("Unable to find the appointments" , e);
		}
		return lst;
		
	}

}
