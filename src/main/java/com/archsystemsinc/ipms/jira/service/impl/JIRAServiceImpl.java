package com.archsystemsinc.ipms.jira.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.archsystemsinc.ipms.jira.service.IJIRASService;
import com.archsystemsinc.ipms.sec.model.Issue;
import com.archsystemsinc.ipms.sec.model.Principal;

import net.rcarz.jiraclient.JiraClient;

@Service
public class JIRAServiceImpl implements IJIRASService {


	protected final Logger logger = LoggerFactory.getLogger(JIRAServiceImpl.class);
	
	@Autowired
	JiraClient jiraClient;

	@Autowired 
	JIRAIssueConverter jIRAIssueConverter;
	
	@Override
	public List<Issue> findCurrentUserIssues(Principal principal)  {
		List<Issue> lst = new ArrayList<>();
		net.rcarz.jiraclient.Issue.SearchResult sr = null;
		try {
			
			sr = jiraClient.searchIssues(" assignee = " + principal.getJiraUsername() + " ORDER BY priority DESC, updated DESC");
			Iterator<net.rcarz.jiraclient.Issue> itr = sr.issues.iterator();
			while (itr.hasNext()) {			
				Issue issue = jIRAIssueConverter.convert(itr.next());
				if(issue != null)
					lst.add(issue);
			}
			return lst;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public String getJIRAProjectName(String key) {
		try {
			if(StringUtils.isEmpty(key))
				return key;
			
			return jiraClient.getProject(key).getName();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<net.rcarz.jiraclient.Project> findAllProjects()  {
		try {
			return jiraClient.getProjects();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}


	@Override
	public Issue findOne(String key) {
		 try {
			return jIRAIssueConverter.convert(jiraClient.getIssue(key));
		} catch (Exception exp) {
			logger.error(exp.getMessage());
			return null;
		}
	}
	

}
