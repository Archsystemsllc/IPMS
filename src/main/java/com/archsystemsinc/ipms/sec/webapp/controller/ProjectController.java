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
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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

import com.archsystemsinc.ipms.jira.service.IJIRASService;
import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.poi.service.DownloadService;
import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.model.Program;
import com.archsystemsinc.ipms.sec.model.Project;
import com.archsystemsinc.ipms.sec.model.ProjectEvm;
import com.archsystemsinc.ipms.sec.persistence.service.IActionItemService;
import com.archsystemsinc.ipms.sec.persistence.service.IIssueService;
import com.archsystemsinc.ipms.sec.persistence.service.ILessonsLearnedService;
import com.archsystemsinc.ipms.sec.persistence.service.IPrincipalService;
import com.archsystemsinc.ipms.sec.persistence.service.IProgramService;
import com.archsystemsinc.ipms.sec.persistence.service.IProjectEvmService;
import com.archsystemsinc.ipms.sec.persistence.service.IProjectService;
import com.archsystemsinc.ipms.sec.persistence.service.IRiskService;
import com.archsystemsinc.ipms.sec.util.GenericConstants;
import com.archsystemsinc.ipms.web.common.AbstractController;


@Controller
public class ProjectController extends AbstractController<Project> {

	public ProjectController() {
		super(Project.class);
	}

	private final Log log = LogFactory.getLog(ProjectController.class);

	@Autowired
	private IProjectService service;

	@Autowired
	private IProgramService programService;

    @Autowired
    private IProjectEvmService projectEvmService;

	@Autowired
	private IPrincipalService principalService;

	@Autowired
	private DownloadService downloadService;
	
	@Autowired
    private IIssueService issueService;

    @Autowired
    private IActionItemService actionItemService;

    @Autowired
    private ILessonsLearnedService learnedService;

    @Autowired
    private IRiskService riskService;


    @Autowired
    private IJIRASService jiraService;

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

	@RequestMapping(value = "/projects")
	public String project(final Model model, java.security.Principal principal) {
		final com.archsystemsinc.ipms.sec.model.Principal currentUser = principalService.findByName(principal.getName());
		model.addAttribute("currentUser", currentUser);
		final List<Project> projects = service.findAll();
		model.addAttribute("projects", projects);
		return "projects";
	}
	
//	@RequestMapping(value = "/upload", method = RequestMethod.GET)
//	public String upload(final Model model, java.security.Principal principal) {
//		final com.archsystemsinc.ipms.sec.model.Principal currentUser = principalService.findByName(principal.getName());
//		model.addAttribute("currentUser", currentUser);
//		return "uploadForm";
//	}

	@RequestMapping(value = "/project/{id}", method = RequestMethod.GET)
	public String project(@PathVariable("id") final Long id,
			final Model model, final HttpServletRequest request, java.security.Principal principal) {
		final com.archsystemsinc.ipms.sec.model.Principal currentUser = principalService.findByName(principal.getName());
		model.addAttribute("currentUser", currentUser);
        try{
		final Project project = service.findOne(id);
		project.setJiraProjectName(jiraService.getJIRAProjectName(project.getJiraProjectKey()));
        List<ProjectEvm> projectEvmList = projectEvmService.findByProjectOrderByDateAsc(project);
		model.addAttribute("project", project);
		String messageString = request.getParameter("success");
		
		if (messageString !=null && !messageString.equalsIgnoreCase("")) {
			Integer messageId = Integer.valueOf(request.getParameter("success"));
			if(messageId == 1) {
				model.addAttribute("success","success.meeting.created");
			} else if(messageId == 2) {
				model.addAttribute("success","success.issue.created");
			}
			   
		}
			
		final String page = request.getParameter("page");
		if (page == null)
		{
			model.addAttribute("page", "");
		}
		else
		{
			model.addAttribute("page", page);
		}
		
		if(projectEvmList!=null) {
			if(projectEvmList.size()>0) {
				int latest = projectEvmList.size() -1;
				model.addAttribute("currentEvm", projectEvmList.get(latest));
			}
		}
        model.addAttribute("projectEvm", projectEvmList);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
       
        	return "project";
	}

	@RequestMapping(value = "/new-project", method = RequestMethod.GET)
	public String newProject(final Model model, java.security.Principal principal) {
		final com.archsystemsinc.ipms.sec.model.Principal currentUser = principalService.findByName(principal.getName());
		model.addAttribute("currentUser", currentUser);
		final Project project = new Project();
		model.addAttribute("project", project);
		model.addAttribute("referenceData", referenceData());
		return "projectsadd";
	}

	@RequestMapping(value = "/new-project/{id}", method = RequestMethod.GET)
	public String newProjectProgram(@PathVariable("id") final Long id,
			final Model model, java.security.Principal principal) {
		final com.archsystemsinc.ipms.sec.model.Principal currentUser = principalService.findByName(principal.getName());
		model.addAttribute("currentUser", currentUser);
		final Project project = new Project();
		project.setProgramId(id);
		model.addAttribute("project", project);
		model.addAttribute("referenceData", referenceData());
		return "projectsadd";
	}
	
	
	@RequestMapping(value = "/edit-project/{id}", method = RequestMethod.GET)
	public String editProject(@PathVariable("id") final Long id,
			final Model model, java.security.Principal principal) {
		final com.archsystemsinc.ipms.sec.model.Principal currentUser = principalService.findByName(principal.getName());
		model.addAttribute("currentUser", currentUser);
		final Project project = service.findOne(id);
		project.setManagerId(project.getManager().getId());
		final Program program = programService.findOne(project.getProgram()
				.getId());
		project.setProgram(program);
		model.addAttribute("project", project);
		model.addAttribute("referenceData", referenceData());
		return "projectsedit";
	}

	@RequestMapping(value = "/new-project", method = RequestMethod.POST)
	public String addProject(
			@Valid @ModelAttribute("project") final Project project,
			final BindingResult result, final Model model) {
		String returnView = "";

		final Principal manager = principalService.findOne(project
				.getManagerId());
		final Program program = programService.findOne(project.getProgramId());
		project.setManager(manager);
		project.setProgram(program);
		try{
		if (result.hasErrors()) {
			returnView = "projectsadd";
			model.addAttribute("project", project);
			model.addAttribute("referenceData", referenceData());
		} else {
			service.create(project);

			final String currentUser = SecurityContextHolder.getContext()
					.getAuthentication().getName();
			model.addAttribute("currentUser", currentUser);
			@SuppressWarnings("unused")
			final Principal principal = principalService
					.findByName(currentUser);
			model.addAttribute("success","success.project.created");
			returnView = "forward:projects";
		}
		}
		catch(Exception e)
		{
			final String currentUser = SecurityContextHolder.getContext()
					.getAuthentication().getName();
			model.addAttribute("currentUser", currentUser);
			model.addAttribute("error","unique.project.projectName");
			returnView = "projectsadd";
			model.addAttribute("project", project);
			model.addAttribute("referenceData", referenceData());	
		}
		
		return returnView;
	}
	
	

	@RequestMapping(value = "/edit-project", method = RequestMethod.POST)
	public String updateProject(
			@Valid @ModelAttribute("project") final Project project,
			final BindingResult result, final Model model,
			final HttpServletRequest request) {

		String returnView = "";
		final Principal manager = principalService.findOne(project
				.getManagerId());
		final Program program = programService.findOne(project.getProgramId());
		project.setManager(manager);
		project.setProgram(program);
		if (request.getParameter("btnAction").equalsIgnoreCase("Activate")) {
			project.setActive(true);

		} else if (request.getParameter("btnAction").equalsIgnoreCase(
				"Deactivate")) {
			project.setActive(false);

		} else if (request.getParameter("btnAction").equalsIgnoreCase(
				"End Project")) {
			project.setEndDate(new Date(System.currentTimeMillis() - 86400000));

		} 
		try{
		if (result.hasErrors()) {
			returnView = "projectsadd";
		} else {
			if (program != null) {
				if (program.isActive())
				{
					service.update(project);
				}
			} else
			{
				service.update(project);
			   returnView = "redirect:project/" + project.getId();
			}
		}
		}
		catch(Exception e)
		{
			model.addAttribute("error","unique.project.projectName");
			returnView = "projectsadd";
			model.addAttribute("project", project);
			model.addAttribute("referenceData", referenceData());	
		}
		final String currentUser = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		model.addAttribute("currentUser", currentUser);
		returnView = "redirect:project/" + project.getId();
		model.addAttribute("project", project);
		model.addAttribute("referenceData", referenceData());
		model.addAttribute("success","success.project.created");
		return returnView;
	}

	protected Map referenceData() {
		final Map referenceData = new HashMap();
		final List<Principal> list = principalService.findAll();
		final Map<Integer, String> pList = new LinkedHashMap<Integer, String>();
		for (int i = 0; i < list.size(); i++) {
			pList.put(list.get(i).getId().intValue(), list.get(i).getName());
		}
		referenceData.put("principalList", pList);

		final List<Program> programlist = programService.findActivePrograms();
		final Map<Integer, String> prList = new LinkedHashMap<Integer, String>();
		for (int i = 0; i < programlist.size(); i++) {
			prList.put(programlist.get(i).getId().intValue(), programlist
					.get(i).getName());
		}
		referenceData.put("programList", prList);
		
		final Map<String, String> jiraProjectList = new TreeMap<>();
		jiraProjectList.put("", "");
		for(net.rcarz.jiraclient.Project project:jiraService.findAllProjects()) {
			jiraProjectList.put(project.getKey(), project.getName());
		}
		referenceData.put("jiraProjectList", jiraProjectList);
		
		return referenceData;
	}

	@Override
	protected IService<Project> getService() {
		return service;
	}

	@RequestMapping(value = "/projects/xls", method = RequestMethod.GET)
	public void getXLS(final HttpServletResponse response, final Model model,
			final java.security.Principal principal)
					throws ClassNotFoundException {
		logger.debug("Received request to download projects report as an XLS");
		final String sheetName = GenericConstants.PROJECTS;
		final String[] coloumnNames = { "1", "2", "3", "4", "5", "6", "7" };
		// Delegate to downloadService. Make sure to pass an instance of
		// HttpServletResponse
		final Principal currentUser = principalService.findByName(principal
				.getName());
		final List projects = service.findUserProjects(currentUser);
		downloadService
		.downloadXLS(response, sheetName, projects, coloumnNames);
	}

}
