package com.archsystemsinc.ipms.sec.model.dto;

import java.util.List;

public class FinancialReportSearchParams {
	
	private Long projectId;
	
	private String projectName;
	
	private Long reportTypeId;
	
	private String reportTypeName;
		
	private List<String> reportIds;
	
	private String startDate;

	private String endDate;
	
	private String inceptionStartDate;
	
	private String inceptionEndDate;

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getReportTypeId() {
		return reportTypeId;
	}

	public void setReportTypeId(Long reportTypeId) {
		this.reportTypeId = reportTypeId;
	}

	public List<String> getReportIds() {
		return reportIds;
	}

	public void setReportIds(List<String> reportIds) {
		this.reportIds = reportIds;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getReportTypeName() {
		return reportTypeName;
	}

	public void setReportTypeName(String reportTypeName) {
		this.reportTypeName = reportTypeName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getInceptionStartDate() {
		return inceptionStartDate;
	}

	public void setInceptionStartDate(String inceptionStartDate) {
		this.inceptionStartDate = inceptionStartDate;
	}

	public String getInceptionEndDate() {
		return inceptionEndDate;
	}

	public void setInceptionEndDate(String inceptionEndDate) {
		this.inceptionEndDate = inceptionEndDate;
	}
	
	
	
}
