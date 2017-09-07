package com.archsystemsinc.ipms.sec.model;

import org.springframework.beans.factory.annotation.Required;

public class BudgetTimeInputJSON {

	
	private String projectId;
	
	private String fromDate;
	
	private String toDate;
	
	@Required
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	@Required
	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	@Required
	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
}
