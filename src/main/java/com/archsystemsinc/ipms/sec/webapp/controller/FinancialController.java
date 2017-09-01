package com.archsystemsinc.ipms.sec.webapp.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.archsystemsinc.ipms.poi.service.UploadService;
import com.archsystemsinc.ipms.sec.model.ActionItemPriority;
import com.archsystemsinc.ipms.sec.model.IssueStatus;
import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.model.Program;
import com.archsystemsinc.ipms.sec.model.Project;
import com.archsystemsinc.ipms.sec.persistence.service.IPrincipalService;
import com.archsystemsinc.ipms.sec.persistence.service.IProjectService;
import com.archsystemsinc.ipms.sec.util.GenericConstants;

@Controller
public class FinancialController {
	
	@Autowired
	private IProjectService projectService;
	
	@Autowired
	private IPrincipalService principalService;
	
	@Autowired
	private UploadService uploadService;
	
	protected Map referenceData() {
		final Map referenceData = new HashMap();
		final List<Project> projectlist = projectService.findActiveProjects();
		final Map<Integer, String> pList = new LinkedHashMap<Integer, String>();
		for (int i = 0; i < projectlist.size(); i++) {
			pList.put(projectlist.get(i).getId().intValue(), projectlist.get(i)
					.getName());
		}
		referenceData.put("projectList", pList);
		
		final String currentUser = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		Principal principal = principalService.findByName(currentUser);
		final List<Project> currentUserProjectlist = projectService.findActiveUserProjects(principal);
		final Map<Integer, String> cpList = new LinkedHashMap<Integer, String>();
		cpList.put(0, "--Select Project--");
		for (int i = 0; i < currentUserProjectlist.size(); i++) {
			cpList.put(currentUserProjectlist.get(i).getId().intValue(), currentUserProjectlist.get(i)
					.getName());
		}
		referenceData.put("currentUserProjectlist", cpList);
		
		return referenceData;
	}

	
	@RequestMapping(value = "/uploadfinancial" , method = RequestMethod.GET)
	public String uploadFinancial(final Model model) {
		//final Issue issue = new Issue();
		//final Metrics metrics = new Metrics();
		model.addAttribute(new FileUpload());
		//model.addAttribute("metrics", metrics);
		model.addAttribute(("referenceData"), referenceData());
		return "uploadfinancial";
	}
	

}
