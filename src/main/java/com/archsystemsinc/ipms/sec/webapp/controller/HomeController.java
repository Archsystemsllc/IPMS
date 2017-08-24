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

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.archsystemsinc.ipms.sec.model.OrganizationGroup;
import com.archsystemsinc.ipms.sec.model.Program;
import com.archsystemsinc.ipms.sec.model.Project;
import com.archsystemsinc.ipms.sec.model.ProjectEvm;
import com.archsystemsinc.ipms.sec.model.Task;
import com.archsystemsinc.ipms.sec.persistence.service.IActionItemService;
import com.archsystemsinc.ipms.sec.persistence.service.IMeetingService;
import com.archsystemsinc.ipms.sec.persistence.service.IOrganizationGroupService;
import com.archsystemsinc.ipms.sec.persistence.service.IPrincipalService;
import com.archsystemsinc.ipms.sec.persistence.service.IProgramService;
import com.archsystemsinc.ipms.sec.persistence.service.IProjectEvmService;
import com.archsystemsinc.ipms.sec.persistence.service.IProjectService;
import com.archsystemsinc.ipms.sec.persistence.service.ITaskService;
import com.archsystemsinc.ipms.sec.persistence.service.IUserActivityService;

@Controller
public class HomeController {

	@Autowired
	private IProjectService projectService;

	@Autowired
	private IOrganizationGroupService organizationGroupService;

	@Autowired
	private IProgramService programService;

	@Autowired
	private IMeetingService meetingService;

	@Autowired
	private IPrincipalService principalService;

	@Autowired
	private IActionItemService actionItemService;

	@Autowired
	private ITaskService taskService;

	@Autowired
	private IProjectEvmService projectEvmService;

	@Autowired
	private IUserActivityService userActivityService;

	private final Log log = LogFactory.getLog(HomeController.class);

	@RequestMapping(value = "/groupdashboard", method = RequestMethod.GET)
	public String groupDashboard(final Model model, final Principal principal) {
		
		try {
			final com.archsystemsinc.ipms.sec.model.Principal currentUser = principalService
					.findByName(principal.getName());
			model.addAttribute("currentUser", currentUser);
			List<String> entityTypes = new ArrayList<String>();
			
			entityTypes.add("OrganizationGroup");
			final List<OrganizationGroup> organizationGroups = organizationGroupService.findAll();	
			organizationGroups.remove(4);
			organizationGroups.remove(4);
			organizationGroups.remove(4);
			organizationGroups.remove(4);
			model.addAttribute("organizationGroups", organizationGroups);
			//restricting list to vertical groups only
			
			for(OrganizationGroup organizationGroup: organizationGroups) {
				Integer numberOfProjects = 0;				
				Set<Program> programs = organizationGroup.getPrograms();
				for(Program program: programs) {
					numberOfProjects += program.getProjects().size();
				}
				
				organizationGroup.setNumberOfProjects(numberOfProjects);
			}
			
			model.addAttribute("view", "groupdashboard");
			
			if (principal.getName().equalsIgnoreCase("projectmanager")) {
				return "redirect:project/36";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "groupdashboard";
	}
	
	@RequestMapping(value = "/dashboard/{id}", method = RequestMethod.GET)
	public String home(@PathVariable("id") final Long id,final Model model, final Principal principal) {
		try {
			final com.archsystemsinc.ipms.sec.model.Principal currentUser = principalService
					.findByName(principal.getName());
					
			OrganizationGroup organizationGroup = organizationGroupService.findOne(id);
			List<String> entityTypes = new ArrayList<String>();
			List<Program> programs = new ArrayList<Program>();
			entityTypes.add("Project");
			entityTypes.add("Program");
			
			List<Program> allPrograms = programService.findProgramListByOrganizationGroup(organizationGroupService.findOne(1L));
			
			for(Program aProgram : allPrograms){				
				switch(id.intValue()){
						case 2:	
							switch(aProgram.getName()){
								case "SOncology Care Model (OCM)":
									programs.add(aProgram);
								    break;
								case "Seamless Care Models Group(CEC)":
									programs.add(aProgram);
								    break;								    		
								default:					
								    break;
							}
						    break;
						case 4:
							break;
						default:					
						    break;
				}				
			}
			
			model.addAttribute("programs", programs);
			
			model.addAttribute("artifactEvents",
					userActivityService.findByEntityTypeInOrderByDateCreatedDesc(entityTypes));
			model.addAttribute("meetings", meetingService.findByInvited(currentUser.getName()));
			model.addAttribute("tasks", taskService.findCurrentUserTasks(currentUser));
			
			final List<Project> projects = projectService.findUserProjects(currentUser);
			model.addAttribute("actionItems", actionItemService.findCurrentUserActionItems(currentUser));
			model.addAttribute("referenceData", referenceData());
			Map<Long, List<ProjectEvm>> projectEvmMap = new HashMap<Long, List<ProjectEvm>>();
			for (Project aProject : projects) {

			}
			model.addAttribute("projects", projects);
			model.addAttribute("projectEvmMap", projectEvmMap);
			switch(id.intValue()){
				case 1:
					model.addAttribute("view", "dashboard");
				    break;
				case 2:
					model.addAttribute("view", "seamless");
				    break;
				default:
					model.addAttribute("view", "dashboard");
				    break;
			}
			//model.addAttribute("view", "dashboard");
			model.addAttribute("currentUser", currentUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "dashboard";
	}		
	
	@RequestMapping(value = "/groupdashboard/business", method = RequestMethod.GET)
	public String homeBsg(final Model model, final Principal principal) {
		try {
			final com.archsystemsinc.ipms.sec.model.Principal currentUser = principalService
					.findByName(principal.getName());
					
			OrganizationGroup organizationGroup = organizationGroupService.findOne(1L);
			List<String> entityTypes = new ArrayList<String>();
			entityTypes.add("Project");
			entityTypes.add("Program");
			
			final List<OrganizationGroup> organizationGroups = organizationGroupService.findAll();	
			organizationGroups.remove(0);
			organizationGroups.remove(0);
			organizationGroups.remove(0);
			organizationGroups.remove(0);
			
			model.addAttribute("programs", programService.findProgramListByOrganizationGroup(organizationGroup));
			//model.addAttribute("programs", organizationGroups);
			//model.addAttribute("organizationGroups", organizationGroups);
			model.addAttribute("artifactEvents",
					userActivityService.findByEntityTypeInOrderByDateCreatedDesc(entityTypes));
			model.addAttribute("meetings", meetingService.findByInvited(currentUser.getName()));
			model.addAttribute("tasks", taskService.findCurrentUserTasks(currentUser));
			
			final List<Project> projects = projectService.findUserProjects(currentUser);
			
			//final List<Project> projects =
			model.addAttribute("actionItems", actionItemService.findCurrentUserActionItems(currentUser));
			model.addAttribute("referenceData", referenceData());
			Map<Long, List<ProjectEvm>> projectEvmMap = new HashMap<Long, List<ProjectEvm>>();
			for (Project aProject : projects) {
				final Project project = projectService.findOne(aProject.getId());
				List<ProjectEvm> projectEvmList = projectEvmService.findByProjectOrderByDateAsc(project);
				projectEvmMap.put(aProject.getId(), projectEvmList);
			}
			model.addAttribute("projects", projects);
			model.addAttribute("projectEvmMap", projectEvmMap);
			model.addAttribute("view", "dashboard-bsg");
			model.addAttribute("currentUser", currentUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "dashboard-bsg";
	}
	
	@RequestMapping(value = "/modeldashboard", method = RequestMethod.GET)
	public String homeModel(final Model model, final Principal principal) {
		try {
			final com.archsystemsinc.ipms.sec.model.Principal currentUser = principalService
					.findByName(principal.getName());
					
			OrganizationGroup organizationGroup = organizationGroupService.findOne(1L);
			List<String> entityTypes = new ArrayList<String>();
			entityTypes.add("Project");
			entityTypes.add("Program");
			
			final List<OrganizationGroup> organizationGroups = organizationGroupService.findAll();	
			organizationGroups.remove(0);
			organizationGroups.remove(0);
			organizationGroups.remove(0);
			organizationGroups.remove(0);
			
			model.addAttribute("programs", programService.findProgramListByOrganizationGroup(organizationGroup));
			//model.addAttribute("programs", organizationGroups);
			//model.addAttribute("organizationGroups", organizationGroups);
			model.addAttribute("artifactEvents",
					userActivityService.findByEntityTypeInOrderByDateCreatedDesc(entityTypes));
			model.addAttribute("meetings", meetingService.findByInvited(currentUser.getName()));
			model.addAttribute("tasks", taskService.findCurrentUserTasks(currentUser));
			
			final List<Project> projects = projectService.findUserProjects(currentUser);
			
			//final List<Project> projects =
			model.addAttribute("actionItems", actionItemService.findCurrentUserActionItems(currentUser));
			model.addAttribute("referenceData", referenceData());
			Map<Long, List<ProjectEvm>> projectEvmMap = new HashMap<Long, List<ProjectEvm>>();
			for (Project aProject : projects) {
				final Project project = projectService.findOne(aProject.getId());
				List<ProjectEvm> projectEvmList = projectEvmService.findByProjectOrderByDateAsc(project);
				projectEvmMap.put(aProject.getId(), projectEvmList);
			}
			model.addAttribute("projects", projects);
			model.addAttribute("projectEvmMap", projectEvmMap);
			model.addAttribute("view", "dashboard");
			model.addAttribute("currentUser", currentUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "dashboard";
	}
	
	@RequestMapping(value = "/projectdashboard/{id}", method = RequestMethod.GET)
	public String projectDashboard(@PathVariable("id") final Long id,final Model model, final Principal principal) {
		try {
			final com.archsystemsinc.ipms.sec.model.Principal currentUser = principalService
					.findByName(principal.getName());
					
			Program program = programService.findOne(id);
			
			List<String> entityTypes = new ArrayList<String>();
			entityTypes.add("Project");
			entityTypes.add("Risk");
			entityTypes.add("Issue");
			entityTypes.add("Task");
			entityTypes.add("LessonsLearned");
			
			//model.addAttribute("programs", programService.findOne(id);
			
			model.addAttribute("artifactEvents",
					userActivityService.findByEntityTypeInOrderByDateCreatedDesc(entityTypes));
			model.addAttribute("meetings", meetingService.findByInvited(currentUser.getName()));
			List<Task> tasks = taskService.findCurrentUserTasks(currentUser);
			List<Task> programTasks = new ArrayList<Task>();
			for(Task task:tasks){
				if(task.getProgram().getId().equals(id)){
					programTasks.add(task);
				}
			}
			model.addAttribute("tasks", programTasks);
			
			final List<Project> projects = projectService.findByProgram(program);
			model.addAttribute("actionItems", actionItemService.findCurrentUserActionItems(currentUser));
			model.addAttribute("referenceData", referenceData());
			Map<Long, List<ProjectEvm>> projectEvmMap = new HashMap<Long, List<ProjectEvm>>();
			for (Project aProject : projects) {
				final Project project = projectService.findOne(aProject.getId());
				List<ProjectEvm> projectEvmList = projectEvmService.findByProjectOrderByDateAsc(project);
				projectEvmMap.put(aProject.getId(), projectEvmList);
			}
			model.addAttribute("projects", projects);
			model.addAttribute("projectEvmMap", projectEvmMap);
			model.addAttribute("view", "projectdashboard");
			model.addAttribute("currentUser", currentUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "projectdashboard";
	}

	@RequestMapping(value = "/reports", method = RequestMethod.GET)
	public String reports(final Model model , final Principal principal) {
		final com.archsystemsinc.ipms.sec.model.Principal currentUser = principalService
				.findByName(principal.getName());
		model.addAttribute("currentUser", currentUser);
		return "reports";
	}

	@RequestMapping("/")
	public String home(final Principal currentUser, final Model model , final Principal principal) {
		model.addAttribute("currentUser", currentUser);
		return "dashboard";
	}

	protected Map referenceData() {
		final Map referenceData = new HashMap();

		final List<Program> listOfPrograms = programService.findAll();
		final Map<Integer, String> prgList = new LinkedHashMap<Integer, String>();
		referenceData.put("programList", programService.findAll());
		return referenceData;
	}

}
