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


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.OrganizationGroup;
import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.model.Program;
import com.archsystemsinc.ipms.sec.persistence.service.IOrganizationGroupService;
import com.archsystemsinc.ipms.sec.persistence.service.IPrincipalService;
import com.archsystemsinc.ipms.sec.persistence.service.IProgramService;
import com.archsystemsinc.ipms.sec.persistence.service.IProjectService;
import com.archsystemsinc.ipms.sec.util.GenericConstants;
import com.archsystemsinc.ipms.web.common.AbstractController;


@Controller
public class OrganizationGroupController extends AbstractController<OrganizationGroup> {

	public OrganizationGroupController() {
		super(OrganizationGroup.class);
	}

	private final Log log = LogFactory.getLog(OrganizationGroupController.class);

	@Autowired
	private IOrganizationGroupService service;

	@Autowired
	private IPrincipalService principalService;

	@Autowired
	private IProjectService projectService;

	@Autowired
	private IProgramService programService;
	
	@Override
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat(
				GenericConstants.DEFAULT_DATE_FORMAT);
		dateFormat.setLenient(false);
		// true passed to CustomDateEditor constructor means convert empty
		// String to null
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	@RequestMapping(value = "/organization-groups")
	public String organizationGroup(final Model model) {
		final List<OrganizationGroup> organizationGroups = service.findAll();
//		if(organizationGroups != null) {
//			Collections.sort((List<OrganizationGroup>) organizationGroups);
//		}		
		model.addAttribute("organizationGroups", organizationGroups);
		return "organization-groups";
	}

	@RequestMapping(value = "/organization-group/{id}")
	public String organizationGroup(@PathVariable("id") final Long id,
			final Model model, final HttpServletRequest request) {
		final OrganizationGroup organizationGroup = service.findOne(id);
		model.addAttribute("organizationGroup", organizationGroup);
		
			final String page = request.getParameter("page");
			if (page == null)
				model.addAttribute("page", "");
			else
				model.addAttribute("page", page);
		return "organization-group";
	}

	@RequestMapping(value = "/new-organization-group", method = RequestMethod.GET)
	public String newOrganizationGroup(final Model model) {
		final OrganizationGroup organizationGroup = new OrganizationGroup();
		organizationGroup.setDateCreated(new Date());
		model.addAttribute("organizationGroup", organizationGroup);
		model.addAttribute("referenceData", referenceData());
		return "organization-groupsadd";
	}
	
	@RequestMapping(value = "/remove-group-program/{groupId}/{programId}", method = RequestMethod.GET)
	public String removeOrganizationGroup(final Model model, @PathVariable("groupId")final Long groupId, @PathVariable("programId")final Long programId) {
		final Program program = programService.findOne(programId);
		if(null!=program) {
			program.setOrganizationGroup(null);
			programService.update(program);
			model.addAttribute("organizationGroup", service.findOne(groupId));
			model.addAttribute("referenceData", referenceData());
		}
		return "organization-groups";
	}

	@RequestMapping(value = "/edit-organization-group/{id}", method = RequestMethod.GET)
	public String editOrganizationGroup(@PathVariable("id") final Long id,
			final Model model) {
		final OrganizationGroup organizationGroup = service.findOne(id);
		model.addAttribute("organizationGroup", organizationGroup);
		model.addAttribute("referenceData", referenceData());
		return "organization-groupsedit";
	}

	@RequestMapping(value = "/new-organization-group", method = RequestMethod.POST)
	public String addOrganizationGroup(
			@Valid @ModelAttribute("organizationGroup") final OrganizationGroup organizationGroup,
			final BindingResult result, final Model model) {
        String returnView = "";

		try{
		if (result.hasErrors()) {
			returnView = "organization-groupsadd";
			model.addAttribute("organizationGroup", organizationGroup);
			model.addAttribute("referenceData", referenceData());
		} else {
			service.create(organizationGroup);
			model.addAttribute("success","success.organizationGroup.created");
			returnView = "forward:organization-groups";
		}	
		}
		catch(Exception e)
		{
			model.addAttribute("error","unique.organizationGroup.organizationGroupName");
			returnView = "organizationGroupsadd";
			model.addAttribute("organizationGroup", organizationGroup);
			model.addAttribute("referenceData", referenceData());	
		}
		return returnView;

	}

	@RequestMapping(value = "/edit-organization-group", method = RequestMethod.POST)
	public String updateOrganizationGroup(
			@Valid @ModelAttribute("organizationGroup") final OrganizationGroup organizationGroup,
			final BindingResult result, final Model model,
			final HttpServletRequest request) {
		String returnView = "";
		// using name as long --bad idea
		
		try{
		if (result.hasErrors()) {
			returnView = "organization-groupsedit";
		} else {
			service.update(organizationGroup);
			returnView = "forward:organization-group/" + organizationGroup.getId();
			model.addAttribute("success","success.organizationGroup.updated");
		}
		model.addAttribute("organizationGroup", organizationGroup);
		model.addAttribute("referenceData", referenceData());
		}catch(Exception e)
		{
			log.error(e.getMessage(), e);
			model.addAttribute("error","Update was not completed successfully");
			returnView = "organization-groups" + organizationGroup.getId();
			model.addAttribute("organizationGroup", organizationGroup);
			model.addAttribute("referenceData", referenceData());	
		}
		
		return returnView;
	}

	protected Map referenceData() {
		final Map referenceData = new HashMap();

		final Map<Integer, String> likelihoodList = new LinkedHashMap<Integer, String>();
		likelihoodList.put(0, "High");
		likelihoodList.put(1, "Medium");
		likelihoodList.put(2, "Low");
		referenceData.put("likelihoodList", likelihoodList);

		final List<Principal> list = principalService.findAll();
		final Map<Integer, String> pList = new LinkedHashMap<Integer, String>();
		for (int i = 0; i < list.size(); i++) {
			pList.put(list.get(i).getId().intValue(), list.get(i).getName());
		}
		referenceData.put("principalList", pList);
		return referenceData;
	}

	@Override
	protected IService<OrganizationGroup> getService() {
		return service;
	}
}
