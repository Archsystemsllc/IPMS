package com.archsystemsinc.ipms.sec.model;

import java.util.Date;

public class MetricModel {
	
	 private Long projectId;
	 
	 private Date fromDate;
	
	 private Date toDate;

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
}
