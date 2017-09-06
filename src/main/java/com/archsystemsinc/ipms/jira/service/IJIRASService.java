package com.archsystemsinc.ipms.jira.service;

import java.util.List;

import com.archsystemsinc.ipms.sec.model.Issue;
import com.archsystemsinc.ipms.sec.model.Principal;


public interface IJIRASService {

	public List<Issue> findCurrentUserIssues(Principal principal);
	
	public String getJIRAProjectName(String key);
	
	public List<net.rcarz.jiraclient.Project> findAllProjects();
	
	public Issue findOne(String key);
}
