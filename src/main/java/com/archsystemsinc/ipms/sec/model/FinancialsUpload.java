package com.archsystemsinc.ipms.sec.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FinancialsUpload {
	private Long projectId;
	private CommonsMultipartFile organizationcategoryFileData;
	private CommonsMultipartFile forecastFileData;
	private CommonsMultipartFile wbsFileData;
	
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public CommonsMultipartFile getForecastFileData() {
		return forecastFileData;
	}
	public void setForecastFileData(CommonsMultipartFile forecastFileData) {
		this.forecastFileData = forecastFileData;
	}
	public CommonsMultipartFile getOrganizationcategoryFileData() {
		return organizationcategoryFileData;
	}
	public void setOrganizationcategoryFileData(
			CommonsMultipartFile organizationcategoryFileData) {
		this.organizationcategoryFileData = organizationcategoryFileData;
	}
	public CommonsMultipartFile getWbsFileData() {
		return wbsFileData;
	}
	public void setWbsFileData(CommonsMultipartFile wbsFileData) {
		this.wbsFileData = wbsFileData;
	}

}
