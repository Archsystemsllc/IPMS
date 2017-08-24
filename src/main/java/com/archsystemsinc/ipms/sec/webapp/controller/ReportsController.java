/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.archsystemsinc.ipms.sec.webapp.controller;

import com.archsystemsinc.ipms.poi.service.DownloadService;
import com.archsystemsinc.ipms.sec.model.*;
import com.archsystemsinc.ipms.sec.persistence.service.*;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.util.*;

@Controller
public class ReportsController {

	public ReportsController() {

	}

	private final Log log = LogFactory.getLog(ReportsController.class);
	
	@Autowired
	private IOrganizationGroupService organizationGroupService;

	@Autowired
	private IPrincipalService principalService;

	@Autowired
	private IIssueService issueService;

	@Autowired
	private IActionItemService actionItemService;

	@Autowired
	private ILessonsLearnedService learnedService;

	@Autowired
	private IRiskService riskService;

	@Autowired
	private IMeetingService meetingService;

	@Autowired
	private DownloadService downloadService;

	@Autowired
	private IProjectService projectService;

	@Autowired
	private IProgramService programService;
	
	@RequestMapping(value = "/groupreport", method = RequestMethod.GET)
	public String groupReport(final Model model, final Principal principal,
			HttpServletRequest request) {
		
		final List<OrganizationGroup> organizationGroups = organizationGroupService.findAll();			
		model.addAttribute("organizationGroups", organizationGroups);
		
		
		return "groupreport";
	}

	@RequestMapping(value = "/groupreport", method = RequestMethod.POST)
	public String groupReportPost(final Model model,
			final Principal principal, HttpServletRequest request) {
		
		final List<OrganizationGroup> organizationGroups = organizationGroupService.findAll();			
		model.addAttribute("organizationGroups", organizationGroups);
		Long id = Long.parseLong(request.getParameter("organizationGroupId"));
		OrganizationGroup organizationGroup = organizationGroupService.findOne(id);
		model.addAttribute("programs", programService.findProgramListByOrganizationGroup(organizationGroup));
		
		return "groupreport";
	}


	@RequestMapping(value = "/projectreport", method = RequestMethod.GET)
	public String projectReport(final Model model, final Principal principal,
			HttpServletRequest request) {
		List<Project> projects = projectService.findAll();
		model.addAttribute("projects", projects);
		if(null != projects) {
			Project project = projects.get(0);
			if(null!=project) getProject(model, request, project.getId());
		}
		return "projectreport";
	}

	@RequestMapping(value = "/projectreport", method = RequestMethod.POST)
	public String projectReportPost(final Model model,
			final Principal principal, HttpServletRequest request) {
		Long id = Long.parseLong(request.getParameter("projectId"));
		getProject(model, request, id);
		return "projectreport";
	}

	private void getProject(final Model model, HttpServletRequest request, Long id) {
		Project project = projectService.findOne(id);
		request.setAttribute("projectId", id);
		model.addAttribute("projects", projectService.findAll());
		final List<Issue> issues = new ArrayList<Issue>();
													
		issues.addAll(project.getIssues());
		int openProgressIssues = 0;
		for (Issue issue : issues) {
			if (StringUtils.equalsIgnoreCase(issue.getStatus(),
					IssueStatus.Open.toString())
					|| StringUtils.equalsIgnoreCase(issue.getStatus(),
							IssueStatus.In_Progress.toString())) {
				openProgressIssues++;
			}
		}

		double issueResolutionRate = 0;
		if (issues != null && issues.size() > 0)
			issueResolutionRate = openProgressIssues / issues.size();
		DecimalFormat formatter = new DecimalFormat("0.00");
		formatter.format(issueResolutionRate);
		model.addAttribute("issues", project.getIssues());
		model.addAttribute("issueResolutionRate", issueResolutionRate);
		final List<ActionItem> actionItems = new ArrayList<ActionItem>();
		actionItems.addAll(project.getActionItems());
		Collections.sort(actionItems);
		int openProgressActionItems = 0;
		for (ActionItem actionItem : actionItems) {
			if (StringUtils.equalsIgnoreCase(actionItem.getStatus(),
					IssueStatus.Open.toString())
					|| StringUtils.equalsIgnoreCase(actionItem.getStatus(),
							IssueStatus.In_Progress.toString())) {
				openProgressActionItems++;
			}
		}
		double actionItemResolutionRate = 0;
		if (actionItems != null && actionItems.size() > 0)
		
			actionItemResolutionRate = openProgressActionItems
					/ actionItems.size();
		formatter.format(issueResolutionRate);
		model.addAttribute("issues", project.getIssues());
		model.addAttribute("actionItems", project.getActionItems());
		model.addAttribute("actionItemResolutionRate", actionItemResolutionRate);
		model.addAttribute("lessonsLearned", project.getLessonsLearned());
		model.addAttribute("risks", project.getRisks());

		final List<Meeting> meetings = meetingService.findAll();
		model.addAttribute("meetings", meetings);
	}
	@RequestMapping(value = "/programreport", method = RequestMethod.GET)
	public String programReport(final Model model, final Principal principal,
			HttpServletRequest request) {
		List<Program> programs = programService.findAll();
		model.addAttribute("programs", programs);
		if (programs != null)
		{
			if (programs.size() > 0) {
				Program program = programs.get(0);
				model.addAttribute("issues", program.getIssues());
				model.addAttribute("actionItems", program.getActionItems());
				model.addAttribute("lessonsLearned",
						program.getLessonsLearned());
				model.addAttribute("risks", program.getRisks());
				model.addAttribute("projects", program.getProjects());
				request.setAttribute("programId", program.getId());
			}
		}
		return "programreport";
	}

	@RequestMapping(value = "/programreport", method = RequestMethod.POST)
	public String programReportPost(final Model model,
			final Principal principal, HttpServletRequest request) {
		Long id = Long.parseLong(request.getParameter("programId"));
		model.addAttribute("programs", programService.findAll());
		Program program = programService.findOne(id);
		request.setAttribute("programId", program.getId());
		model.addAttribute("issues", program.getIssues());
		model.addAttribute("actionItems", program.getActionItems());
		model.addAttribute("lessonsLearned", program.getLessonsLearned());
		model.addAttribute("risks", program.getRisks());
		model.addAttribute("projects", program.getProjects());

		return "programreport";
	}

	@RequestMapping(value = "/statusreport", method = RequestMethod.GET)
	public String statusReport(final Model model, final Principal principal,
			HttpServletRequest request) {
		model.addAttribute("projects", projectService.findAll());
		return "statusreport";
	}

	@RequestMapping(value = "/statusreport", method = RequestMethod.POST)
	public String statusReportPost(final Model model,
			final Principal principal, HttpServletRequest request) {
		Long id = Long.parseLong(request.getParameter("projectId"));
		Project project = projectService.findOne(id);
		request.setAttribute("projectId", id);
		model.addAttribute("projects", projectService.findAll());
		final List<Issue> issues = new ArrayList<Issue>();														
		issues.addAll(project.getIssues());
		int openProgressIssues = 0;
		for (Issue issue : issues) {
			if (StringUtils.equalsIgnoreCase(issue.getStatus(),
					IssueStatus.Open.toString())
					|| StringUtils.equalsIgnoreCase(issue.getStatus(),
							IssueStatus.In_Progress.toString())) {
				openProgressIssues++;
			}
		}

		double issueResolutionRate = 0;
		if (issues != null && issues.size() > 0)
			issueResolutionRate = openProgressIssues / issues.size();
		DecimalFormat formatter = new DecimalFormat("0.00");
		formatter.format(issueResolutionRate);
		model.addAttribute("issues", project.getIssues());
		model.addAttribute("issueResolutionRate", issueResolutionRate);
		final List<ActionItem> actionItems = new ArrayList<ActionItem>();
		actionItems.addAll(project.getActionItems());
		Collections.sort(actionItems);
		int openProgressActionItems = 0;
		for (ActionItem actionItem : actionItems) {
			if (StringUtils.equalsIgnoreCase(actionItem.getStatus(),
					IssueStatus.Open.toString())
					|| StringUtils.equalsIgnoreCase(actionItem.getStatus(),
							IssueStatus.In_Progress.toString())) {
				openProgressActionItems++;
			}
		}
		double actionItemResolutionRate = 0;
		if (actionItems != null && actionItems.size() > 0)
			actionItemResolutionRate = openProgressActionItems
					/ actionItems.size();
		formatter.format(issueResolutionRate);
		model.addAttribute("issues", project.getIssues());
		model.addAttribute("actionItems", project.getActionItems());
		model.addAttribute("actionItemResolutionRate", actionItemResolutionRate);
		model.addAttribute("lessonsLearned", project.getLessonsLearned());
		model.addAttribute("risks", project.getRisks());

		final List<Meeting> meetings = meetingService.findAll();
		model.addAttribute("meetings", meetings);

		return "statusreport";
	}

	/**
	 * Downloads the report as an Excel format.
	 * <p>
	 * Make sure this method doesn't return any model. Otherwise, you'll get an
	 * "IllegalStateException: getOutputStream() has already been called for this response"
	 * final HttpServletResponse response, final String sheetName, final
	 * List<Object> exportList, final String[] coloumnNames
	 */
	@RequestMapping(value = "/projectreport/xls", method = RequestMethod.GET)
	public void getProjectReportXLS(HttpServletRequest request,
			final HttpServletResponse response, final Principal principal,
			Model model) throws Exception {
		try {
			Long id = Long.parseLong(request.getParameter("projectId"));
			Project project = projectService.findOne(id);
			Map<String, Object> dataMap = getDataMap(project);

			downloadService.buildReportsExcelDocument(dataMap, response);
		}catch(Exception e) {
			
		}
	}

	private Map<String, Object> getDataMap(Project project) {

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("issues", project.getIssues());
		dataMap.put("actionItems", project.getActionItems());
		dataMap.put("lessonsLearned", project.getLessonsLearned());
		dataMap.put("risks", project.getRisks());
		final List<Project> projects = projectService.findAll();
		dataMap.put("projects", projects);
		return dataMap;
	}

	@RequestMapping(value = "/programreport/xls", method = RequestMethod.GET)
	public void getProgramReportXLS(final HttpServletResponse response,
			final HttpServletRequest request, final Principal principal,
			Model model) throws Exception {

		try {
			Long id = null;
			if(null==request.getParameter("programId")) {
				List<Program> programs = programService.findAll(); //'programs' will not be empty per jsp
				id = programs.get(0).getId();
			}
			else
				id = Long.parseLong(request.getParameter("programId"));
			Program program = programService.findOne(id);
			Map<String, Object> dataMap = getDataMap(program);
			downloadService.buildReportsExcelDocument(dataMap, response);
		}catch(Exception e) {
			
		}
	}

	private Map<String, Object> getDataMap(Program program) {

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("issues", program.getIssues());
		dataMap.put("actionItems", program.getActionItems());
		dataMap.put("lessonsLearned", program.getLessonsLearned());
		dataMap.put("risks", program.getRisks());
		final List<Program> programs = programService.findAll();
		dataMap.put("programs", programs);
		return dataMap;
		
		

	}

	@RequestMapping(value = "/statusreport/xls", method = RequestMethod.GET)
	public void getStatusReportXLS(final HttpServletResponse response,
			final Principal principal) throws Exception {

		Map<String, Object> dataMap = getDataMap(principal);
		final List<Project> projects = projectService.findAll();
		dataMap.put("projects", projects);
		downloadService.buildReportsExcelDocument(dataMap, response);
	}

	private Map<String, Object> getDataMap(Principal principal) {
		final Principal currentUser = principalService.findByName(principal
				.getName());
		final List<Issue> issues = issueService
				.findCurrentUserIssues(currentUser);
		final List<ActionItem> actionItems = actionItemService
				.findCurrentUserActionItems(currentUser);
		Collections.sort(actionItems);
		List<LessonsLearned> lessonsLearned = learnedService.findAll();
		List<Risk> risks = riskService.findAll();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("issues", issues);
		dataMap.put("actionItems", actionItems);
		dataMap.put("lessonsLearned", lessonsLearned);
		dataMap.put("risks", risks);
		final List<Project> projects = projectService.findAll();
		dataMap.put("projects", projects);
		return dataMap;
	}

	protected Map referenceData() {
		final Map referenceData = new HashMap();
		return referenceData;
	}

}