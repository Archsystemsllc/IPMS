package com.archsystemsinc.ipms.sec.webapp.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.archsystemsinc.ipms.sec.model.Task;

public class FileUpload {
	private String name;
	private String desc;
	private Date startDate;
	private Date endDate;
	private List<Task> tasks;
    private Long programId;
    private Map<Integer, String> programs;
    private Long projectId;
    private Long meetingId;
    
	public Map<Integer, String> getPrograms() {
		return programs;
	}

	public void setPrograms(Map<Integer, String> programs) {
		this.programs = programs;
	}

	private CommonsMultipartFile fileData;

	public Long getProgramId() {
		return programId;
	}

	public void setProgramId(Long programId) {
		this.programId = programId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(final Date startDate1) {
		startDate = startDate1;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(final Date endDate1) {
		endDate = endDate1;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(final List<Task> tasks1) {
		tasks = tasks1;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name1) {
		name = name1;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(final String desc1) {
		desc = desc1;
	}

	public CommonsMultipartFile getFileData() {
		return fileData;
	}

	public void setFileData(final CommonsMultipartFile fileData1) {
		fileData = fileData1;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(Long meetingId) {
		this.meetingId = meetingId;
	}
}
