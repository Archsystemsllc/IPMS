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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.validation.Valid;

import com.archsystemsinc.ipms.sec.model.Privilege;
import com.archsystemsinc.ipms.sec.persistence.service.IPrivilegeService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
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

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.model.Role;
import com.archsystemsinc.ipms.sec.persistence.service.IPrincipalService;
import com.archsystemsinc.ipms.sec.persistence.service.IRoleService;
import com.archsystemsinc.ipms.sec.util.GenericConstants;
import com.archsystemsinc.ipms.web.common.AbstractController;


@Controller
public class PrincipalController extends AbstractController<Principal> {

	public PrincipalController() {
		super(Principal.class);
	}

	private final Log log = LogFactory.getLog(PrincipalController.class);

	@Autowired
	private IPrincipalService service;

	@Autowired
	private IRoleService roleService;

    @Autowired
    private IPrivilegeService privilegeService;
    
    @Autowired
    private IPrincipalService principalService;

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

	@RequestMapping(value = "/principals")
	public String principal(final Model model, java.security.Principal principal) {
		final com.archsystemsinc.ipms.sec.model.Principal currentUser = principalService.findByName(principal.getName());
		model.addAttribute("currentUser", currentUser);
		final List<Principal> principals = service.findAll();
		model.addAttribute("principals", principals);
		return "principals";
	}
	
	@RequestMapping(value = "/principal/{id}", method = RequestMethod.GET)
	public String principal(@PathVariable("id") final Long id, final Model model, java.security.Principal principal) {
		final com.archsystemsinc.ipms.sec.model.Principal currentUser = principalService.findByName(principal.getName());
		model.addAttribute("currentUser", currentUser);
		final Principal iprincipal = service.findOne(id);
		model.addAttribute("principal", iprincipal);
		return "principal";
	}
	
	@RequestMapping(value = "/profile/{name}", method = RequestMethod.GET)
	public String principalWithName(@PathVariable("name") final String name, final Model model, java.security.Principal principal) {
		final com.archsystemsinc.ipms.sec.model.Principal currentUser = principalService.findByName(principal.getName());
		model.addAttribute("currentUser", currentUser);
		final Principal iprincipal = service.findByName(name);
		model.addAttribute("principal", iprincipal);
		return "principal";
	}

	@RequestMapping(value = "/new-principal", method = RequestMethod.GET)
	public String newPrincipal(final Model model, java.security.Principal principal) {
		final com.archsystemsinc.ipms.sec.model.Principal currentUser = principalService.findByName(principal.getName());
		model.addAttribute("currentUser", currentUser);
		final Principal iprincipal = new Principal();
		model.addAttribute("principal", iprincipal);
		model.addAttribute("referenceData", referenceData());
		return "principalsadd";
	}

	@RequestMapping(value = "/edit-principal/{id}", method = RequestMethod.GET)
	public String editPrincipal(@PathVariable("id") final Long id,
			final Model model, java.security.Principal principal) {
		final com.archsystemsinc.ipms.sec.model.Principal currentUser = principalService.findByName(principal.getName());
		model.addAttribute("currentUser", currentUser);
		final Principal iprincipal = service.findOne(id);
		final Set<Role> roles = iprincipal.getRoles();
		List<Privilege> privileges = new ArrayList<Privilege>();
		for(Role role: roles) {
			privileges.addAll(role.getPrivileges());
		}
		List<Long> rolesList = new ArrayList<Long>();
		for(Role role: roles) {
			rolesList.add(role.getId());			
		}
		iprincipal.setRolesList(rolesList);
		model.addAttribute("principal", iprincipal);
		model.addAttribute("referenceData", referenceData());
		model.addAttribute("privileges", privileges);
		return "principalsedit";
	}

	@RequestMapping(value = "/new-principal", method = RequestMethod.POST)
	public String addPrincipal(
			@Valid @ModelAttribute("principal") final Principal principal,
			final BindingResult result, final Model model, java.security.Principal iprincipal) {
		final com.archsystemsinc.ipms.sec.model.Principal currentUser = principalService.findByName(iprincipal.getName());
		model.addAttribute("currentUser", currentUser);

		String returnView = "";

		if (result.hasErrors()) {
			returnView = "principalsadd";
			model.addAttribute("principal", principal);
			model.addAttribute("referenceData", referenceData());
		} else {
			final Set<Role> rolesToSet = new HashSet<Role>();			

			for (Long roleId: principal.getRolesList()) {
				rolesToSet.add(roleService.findOne(roleId));
			}
			principal.setRoles(rolesToSet);
			service.create(principal);
			model.addAttribute("success", "success.principal.created");
			returnView = "forward:principals";
		}		
		return returnView;
	}

	@RequestMapping(value = "/edit-principal", method = RequestMethod.POST)
	public String updatePrincipal(
			@Valid @ModelAttribute("principal") final Principal principal,
			final BindingResult result, final Model model) {
		String returnView = "";
		// using name as long --bad idea
		final String currentUser = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		model.addAttribute("currentUser", currentUser);
		if (result.hasErrors()) {
			returnView = "principalsedit";
			model.addAttribute("principal", principal);
			model.addAttribute("referenceData", referenceData());
		} else {
			final Set<Role> rolesToSet = new HashSet<Role>();			

			for (Long roleId: principal.getRolesList()) {
				rolesToSet.add(roleService.findOne(roleId));
			}
			principal.setRoles(rolesToSet);
			service.update(principal);
			
			returnView = "forward:principals";
			model.addAttribute("success","success.principal.updated");			
		}		
		return returnView;
	}
	
	@RequestMapping(value = "/getPrivOfRole/{id}", method = RequestMethod.POST)
	public Map<Integer, String> gerPrivilegesOfRole(@PathVariable("id") final Long id, final Model model) {
		final String currentUser = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		model.addAttribute("currentUser", currentUser);
		final Role role = roleService.findOne(id);
		final List<Privilege> list = (List<Privilege>) role.getPrivileges();
		final Map<Integer, String> pList = new LinkedHashMap<Integer, String>();
		for (int i = 0; i < list.size(); i++) {
			pList.put(list.get(i).getId().intValue(), list.get(i).getName());
		}
		return pList;
	}
	
	@RequestMapping(value = "/delete-principal/{id}/{returnPage}", method = RequestMethod.GET)
	public String deleteRole(@PathVariable("id") final Long id, @PathVariable("returnPage") final String returnPage,
			final Model model) {
		final String currentUser = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		model.addAttribute("currentUser", currentUser);
		String returnView = "";
		service.delete(id);
		model.addAttribute("success","success.principal.deleted");
		if(returnPage.equalsIgnoreCase("")) {
			returnView = "forward:principals"; 
		} else {
			returnView = "forward:" + returnPage;	
		}			
		return returnView;
	}

	protected Map referenceData() {
		final Map referenceData = new HashMap();
		final List<Role> rList = roleService.findAll();
		referenceData.put("roleCompleteList", rList);
   		return referenceData;
	}

	@Override
	protected IService<Principal> getService() {
		return service;
	}
}
