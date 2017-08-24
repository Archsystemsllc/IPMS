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
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.collections.comparators.BooleanComparator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.archsystemsinc.ipms.poi.service.DownloadService;
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
public class ProgramController extends AbstractController<Program> {

	public ProgramController() {
		super(Program.class);
	}

	private final Log log = LogFactory.getLog(ProgramController.class);

	@Autowired
	private IProgramService service;

	@Autowired
	private IPrincipalService principalService;
	
	@Autowired
	private IOrganizationGroupService organizationGroupService;

	@Autowired
	private IProjectService projectService;

	@Autowired
	private DownloadService downloadService;

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

	@RequestMapping(value = "/programs")
	public String program(final Model model, java.security.Principal principal) {
		final com.archsystemsinc.ipms.sec.model.Principal currentUser = principalService.findByName(principal.getName());
		model.addAttribute("currentUser", currentUser);
		final List<Program> programs = service.findAll();
		if(programs != null) {
			Collections.sort((List<Program>) programs);
		}		
		model.addAttribute("programs", programs);
		return "programs";
	}

	@RequestMapping(value = "/program/{id}")
	public String program(@PathVariable("id") final Long id,
			final Model model, final HttpServletRequest request, java.security.Principal principal) {
		final com.archsystemsinc.ipms.sec.model.Principal currentUser = principalService.findByName(principal.getName());
		model.addAttribute("currentUser", currentUser);
		final Program program = service.findOne(id);
		model.addAttribute("program", program);
		
			final String page = request.getParameter("page");
			if (page == null)
				model.addAttribute("page", "");
			else
				model.addAttribute("page", page);
		return "program";
	}

	@RequestMapping(value = "/new-program", method = RequestMethod.GET)
	public String newProgram(final Model model, java.security.Principal principal) {
		final com.archsystemsinc.ipms.sec.model.Principal currentUser = principalService.findByName(principal.getName());
		model.addAttribute("currentUser", currentUser);
		final Program program = new Program();
		model.addAttribute("program", program);
		model.addAttribute("referenceData", referenceData());
		return "programsadd";
	}
	
	@RequestMapping(value = "/new-program/{groupId}", method = RequestMethod.GET)
	public String newProgramWithGroup(final Model model, @PathVariable("groupId") final Long groupId, java.security.Principal principal) {
		final com.archsystemsinc.ipms.sec.model.Principal currentUser = principalService.findByName(principal.getName());
		model.addAttribute("currentUser", currentUser);
		final OrganizationGroup group = organizationGroupService.findOne(groupId);
		final Program program = new Program();
		if(null!=group) {
			program.setOrganizationGroup(group);
		}
		model.addAttribute("program", program);
		model.addAttribute("referenceData", referenceData());
		return "programsadd";
	}

	@RequestMapping(value = "/edit-program/{id}", method = RequestMethod.GET)
	public String editProgram(@PathVariable("id") final Long id,
			final Model model, java.security.Principal principal) {
		final com.archsystemsinc.ipms.sec.model.Principal currentUser = principalService.findByName(principal.getName());
		model.addAttribute("currentUser", currentUser);
		final Program program = service.findOne(id);
		program.setManagerId(program.getManager().getId());
		model.addAttribute("program", program);
		model.addAttribute("referenceData", referenceData());
		return "programsedit";
	}

	@RequestMapping(value = "/new-program", method = RequestMethod.POST)
	public String addProgram(
			@Valid @ModelAttribute("program") final Program program,
			final BindingResult result, final Model model, java.security.Principal principal) {
		final com.archsystemsinc.ipms.sec.model.Principal currentUser = principalService.findByName(principal.getName());
		model.addAttribute("currentUser", currentUser);
System.out.println("testing");
		String returnView = "";
		final Principal manager = principalService.findOne(program
				.getManagerId());

		program.setManager(manager);
		try{
		if (result.hasErrors()) {
			returnView = "programsadd";
			model.addAttribute("program", program);
			model.addAttribute("referenceData", referenceData());
		} else {
			service.create(program);
			model.addAttribute("success","success.program.created");
			returnView = "forward:programs";
		}	
		}
		catch(Exception e)
		{
			model.addAttribute("error","unique.program.programName");
			returnView = "programsadd";
			model.addAttribute("program", program);
			model.addAttribute("referenceData", referenceData());	
		}
		return returnView;

	}

	@RequestMapping(value = "/edit-program", method = RequestMethod.POST)
	public String updateProgram(
			@Valid @ModelAttribute("program") final Program program,
			final BindingResult result, final Model model,
			final HttpServletRequest request, java.security.Principal principal) {
		final com.archsystemsinc.ipms.sec.model.Principal currentUser = principalService.findByName(principal.getName());
		model.addAttribute("currentUser", currentUser);
		String returnView = "";
		// using name as long --bad idea
		if(program.getOrganizationGroup().getId().equals(0L)) {
			program.setOrganizationGroup(null);
		}
		final Principal iprincipal = principalService.findOne(program
				.getManagerId());
		program.setManager(iprincipal);
		if (request.getParameter("btnAction").equalsIgnoreCase("Activate")) {
			program.setActive(true);
			
		} else if (request.getParameter("btnAction").equalsIgnoreCase(
				"Deactivate")) {
			program.setActive(false);

		} else if (request.getParameter("btnAction").equalsIgnoreCase(
				"End Program")) {
			System.out.println("Ending program " + program.getId());
			program.setEndDate(new Date(System.currentTimeMillis()));
		} 
		try{
		if (result.hasErrors()) {
			returnView = "programsedit";
		} else {
			service.update(program);
			returnView = "forward:program/" + program.getId();
			model.addAttribute("success","success.program.updated");
		}
		model.addAttribute("program", program);
		model.addAttribute("referenceData", referenceData());
		}catch(Exception e)
		{
			model.addAttribute("error","unique.program.programName");
			returnView = "programsadd";
			model.addAttribute("program", program);
			model.addAttribute("referenceData", referenceData());	
		}
		
		return returnView;
	}

	@SuppressWarnings("unchecked")
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
		
		List<OrganizationGroup> orgs = organizationGroupService.findAll();
		referenceData.put("organizationGroupList", orgs);
		return referenceData;
	}

	@Override
	protected IService<Program> getService() {
		return service;
	}

	@RequestMapping(value = "/programs/xls", method = RequestMethod.GET)
	public void getXLS(final HttpServletResponse response, final Model model,
			final java.security.Principal principal)
					throws ClassNotFoundException {
		logger.debug("Received request to download issues report as an XLS");
		final String sheetName = GenericConstants.PROGRAMS;
		final String[] coloumnNames = { "1", "2", "3", "4", "5", "6" };
		// Delegate to downloadService. Make sure to pass an instance of
		// HttpServletResponse
		final Principal currentUser = principalService.findByName(principal
				.getName());
		final List programs = service.findUserPrograms(currentUser);
		downloadService
				.downloadXLS(response, sheetName, programs, coloumnNames);
	}

}
