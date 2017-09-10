/**
* Copyright (c) 2017, Archsystems Inc and/or its affiliates. All rights reserved.
*/
package com.archsystemsinc.ipms.jira.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.JiraClient;

/**
* JIRA Configuration
* This class holds the JIRA configuration such as outlook userid and outlook password
* @author Martin
* @version 0.2.1
*/
@Configuration
public class JIRAConfig {

	/**
	 * The JIRA User id
	 */
	@Value( "${jira.userid}" ) private String userid;
	
	/**
	 * The JIRA password
	 */
	@Value( "${jira.password}" ) private String password;
	
	/**
	 * The JIRA url
	 */
	@Value( "${jira.url}" ) private String url;
	
	/**
	 * Returns The JiraClient that needed to talk to JIRA
	 * @return The JiraClient
	 */
	@Bean
	public JiraClient jiraClient() {
        BasicCredentials creds = new BasicCredentials(userid, password);
        JiraClient jira = new JiraClient(url, creds);
        return jira;
	}
}
