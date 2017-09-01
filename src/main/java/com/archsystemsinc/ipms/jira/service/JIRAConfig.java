package com.archsystemsinc.ipms.jira.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.JiraClient;

@Configuration
public class JIRAConfig {


	@Value( "${jira.userid}" ) private String userid;
	@Value( "${jira.password}" ) private String password;
	@Value( "${jira.url}" ) private String url;
	
	@Bean
	public JiraClient jiraClient() {
        BasicCredentials creds = new BasicCredentials(userid, password);
        JiraClient jira = new JiraClient(url, creds);
        return jira;
	}
}
