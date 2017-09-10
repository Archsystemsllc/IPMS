/**
* Copyright (c) 2017, Archsystems Inc and/or its affiliates. All rights reserved.
*/
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

/**
* The Service implementation for JIRA services
* @author Martin
* @version 0.2.1
*/
@Service
public class JIRAServiceImpl implements IJIRASService {


	/**
	 * The logger
	 */
	protected final Logger logger = LoggerFactory.getLogger(JIRAServiceImpl.class);
	
	/**
	 * The Autowired JIRAClient to communicate with JIRA
	 */
	@Autowired
	JiraClient jiraClient;

	/**
	 * The converter to converter the JIRA Issue with the corresponding IPMS Issue
	 */
	@Autowired 
	JIRAIssueConverter jIRAIssueConverter;
	
	/**
	 * This method returns the list of issues which are assigned to the current user
	 * @param principal The current User
	 * @return the List of IPMS issues
	 */
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
	
	/**
	 * Helper method to format JIRA Query
	 * @param key The Key
	 * @param lst The List
	 * @param isQuoteRequired add a Quote or not
	 * @return The formatted Query
	 */
	private String getCriteria(String key, List<String> lst, boolean isQuoteRequired) {
		
		StringBuilder qry = new StringBuilder();
		if(lst != null && lst.size()>0) {
			qry.append(" " + key + " in (");
			Iterator<String> itr = lst.iterator();
			while(itr.hasNext()) {
				String str = itr.next();;
				if(isQuoteRequired)
					qry.append("\"" + str + "\"");
				else
					qry.append(str);
				if(itr.hasNext())
					qry.append(",");
			}
			qry.append (")");
		}
		return qry.toString();
	}
	/**
	 * Search for JIRA issues
	 * @param users the Issue assigned any one of the following users
	 * @param projects The List if projects to be search
	 * @param statuses The Jira status
	 * @return the List of Issues in IPMS model
	 */
	public List<Issue> searchIssues(List<String> users, List<String> projects, List<String> statuses)  {
		List<Issue> lst = new ArrayList<>();
		net.rcarz.jiraclient.Issue.SearchResult sr = null;
		try {
			StringBuilder qry = new StringBuilder();
			List<String> qryList = new ArrayList<>();
			qryList.add(getCriteria("status", statuses, true));
			qryList.add(getCriteria("assignee ", users, false));
			qryList.add(getCriteria("project", projects, false));
			
			{
				Iterator<String> itr = qryList.iterator();
				while(itr.hasNext()) {
					String str = itr.next();
					if(StringUtils.isEmpty(str))
						continue;
					if(qry.length()>0)
						qry.append(" AND ");
					
					qry.append(str);
					
				}
			}
			
			sr = jiraClient.searchIssues(qry.toString() + " ORDER BY priority DESC, updated DESC");
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
	/**
	 * This method returns the Project Name for the given JIRA project key
	 * @param key The Project Key
	 * @return The Project Name
	 */
	public String getJIRAProjectName(String key) {
		try {
			if(StringUtils.isEmpty(key))
				return key;
			
			return jiraClient.getProject(key).getName();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * This method returns All Projects from JIRA
	 * @return the list of IPMS Projects from JIRA
	 */
	@Override
	public List<net.rcarz.jiraclient.Project> findAllProjects()  {
		try {
			return jiraClient.getProjects();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	/**
	 * This method returns the Issue for the given Issue id
	 * @param key the JIRA Key
	 * @return the IPMS issue 
	 */
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
