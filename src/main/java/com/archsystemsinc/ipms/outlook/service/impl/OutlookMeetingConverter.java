/**
* Copyright (c) 2017, Archsystems Inc and/or its affiliates. All rights reserved.
*/
package com.archsystemsinc.ipms.outlook.service.impl;

/**
* Meeting Converter that converts Outlook Appointment to the corresponding IPMS meeting
* @author Martin
* @version 0.2.1
*/
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import com.archsystemsinc.ipms.sec.model.Meeting;
import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.persistence.service.IPrincipalService;
import com.archsystemsinc.ipms.util.DateUtil;

import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.core.service.item.Appointment;

@Service
public class OutlookMeetingConverter implements Converter<Appointment, Meeting> {

	/**
	 * The Autowired principlaService that needed for Principal related service calls
	 */
	@Autowired
	IPrincipalService principalService;
	
	/**
	 * Returns the Meeting for the corresponding outlook Appointment
	 * @return <code>Meeting</code>
	 */
	@Override
	public Meeting convert(Appointment appt) {
		
		try {
			Meeting meeting = new Meeting();
			meeting.setDate(DateUtil.getDateOnly(appt.getStart()));
		
			Principal organizer = principalService.findByName(appt.getOrganizer().getName());
			if(organizer != null) {
				meeting.setOrganizer(organizer);
				meeting.setOrganizerId(organizer.getId());
			}
			SimpleDateFormat fmt = new SimpleDateFormat("h:mm a");
			meeting.setTime(fmt.format(appt.getStart()));
			meeting.setTitle(appt.getSubject());
			meeting.setTypeString(appt.getLocation());
			meeting.setDuration(appt.getDuration().getMinutes() + 0f);
			meeting.setStatus("SCHEDULED");
			return meeting;
		} catch (ServiceLocalException e) {
			throw new RuntimeException(e);
		}
	}

}
