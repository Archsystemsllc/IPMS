/**
* Copyright (c) 2017, Archsystems Inc and/or its affiliates. All rights reserved.
*/
package com.archsystemsinc.ipms.jira.service;

import java.util.List;

import com.archsystemsinc.ipms.sec.model.Issue;
import com.archsystemsinc.ipms.sec.model.Principal;

/**
* The Service implementation for JIRA services
* @author Martin
* @version 0.2.1
*/
public interface IJIRASService {
	/**
	 * This method returns the list of issues which are assigned to the current user
	 * @param principal The current User
	 * @return the List of IPMS issues
	 */
	public List<Issue> findCurrentUserIssues(Principal principal);
	
	/**
	 * Search for JIRA issues
	 * @param users the Issue assigned any one of the following users
	 * @param projects The List if projects to be search
	 * @param statuses The Jira status
	 * @return the List of Issues in IPMS model
	 */
	public List<Issue> searchIssues(List<String> users, List<String> projects, List<String> statuses);
	
	/**
	 * This method returns the Project Name for the given JIRA project key
	 * @param key The Project Key
	 * @return The Project Name
	 */
	public String getJIRAProjectName(String key);
	
	/**
	 * This method returns All Projects from JIRA
	 * @return the list of IPMS Projects from JIRA
	 */
	public List<net.rcarz.jiraclient.Project> findAllProjects();
	
	/**
	 * This method returns the Issue for the given Issue id
	 * @param key the JIRA Key
	 * @return the IPMS issue 
	 */
	public Issue findOne(String key);
}
