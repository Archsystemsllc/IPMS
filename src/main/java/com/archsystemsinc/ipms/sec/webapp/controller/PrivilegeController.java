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


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.Privilege;
import com.archsystemsinc.ipms.sec.persistence.service.IPrivilegeService;
import com.archsystemsinc.ipms.web.common.AbstractController;


@Controller
public class PrivilegeController extends AbstractController<Privilege> {

	public PrivilegeController() {
		super(Privilege.class);
	}

	private final Log log = LogFactory.getLog(PrivilegeController.class);

	@Autowired
	private IPrivilegeService service;

	@RequestMapping(value = "/privileges")
	public String privilege(final Model model) {
		final List<Privilege> privileges = service.findAll();
		model.addAttribute("privileges", privileges);
		return "privileges";
	}

	@RequestMapping(value = "/privilege/{id}", method = RequestMethod.GET)
	public String privilege(@PathVariable("id") final Long id, final Model model) {
		final Privilege privilege = service.findOne(id);
		model.addAttribute("privilege", privilege);
		return "privilege";
	}

	@RequestMapping(value = "/new-privilege", method = RequestMethod.GET)
	public String newPrivilege(final Model model) {
		final Privilege privilege = new Privilege();
		model.addAttribute("privilege", privilege);
		model.addAttribute("referenceData", referenceData());
		return "privilegesadd";
	}

	@RequestMapping(value = "/edit-privilege/{id}", method = RequestMethod.GET)
	public String editPrivilege(@PathVariable("id") final Long id,
			final Model model) {
		final Privilege privilege = service.findOne(id);
		model.addAttribute("privilege", privilege);
		model.addAttribute("referenceData", referenceData());
		return "privilegesedit";
	}

	@RequestMapping(value = "/new-privilege", method = RequestMethod.POST)
	public String addPrivilege(
			@Valid @ModelAttribute("privilege") final Privilege privilege,
			final BindingResult result, final Model model) {

		String returnView = "";

		if (result.hasErrors()) {
			returnView = "privilegesadd";
			model.addAttribute("privilege", privilege);
			model.addAttribute("referenceData", referenceData());
		} else {
			service.create(privilege);
			model.addAttribute("success","success.privilege.created");
			returnView = "forward:privileges";
		}		
		return returnView;

	}

	@RequestMapping(value = "/edit-privilege", method = RequestMethod.POST)
	public String updatePrivilege(
			@Valid @ModelAttribute("privilege") final Privilege privilege,
			final BindingResult result, final Model model) {
		String returnView = "";
		// using name as long --bad idea

		if (result.hasErrors()) {
			returnView = "privilegesedit";
			model.addAttribute("privilege", privilege);
			model.addAttribute("referenceData", referenceData());
		} else {
			service.update(privilege);
			model.addAttribute("success","success.privilege.updated");
			returnView = "forward:privileges";
		}		
		model.addAttribute("privilege", privilege);
		model.addAttribute("referenceData", referenceData());
		return returnView;
	}
	
	@RequestMapping(value = "/delete-privilege/{id}/{returnPage}", method = RequestMethod.GET)
	public String deleteRole(@PathVariable("id") final Long id, @PathVariable("returnPage") final String returnPage,
			final Model model) {
		String returnView = "";
		service.delete(id);
		model.addAttribute("success","success.privilege.deleted");
		if(returnPage.equalsIgnoreCase("")) {
			returnView = "forward:privileges"; 
		} else {
			returnView = "forward:" + returnPage;	
		}			
		return returnView;
	}

	protected Map referenceData() {
		final Map referenceData = new HashMap();

		final Map<Integer, String> likelihoodList = new LinkedHashMap<Integer, String>();

		return referenceData;
	}

	@Override
	protected IService<Privilege> getService() {
		return service;
	}

}
