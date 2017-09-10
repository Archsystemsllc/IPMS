package com.archsystemsinc.ipms.sec.model.dto;

import java.util.Date;
import java.util.List;

public class MeetingSearchParams {
	
	private List<Long> projectIds;
	
	private Date startDate;
	
	private Date endDate;
	
	
	private String subject;

	public List<Long> getProjectIds() {
		return projectIds;
	}

	public void setProjectIds(List<Long> projectIds) {
		this.projectIds = projectIds;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	

}
