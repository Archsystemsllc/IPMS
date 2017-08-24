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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.poi.service.DownloadService;
import com.archsystemsinc.ipms.sec.model.Comment;
import com.archsystemsinc.ipms.sec.model.OrganizationGroup;
import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.model.Program;
import com.archsystemsinc.ipms.sec.model.Project;
import com.archsystemsinc.ipms.sec.model.Requirement;
import com.archsystemsinc.ipms.sec.model.RevisionHistory;
import com.archsystemsinc.ipms.sec.persistence.service.ICommentService;
import com.archsystemsinc.ipms.sec.persistence.service.IOrganizationGroupService;
import com.archsystemsinc.ipms.sec.persistence.service.IPrincipalService;
import com.archsystemsinc.ipms.sec.persistence.service.IProgramService;
import com.archsystemsinc.ipms.sec.persistence.service.IProjectService;
import com.archsystemsinc.ipms.sec.persistence.service.IRequirementService;
import com.archsystemsinc.ipms.sec.persistence.service.IRevisionHistoryService;
import com.archsystemsinc.ipms.sec.util.GenericConstants;
import com.archsystemsinc.ipms.web.common.AbstractController;

@Controller
public class RequirementController extends AbstractController<Requirement> {

	public RequirementController() {
		super(Requirement.class);
	}

	private final Log log = LogFactory.getLog(RequirementController.class);

	@Autowired
	private IRequirementService service;
	@Autowired
	private IRevisionHistoryService revisionHistoryService;
	@Autowired
	private ICommentService commentsService;
	@Autowired
	private IPrincipalService principalService;
	@Autowired
	private DownloadService downloadService;
	@Autowired
	private IProgramService programService;
	@Autowired
	private IOrganizationGroupService organizationGroupService;
	@Autowired
	private IProjectService projectService;

	@RequestMapping(value = "/requirements")
	public String requirement(final Model model) {
		final List<Requirement> requirements = service.findAll();
		model.addAttribute("requirements", requirements);
		return "requirements";
	}

	@RequestMapping(value = "/requirement/{id}", method = RequestMethod.GET)
	public String requirement(@PathVariable("id") final Long id,
			final Model model) {
		final Comment comment = new Comment();
		final Requirement requirement = service.findOne(id);
		comment.setRequirement(requirement);
		final List<RevisionHistory> revisionHist = revisionHistoryService
				.findByRequirement(id);
		requirement.setRevisions(new HashSet<RevisionHistory>(revisionHist));
		model.addAttribute("requirement", requirement);
		model.addAttribute("comment", comment);
		return "requirement";
	}

	@RequestMapping(value = "/new-requirement", method = RequestMethod.GET)
	public String newRequirement(final Model model) {
		final Requirement requirement = new Requirement();
		model.addAttribute("requirement", requirement);
		model.addAttribute("referenceData", referenceData());
		return "requirementsadd";
	}

	public static HttpSession getSession() {
		final ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		return attr.getRequest().getSession(true); // true == allow create
	}

	@RequestMapping(value = "/edit-requirement/{id}", method = RequestMethod.GET)
	public String editRequirement(@PathVariable("id") final Long id,
			final Model model) {
		final Requirement requirement = service.findOne(id);
		model.addAttribute("requirement", requirement);
		model.addAttribute("referenceData", referenceData());

		final HttpSession session = getSession();
		session.setAttribute("requirement", requirement);
		return "requirementsedit";
	}

	@RequestMapping(value = "/new-requirement", method = RequestMethod.POST)
	public String addRequirement(
			@Valid @ModelAttribute("requirement") final Requirement requirement,
			final BindingResult result, final Model model) {
		String returnView = "";
		if (result.hasErrors()) {
			returnView = "requirementsadd";
			model.addAttribute("requirement", requirement);
			model.addAttribute("referenceData", referenceData());
		} else {
			service.create(requirement);

			final String currentUser = SecurityContextHolder.getContext()
					.getAuthentication().getName();
			final Principal principal = principalService
					.findByName(currentUser);

			model.addAttribute("success", "success.requirement.created");
			returnView = "redirect:requirements";

		}
		return returnView;
	}

	private List<RevisionHistory> buildRevisionHistory(Requirement oldRequirement,
			Requirement requirement, Principal principal) {
		List<RevisionHistory> returnList = new ArrayList<RevisionHistory>();
		RevisionHistory revisionHistory = null;
		if (!oldRequirement.getPriorityString().equalsIgnoreCase(
				requirement.getPriorityString())){
			revisionHistory = new RevisionHistory();
			revisionHistory.setPrincipal(principal);
			revisionHistory.setPrincipalId(principal.getId());
			revisionHistory.setRequirementId(requirement.getId());
			
			revisionHistory.setText("Priority String : " + oldRequirement.getPriorityString() + " - "
					+ requirement.getPriorityString());
			revisionHistory.setRequirement(requirement);
			returnList.add(revisionHistory);
			
		}
		if(!oldRequirement.getDescription().equalsIgnoreCase(
						requirement.getDescription())){
			revisionHistory = new RevisionHistory();
			revisionHistory.setPrincipal(principal);
			revisionHistory.setPrincipalId(principal.getId());
			revisionHistory.setRequirementId(requirement.getId());
			
			revisionHistory.setText("Description : " + oldRequirement.getDescription() + " - "
					+ requirement.getDescription());
			revisionHistory.setRequirement(requirement);
			returnList.add(revisionHistory);
		}
		if(!oldRequirement.getRiskLevelString().equalsIgnoreCase(
				requirement.getRiskLevelString())){
			revisionHistory = new RevisionHistory();
			revisionHistory.setPrincipal(principal);
			revisionHistory.setPrincipalId(principal.getId());
			revisionHistory.setRequirementId(requirement.getId());
			
			revisionHistory.setText("Risk Level : " + oldRequirement.getRiskLevelString() + " - "
					+ requirement.getRiskLevelString());
			revisionHistory.setRequirement(requirement);
			returnList.add(revisionHistory);
		}
		if(!oldRequirement.getRiskDescription().equalsIgnoreCase(
				requirement.getRiskDescription())){
			revisionHistory = new RevisionHistory();
			revisionHistory.setPrincipal(principal);
			revisionHistory.setPrincipalId(principal.getId());
			revisionHistory.setRequirementId(requirement.getId());
			
			revisionHistory.setText("Risk Description : " + oldRequirement.getRiskDescription() + " - "
					+ requirement.getRiskDescription());
			revisionHistory.setRequirement(requirement);
			returnList.add(revisionHistory);
		}
		
		return returnList;
	}

	@RequestMapping(value = "/new-comment", method = RequestMethod.POST)
	public String addComment(@ModelAttribute("comment") final Comment comment,
			final BindingResult result, final Model model) {
		final Requirement requirement = service.findOne(comment
				.getRequirementId());
		final java.util.Date date = new java.util.Date();
		comment.setMadeAt(new Timestamp(date.getTime()));
		final String currentUser = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		final Principal principal = principalService.findByName(currentUser);
		comment.setPrincipal(principal);
		comment.setRequirement(requirement);
		commentsService.create(comment);
		return "redirect:requirement/" + comment.getRequirementId();
	}

	@RequestMapping(value = "/edit-requirement", method = RequestMethod.POST)
	public String updateRequirement(
			@ModelAttribute("requirement") final Requirement requirement,
			final BindingResult result, final Model model) {
		String returnView = "";
		if (result.hasErrors()) {
			returnView = "requirementsedit";
		} else {
			final String currentUser = SecurityContextHolder.getContext()
					.getAuthentication().getName();
			Principal principal = principalService.findByName(currentUser);
			Requirement oldReq = service.findOne(requirement.getId());
			List<RevisionHistory> histList = buildRevisionHistory(oldReq,
														requirement, principal);
			service.update(requirement);
			if(null != histList && histList.size() > 0){
				revisionHistoryService.bulkCreate(histList);
			}
			
			model.addAttribute("success", "success.requirement.deleted");

			returnView = "redirect:requirements";
		}

		model.addAttribute("requirement", requirement);
		model.addAttribute("referenceData", referenceData());
		return returnView;
	}
	
	@RequestMapping(value = "/requirements/xls", method = RequestMethod.GET)
	public void getXLS(final HttpServletResponse response, final Model model,
			final java.security.Principal principal)
					throws ClassNotFoundException {
		logger.debug("Received request to download requirements report as an XLS");
		final String sheetName = GenericConstants.REQUIREMENTS;
		final String[] coloumnNames = { "1", "2", "3", "4", "5"};
		// Delegate to downloadService. Make sure to pass an instance of
		// HttpServletResponse
		final Principal currentUser = principalService.findByName(principal
				.getName());
		final List requirements = service.findCurrentUserRequirements(currentUser);
		downloadService.downloadXLS(response, sheetName, requirements, coloumnNames);
	}


	protected Map referenceData() {
		final Map referenceData = new HashMap();

		final Map<Integer, String> priorityList = new LinkedHashMap<Integer, String>();
		priorityList.put(0, "High");
		priorityList.put(1, "Medium");
		priorityList.put(2, "Low");
		referenceData.put("priorityList", priorityList);

		final Map<Integer, String> riskLevelList = new LinkedHashMap<Integer, String>();
		riskLevelList.put(0, "High");
		riskLevelList.put(1, "Medium");
		riskLevelList.put(2, "Low");
		referenceData.put("riskLevelList", riskLevelList);

		Map<Integer, String> typeList = new LinkedHashMap<Integer, String>();
		typeList.put(0, "Business");
		typeList.put(1, "Functional");
		typeList.put(2, "User");
		referenceData.put("typeList", typeList);
		
		// Code for Modifying requirements to systems based;
		
		OrganizationGroup organizationGroup = organizationGroupService.findOne(1);
		
		final List<Program> programlist = programService.findProgramListByOrganizationGroup(organizationGroup);
		final Map<Integer, String> prList = new LinkedHashMap<Integer, String>();
		for (int i = 0; i < programlist.size(); i++) {
			prList.put(programlist.get(i).getId().intValue(), programlist
					.get(i).getName());
		}
		referenceData.put("programList", prList);

		final List<Principal> list = principalService.findAll();
		final Map<Integer, String> pList = new LinkedHashMap<Integer, String>();
		for (int i = 0; i < list.size(); i++) {
			pList.put(list.get(i).getId().intValue(), list.get(i).getName());
		}
		referenceData.put("principalList", pList);
		
		final List<Project> projectsList = projectService.findActiveProjects();
		
		final Map<Integer, String> projList = new LinkedHashMap<Integer, String>();
		for (int i = 0; i < projectsList.size(); i++) {
			projList.put(projectsList.get(i).getId().intValue(), projectsList.get(i).getName());
		}
		referenceData.put("projectList", projList);

		return referenceData;
	}

	@Override
	protected IService<Requirement> getService() {
		return service;
	}

}
