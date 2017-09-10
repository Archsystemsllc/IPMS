package com.archsystemsinc.ipms.sec.model.dto;

import java.util.List;

public class IssueSearchParams {
	
	private List<Long> projectIds;
	
	private List<Long> principalIds;
	
	private List<String> statuses;

	public List<Long> getProjectIds() {
		return projectIds;
	}

	public void setProjectIds(List<Long> projectIds) {
		this.projectIds = projectIds;
	}

	public List<Long> getPrincipalIds() {
		return principalIds;
	}

	public void setPrincipalIds(List<Long> principalIds) {
		this.principalIds = principalIds;
	}

	public List<String> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<String> statuses) {
		this.statuses = statuses;
	}

}
