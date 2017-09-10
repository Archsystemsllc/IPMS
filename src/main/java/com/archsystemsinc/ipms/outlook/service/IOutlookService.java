/**
* Copyright (c) 2017, Archsystems Inc and/or its affiliates. All rights reserved.
*/
package com.archsystemsinc.ipms.outlook.service;

import java.util.Date;
import java.util.List;

import com.archsystemsinc.ipms.sec.model.Meeting;
import com.archsystemsinc.ipms.sec.model.Project;


/**
* Service class for Outlook
* @author Martin
* @version 0.2.1
*/
public interface IOutlookService {

	/**
	 * 
	 * @param project	The project calendar
	 * @param searchStr The optional subject to search
	 * @param startDate The Start Date of calendar to find
	 * @param endDate The End Date of the calendar to find
	 * @return The List of <code>Meeting</code> found on the project calendar given date range
	 */
	public List<Meeting> findAppointments(Project project, String searchStr, Date startDate, Date endDate);
}
