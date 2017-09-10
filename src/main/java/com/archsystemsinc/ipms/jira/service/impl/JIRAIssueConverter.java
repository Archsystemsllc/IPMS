/**
* Copyright (c) 2017, Archsystems Inc and/or its affiliates. All rights reserved.
*/
package com.archsystemsinc.ipms.jira.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import com.archsystemsinc.ipms.sec.model.Issue;
import com.archsystemsinc.ipms.sec.model.Project;
import com.archsystemsinc.ipms.sec.persistence.service.IProjectService;

import net.sf.json.JSONObject;

/**
* JIRA Converter that converts JIRA Issue to the corresponding IPMS Issue
* @author Martin
* @version 0.2.1
*/
@Service
public class JIRAIssueConverter implements Converter<net.rcarz.jiraclient.Issue, Issue> {

	/**
	 * The Autowired principlaService that needed for Project related service calls
	 */
	@Autowired
	IProjectService projectService;
	
	/**
	 * Returns the IPMS Issue for the corresponding JIRA Issue
	 * @return <code>Issue</code>
	 */
	@Override
	public com.archsystemsinc.ipms.sec.model.Issue convert(net.rcarz.jiraclient.Issue jcIssue) {
		
		Issue issue;
		
		try {
			
			Project project = projectService.findByJiraProjectKey(jcIssue.getProject().getKey());
			if(project == null)
				return null;
			
			issue = new Issue();
			issue.setSource("JIRA");
			issue.setJiraId(jcIssue.getKey());
			issue.setDueDate(jcIssue.getDueDate());
			issue.setSummary(jcIssue.getSummary());
			issue.setStatus(jcIssue.getStatus().getName());
			issue.setPriority(jcIssue.getPriority().getName());
			
			issue.setProject(project);
			
			com.archsystemsinc.ipms.sec.model.Principal assignedTo = new com.archsystemsinc.ipms.sec.model.Principal();
			if(jcIssue != null)
				assignedTo.setName(jcIssue.getAssignee().getDisplayName());
			issue.setAssigned(assignedTo);
			

			com.archsystemsinc.ipms.sec.model.Principal assignee = new com.archsystemsinc.ipms.sec.model.Principal();
			JSONObject obj = (JSONObject) jcIssue.getField("creator");			
			assignee.setName((String)obj.get("displayName"));
			issue.setAssignee(assignee);

			Object createdDate = jcIssue.getField("created");
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			issue.setDateReported(fmt.parse(((String) createdDate)));
			
		} catch (ParseException exp) {
			throw new RuntimeException(exp);
		}
		
		return issue;
	}

}
